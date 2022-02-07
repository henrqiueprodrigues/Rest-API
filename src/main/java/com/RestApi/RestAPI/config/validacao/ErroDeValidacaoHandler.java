package com.RestApi.RestAPI.config.validacao;

import com.RestApi.RestAPI.config.DTO.ErroFormDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice //Diz que é uma classe para tratamento de erro
public class ErroDeValidacaoHandler {

    @Autowired
    private MessageSource messageSource;

    @ResponseStatus(code = HttpStatus.BAD_REQUEST) //Devolve esse estado ao invez do 200 quando ocorre a exception
    @ExceptionHandler(MethodArgumentNotValidException.class) //Diz para o spring que sempre que ocorrer essa excesão executar esse metodo
    public List<ErroFormDto> handle(MethodArgumentNotValidException exception){
        List<ErroFormDto> erros= new ArrayList<>();
        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        fieldErrors.forEach(e ->{
            //Obtem a mensagem associadoa ao erro passado por parametro e a traduz para a linguagem padrão da maquina (portugues)
            String mensagem= messageSource.getMessage(e, LocaleContextHolder.getLocale());
            //Encontra o campo em que ocorreu o erro e adiciona ele e a mensagem dele a lista de erros
            ErroFormDto erro= new ErroFormDto(e.getField(),mensagem);
        });
        return erros;
    }
}
