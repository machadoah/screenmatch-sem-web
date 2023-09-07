package com.medium.machadoah.sreenmatch;

import com.medium.machadoah.sreenmatch.model.DadosSerie;
import com.medium.machadoah.sreenmatch.service.ConsumoAPI;
import com.medium.machadoah.sreenmatch.service.ConverteDados;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SreenmatchApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SreenmatchApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        // run é o método main do spring!

        // System.out.println("Primeiro projeto Spring sem Web 🍃");

        var consumoAPI = new ConsumoAPI();
        var json = consumoAPI.obterDados("https://www.omdbapi.com/?t=gilmore+girls&apikey=e43780bb");

        // System.out.println(json);

        var converteDados = new ConverteDados();
        DadosSerie dados = converteDados.obterDados(json, DadosSerie.class);

        System.out.println(dados);
    }
}
