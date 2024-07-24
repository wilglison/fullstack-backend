<template>
  <tr>
    <td>{{ produto.id }}</td>
    <td>{{ produto.nome }}</td>
    <td>{{ produto.preco }}</td>
    <td>{{ produto.categoria.nome }}</td>
    <td>
      <button @click="editarProduto">Editar</button>
      <button @click="deletarProduto">Deletar</button>
    </td>
  </tr>
</template>

<script>
import axios from '../axios';

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

<style scoped>

</style>

