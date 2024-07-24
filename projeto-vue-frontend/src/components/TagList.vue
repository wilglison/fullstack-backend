<template>
  <div>
    <h1>Lista de Tags</h1>
    <TagForm @tagAdicionado="listarTags"/>
    <table>
      <thead>
        <tr>
          <th>ID</th>
          <th>Nome</th>
          <th>Ações</th>
        </tr>
      </thead>
      <tbody>
  <TagItem 
    v-for="categoria in categorias" 
    :key="categoria.id" 
    :categoria="categoria" 
    @tagAtualizado="listarTags" 
    @tagDeletado="listarTags"
  />
</tbody>
    </table>
  </div>
</template>

<script>
import axios from '../axios';
import TagForm from './TagForm.vue';
import TagItem from './TagItem.vue';

export default {
  components: { TagForm, TagItem },
  data() {
    return {
      tags: []
    }
  },
  methods: {
    async listarTags() {
      try {
        const response = await axios.get('/tags');
        this.tags = response.data;
      } catch (error) {
        console.error("Erro ao listar tags:", error);
      }
    }
  },
  created() {
    this.listarTags();
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