package br.ufg.inf.api.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.ufg.inf.api.model.Produto;
import br.ufg.inf.api.model.Categoria;
import br.ufg.inf.api.model.Tag;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class ControllerIntegrationTest {

       @Autowired
       private MockMvc mockMvc;

       @Autowired
       private ObjectMapper objectMapper;

       @Test
       public void testProdutoController() throws Exception {
              // Test GET all produtos
              mockMvc.perform(get("/api/produtos"))
                     .andExpect(status().isOk());

              // Test POST new produto
              Produto newProduto = new Produto();
              newProduto.setNome("Test Produto");
              newProduto.setCategoria(new Categoria("Test Categoria"));
              Tag tag1 = new Tag("Test Tag");
              Tag tag2 = new Tag("Test Tag2");
              List<Tag> tags=new ArrayList<Tag>();
              tags.add(tag1);
              tags.add(tag2);
              newProduto.setTags(tags);

              mockMvc.perform(post("/api/produtos")
                     .contentType(MediaType.APPLICATION_JSON)
                     .content(objectMapper.writeValueAsString(newProduto)))
                     .andExpect(status().isCreated());

              // Test PUT produto
              newProduto.setNome("Updated Test Produto");
              mockMvc.perform(put("/api/produtos/1")
                     .contentType(MediaType.APPLICATION_JSON)
                     .content(objectMapper.writeValueAsString(newProduto)))
                     .andExpect(status().isOk());

              // Test DELETE produto
              mockMvc.perform(delete("/api/produtos/1"))
              	     .andExpect(status().isNoContent());
       }

       @Test
       public void testCategoriaController() throws Exception {
              // Test GET all categorias
              mockMvc.perform(get("/api/categorias"))
                     .andExpect(status().isOk());

              // Test POST new categoria
              Categoria newCategoria = new Categoria("Test Categoria");

              mockMvc.perform(post("/api/categorias")
                     .contentType(MediaType.APPLICATION_JSON)
                     .content(objectMapper.writeValueAsString(newCategoria)))
                     .andExpect(status().isCreated());

              // Test PUT categoria
              newCategoria.setNome("Updated Test Categoria");
              mockMvc.perform(put("/api/categorias/1")
                     .contentType(MediaType.APPLICATION_JSON)
                     .content(objectMapper.writeValueAsString(newCategoria)))
                     .andExpect(status().isOk());

              // Test DELETE categoria
              mockMvc.perform(delete("/api/categorias/1"))
                     .andExpect(status().isNoContent());
       }

       @Test
       public void testTagController() throws Exception {
              // Test GET all tags
              mockMvc.perform(get("/api/tags"))
                     .andExpect(status().isOk());

              // Test POST new tag
              Tag newTag = new Tag("Test Tag");

              MvcResult result = mockMvc.perform(post("/api/tags")
                     .contentType(MediaType.APPLICATION_JSON)
                     .content(objectMapper.writeValueAsString(newTag)))
                     .andExpect(status().isCreated())
                     .andReturn();

              String content = result.getResponse().getContentAsString();
              Tag createdTag = objectMapper.readValue(content, Tag.class);

              // Test PUT tag
              createdTag.setNome("Updated Test Tag");
              mockMvc.perform(put("/api/tags/1")
                     .contentType(MediaType.APPLICATION_JSON)
                     .content(objectMapper.writeValueAsString(createdTag)))
                     .andExpect(status().isOk());

              // Test DELETE tag
              mockMvc.perform(delete("/api/tags/" + createdTag.getId()))
              .andExpect(status().isNoContent());
       }
}