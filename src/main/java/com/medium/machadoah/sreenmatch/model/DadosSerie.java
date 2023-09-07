package com.medium.machadoah.sreenmatch.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true) // Pô ignora o que eu não pedir meu chapa!
public record DadosSerie(@JsonAlias({"title","Title"}) String titulo,
                         @JsonAlias("totalSeasons") Integer totalTemporadas,
                         @JsonAlias("imdbRating") String avaliacao) {

    /*
     * JsonAlias -> somente para a leitura de um json
     * JsonProperty -> Leitura e escrita de um Json
     */

}
