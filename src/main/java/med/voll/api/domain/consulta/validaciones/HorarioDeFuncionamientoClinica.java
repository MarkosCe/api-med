package med.voll.api.domain.consulta.validaciones;

import jakarta.validation.ValidationException;
import med.voll.api.domain.consulta.DatosRegistroConsulta;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;

@Component
public class HorarioDeFuncionamientoClinica implements ValidadorDeConsultas {

    @Override
    public void validar(DatosRegistroConsulta datos){
        var domingo = DayOfWeek.SUNDAY.equals(datos.fecha().getDayOfWeek());
        var antesDeApertura = datos.fecha().getHour() < 7;
        var despuesDeCierre= datos.fecha().getHour() > 19;

        if (domingo || antesDeApertura || despuesDeCierre){
            throw new ValidationException("EL horario de atencion a la clinica es de lunes a sabado de 7 a 19 hrs");
        }


    }
}
