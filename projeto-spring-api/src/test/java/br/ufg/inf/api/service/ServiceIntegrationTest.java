package br.ufg.inf.api.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import br.ufg.inf.api.model.Produto;
import br.ufg.inf.api.model.Categoria;
import br.ufg.inf.api.model.Tag;
import br.ufg.inf.api.service.CategoriaService;
import br.ufg.inf.api.service.ProdutoService;
import br.ufg.inf.api.service.TagService;

import java.util.List;

@SpringBootTest
@Transactional
public class ServiceIntegrationTest {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private TagService tagService;

    @Test
    public void testProdutoService() {
        // Create a new produto
        Produto newProduto = new Produto();
        newProduto.setNome("Test Produto");
        newProduto.setCategoria(new Categoria("Test Categoria"));
        newProduto.getTags().add(new Tag("Test Tag"));

        // Save the produto
        Produto savedProduto = produtoService.salvarProduto(newProduto);
        assertNotNull(savedProduto.getId());

        // Retrieve the produto
        Produto retrievedProduto = produtoService.obterProduto(savedProduto.getId());
        assertNotNull(retrievedProduto);
        assertEquals("Test Produto", retrievedProduto.getNome());

        // Update the produto
        savedProduto.setNome("Updated Test Produto");
        Produto updatedProduto = produtoService.salvarProduto(savedProduto);
        assertEquals("Updated Test Produto", updatedProduto.getNome());

        // Delete the produto
        produtoService.deletarProduto(savedProduto.getId());
        assertNull(produtoService.obterProduto(savedProduto.getId()));
    }

    @Test
    public void testCategoriaService() {
        // Create a new categoria
        Categoria newCategoria = new Categoria("Test Categoria");

        // Save the categoria
        Categoria savedCategoria = categoriaService.salvarCategoria(newCategoria);
        assertNotNull(savedCategoria.getId());

        // Retrieve the categoria
        Categoria retrievedCategoria = categoriaService.obterCategoria(savedCategoria.getId());
        assertNotNull(retrievedCategoria);
        assertEquals("Test Categoria", retrievedCategoria.getNome());

        // Update the categoria
        savedCategoria.setNome("Updated Test Categoria");
        Categoria updatedCategoria = categoriaService.salvarCategoria(savedCategoria);
        assertEquals("Updated Test Categoria", updatedCategoria.getNome());

        // Delete the categoria
        categoriaService.deletarCategoria(savedCategoria.getId());
        assertNull(categoriaService.obterCategoria(savedCategoria.getId()));
    }

    @Test
    public void testTagService() {
        // Create a new tag
        Tag newTag = new Tag("Test Tag");

        // Save the tag
        Tag savedTag = tagService.salvarTag(newTag);
        assertNotNull(savedTag.getId());

        // Retrieve the tag
        Tag retrievedTag = tagService.obterTag(savedTag.getId());
        assertNotNull(retrievedTag);
        assertEquals("Test Tag", retrievedTag.getNome());

        // Update the tag
        savedTag.setNome("Updated Test Tag");
        Tag updatedTag = tagService.salvarTag(savedTag);
        assertEquals("Updated Test Tag", updatedTag.getNome());

        // Delete the tag
        tagService.deletarTag(savedTag.getId());
        assertNull(tagService.obterTag(savedTag.getId()));
    }
}