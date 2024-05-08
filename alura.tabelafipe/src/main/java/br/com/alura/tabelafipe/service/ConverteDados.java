package br.com.alura.tabelafipe.service;

import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

//PASSO3 3- Criação da Classe Converte dados implementando a interface IConverte

public class ConverteDados implements IConverteDados {

	private ObjectMapper mapper = new ObjectMapper();

	@Override
	public <T> T obeterDados(String json, Class<T> classe) {

		try {
			return mapper.readValue(json, classe);
		} catch (JsonProcessingException e) {
			throw new RuntimeException();
		}

	}

	@Override
	public <T> List<T> obterLista(String json, Class<T> classe) {

		CollectionType lista = mapper.getTypeFactory().constructCollectionType(List.class, classe);

		try {
			return mapper.readValue(json, lista);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

}
