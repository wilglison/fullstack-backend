<template>
  <div class="card mb-4">
    <h1 class="display-8">Produtos</h1>
    <div class="card-body">
      <div v-if="message" :class="['alert', message.type === 'success' ? 'alert-success' : 'alert-danger', 'alert-dismissible', 'fade', 'show']" role="alert">
        {{ message.text }}
        <button type="button" class="btn-close" @click="closeMessage" aria-label="Close"></button>
      </div>
      <h2 class="h4 mb-0">{{ editando ? 'Editar' : 'Adicionar' }} Produto</h2>
      <form @submit.prevent="salvarProduto" class="mb-4">
        <div class="row">
          <div class="col-md-4">
            <input class="form-control" v-model="produtoAtual.nome" placeholder="Nome" required/>
          </div>
          <div class="col-md-4">
            <input class="form-control" v-model="produtoAtual.preco" placeholder="Preço" type="number" step="0.01" required/>
          </div>
          <div class="col-md-4">
              <v-select
                v-model="produtoAtual.categoria"
                :options="categorias"
                :reduce="categoria => categoria"
                label="nome"
                placeholder="Selecione uma categoria"
                required
              >
                <template v-slot:option="{ nome }">
                  {{ nome }}
                </template>
              </v-select>
          </div>
        </div>
        <div class="row mt-3">
          <div class="col-md-12">
            <label>Tags:</label>
            <v-select
              v-model="produtoAtual.tags"
              :options="allTags"
              :reduce="tag => tag"
              label="nome"
              multiple
              :closeOnSelect="false"
              placeholder="Selecione as tags"
            >
              <template v-slot:option="{ nome }">
                <span class="badge bg-primary me-1">{{ nome }}</span>
              </template>
              <template v-slot:selected-option="{ nome }">
                <span class="badge bg-primary me-1">{{ nome }}</span>
              </template>
            </v-select>
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
          <td>{{ produto.preco.toFixed(2) }}</td>
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
import vSelect from 'vue-select';
import 'vue-select/dist/vue-select.css';

export default {
  components: {
    vSelect
  },
  data() {
    return {
      produtos: [],
      categorias: [],
      allTags: [],
      produtoAtual: {
        id: null,
        nome: '',
        preco: '',
        categoria: null,
        tags: [],
      },
      editando: false,
      message: null
    }
  },
  methods: {
    async listarProdutos() {
      try {
        const response = await axios.get('/produtos');
        this.produtos = response.data;
      } catch (error) {
        console.error("Erro ao listar produtos:", error);
        this.showMessage('Erro ao listar produtos', 'error');
      }
    },
    async listarCategorias() {
      try {
          const response = await axios.get('/categorias');
          this.categorias = response.data;
      } catch (error) {
          console.error("Erro ao listar categorias:", error);
          this.showMessage('Erro ao listar categorias', 'error');
      }
    },
    async listarTags() {
      try {
          const response = await axios.get('/tags');
          this.allTags = response.data;
      } catch (error) {
          console.error("Erro ao listar tags:", error);
          this.showMessage('Erro ao listar tags', 'error');
      }
    },
    async salvarProduto() {
      try {
        const produtoParaSalvar = {
          ...this.produtoAtual,
          preco: parseFloat(this.produtoAtual.preco)
        };
        if (this.editando) {
          await axios.put(`/produtos/${produtoParaSalvar.id}`, produtoParaSalvar);
          this.showMessage('Produto atualizado com sucesso', 'success');
        } else {
          await axios.post('/produtos', produtoParaSalvar);
          this.showMessage('Produto adicionado com sucesso', 'success');
        }
        this.listarProdutos();
        this.limparSelecao();
      } catch (error) {
        if (error.response) {
          this.showMessage(error.response.data, 'error');
        } else {
          this.showMessage('Erro ao salvar produto', 'error');
        }
      }
    },
    selecionarProduto(produto) {
      this.produtoAtual = { ...produto, preco: produto.preco.toFixed(2) };
      this.editando = true;
    },
    limparSelecao() {
      this.produtoAtual = { id: null, nome: '', preco: '', categoria: null, tags: [] };
      this.editando = false;
    },
    async deletarProduto(id) {
      try {
        await axios.delete(`/produtos/${id}`);
        this.listarProdutos();
        this.showMessage('Produto deletado com sucesso', 'success');
      } catch (error) {
        console.error("Erro ao deletar produto:", error);
        this.showMessage('Erro ao deletar produto', 'error');
      }
    },
    showMessage(text, type) {
      this.message = { text, type };
      setTimeout(() => {
        this.closeMessage();
      }, 5000);
    },
    closeMessage() {
      this.message = null;
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
.v-select .vs__selected {
  margin: 2px 2px 2px 0;
}
.v-select .vs__deselect {
  fill: white;
}
.alert {
  position: fixed;
  top: 20px;
  right: 20px;
  z-index: 1000;
}
</style>
