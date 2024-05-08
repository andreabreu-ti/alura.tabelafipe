package br.com.alura.tabelafipe.principal;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import br.com.alura.tabelafipe.model.Dados;
import br.com.alura.tabelafipe.model.Modelos;
import br.com.alura.tabelafipe.model.Veiculo;
import br.com.alura.tabelafipe.service.ConsumoApi;
import br.com.alura.tabelafipe.service.ConverteDados;

//PASSO 5 - Criação da Classe principal

public class Principal {

	private Scanner leitura = new Scanner(System.in);

	private ConsumoApi consumo = new ConsumoApi();

	private ConverteDados conversor = new ConverteDados();

	private final String URL_BASE = "https://parallelum.com.br/fipe/api/v1/";

	public void exibeMenu() {

		var menu = """
				*** OPÇÕES ***
				Carro
				Moto
				Caminhão

				Digite uma das opções para consultar:
				""";

		System.out.println(menu);

		var opcao = leitura.nextLine();

		String endereco;
		if (opcao.toLowerCase().contains("carr")) {

			endereco = URL_BASE + "carros/marcas";

		} else if (opcao.toLowerCase().contains("mot")) {

			endereco = URL_BASE + "motos/marcas";

		} else {

			endereco = URL_BASE + "caminhoes/marcas";
		}

		// Exibição da Marca
		var json = consumo.obterDados(endereco);
		System.out.println(json);
		var marcas = conversor.obterLista(json, Dados.class);
		marcas.stream().sorted(Comparator.comparing(Dados::codigo)).forEach(System.out::println);

		// Modelos de uma determinada marca
		System.out.println("Informe o código da marca para consulta: ");
		var codigoMarca = leitura.nextLine();
		endereco = endereco + "/" + codigoMarca + "/modelos";
		json = consumo.obterDados(endereco);
		var modeloLista = conversor.obeterDados(json, Modelos.class);
		System.out.println("\nModelos dessa marca:");
		modeloLista.modelos().stream().sorted(Comparator.comparing(Dados::codigo)).forEach(System.out::println);

		// Trazer todos os modelos daquela marca
		System.out.println("\nDigite um trecho do nome do carro a ser buscado:");
		var nomeVeiculo = leitura.nextLine();
		List<Dados> modelosFiltrados = modeloLista.modelos().stream()
				.filter(m -> m.nome().toLowerCase().contains(nomeVeiculo.toLowerCase())).collect(Collectors.toList());

		System.out.println("\nModelos Filtrados: ");
		modelosFiltrados.forEach(System.out::println);

		System.out.println("\nDigite o código do modelo: ");
		var codigoModelo = leitura.nextLine();

		endereco = endereco + "/" + codigoModelo + "/anos";
		json = consumo.obterDados(endereco);
		List<Dados> anos = conversor.obterLista(json, Dados.class);
		
		List<Veiculo> veiculos = new ArrayList<>();
		
		for (int i = 0; i < anos.size(); i++) {
			var enderecoAnos = endereco + "/" + anos.get(i).codigo();
			
			json = consumo.obterDados(enderecoAnos);
			Veiculo veiculo = conversor.obeterDados(json, Veiculo.class);
			veiculos.add(veiculo);
			
		}
		
		System.out.println("\nTodos os veículos filtrados com avaliações por ano:");
		
		veiculos.forEach(System.out::println);
		

	}

}
