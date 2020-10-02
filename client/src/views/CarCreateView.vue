<template>
  <div class="container mt-5" style="max-width: 800px">
    <h4 class="mb-4">Criar novo carro</h4>
    <form>
      <div class="form-group">
        <label for="nameInput">Nome</label>
        <input type="text" v-model="name" class="form-control" id="nameInput" />
      </div>
      <div class="form-group">
        <label for="brandInput">Marca</label>
        <input
          type="text"
          class="form-control"
          v-model="brand"
          id="brandInput"
        />
      </div>
      <div class="form-group">
        <label for="saleDateInput">Data da venda</label>
        <input
          type="text"
          class="form-control"
          v-model="saleDate"
          id="saleDateInput"
        />
      </div>
      <div class="form-group">
        <label for="fabricationYearInput">Ano de fabricação</label>
        <input
          type="number"
          class="form-control"
          v-model="fabricationYear"
          id="fabricationYearInput"
        />
      </div>
      <button type="button" v-on:click="createCar" class="btn btn-primary btn-block mt-5">
        Criar
      </button>
    </form>
  </div>
</template>

<script>
import httpClient from "../providers/httpClient";

export default {
  name: "car-create-view",
  data() {
    return {
      name: "",
      saleDate: "",
      brand: "",
      fabricationYear: "",
    };
  },
  methods: {
    createCar() {
      httpClient
        .post("/cars", {
          name: this.name,
          saleDate: this.saleDate,
          brand: this.brand,
          fabricationYear: this.fabricationYear,
        })
        .then(() => {
          this.$router.push({path: '/carros'})
        })
        .catch(() => {
          alert("Ocorreu um error ao salvar seu carro");
        });
    },
  },
};
</script>