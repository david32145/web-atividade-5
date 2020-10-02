<template>
  <div class="container mt-5">
    <div class="row">
      <div class="col-6">
        <h4>Lista de carros</h4>
      </div>
      <div class="col-3">
        <input
          placeholder="buscar por nome..."
          type="text"
          v-model="search"
          class="form-control"
          id="exampleInputEmail1"
          aria-describedby="emailHelp"
        />
      </div>
      <div class="col-1">
        <input
          placeholder="0"
          min="0"
          v-model="limit"
          type="number"
          class="form-control"
          id="exampleInputEmail1"
          aria-describedby="emailHelp"
        />
      </div>
      <div class="col-2">
        <button
          v-on:click="findCars"
          type="button"
          class="btn btn-primary btn-block"
        >
          Buscar
        </button>
      </div>
    </div>

    <table class="table mt-5">
      <thead>
        <tr>
          <th scope="col"># id</th>
          <th scope="col">Nome</th>
          <th scope="col">Marca</th>
          <th scope="col">Ano de fabricação</th>
          <th scope="col">Data da venda</th>
          <th scope="col">Ações</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="car in cars" :key="car.id">
          <th scope="row">{{ car.id }}</th>
          <td>{{ car.name }}</td>
          <td>{{ car.brand }}</td>
          <td>{{ car.fabricationYear }}</td>
          <td>{{ car.saleDate }}</td>
          <td>
            <router-link :to="'/carros/editar/' + car.id">Editar</router-link>
            <a
              href="#"
              role="button"
              v-on:click="deleteCar(car.id)"
              class="ml-3"
              >Excluir</a
            >
            <a href="#" role="button" v-on:click="viewCar(car)" class="ml-3"
              >Ver</a
            >
          </td>
        </tr>
      </tbody>
    </table>
    <div
      v-show="showDialogCarInfo"
      class="modal my-modal"
      tabindex="-1"
      role="dialog"
    >
      <div class="modal-dialog modal-dialog-centered" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title">Informações do carro</h5>
            <button
              type="button"
              class="close"
              v-on:click="showDialogCarInfo = false"
            >
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <div class="modal-body">
            <p><strong>Id: </strong>{{ currentCar.id }}</p>
            <p><strong>Nome: </strong>{{ currentCar.name }}</p>
            <p><strong>Marca: </strong>{{ currentCar.brand }}</p>
            <p><strong>Data da venda: </strong>{{ currentCar.saleDate }}</p>
            <p>
              <strong>Ano de fabricação: </strong
              >{{ currentCar.fabricationYear }}
            </p>
          </div>
          <div class="modal-footer">
            <button
              type="button"
              class="btn btn-secondary"
              v-on:click="showDialogCarInfo = false"
            >
              Fechar
            </button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import httpClient from "../providers/httpClient";

export default {
  name: "car-list-view",
  data() {
    return {
      search: "",
      limit: "",
      cars: [],
      currentCar: {},
      showDialogCarInfo: false,
    };
  },
  created() {
    httpClient
      .get("/cars")
      .then((response) => {
        this.cars = response.data;
      })
      .catch(() => {
        alert("Ocorreu um error ao buscar seus dados");
      });
  },
  methods: {
    findCars() {
      let query = "";
      if (this.search) {
        query = `?brand=${this.search}`;
      }
      if (this.limit) {
        query =
          query.length === 0 ? `?limit=${this.limit}` : `&limit=${this.limit}`;
      }

      httpClient
        .get(`/cars${query}`)
        .then((response) => {
          this.cars = response.data;
        })
        .catch(() => {
          alert("Ocorreu um error ao buscar seus dados");
        });
    },
    deleteCar(carId) {
      httpClient
        .delete(`/cars/${carId}`)
        .then(() => {
          this.cars = this.cars.filter((car) => car.id !== carId);
        })
        .catch(() => {
          alert("Ocorreu um error ao buscar seus dados");
        });
    },
    viewCar(car) {
      console.log("aqui");
      this.currentCar = car;
      this.showDialogCarInfo = true;
    },
  },
};
</script>

<style>
.my-modal {
  display: block;
  position: fixed;
  width: 100vw;
  height: 100vh;
  z-index: 10;
  background-color: rgba(0, 0, 0, 0.28);
}
</style>