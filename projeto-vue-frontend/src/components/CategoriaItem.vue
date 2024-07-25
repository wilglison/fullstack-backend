<template>
  <tr>
    <td>{{ categoria.id }}</td>
    <td>{{ categoria.nome }}</td>
    <td>{{ categoria.preco }}</td>
    <td>{{ categoria.categoria.nome }}</td>
    <td>
      <button @click="editarCategoria">Editar</button>
      <button @click="deletarCategoria">Deletar</button>
    </td>
  </tr>
</template>

<script>
import axios from '../axios';

export default {
  props: ['categoria'],
  data() {
    return {
      editando: false,
      categoriaAtualizado: { ...this.categoria }
    }
  },
  methods: {
    editarCategoria() {
      this.editando = true;
    },
    cancelarEdicao() {
      this.editando = false;
      this.categoriaAtualizado = { ...this.categoria };
    },
    async atualizarCategoria() {
      try {
        await axios.put(`/categorias/${this.categoriaAtualizado.id}`, this.categoriaAtualizado);
        this.editando = false;
        this.$emit('categoriaAtualizado');
      } catch (error) {
        console.error("Erro ao atualizar categoria:", error);
      }
    },
    async deletarCategoria() {
      try {
        await axios.delete(`/categorias/${this.categoria.id}`);
        this.$emit('categoriaDeletado');
      } catch (error) {
        console.error("Erro ao deletar categoria:", error);
      }
    }
  }
}
</script>

<style scoped>

</style>

