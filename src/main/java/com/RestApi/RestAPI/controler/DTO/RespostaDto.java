package com.RestApi.RestAPI.controler.DTO;

import com.RestApi.RestAPI.modelo.Resposta;

import java.time.LocalDateTime;

public class RespostaDto {
    private Long id;
    private String autor;
    private String mensgem;
    private LocalDateTime dataCriacao;

    public RespostaDto(Resposta resposta) {
        this.id = resposta.getId();
        this.autor = resposta.getAutor().getNome();
        this.mensgem = resposta.getMensagem();
        this.dataCriacao = resposta.getDataCriacao();
    }

    public Long getId() {
        return id;
    }

    public String getAutor() {
        return autor;
    }

    public String getMensgem() {
        return mensgem;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }
}
