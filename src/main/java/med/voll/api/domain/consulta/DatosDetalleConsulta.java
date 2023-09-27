package med.voll.api.domain.consulta;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record DatosDetalleConsulta(
        Long id,
        Long idPaciente,
        Long idMedico,

        @JsonFormat(pattern = "yyyy/MM/dd HH:mm") //lo devuelve en otro formato
        LocalDateTime fecha) {
    public DatosDetalleConsulta(DatosRegistroConsulta datosRegistroConsulta) {
        this(null, datosRegistroConsulta.idPaciente(), datosRegistroConsulta.idMedico(), datosRegistroConsulta.fecha());
    }
}
