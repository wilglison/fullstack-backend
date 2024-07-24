<template>
  <li>
    <span>{{ produto.nome }} - {{ produto.preco }}</span>
    <button @click="editarProduto">Editar</button>
    <button @click="deletarProduto">Deletar</button>
    <div v-if="editando">
      <input v-model="produtoAtualizado.nome" placeholder="Nome" required/>
      <input v-model="produtoAtualizado.preco" placeholder="Preço" required/>
      <button @click="atualizarProduto">Atualizar</button>
      <button @click="cancelarEdicao">Cancelar</button>
    </div>
  </li>
</template>

<script>
import axios from '../axios'; // Importa a instância configurada do Axios

export default {
  props: ['produto'],
  data() {
    return {
      editando: false,
      produtoAtualizado: { ...this.produto }
    }
  },
  methods: {
    editarProduto() {
      this.editando = true;
    },
    cancelarEdicao() {
      this.editando = false;
      this.produtoAtualizado = { ...this.produto };
    },
    async atualizarProduto() {
      try {
        await axios.put(`/produtos/${this.produtoAtualizado.id}`, this.produtoAtualizado);
        this.editando = false;
        this.$emit('produtoAtualizado');
      } catch (error) {
        console.error("Erro ao atualizar produto:", error);
      }
    },
    async deletarProduto() {
      try {
        await axios.delete(`/produtos/${this.produto.id}`);
        this.$emit('produtoDeletado');
      } catch (error) {
        console.error("Erro ao deletar produto:", error);
      }
    }
  }
}
</script>
