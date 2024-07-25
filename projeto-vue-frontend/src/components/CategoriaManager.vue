<template>
    <div>
      <h1>Gerenciamento de Categorias</h1>
  
      <!-- Formulário de Adição -->
      <div>
        <h2>Adicionar Categoria</h2>
        <form @submit.prevent="adicionarCategoria">
          <input v-model="novaCategoria.nome" placeholder="Nome" required/>
          <button type="submit">Adicionar</button>
        </form>
      </div>
  
      <table>
        <thead>
          <tr>
            <th>ID</th>
            <th>Nome</th>
            <th>Ações</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="categoria in categorias" :key="categoria.id">
            <td>{{ categoria.id }}</td>
            <td>{{ categoria.nome }}</td>
            <td>
              <button @click="editarCategoria(categoria)">Editar</button>
              <button @click="deletarCategoria(categoria.id)">Deletar</button>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </template>
  
  <script>
  import axios from '../axios';
  
  export default {
    data() {
      return {
        categorias: [],
        novaCategoria: {
          nome: '',
        },
        editando: false,
        categoriaAtualizada: {}
      }
    },
    methods: {
      async listarCategorias() {
        try {
          const response = await axios.get('/categorias');
          this.categorias = response.data;
        } catch (error) {
          console.error("Erro ao listar categorias:", error);
        }
      },
      async adicionarCategoria() {
        try {
          await axios.post('/categorias', this.novaCategoria);
          this.listarCategorias();
          this.novaCategoria = { nome: ''};
        } catch (error) {
          console.error("Erro ao adicionar categoria:", error);
        }
      },
      editarCategoria(categoria) {
        this.editando = true;
        this.categoriaAtualizada = { ...categoria };
      },
      async atualizarCategoria() {
        try {
          await axios.put(`/categorias/${this.categoriaAtualizada.id}`, this.categoriaAtualizada);
          this.editando = false;
          this.listarCategorias();
        } catch (error) {
          console.error("Erro ao atualizar categoria:", error);
        }
      },
      async deletarCategoria(id) {
        try {
          await axios.delete(`/categorias/${id}`);
          this.listarCategorias();
        } catch (error) {
          console.error("Erro ao deletar categoria:", error);
        }
      }
    },
    created() {
      this.listarCategorias();
    }
  }
  </script>
  
  <style scoped>
  table {
    width: 80%;
    margin: 20px auto;
    border-collapse: collapse;
    box-shadow: 0 2px 3px rgba(0, 0, 0, 0.1);
  }
  
  th, td {
    padding: 12px;
    border-bottom: 1px solid #ddd;
  }
  
  th {
    background-color: #f2f2f2;
    font-weight: bold;
  }
  
  tr:nth-child(even) {
    background-color: #f8f8f8;
  }
  
  tr:hover {
    background-color: #f5f5f5;
  }
  </style>
  