package med.voll.api.domain.medico;

public record DatosListadoMedico(
        Long id,
        String nombre,
        String email,
        String documento,
        String especialidad
) {
    public DatosListadoMedico(Medico medico){
        this(medico.getId(), medico.getNombre(), medico.getEmail(), medico.getDocumento(), medico.getEspecialidad().toString());
    }

}
