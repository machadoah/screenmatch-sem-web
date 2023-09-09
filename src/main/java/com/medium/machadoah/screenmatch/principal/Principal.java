package com.medium.machadoah.screenmatch.principal;

import com.medium.machadoah.screenmatch.model.DadoTemporada;
import com.medium.machadoah.screenmatch.model.DadosEpisodio;
import com.medium.machadoah.screenmatch.model.DadosSerie;
import com.medium.machadoah.screenmatch.model.Episodio;
import com.medium.machadoah.screenmatch.service.ConsumoAPI;
import com.medium.machadoah.screenmatch.service.ConverteDados;

import java.util.*;
import java.util.stream.Collectors;

public class Principal {

    private Scanner scanner = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private ConverteDados converteDados = new ConverteDados();
    private final String ENDERECO = "https://www.omdbapi.com/?t=";
    private final String API_KEY = "&apikey=e43780bb";

    public void exibeMenu() {
        System.out.print("Digite o nome da serie para busca: ");
        var nomeSerie = scanner.nextLine();

        var enderecoBusca = ENDERECO + nomeSerie.replace(" ", "+") + API_KEY;

        var json = consumoAPI.obterDados(enderecoBusca);

        DadosSerie dados = converteDados.obterDados(json, DadosSerie.class);

        System.out.println(dados);

        List<DadoTemporada> temporadas = new ArrayList<>();

        for (int i = 1; i <= dados.totalTemporadas(); i++) {
            json = consumoAPI.obterDados((ENDERECO + nomeSerie.replace(" ", "+")+ "&season=" +i+ "&" + API_KEY).formatted(i));
            DadoTemporada dadoTemporada = converteDados.obterDados(json, DadoTemporada.class);

            temporadas.add(dadoTemporada);
        }

        //temporadas.forEach(System.out::println);

//        for (int i = 0; i < dados.totalTemporadas(); i++) {
//            List<DadosEpisodio> episodiosTemporada = temporadas.get(i).episodios();
//
//            for (int j = 0; j < episodiosTemporada.size(); j++) {
//                //System.out.println(episodiosTemporada.get(j).titulo());
//            }
//        }

        // ------------ FUNÇÕES LAMBDAS -----------------

        // Para cada episodio de uma temporada t, imprimir seus titulos
        temporadas.forEach(t -> t.episodios().forEach(e -> System.out.println(e.titulo())));

        List<String> nomes = Arrays.asList("Jacque", "Iasmin", "Paulo", "Rodrigo", "Nico");

        // ------------ STREAM API ------------------------

        // nomes.stream().sorted().limit(3).filter(n -> n.startsWith("N")).map(n -> n.toUpperCase()).forEach(System.out::println);

        List<DadosEpisodio> dadosEpisodios = temporadas.stream().flatMap(t -> t.episodios().stream()).collect(Collectors.toList());

        System.out.println("\nTop 5 Episodios!");
        dadosEpisodios.stream()
                .filter(e -> !e.avaliacao().equalsIgnoreCase("N/A"))
                .sorted(Comparator.comparing(DadosEpisodio::avaliacao).reversed())
                .limit(5).forEach(System.out::println);

//        List<Episodio> episodios = temporadas.stream()
//                .flatMap(t -> t.episodios().stream()
//                        .map(d -> new Episodio(t.numero(), d))
//                        .collect(Collectors.toList());
//
    }
}
