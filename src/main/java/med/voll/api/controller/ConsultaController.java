package med.voll.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.domain.consulta.AgendaDeConsultaService;
import med.voll.api.domain.consulta.DatosCancelacionConsulta;
import med.voll.api.domain.consulta.DatosDetalleConsulta;
import med.voll.api.domain.consulta.DatosRegistroConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consultas")
@SecurityRequirement(name = "bearer-key")
public class ConsultaController {

    @Autowired
    private AgendaDeConsultaService service;

    @PostMapping
    @Operation(
            summary = "Registra una consulta en la base de datos",
            description = "",
            tags = {"consulta","post"})
    public ResponseEntity<DatosDetalleConsulta> agendar(@RequestBody @Valid DatosRegistroConsulta datosRegistroConsulta){

        var response = service.agendar(datosRegistroConsulta);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping
    @Transactional
    @Operation(
            summary = "Cancela una consulta de la agenda",
            description = "",
            tags = {"consulta","delete"})
    public ResponseEntity<Void> cancelar(@RequestBody @Valid DatosCancelacionConsulta datosCancelacionConsulta){

        service.cancelar(datosCancelacionConsulta);

        return ResponseEntity.ok().build();
    }
}
