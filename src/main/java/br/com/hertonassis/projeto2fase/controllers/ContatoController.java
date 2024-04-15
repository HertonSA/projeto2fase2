package br.com.hertonassis.projeto2fase.controllers;

import br.com.hertonassis.projeto2fase.entidades.Contato;
import br.com.hertonassis.projeto2fase.respositorios.ContatoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/contatos")
public class ContatoController {
    @Autowired
    private ContatoRepository repository;

    @GetMapping
    public List<Contato> listarTodosOsContatos() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contato> getContatoPorId(@PathVariable Long id) {
        try {
            return new ResponseEntity<>(repository.findById(id).get(), HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public Contato adicionarContato(@RequestBody Contato Fabricio) {
        return repository.save(Fabricio);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removerPorId(@PathVariable Long id) {
        try {
            repository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Contato> alterarContatoPorId(@PathVariable Long id, @RequestBody Contato novosDados) {
        try {
            Contato contatoAntigo = repository.findById(id).get();
            contatoAntigo.setNome(novosDados.getNome());
            contatoAntigo.setEmail(novosDados.getEmail());
            contatoAntigo.setTelefone(novosDados.getTelefone());
            return new ResponseEntity<>(repository.save(contatoAntigo), HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}