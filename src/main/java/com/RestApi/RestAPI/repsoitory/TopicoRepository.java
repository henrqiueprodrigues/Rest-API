package com.RestApi.RestAPI.repsoitory;

import com.RestApi.RestAPI.modelo.Topico;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TopicoRepository extends JpaRepository <Topico, Long> {
    //findBy + classe + atributo= busca a o atributo expecificado na classe expecificada
    List<Topico> findByCurso_Nome(String nomeCurso); //Recebe um nome na URL e busca o curso correspondente
}
