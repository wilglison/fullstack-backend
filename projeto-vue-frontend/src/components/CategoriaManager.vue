<template>
  <div class="card mb-4 justify-content-center">
    <h1 class="display-8 justify-content-center">Categorias</h1>
    <div class="card-body justify-content-center">
      <h2 class="h4 mb-0 justify-content-center">{{ editando ? 'Editar' : 'Adicionar' }} Categoria</h2>
      <form @submit.prevent="salvarCategoria" class="mb-4">
        <div class="row justify-content-center">
          <div class="col-md-4">
            <input class="form-control justify-content-center" v-model="categoriaAtual.nome" placeholder="Nome" required/>
          </div>
        </div>
        <div class="mt-2 justify-content-center">
          <button class="btn btn-success me-2 justify-content-center" type="submit">{{ editando ? 'Atualizar' : 'Salvar' }}</button>
          <button class="btn btn-secondary justify-content-center" type="button" @click="limparSelecao">Cancelar</button>
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
  props: ['showMessage'],
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
        this.showMessage('Erro ao listar categorias', 'error');        
      }
    },
    async salvarCategoria() {
      try {
        if (this.editando) {
          await axios.put(`/categorias/${this.categoriaAtual.id}`, this.categoriaAtual);
          this.showMessage('Categoria atualizada com sucesso', 'success');
        } else {
          await axios.post('/categorias', this.categoriaAtual);
          this.showMessage('Categoria adicionada com sucesso', 'success');
        }
        this.listarCategorias();
        this.limparSelecao();
      } catch (error) {
        if (error.response) {
          this.showMessage(error.response.data, 'error');
        } else {
          this.showMessage('Erro ao salvar Categoria', 'error');
        }
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
        const response = await axios.delete(`/categorias/${id}`);
        if (response.status === 204) {
          this.listarCategorias();
          this.showMessage('Categoria deletada com sucesso', 'success');
        } else {
          this.showMessage(response.data, 'error');
        }
      } catch (error) {
        if (error.response && error.response.data) {
          this.showMessage(error.response.data, 'error');
        } else {
          this.showMessage('Erro ao deletar Categoria', 'error');
        }
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
