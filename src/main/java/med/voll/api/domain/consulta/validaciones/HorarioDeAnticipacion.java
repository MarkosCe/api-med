package med.voll.api.domain.consulta.validaciones;

import jakarta.validation.ValidationException;
import med.voll.api.domain.consulta.DatosRegistroConsulta;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class HorarioDeAnticipacion implements ValidadorDeConsultas{

    @Override
    public void validar(DatosRegistroConsulta datos){

         var now = LocalDateTime.now();
         var horaDeConsulta = datos.fecha();

         var diferenciaDe30Min = Duration.between(now, horaDeConsulta).toMinutes() < 30;

         if (diferenciaDe30Min){
             throw new ValidationException("Las consultas deben tener al menos 30 min de anticipacion");
         }

    }

}
