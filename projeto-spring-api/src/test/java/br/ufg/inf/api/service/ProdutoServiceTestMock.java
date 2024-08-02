package br.ufg.inf.api.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.ufg.inf.api.model.Produto;
import br.ufg.inf.api.repository.ProdutoRepository;
import br.ufg.inf.api.service.ProdutoService;

@ExtendWith(SpringExtension.class)
//@SpringBootTest
public class ProdutoServiceTestMock {
    
    @Mock
    private ProdutoRepository produtoRepository; // Mock do repositório
    
    @InjectMocks
    private ProdutoService service; // Injeção do mock no serviço
    
    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this); // Inicializa mocks e injectMocks
    }
    
    @Test
    public void testAddProduto() {
        String nome = "Teste";
        double preco = 100;
        Produto produto = new Produto(null, nome, preco);

        when(produtoRepository.save(any(Produto.class))).thenAnswer(i -> {
            Produto p = i.getArgument(0);
            p.setId(1L); // Simular a geração de ID pelo banco de dados
            return p;
        });

        Produto produtoSalvo = service.salvarProduto(produto);

        assertNotNull(produtoSalvo);
        assertNotNull(produtoSalvo.getId());
        assertEquals(nome, produtoSalvo.getNome());
        assertEquals(preco, produtoSalvo.getPreco());


    
    }
}