package br.ufg.inf.api.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class RepositoryTests {

    @Autowired
    private ProdutoRepository repository;
    
    @Test
    public void testExample() throws Exception {
    // teste que utiliza o repositório com um banco de  dados em memória
    }
}