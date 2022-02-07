package com.RestApi.RestAPI.controler.DTO;

import com.RestApi.RestAPI.modelo.Topico;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/*
    É uma sunclasse de Topicos que recebe apenas alguns de seus valores,
    assim evitando a necessidade de passar valores desncessarios de Topico
    o que poderia causar vazamento de dados sensiveis como senhas
 */
public class TopicoDto {

    private long id;
    private String titulo;
    private String mensgem;
    private LocalDateTime dataCriacao;

    public TopicoDto(Topico topico) {
        this.id = topico.getId();
        this.titulo= topico.getTitulo();
        this.mensgem= topico.getMensagem();
        this.dataCriacao= topico.getDataCriacao();
    }

    /*
    @retunr: recebe uma lista de topicos e os converte para topicosDto
    coletando os novos topicosDtos tranforman-os em uma lista
     */
    public static List<TopicoDto> converter(List<Topico> topicos) {
        return  topicos.stream().map(TopicoDto::new).collect(Collectors.toList());
    }

    public long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getMensgem() {
        return mensgem;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }
}
