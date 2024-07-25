<template>
    <div class="card mb-4">
      <h1 class="display-8">Produtos</h1>
      <div class="card-body">
        <h2 class="h4 mb-0">{{ editando ? 'Editar' : 'Adicionar' }} Produto</h2>
        <form @submit.prevent="salvarProduto" class="mb-4">
          <div class="row">
            <div class="col-md-4">
              <input class="form-control" v-model="produtoAtual.nome" placeholder="Nome" required/>
            </div>
            <div class="col-md-4">
              <input class="form-control" v-model="produtoAtual.preco" placeholder="Preco" required/>
            </div>
            <div class="col-md-4">
                <select class="form-control" v-model="produtoAtual.categoria.id" required>
                    <option v-for="categoria in categorias" :key="categoria.id" :value="categoria.id">
                    {{ categoria.nome }}
                    </option>
                </select>
            </div>
          </div>
          <div class="row mt-3">
            <div class="col-md-6">
              <label>Tags atuais:</label>
              <div>
                <span v-for="tag in produtoAtual.tags" :key="tag.id" class="badge bg-primary me-1 mb-1">
                    {{ tag.nome }}
                    <button @click="removerTag(tag)" class="btn-close btn-close-white" aria-label="Close"></button>
                </span>
              </div>
            </div>
            <div class="col-md-6">
              <label>Adicionar tags:</label>
              <div class="input-group">
                <select v-model="selectedTags" multiple class="form-control">
                    <option v-for="tag in allTags" :key="tag.id" :value="tag">{{ tag.nome }}</option>
                </select>
                <button @click="adicionarTags" type="button" class="btn btn-secondary">Adicionar</button>
              </div>
            </div>
          </div>
          <div class="mt-3">
            <button class="btn btn-success me-2" type="submit">{{ editando ? 'Atualizar' : 'Salvar' }}</button>
            <button class="btn btn-secondary" type="button" @click="limparSelecao">Cancelar</button>
          </div>
        </form>
      </div>
  
      <table class="table table-striped table-hover">
        <thead>
          <tr>
            <th>ID</th>
            <th>Nome</th>
            <th>Preço</th>
            <th>Categoria</th>
            <th>Tags</th>
            <th>Ações</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="produto in produtos" :key="produto.id" @click="selecionarProduto(produto)">
            <td>{{ produto.id }}</td>
            <td>{{ produto.nome }}</td>
            <td>{{ produto.preco }}</td>
            <td>{{ produto.categoria.nome }}</td>
            <td><span v-for="tag in produto.tags" :key="tag.id" class="badge bg-secondary me-1">{{ tag.nome }}</span></td>
            <td><button  class="btn btn-danger btn-sm" @click.stop="deletarProduto(produto.id)">Deletar</button></td>
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
        produtos: [],
        categorias: [],
        allTags: [],
        produtoAtual: {
          id: null,
          nome: '',
          categoria: { id: null, nome: '' },
          tags: [],
        },
        selectedTags: [],
        editando: false
      }
    },
    methods: {
      async listarProdutos() {
        try {
          const response = await axios.get('/produtos');
          this.produtos = response.data;
        } catch (error) {
          console.error("Erro ao listar produtos:", error);
        }
      },
      async listarCategorias() {
        try {
            const response = await axios.get('/categorias');
            this.categorias = response.data;
        } catch (error) {
            console.error("Erro ao listar categorias:", error);
        }
      },
      async listarTags() {
        try {
            const response = await axios.get('/tags');
            this.allTags = response.data;
        } catch (error) {
            console.error("Erro ao listar tags:", error);
        }
      },
      async salvarProduto() {
        try {
          if (this.editando) {
            await axios.put(`/produtos/${this.produtoAtual.id}`, this.produtoAtual);
          } else {
            await axios.post('/produtos', this.produtoAtual);
          }
          this.listarProdutos();
          this.limparSelecao();
        } catch (error) {
          console.error("Erro ao salvar produto:", error);
        }
      },
      selecionarProduto(produto) {
        this.produtoAtual = { ...produto };
        this.editando = true;
      },
      limparSelecao() {
        this.produtoAtual = { id: null, nome: '', categoria: { id: null, nome: '' }, tags: [] };
        this.selectedTags = [];
        this.editando = false;
      },
      async deletarProduto(id) {
        try {
          await axios.delete(`/produtos/${id}`);
          this.listarProdutos();
        } catch (error) {
          console.error("Erro ao deletar produto:", error);
        }
      },
      adicionarTags() {
        this.selectedTags.forEach(tag => {
          if (!this.produtoAtual.tags.some(t => t.id === tag.id)) {
            this.produtoAtual.tags.push(tag);
          }
        });
        this.selectedTags = [];
      },
      removerTag(tag) {
        this.produtoAtual.tags = this.produtoAtual.tags.filter(t => t.id !== tag.id);
      }
    },
    created() {
      this.listarProdutos();
      this.listarCategorias();
      this.listarTags();
    }
  }
  </script>
  
  <style scoped>
  
  </style>
