<template>
  <div class="flex flex-col items-center justify-center">
    <h1 class="text-3xl mb-8">Welcome to Weather App, {{user}}</h1>
    <p class="text-xl font-light mb-8"> Here is weather for city you are currently in.</p>
    <p class="text-sm font-light">please enable location*</p>
    <instant-weather/>
  </div>
</template>


<script setup>
import {onMounted, ref} from "vue";
import {useAuthStore} from "../stores/auth";
import axios from "axios";
import InstantWeather from "../components/InstantWeather.vue";

const store = useAuthStore();
const user = store.user;
const token = store.token;
const data = ref('');

/*onMounted(async () => {
  const response = await fetch('http://localhost:8080/api/v1/user', {
    headers: {
      Authorization: `Bearer ${token}`,
    },
  });
  data.value = await response.text();


});*/

</script>

<style scoped>

</style>