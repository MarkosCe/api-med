package med.voll.api.domain.consulta.validacionescancel;

import jakarta.validation.ValidationException;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DatosCancelacionConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class HorarioAntecedencia implements ValidadorCancelacionDeConsulta{

    @Autowired
    private ConsultaRepository consultaRepository;
    @Override
    public void validar(DatosCancelacionConsulta datos) {
        var consulta = consultaRepository.getReferenceById(datos.idConsulta());
        var horas = Duration.between(LocalDateTime.now(), consulta.getFecha()).toHours();
        if (horas < 24){
            throw new ValidationException("La cita solo se puede cancelar con al menos 24 horas de anticipacion");
        }
    }
}
