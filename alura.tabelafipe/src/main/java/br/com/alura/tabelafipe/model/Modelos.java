package br.com.alura.tabelafipe.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//PASSO 8

@JsonIgnoreProperties(ignoreUnknown = true)
public record Modelos(List<Dados> modelos) {

}
