package com.person.person_service.model;

import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@RequiredArgsConstructor
@EqualsAndHashCode(of={"id"})
public class Evento {

public Evento(){}

@Id
@GeneratedValue(strategy = GenerationType.UUID)    
private UUID id;
@NonNull
private String titulo;
@NonNull
private String descricao;
@NonNull
private Integer valor;

}
