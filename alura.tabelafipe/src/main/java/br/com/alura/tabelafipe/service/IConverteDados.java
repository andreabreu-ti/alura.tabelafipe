package br.com.alura.tabelafipe.service;

import java.util.List;

//PASSO 2 - Criação da interface

public interface IConverteDados {

	<T> T obeterDados(String json, Class<T> classe);

	<T> List<T> obterLista(String json, Class<T> classe);
}
