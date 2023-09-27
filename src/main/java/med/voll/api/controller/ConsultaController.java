package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.domain.consulta.AgendaDeConsultaService;
import med.voll.api.domain.consulta.DatosDetalleConsulta;
import med.voll.api.domain.consulta.DatosRegistroConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    @Autowired
    private AgendaDeConsultaService service;

    @PostMapping
    public ResponseEntity<DatosDetalleConsulta> agendar(@RequestBody @Valid DatosRegistroConsulta datosRegistroConsulta){

        service.agendar(datosRegistroConsulta);

        return ResponseEntity.ok(new DatosDetalleConsulta(datosRegistroConsulta));
    }
}
