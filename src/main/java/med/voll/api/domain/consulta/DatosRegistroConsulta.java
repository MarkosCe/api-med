package med.voll.api.domain.consulta;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.medico.Especialidad;

import java.time.LocalDateTime;

public record DatosRegistroConsulta(
        //Long id,
        @NotNull
        @JsonAlias({"paciente_id","id_paciente"}) //La anotación @JsonAlias sirve para mapear "alias" alternativos para los campos que se recibirán del JSON, y es posible asignar múltiples alias
        Long idPaciente,
        @JsonAlias({"medico_id","id_medico"})
        Long idMedico,
        Especialidad especialidad,
        @NotNull
        @Future
        @JsonAlias("date")
        @JsonFormat(pattern = "dd/MM/yyyy HH:mm")
        LocalDateTime fecha) {
}
