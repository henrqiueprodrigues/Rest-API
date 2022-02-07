package com.RestApi.RestAPI.config.DTO;

public class ErroFormDto {
    private String campo;
    private String mensagemErro;

    public ErroFormDto(String campo, String mensagemErro) {
        this.campo = campo;
        this.mensagemErro = mensagemErro;
    }

    public String getCampo() {
        return campo;
    }

    public String getMensagemErro() {
        return mensagemErro;
    }
}
