<template>
  <div>
    <h1>Lista de Categorias</h1>
    <CategoriaForm @categoriaAdicionado="listarCategorias"/>
    <table>
      <thead>
        <tr>
          <th>ID</th>
          <th>Nome</th>
          <th>Ações</th>
        </tr>
      </thead>
      <tbody>
  <CategoriaItem 
    v-for="categoria in categorias" 
    :key="categoria.id" 
    :categoria="categoria" 
    @categoriaAtualizado="listarCategorias" 
    @categoriaDeletado="listarCategorias"
  />
</tbody>
    </table>
  </div>
</template>

<script>
import axios from '../axios';
import CategoriaForm from './CategoriaForm.vue';
import CategoriaItem from './CategoriaItem.vue';

export default {
  components: { CategoriaForm, CategoriaItem },
  data() {
    return {
      categorias: []
    }
  },
  methods: {
    async listarCategorias() {
      try {
        const response = await axios.get('/categorias');
        this.categorias = response.data;
      } catch (error) {
        console.error("Erro ao listar categorias:", error);
      }
    }
  },
  created() {
    this.listarCategorias();
  }
}
</script>


<style scoped>

table {
  width: 80%;
  margin: 20px auto;
  border-collapse: collapse;
  box-shadow: 0 2px 3px rgba(0, 0, 0, 0.1);

}

th, td {
  padding: 12px;
  border-bottom: 1px solid #ddd;
}

th {
  background-color: #f2f2f2;
  font-weight: bold;
}

tr:nth-child(even) {
  background-color: #f8f8f8;
}

tr:hover {
  background-color: #f5f5f5;
}



</style>