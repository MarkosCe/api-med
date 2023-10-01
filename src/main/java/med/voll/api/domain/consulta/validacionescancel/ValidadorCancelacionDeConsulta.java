package med.voll.api.domain.consulta.validacionescancel;

import med.voll.api.domain.consulta.DatosCancelacionConsulta;

public interface ValidadorCancelacionDeConsulta {

    public void validar(DatosCancelacionConsulta datos);
}
