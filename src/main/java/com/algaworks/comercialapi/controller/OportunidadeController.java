package com.algaworks.comercialapi.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.algaworks.comercialapi.model.Oportunidade;
import com.algaworks.comercialapi.service.OportunidadeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * OportunidadeController
 */
@RestController
@RequestMapping(value = "/oportunidades")
@CrossOrigin("http://localhost:4200")
public class OportunidadeController {

    private OportunidadeService service;

    @Autowired
    public OportunidadeController(OportunidadeService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Oportunidade>> obterTodas() {

        List<Oportunidade> oportunidades = service.findAll();
        return ResponseEntity.ok().body(oportunidades);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Oportunidade> obterPorId(@PathVariable Long id) {
        Optional<Oportunidade> oportunidade = service.findById(id);
        return ResponseEntity.ok().body(oportunidade.get());
    }

    @PostMapping
    public ResponseEntity<Oportunidade> insert(@Valid @RequestBody Oportunidade entity) {

        Oportunidade oportunidade = service.save(entity);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(oportunidade.getId())
                .toUri();
        return ResponseEntity.created(uri).body(oportunidade);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Oportunidade> update(@PathVariable Long id, @RequestBody Oportunidade entity) {

        Oportunidade oportunidade = service.uptade(id, entity);
        return ResponseEntity.ok().body(oportunidade);

    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Oportunidade> remove(@PathVariable Long id) {

        service.deleteById(id);
        return ResponseEntity.noContent().build();

    }

}
