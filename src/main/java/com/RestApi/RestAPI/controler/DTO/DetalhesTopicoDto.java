package com.RestApi.RestAPI.controler.DTO;

import com.RestApi.RestAPI.modelo.Resposta;
import com.RestApi.RestAPI.modelo.StatusTopico;
import com.RestApi.RestAPI.modelo.Topico;
import com.RestApi.RestAPI.modelo.Usuario;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DetalhesTopicoDto {

    private long id;
    private String titulo;
    private String mensgem;
    private LocalDateTime dataCriacao;
    private StatusTopico status;
    private String autor;
    private List<RespostaDto> respostas = new ArrayList<>();

    public DetalhesTopicoDto(Topico topico) {
        this.id = topico.getId();
        this.titulo= topico.getTitulo();
        this.mensgem= topico.getMensagem();
        this.dataCriacao= topico.getDataCriacao();
        this.autor= topico.getAutor().getNome();
        this.status= topico.getStatus();

        this.respostas= new ArrayList<>();
        this.respostas.addAll(topico.getRespostas().stream()
                .map(RespostaDto::new).toList());
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

    public StatusTopico getStatus() {
        return status;
    }

    public String getAutor() {
        return autor;
    }

    public List<RespostaDto> getRespostas() {
        return respostas;
    }
}
