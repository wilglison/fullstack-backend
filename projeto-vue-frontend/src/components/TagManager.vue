<template>
  <div class="card mb-4">
    <h1 class="display-8">Tags</h1>
    <div class="card-body">
      <h2 class="h4 mb-0">{{ editando ? 'Editar' : 'Adicionar' }} Tag</h2>
      <form @submit.prevent="salvarTag" class="mb-4">
        <div class="row justify-content-center">
          <div class="col-md-4">
            <input class="form-control" v-model="tagAtual.nome" placeholder="Nome" required/>
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
        <tr v-for="tag in tags" :key="tag.id" @click="selecionarTag(tag)">
          <td>{{ tag.id }}</td>
          <td>{{ tag.nome }}</td>
          <td>
            <button  class="btn btn-danger btn-sm" @click.stop="deletarTag(tag.id)">Deletar</button>
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
      tags: [],
      tagAtual: {
        id: null,
        nome: '',
      },
      editando: false
    }
  },
  methods: {
    async listarTags() {
      try {
        const response = await axios.get('/tags');
        this.tags = response.data;
      } catch (error) {
        console.error("Erro ao listar tags:", error);
        this.showMessage('Erro ao listar tags', 'error');
      }
    },
    async salvarTag() {
      try {
        if (this.editando) {
          await axios.put(`/tags/${this.tagAtual.id}`, this.tagAtual);
          this.showMessage('Tag atualizada com sucesso', 'success');
        } else {
          await axios.post('/tags', this.tagAtual);
          this.showMessage('Tag adicionada com sucesso', 'success');
        }
        this.listarTags();
        this.limparSelecao();
      } catch (error) {
        if (error.response) {
          this.showMessage(error.response.data, 'error');
        } else {
          this.showMessage('Erro ao salvar Tag', 'error');
        }
      }
    },
    selecionarTag(tag) {
      this.tagAtual = { ...tag };
      this.editando = true;
    },
    limparSelecao() {
      this.tagAtual = { id: null, nome: '' };
      this.editando = false;
    },
    async deletarTag(id) {
      try {
        await axios.delete(`/tags/${id}`);
        this.listarTags();
        this.showMessage('Tag deletada com sucesso', 'success');
      } catch (error) {
        console.error("Erro ao deletar tag:", error);
        this.showMessage('Erro ao deletar Tag', 'error');
      }
    }
  },
  created() {
    this.listarTags();
  }
}
</script>

<style scoped>

</style>
