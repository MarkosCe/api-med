package med.voll.api.domain.medico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.domain.direccion.DatosDireccion;

public record DatosRegistroMedico( //DTO
        //@NotNull
        @NotBlank //valida que nombre no llegue ni nulo ni blanco
        String nombre,
        @NotBlank
        @Email
        String email,
        @NotBlank
        String telefono,
        @NotBlank
        @Pattern(regexp = "\\d{4,6}")
        String documento,
        @NotNull
        Especialidad especialidad,
        @NotNull //porque es objeto no va a llegar en blank va a llegar en null
        @Valid
                                   DatosDireccion direccion) {
}
