<template>
  <div>
    <h2>Adicionar Categoria</h2>
    <form @submit.prevent="adicionarCategoria">
      <input v-model="categoria.nome" placeholder="Nome" required/>
      <input v-model="categoria.preco" placeholder="Preço" required/>
      <input v-model="categoria.categoria" placeholder="Categoria" />
      <input type="text" id="tags" placeholder="Ex: 1,2,3" name="tagsId" v-model="categoria.tagsId">  
      <button type="submit">Adicionar</button>
    </form>

    <h2>Adicionar Categoria</h2>
    <form @submit.prevent="adicionarCategoria">
      <input v-model="categoria.nome" placeholder="Nome" required/>
      <button type="submit">Adicionar</button>
    </form>

    <h2>Adicionar Tag</h2>
    <form @submit.prevent="adicionarCategoria">
      <input v-model="categoria.nome" placeholder="Nome" required/>
      <button type="submit">Adicionar</button>
    </form>
  </div>
</template>

<script>
import axios from '../axios';

export default {
  data() {
    return {
      categoria: {
        nome: '',
        preco: 0
      }
    }
  },
  methods: {
    async adicionarCategoria() {
      try {
        await axios.post('/categorias', this.categoria);
        this.$emit('categoriaAdicionado');
        this.categoria = { nome: '', preco: 0 }; // Resetar o formulário
      } catch (error) {
        console.error("Erro ao adicionar categoria:", error);
      }
    }
  }
}
</script>
