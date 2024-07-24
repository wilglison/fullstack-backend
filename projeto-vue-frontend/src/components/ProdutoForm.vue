<template>
  <div>
    <h2>Adicionar Produto</h2>
    <form @submit.prevent="adicionarProduto">
      <input v-model="produto.nome" placeholder="Nome" required/>
      <input v-model="produto.preco" placeholder="Preço" required/>
      <button type="submit">Adicionar</button>
    </form>
  </div>
</template>

<script>
import axios from '../axios'; // Importa a instância configurada do Axios

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
