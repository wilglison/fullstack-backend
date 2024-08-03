package br.ufg.inf.api.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.ufg.inf.api.model.Categoria;
import br.ufg.inf.api.model.Produto;
import br.ufg.inf.api.model.Tag;
import br.ufg.inf.api.service.CategoriaService;
import br.ufg.inf.api.service.ProdutoService;
import br.ufg.inf.api.service.TagService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = {ProdutoController.class, TagController.class, CategoriaController.class})
public class ControllerTestsUnits {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private ProdutoService produtoService;

	@MockBean
	private CategoriaService categoriaService;

	@MockBean
	private TagService tagService;

	@Autowired
	private ObjectMapper objectMapper;

	private Produto produto;
	private Categoria categoria;
	private Tag tag;

	@BeforeEach
	void setUp() {
		categoria = new Categoria("Eletrônicos");
		categoria.setId(1L);
		produto = new Produto(1L, "Smartphone", 999.99);
		produto.setCategoria(categoria);
		tag = new Tag("Technology");
		tag.setId(1L);
		produto.setTags(new ArrayList<>());
		produto.getTags().add(tag);
	}

	@Test
	void testListarProdutos() throws Exception {
		List<Produto> produtos = Arrays.asList(produto);
		when(produtoService.listarProdutos()).thenReturn(produtos);

		mockMvc.perform(get("/api/produtos"))
		.andExpect(status().isOk())
		.andExpect(content().json(objectMapper.writeValueAsString(produtos)));
	}

	@Test
	void testAdicionarProduto() throws Exception {
		when(produtoService.salvarProduto(any(Produto.class))).thenReturn(produto);

		mockMvc.perform(post("/api/produtos")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(produto)))
		.andExpect(status().isCreated())
		.andExpect(jsonPath("$.id").value(1))
		.andExpect(jsonPath("$.nome").value("Smartphone"))
		.andExpect(jsonPath("$.preco").value(999.99));
	}

	@Test
	void testAtualizarProduto() throws Exception {
		when(produtoService.salvarProduto(any(Produto.class))).thenReturn(produto);

		mockMvc.perform(put("/api/produtos/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(produto)))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.id").value(1))
		.andExpect(jsonPath("$.nome").value("Smartphone"))
		.andExpect(jsonPath("$.preco").value(999.99));
	}

	@Test
	void testObterProduto() throws Exception {
		when(produtoService.obterProduto(1L)).thenReturn(produto);

		mockMvc.perform(get("/api/produtos/1"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.id").value(1))
		.andExpect(jsonPath("$.nome").value("Smartphone"))
		.andExpect(jsonPath("$.preco").value(999.99));
	}

	@Test
	void testDeletarProduto() throws Exception {
		doNothing().when(produtoService).deletarProduto(1L);

		mockMvc.perform(delete("/api/produtos/1"))
		.andExpect(status().isNoContent());
	}

	@Test
	void testListarTags() throws Exception {
		List<Tag> tags = Arrays.asList(tag);
		when(tagService.listarTags()).thenReturn(tags);

		mockMvc.perform(get("/api/tags"))
		.andExpect(status().isOk())
		.andExpect(content().json(objectMapper.writeValueAsString(tags)));
	}

	@Test
	void testAdicionarTag() throws Exception {
		when(tagService.salvarTag(any(Tag.class))).thenReturn(tag);

		mockMvc.perform(post("/api/tags")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(tag)))
		.andExpect(status().isCreated())
		.andExpect(jsonPath("$.id").value(1))
		.andExpect(jsonPath("$.nome").value("Technology"));
	}

	@Test
	void testAtualizarTag() throws Exception {
		when(tagService.obterTag(1L)).thenReturn(tag);
		when(tagService.salvarTag(any(Tag.class))).thenReturn(tag);

		mockMvc.perform(put("/api/tags/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(tag)))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.id").value(1))
		.andExpect(jsonPath("$.nome").value("Technology"));
	}

	@Test
	void testObterTag() throws Exception {
		when(tagService.obterTag(1L)).thenReturn(tag);

		mockMvc.perform(get("/api/tags/1"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.id").value(1))
		.andExpect(jsonPath("$.nome").value("Technology"));
	}

	@Test
	void testDeletarTag() throws Exception {
		when(tagService.obterTag(1L)).thenReturn(tag);
		doNothing().when(tagService).deletarTag(1L);

		mockMvc.perform(delete("/api/tags/1"))
		.andExpect(status().isNoContent());
	}

	@Test
	void testListarCategorias() throws Exception {
		List<Categoria> categorias = Arrays.asList(categoria);
		when(categoriaService.listarCategorias()).thenReturn(categorias);

		mockMvc.perform(get("/api/categorias"))
		.andExpect(status().isOk())
		.andExpect(content().json(objectMapper.writeValueAsString(categorias)));
	}

	@Test
	void testAdicionarCategoria() throws Exception {
		when(categoriaService.salvarCategoria(any(Categoria.class))).thenReturn(categoria);

		mockMvc.perform(post("/api/categorias")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(categoria)))
		.andExpect(status().isCreated())
		.andExpect(jsonPath("$.id").value(1))
		.andExpect(jsonPath("$.nome").value("Eletrônicos"));
	}

	@Test
	void testAtualizarCategoria() throws Exception {
		when(categoriaService.salvarCategoria(any(Categoria.class))).thenReturn(categoria);

		mockMvc.perform(put("/api/categorias/1")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(categoria)))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.id").value(1))
		.andExpect(jsonPath("$.nome").value("Eletrônicos"));
	}

	@Test
	void testObterCategoria() throws Exception {
		when(categoriaService.obterCategoria(1L)).thenReturn(categoria);

		mockMvc.perform(get("/api/categorias/1"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.id").value(1))
		.andExpect(jsonPath("$.nome").value("Eletrônicos"));
	}

	@Test
	void testDeletarCategoria() throws Exception {
		when(categoriaService.obterCategoria(1L)).thenReturn(categoria);
		doNothing().when(categoriaService).deletarCategoria(1L);

		mockMvc.perform(delete("/api/categorias/1"))
		.andExpect(status().isNoContent());
	}
}
