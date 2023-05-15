<template>
  <div class="flex justify-center items-center">
    <div class="bg-white shadow-md rounded p-8">
      <h1 class="text-3xl font-bold mb-6">Current Weather for {{ city }}</h1>
      <div class="flex flex-col md:flex-row">
        <div class="w-full md:w-1/2">
          <div class="flex flex-col mb-4">
            <span class="text-lg font-bold mb-1">Date</span>
            <span class="text-gray-800 font-semibold">{{ dateTime }}</span>
          </div>
          <div class="flex flex-col mb-4">
            <span class="text-lg font-bold mb-1">Temperature </span>
            <span class="text-gray-800 font-semibold">{{ temperature }} °C</span>
          </div>
          <div class="flex flex-col mb-4">
            <span class="text-lg font-bold mb-1">Feels Like Temperature </span>
            <span class="text-gray-800 font-semibold">{{ feelsLikeTemperature }} °C</span>
          </div>
        </div>
        <div class="w-full md:w-1/2">
          <div class="flex flex-col mb-4">
            <span class="text-lg font-bold mb-1">Wind Speed</span>
            <span class="text-gray-800 font-semibold">{{ windSpeed }}  m/s</span>
          </div>
          <div class="flex flex-col mb-4">
            <span class="text-lg font-bold mb-1">Humidity </span>
            <span class="text-gray-800 font-semibold">{{ humidity }} %</span>
          </div>
          <div class="flex flex-col mb-4">
            <span class="text-lg font-bold mb-1">Description</span>
            <span class="text-gray-800 font-semibold">{{ description }}</span>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script setup>
import axios from 'axios';
import { useAuthStore } from "../stores/auth";
import { onMounted, ref } from 'vue';

const store = useAuthStore();
const city = ref('');
const temperature = ref('');
const windSpeed = ref('');
const humidity = ref('');
const description = ref('');
const feelsLikeTemperature = ref('');
const dateTime = ref('');

onMounted(() => {
  getLocation();
});

async function getLocation() {
  if (navigator.geolocation) {
    navigator.geolocation.getCurrentPosition(getWeatherData);
  } else {
    console.error("Geolocation is not supported by this browser.");
  }
}

async function getWeatherData(position) {
  console.log(position);
  try {
    const config = {
      headers: { Authorization: `Bearer ${store.token}` }
    };
    const response = await axios.get(`http://localhost:8080/api/v1/weather/current/cords?latitude=${position.coords.latitude}&longitude=${position.coords.longitude}`, config);
    temperature.value = response.data.temperature;
    windSpeed.value = response.data.windSpeed;
    humidity.value = response.data.humidity;
    description.value = response.data.description;
    feelsLikeTemperature.value = response.data.feelsLikeTemperature;
    dateTime.value = response.data.dateTime;
    city.value = response.data.city;
  } catch (error) {
    console.error(error);
  }
}
</script>


<style scoped>

</style>