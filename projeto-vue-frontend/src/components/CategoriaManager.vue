<template>
  <div class="card mb-4">
    <h1 class="display-8">Categorias</h1>
    <div class="card-body">
      <h2 class="h4 mb-0">{{ editando ? 'Editar' : 'Adicionar' }} Categoria</h2>
      <form @submit.prevent="salvarCategoria" class="mb-4">
        <div class="row justify-content-center">
          <div class="col-md-4">
            <input class="form-control" v-model="categoriaAtual.nome" placeholder="Nome" required/>
          </div>
        </div>
        <div class="mt-2">
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
          <th>Ações</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="categoria in categorias" :key="categoria.id" @click="selecionarCategoria(categoria)">
          <td>{{ categoria.id }}</td>
          <td>{{ categoria.nome }}</td>
          <td>
            <button  class="btn btn-danger btn-sm" @click.stop="deletarCategoria(categoria.id)">Deletar</button>
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
      categoriaAtual: {
        id: null,
        nome: '',
      },
      editando: false
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
    async salvarCategoria() {
      try {
        if (this.editando) {
          await axios.put(`/categorias/${this.categoriaAtual.id}`, this.categoriaAtual);
        } else {
          await axios.post('/categorias', this.categoriaAtual);
        }
        this.listarCategorias();
        this.limparSelecao();
      } catch (error) {
        console.error("Erro ao salvar categoria:", error);
      }
    },
    selecionarCategoria(categoria) {
      this.categoriaAtual = { ...categoria };
      this.editando = true;
    },
    limparSelecao() {
      this.categoriaAtual = { id: null, nome: '' };
      this.editando = false;
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

</style>
