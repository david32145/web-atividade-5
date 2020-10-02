<template>
  <div class="container mt-5" style="max-width: 800px">
    <h4 class="mb-4">Atualizar carro</h4>
    <form>
      <div class="form-group">
        <label for="idInput">Id</label>
        <input
          type="text"
          v-model="id"
          disabled
          class="form-control"
          id="idInput"
        />
      </div>
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
      <button
        type="button"
        v-on:click="updateCar"
        class="btn btn-primary btn-block mt-5 mb-3"
      >
        Atualizar
      </button>
    </form>
  </div>
</template>

<script>
import httpClient from "../providers/httpClient";

export default {
  name: "car-update-view",
  data() {
    return {
      name: "",
      saleDate: "",
      brand: "",
      fabricationYear: "",
      id: "",
    };
  },
  created() {
    const id = this.$route.params.id;
    httpClient
      .get(`/cars/${id}`)
      .then((response) => {
        this.name = response.data.name;
        this.saleDate = response.data.saleDate;
        this.brand = response.data.brand;
        this.fabricationYear = response.data.fabricationYear;
        this.id = response.data.id;
      })
      .catch(() => {
        this.$router.push({ path: "/carros" });
      });
  },
  methods: {
    updateCar() {
      httpClient
        .put(`/cars/${this.id}`, {
          name: this.name,
          saleDate: this.saleDate,
          brand: this.brand,
          fabricationYear: this.fabricationYear,
        })
        .then(() => {
          this.$router.push({ path: "/carros" });
        })
        .catch(() => {
          alert("Ocorreu um error ao atualizar seu carro");
        });
    },
  },
};
</script>