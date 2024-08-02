package br.ufg.inf.api.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.ufg.inf.api.model.Produto;
import br.ufg.inf.api.service.ProdutoService;

@SpringBootTest
public class ProdutoServiceTest {

    @Autowired
    private ProdutoService service;
    
    @Test
    public void testAddProduct() {
        String nome = "Teste";
        double preco = 100;
        Produto produto = new Produto(null, nome, preco);

        Produto produtoSalvo = service.salvarProduto(produto);

        assertNotNull(produtoSalvo);
        assertNotNull(produtoSalvo.getId());
        assertEquals(nome, produtoSalvo.getNome());
        assertEquals(preco, produtoSalvo.getPreco());
    }
}