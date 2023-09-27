package med.voll.api.domain.consulta;

import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.paciente.Paciente;
import med.voll.api.domain.paciente.PacienteRepository;
import med.voll.api.infra.errores.ValidacionDeIntegridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AgendaDeConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;
    @Autowired
    private MedicoRepository medicoRepository;
    @Autowired
    private PacienteRepository pacienteRepository;

    public void agendar(DatosRegistroConsulta datos){
        //validaciones
        if (pacienteRepository.findById(datos.idPaciente()).isPresent()){
            throw new ValidacionDeIntegridad("Id de paciente no fue encontrado");
        }

        if (datos.idMedico() != null && medicoRepository.existsById(datos.idMedico())){
            throw new ValidacionDeIntegridad("Id de medico no fue encontrado");
        }
        Paciente paciente = pacienteRepository.getReferenceById(datos.idPaciente());
        Medico medico = seleccionarMedico(datos);

        Consulta consulta = new Consulta(null, medico, paciente, datos.fecha());
        consultaRepository.save(consulta);
    }

    private Medico seleccionarMedico(DatosRegistroConsulta datos) {
        if (datos.idMedico() != null){
            return medicoRepository.getReferenceById(datos.idMedico());
        }
        if (datos.especialidad() == null){
            throw new ValidacionDeIntegridad("Debe seleccionar una especialidad");
        }
        return medicoRepository.seleccionarMedicoConEspecialidadEnFecha(datos.especialidad(), datos.fecha());
    }
}
