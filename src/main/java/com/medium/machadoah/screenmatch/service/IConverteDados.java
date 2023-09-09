package com.medium.machadoah.screenmatch.service;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface IConverteDados {

    // Generics <>!
    <T> T obterDados(String json, Class<T> classe) throws JsonProcessingException;
}
