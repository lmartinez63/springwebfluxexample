package com.example.demowebflux2.controller;

import com.example.demowebflux2.model.Persona;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/personas")
public class PersonaController {
    @GetMapping("/mostrar")
    public Mono<Persona> mostrarPersona(){
        return Mono.just(new Persona(1,"Bootcamp"));
    }
    @GetMapping("/listar")
    public Flux<Persona> listarPersonas(){
        List<Persona> listaPersonas = new ArrayList<Persona>();
        listaPersonas.add(new Persona(1,"Bootcamp1"));
        listaPersonas.add(new Persona(2,"Bootcamp2"));
        listaPersonas.add(new Persona(3,"Bootcamp3"));
        return Flux.fromIterable(listaPersonas);
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> eliminarPersona(@PathVariable Integer id){
        return buscar(id).
                flatMap( p -> {
                    return eliminar(p).then(Mono.just(new ResponseEntity<Void>(HttpStatus.NO_CONTENT)));
                }).
                defaultIfEmpty(new ResponseEntity<Void>(HttpStatus.NOT_FOUND));
    }

    public Mono<Persona> buscar(Integer id){
        if(id == 1){
           return Mono.just(new Persona(1,"Bootcamp"));
        } else {
            return Mono.empty();
        }
    }

    public Mono<Void> eliminar(Persona persona){
        return Mono.empty();
    }
}