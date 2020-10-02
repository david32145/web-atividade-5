<template>
  <div id="app">
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
      <a class="navbar-brand" href="#">Sistema de carros</a>
      <div class="collapse navbar-collapse" id="navbarText">
        <ul class="navbar-nav mr-auto">
          <li class="nav- active">
            <router-link class="nav-link" to="/carros">Listar</router-link>
          </li>
          <li class="nav-item active">
            <router-link class="nav-link" to="/carros/novo">Novo</router-link>
          </li>
        </ul>
      </div>
    </nav>
    <router-view />
  </div>
</template>