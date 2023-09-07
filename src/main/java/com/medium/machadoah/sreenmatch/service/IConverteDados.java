package com.medium.machadoah.sreenmatch.service;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface IConverteDados {

    // Generics <>!
    <T> T obterDados(String json, Class<T> classe) throws JsonProcessingException;
}
