<template>
  <div>
    <nav class="navbar-expand-lg navbar-light bg-light mb-4">
      <div class="container-fluid">
        <div class="navbar-nav d-flex justify-content-center w-100">
          <button class="nav-item btn btn-link" :class="{ 'active': currentComponent === 'ProdutoManager' }" @click="currentComponent = 'ProdutoManager'">Produtos</button>
          <button class="nav-item btn btn-link" :class="{ 'active': currentComponent === 'TagManager' }" @click="currentComponent = 'TagManager'">Tags</button>
          <button class="nav-item btn btn-link" :class="{ 'active': currentComponent === 'CategoriaManager' }" @click="currentComponent = 'CategoriaManager'">Categorias</button>
        </div>
      </div>
    </nav>
    <div class="container">
      <component :is="currentComponent"></component>
    </div>
  </div>
</template>

<script>
import ProdutoManager from '../components/ProdutoManager.vue';
import TagManager from '../components/TagManager.vue';
import CategoriaManager from '../components/CategoriaManager.vue';

export default {
  name: 'HomeView',
  components: { ProdutoManager, TagManager, CategoriaManager },
  data() {
    return {
      currentComponent: 'ProdutoManager'
    }
  }
}
</script>

<style scoped>
.navbar-nav .btn-link {
  color: rgba(0,0,0,.5);
  text-decoration: none;
}
.navbar-nav .btn-link:hover {
  color: rgba(0,0,0,.7);
}
.navbar-nav .btn-link.active {
  color: rgba(0,0,0,.9);
  font-weight: bold;
}
</style>
