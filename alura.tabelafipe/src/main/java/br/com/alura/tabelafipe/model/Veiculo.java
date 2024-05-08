package br.com.alura.tabelafipe.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

//PASSO 9

@JsonIgnoreProperties(ignoreUnknown = true)
public record Veiculo(

		@JsonAlias("Valor") String valor, 
		@JsonAlias("Marca") String marca, 
		@JsonAlias("Modelo") String modelo,
		@JsonAlias("AnoModelo") Integer ano, 
		@JsonAlias("Combustivel") String tipoCombustivel

) {

}
