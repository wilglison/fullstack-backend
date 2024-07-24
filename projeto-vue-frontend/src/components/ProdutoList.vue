<template>
  <div>
    <h1>Lista de Produtos</h1>
    <ProdutoForm @produtoAdicionado="listarProdutos"/>
    <ul>
      <ProdutoItem v-for="produto in produtos" :key="produto.id" :produto="produto" @produtoAtualizado="listarProdutos" @produtoDeletado="listarProdutos"/>
    </ul>
  </div>
</template>

<script>
import axios from '../axios'; // Importa a instância configurada do Axios
import ProdutoForm from './ProdutoForm.vue';
import ProdutoItem from './ProdutoItem.vue';

export default {
  components: { ProdutoForm, ProdutoItem },
  data() {
    return {
      produtos: []
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
    }
  },
  created() {
    this.listarProdutos();
  }
}
</script>
