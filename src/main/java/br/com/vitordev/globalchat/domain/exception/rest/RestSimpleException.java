package br.com.vitordev.globalchat.domain.exception.rest;

public record RestSimpleException(
    Integer status, 
    String error
) { }
