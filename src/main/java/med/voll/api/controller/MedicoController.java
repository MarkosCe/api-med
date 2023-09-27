package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.medico.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired //fines didacticos no recomendado en la practica
    private MedicoRepository medicoRepository;

    @PostMapping
    public ResponseEntity<DatosRespuestaMedico> registrarMedico(@RequestBody @Valid DatosRegistroMedico datosRegistroMedico,
                                          UriComponentsBuilder uriComponentsBuilder){
        //System.out.println(datosRegistroMedico);
        Medico medico = medicoRepository.save(new Medico(datosRegistroMedico));
        DatosRespuestaMedico datosRespuestaMedico = new DatosRespuestaMedico(medico.getId(),
                medico.getNombre(), medico.getEmail(), medico.getDocumento());
        //Return 201 created
        // URL donde encontrar al medico
        // GET http://localhost:8080/medico/id
        URI url = uriComponentsBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
        return ResponseEntity.created(url).body(datosRespuestaMedico);

    }

    /*@GetMapping //todos
    public List<DatosListadoMedico> listadoMedicos(){
        return medicoRepository.findAll().stream().map(DatosListadoMedico::new).toList();
    }*/

    //paginacion
    @GetMapping
    public ResponseEntity<Page<DatosListadoMedico>> listadoMedicos(@PageableDefault(size = 5, sort = "nombre", direction = Sort.Direction.DESC)
            Pageable pageable){ //pagedefault para poder valores ya por default

        //return medicoRepository.findAll(pageable).map(DatosListadoMedico::new); //listar todos los medicos
        return ResponseEntity.ok(medicoRepository.findByActivoTrue(pageable).map(DatosListadoMedico::new)); //obtener solo los activos(utilizando un metodo de consulta derivado)
    }
    //return medicoRepository.findByNombre("katttt", pageable).map(DatosListadoMedico::new);

    @PutMapping
    @Transactional //para que jpa complete la transaccion(hacer un commit), si ocurre un error hace ROLLBACK
    public ResponseEntity<DatosRespuestaMedico> actualizarMedico(@RequestBody @Valid DatosActualizarMedico datosActualizarMedico){
        Medico medico = medicoRepository.getReferenceById(datosActualizarMedico.id());
        medico.actualizarDatos(datosActualizarMedico);
        return ResponseEntity.ok(new DatosRespuestaMedico(medico.getId(),
                medico.getNombre(), medico.getEmail(), medico.getDocumento()));
    }

    //DELETE EN BASE DE DATOS
    /*@DeleteMapping("/{id}")     //delete usando path variables
    @Transactional
    public void eliminarMedico(@PathVariable Long id){
        Medico medico = medicoRepository.getReferenceById(id);
        medicoRepository.delete(medico); //medicoRepository.deleteById(id)
    }*/

    //Delete logico(sin borrar de la base de datos-exclusion logica)
    @DeleteMapping("/{id}")     //delete usando path variables
    @Transactional
    public ResponseEntity<Void> eliminarMedico(@PathVariable Long id){
        Medico medico = medicoRepository.getReferenceById(id);
        medico.desactivarMedico();
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DatosRespuestaMedico> retornaDatosMedico(@PathVariable Long id){
        Medico medico = medicoRepository.getReferenceById(id);
        var datos = new DatosRespuestaMedico(medico.getId(),
                medico.getNombre(), medico.getEmail(), medico.getDocumento());
        return ResponseEntity.ok(datos);
    }

}
