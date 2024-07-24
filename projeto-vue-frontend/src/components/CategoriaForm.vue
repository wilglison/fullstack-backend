<template>
  <div>
    <h2>Adicionar Produto</h2>
    <form @submit.prevent="adicionarProduto">
      <input v-model="produto.nome" placeholder="Nome" required/>
      <input v-model="produto.preco" placeholder="Preço" required/>
      <input v-model="produto.categoria" placeholder="Categoria" />
      <input type="text" id="tags" placeholder="Ex: 1,2,3" name="tagsId" v-model="produto.tagsId">  
      <button type="submit">Adicionar</button>
    </form>

    <h2>Adicionar Categoria</h2>
    <form @submit.prevent="adicionarCategoria">
      <input v-model="produto.nome" placeholder="Nome" required/>
      <button type="submit">Adicionar</button>
    </form>

    <h2>Adicionar Tag</h2>
    <form @submit.prevent="adicionarCategoria">
      <input v-model="produto.nome" placeholder="Nome" required/>
      <button type="submit">Adicionar</button>
    </form>
  </div>
</template>

<script>
import axios from '../axios';

export default {
  data() {
    return {
      produto: {
        nome: '',
        preco: 0
      }
    }
  },
  methods: {
    async adicionarProduto() {
      try {
        await axios.post('/produtos', this.produto);
        this.$emit('produtoAdicionado');
        this.produto = { nome: '', preco: 0 }; // Resetar o formulário
      } catch (error) {
        console.error("Erro ao adicionar produto:", error);
      }
    }
  }
}
</script>
