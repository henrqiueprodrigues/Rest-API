package com.RestApi.RestAPI.controler;

import com.RestApi.RestAPI.controler.DTO.DetalhesTopicoDto;
import com.RestApi.RestAPI.controler.DTO.TopicoDto;
import com.RestApi.RestAPI.controler.form.AtualizacaoTopicoForm;
import com.RestApi.RestAPI.controler.form.TopicoForm;
import com.RestApi.RestAPI.modelo.Topico;
import com.RestApi.RestAPI.repsoitory.CursoRepository;
import com.RestApi.RestAPI.repsoitory.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

import java.util.List;
import java.util.Optional;

@RestController //Controler junto a response body
@RequestMapping("/topicos")
public class TopicosController {

    @Autowired
    private TopicoRepository topicoRepository;
    @Autowired
    private CursoRepository cursoRepository;

    @GetMapping //executa para metododos GET
    public List<TopicoDto> lista(String nomeCurso){

        if(nomeCurso== null) {
            List<Topico> topicos = topicoRepository.findAll();
            return TopicoDto.converter(topicos);
        } else{
            List<Topico> topicos = topicoRepository.findByCurso_Nome(nomeCurso);
            return TopicoDto.converter(topicos);
        }

    }

    /*
        CRIA UMA NOVA ENTRADA NO BANCO DE DADOS
     */
    @PostMapping //executa para metododos POST
    @Transactional
    public ResponseEntity<TopicoDto> cadastrar(@RequestBody @Valid TopicoForm form,
                                               UriComponentsBuilder uriBuilder){
        Topico topico= (Topico) form.converter(cursoRepository);
        topicoRepository.save(topico);

        //Passa para o URI o caminho URL do nova entidade criada
        URI uri= uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri(); //encontra o topico pelo id e o converte para URI
        return ResponseEntity.created(uri).body(new TopicoDto(topico)); //retorna mensagem 201(created), junto a URI e o topicoDto criado

    }

    /*
        ENCORNTRA UMA ENTRADA NO BANCO DE DADOS
     */
    @GetMapping("/{id}") //Cria uma URL dinamica
    public ResponseEntity <DetalhesTopicoDto> detalhar(@PathVariable Long id){ //Recebe uma variavel id pelo URL
        Optional<Topico> topico = topicoRepository.findById(id); //Faz a busca de um topico pelo ID
        if(topico.isPresent()) {
            return ResponseEntity.ok(new DetalhesTopicoDto(topico.get()));
        }
        return  ResponseEntity.notFound().build(); //Retorna uma mensagem 404
    }


    /*
        ATUALIZA UMA ENTRADA DO BANCO DE DADOS
     */
    @PutMapping("/{id}")
    @Transactional //Diz ao spring para salval as alterações no banco de dados
    public ResponseEntity<TopicoDto> atualiza(@PathVariable Long id,@RequestBody @Valid AtualizacaoTopicoForm form){
        Optional<Topico> optional = topicoRepository.findById(id); //Faz a busca de um topico pelo ID
        if(optional.isPresent()) {
            Topico topico= form.atualizar(id,topicoRepository);
            return ResponseEntity.ok(new TopicoDto(topico)); //Retorna o corpo atualizado
        }
        return  ResponseEntity.notFound().build();


    }

    /*
        EXCLUI UMA ENTRADA DO BANCO DE DADOS
     */
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> remove(@PathVariable Long id){
        Optional<Topico> optional = topicoRepository.findById(id); //Faz a busca de um topico pelo ID
        if(optional.isPresent()) {
            topicoRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return  ResponseEntity.notFound().build();
    }

}
