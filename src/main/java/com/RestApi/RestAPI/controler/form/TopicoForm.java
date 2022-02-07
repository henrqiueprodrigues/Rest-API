package com.RestApi.RestAPI.controler.form;

import com.RestApi.RestAPI.modelo.Curso;
import com.RestApi.RestAPI.modelo.Topico;
import com.RestApi.RestAPI.repsoitory.CursoRepository;
import com.sun.istack.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;


public class TopicoForm {
    /*Define que o campo não pode ser nulo, nem um uma stirng vazia ou com menos de 5 caracteres,
     tambem pode ser adicionar mensagens caso não se enquadre nos padrões especificados com:
     , message = "SUA MENSAGEM"
     */
    @NotNull @NotEmpty @Size(min= 5)
    private String titulo;

    @NotNull @NotEmpty @Size(min= 15)
    private String mensagem;

    @NotNull @NotEmpty @Size(min= 5)
    private String nomeCurso;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

    public String getNomeCurso() {
        return nomeCurso;
    }

    public void setNomeCurso(String nomeCurso) {
        this.nomeCurso = nomeCurso;
    }

    public Object converter(CursoRepository repository) {
        Curso curso= repository.findByNome(nomeCurso);
        return new Topico(titulo,mensagem,curso);
    }
}
