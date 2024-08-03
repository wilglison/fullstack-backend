package br.ufg.inf.api.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.ufg.inf.api.model.Produto;
import br.ufg.inf.api.model.Categoria;
import br.ufg.inf.api.model.Tag;
import br.ufg.inf.api.repository.CategoriaRepository;
import br.ufg.inf.api.repository.ProdutoRepository;
import br.ufg.inf.api.repository.TagRepository;

@ExtendWith(SpringExtension.class)
public class ServiceTestsUnit {

    @InjectMocks
    private ProdutoService produtoService;

    @Mock
    private ProdutoRepository produtoRepository;

    @InjectMocks
    private CategoriaService categoriaService;

    @Mock
    private CategoriaRepository categoriaRepository;

    @InjectMocks
    private TagService tagService;

    @Mock
    private TagRepository tagRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testListarProdutos() {
        List<Produto> produtos = Arrays.asList(
            new Produto(1L, "Produto 1", 10.0),
            new Produto(2L, "Produto 2", 20.0)
        );
        when(produtoRepository.findAll()).thenReturn(produtos);

        List<Produto> result = produtoService.listarProdutos();

        assertEquals(2, result.size());
        assertEquals("Produto 1", result.get(0).getNome());
        assertEquals("Produto 2", result.get(1).getNome());
    }

    @Test
    public void testObterProduto() {
        Produto produto = new Produto(1L, "Produto 1", 10.0);
        when(produtoRepository.findById(1L)).thenReturn(Optional.of(produto));

        Produto result = produtoService.obterProduto(1L);

        assertNotNull(result);
        assertEquals("Produto 1", result.getNome());
    }

    @Test
    public void testSalvarProduto() {
        Produto newProduto = new Produto(null, "Novo Produto", 15.0);
        Produto savedProduto = new Produto(1L, "Novo Produto", 15.0);
        when(produtoRepository.save(newProduto)).thenReturn(savedProduto);

        Produto result = produtoService.salvarProduto(newProduto);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Novo Produto", result.getNome());
    }

    @Test
    public void testAtualizarProduto() {
        Produto existingProduto = new Produto(1L, "Produto Existente", 10.0);
        Produto updatedProduto = new Produto(1L, "Produto Atualizado", 20.0);
        when(produtoRepository.findById(1L)).thenReturn(Optional.of(existingProduto));
        when(produtoRepository.save(any(Produto.class))).thenReturn(updatedProduto);

        Produto result = produtoService.salvarProduto(updatedProduto);

        assertNotNull(result);
        assertEquals("Produto Atualizado", result.getNome());
        assertEquals(20.0, result.getPreco());
    }

    @Test
    public void testDeletarProduto() {
        doNothing().when(produtoRepository).deleteById(1L);

        produtoService.deletarProduto(1L);

        verify(produtoRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testListarCategorias() {
        List<Categoria> categorias = Arrays.asList(
            new Categoria("Categoria 1"),
            new Categoria("Categoria 2")
        );
        when(categoriaRepository.findAll()).thenReturn(categorias);

        List<Categoria> result = categoriaService.listarCategorias();

        assertEquals(2, result.size());
        assertEquals("Categoria 1", result.get(0).getNome());
        assertEquals("Categoria 2", result.get(1).getNome());
    }

    @Test
    public void testObterCategoria() {
        Categoria categoria = new Categoria("Categoria 1");
        categoria.setId(1L);
        when(categoriaRepository.findById(1L)).thenReturn(Optional.of(categoria));

        Categoria result = categoriaService.obterCategoria(1L);

        assertNotNull(result);
        assertEquals("Categoria 1", result.getNome());
    }

    @Test
    public void testSalvarCategoria() {
        Categoria newCategoria = new Categoria("Nova Categoria");
        Categoria savedCategoria = new Categoria("Nova Categoria");
        savedCategoria.setId(1L);
        when(categoriaRepository.save(newCategoria)).thenReturn(savedCategoria);

        Categoria result = categoriaService.salvarCategoria(newCategoria);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Nova Categoria", result.getNome());
    }

    @Test
    public void testDeletarCategoria() {
        doNothing().when(categoriaRepository).deleteById(1L);

        categoriaService.deletarCategoria(1L);

        verify(categoriaRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testListarTags() {
        List<Tag> tags = Arrays.asList(
            new Tag("Tag 1"),
            new Tag("Tag 2")
        );
        when(tagRepository.findAll()).thenReturn(tags);

        List<Tag> result = tagService.listarTags();

        assertEquals(2, result.size());
        assertEquals("Tag 1", result.get(0).getNome());
        assertEquals("Tag 2", result.get(1).getNome());
    }

    @Test
    public void testObterTag() {
        Tag tag = new Tag("Tag 1");
        tag.setId(1L);
        when(tagRepository.findById(1L)).thenReturn(Optional.of(tag));

        Tag result = tagService.obterTag(1L);

        assertNotNull(result);
        assertEquals("Tag 1", result.getNome());
    }

    @Test
    public void testSalvarTag() {
        Tag newTag = new Tag("Nova Tag");
        Tag savedTag = new Tag("Nova Tag");
        savedTag.setId(1L);
        when(tagRepository.save(newTag)).thenReturn(savedTag);

        Tag result = tagService.salvarTag(newTag);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        assertEquals("Nova Tag", result.getNome());
    }

    @Test
    public void testDeletarTag() {
        doNothing().when(tagRepository).deleteById(1L);

        tagService.deletarTag(1L);

        verify(tagRepository, times(1)).deleteById(1L);
    }
}
