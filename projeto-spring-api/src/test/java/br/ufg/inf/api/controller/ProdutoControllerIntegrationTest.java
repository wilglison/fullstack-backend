package br.ufg.inf.api.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.ufg.inf.api.model.Categoria;
import br.ufg.inf.api.model.Produto;
import br.ufg.inf.api.model.Tag;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProdutoControllerIntegrationTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testSalvarProduto() {
        String nome = "Teste";
        double preco = 100;
        Categoria categoria = new Categoria("Teste Categoria");
        Tag tag = new Tag("Teste Tag");

        Produto produto = new Produto(null, nome, preco);
        produto.setCategoria(categoria);
        produto.setTags(new ArrayList<>());
        produto.getTags().add(tag);
        ResponseEntity<Tag> responseTag = restTemplate.postForEntity("/api/tags", tag, Tag.class);
        ResponseEntity<Categoria> responseCat = restTemplate.postForEntity("/api/categorias", categoria, Categoria.class);
        ResponseEntity<Produto> response = restTemplate.postForEntity("/api/produtos", produto, Produto.class);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(HttpStatus.CREATED, responseCat.getStatusCode());
        assertEquals(HttpStatus.CREATED, responseTag.getStatusCode());

        Produto produtoSalvo = response.getBody();
        assertNotNull(produtoSalvo);
        System.err.println(produtoSalvo.getNome());
        assertNotNull(produtoSalvo.getId());
        assertEquals(nome, produtoSalvo.getNome());
        assertEquals(preco, produtoSalvo.getPreco());
    }
}