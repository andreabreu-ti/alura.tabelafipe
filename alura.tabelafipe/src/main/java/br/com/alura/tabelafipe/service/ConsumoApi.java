package br.com.alura.tabelafipe.service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

//PASSO 4 - Criação da Classe Consumo API -> Ela faz a requisição e obtém a resposta de qualquer endereço que inserirmos

public class ConsumoApi {

	public String obterDados(String endereco) {
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(endereco)).build();

		HttpResponse<String> response = null;

		try {

			response = client.send(request, HttpResponse.BodyHandlers.ofString());

		} catch (IOException e) {

			throw new RuntimeException();

		} catch (InterruptedException e) {

			throw new RuntimeException();

		}
		String json = response.body();
		return json;
	}

}
