package com.person.person_service.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.person.person_service.metrics.Metrics;
import com.person.person_service.model.Evento;
import com.person.person_service.repository.EventoRepository;

@RestController
@RequestMapping("/eventos")
public class EventoController {

    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    private Metrics metrics;

    @PostMapping
    public ResponseEntity<Evento> createEvento(@RequestBody Evento evento) {
        if (evento.getTitulo() == null || evento.getDescricao() == null) {
            metrics.addTag("Falha ao registrar evento (dados incompletos)");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Evento savedEvento = eventoRepository.saveAndFlush(evento);

        // Adiciona a métrica com a tag personalizada "Novo evento registrado"
        metrics.addTag("Novo evento registrado");
        metrics.captureValorMetrica(savedEvento.getValor());

        return new ResponseEntity<>(savedEvento, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Evento> getEventoById(@PathVariable String id) {
        UUID uid = UUID.fromString(id);        
        Optional<Evento> evento = eventoRepository.findById(uid);
        
        if (evento.isPresent()) {
            // Adiciona a métrica com a tag personalizada "Evento encontrado por ID"
            metrics.addTag("Evento encontrado por ID");
            return new ResponseEntity<>(evento.get(), HttpStatus.OK);
        } else {
            metrics.addTag("Evento não encontrado por ID");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<Evento>> getAllEventos() {
        List<Evento> eventos = eventoRepository.findAll();

        // Adiciona a métrica com a tag personalizada "Captura todos eventos"
        metrics.addTag("Captura todos eventos");
        

        return new ResponseEntity<>(eventos, HttpStatus.OK);
    }

}
