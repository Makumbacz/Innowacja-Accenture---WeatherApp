<template>
  <div class="p-6 rounded-md bg-white shadow-md">
    <h2 class="text-2xl font-bold mb-6">Forecast for 5 days!</h2>
    <form @submit.prevent="getForecast" class="flex items-center mb-6">
      <label for="city" class="mr-4 text-lg">City:</label>
      <input type="text" v-model="city" id="city" required class="p-2 border border-gray-300 rounded-md flex-1">
      <button type="submit" class="ml-4 bg-green-500 hover:bg-green-600 text-white py-2 px-4 rounded">Get Forecast</button>
    </form>
    <h3  v-if="forecast" class="text-2xl items-center justify-center font-semi-bold mb-6 ">Forecast for {{city}}!</h3>

    <table v-if="forecast" class="table-auto items-center mb-6">
      <thead>
      <tr class="bg-gray-200 mb-6">
        <th class="px-4 py-2">Date</th>
        <th class="px-4 py-2">Top Temperature</th>
        <th class="px-4 py-2">Lowest Temperature</th>
        <th class="px-4 py-2">Wind Speed</th>
        <th class="px-4 py-2">Humidity</th>
        <th class="px-4 py-2">Small description</th>

      </tr>
      </thead>
      <tbody>
      <tr v-for="day in forecast" :key="day.date" class="hover:bg-gray-100">
        <td class="border px-6 py-4 font-semibold">{{ day.date }}</td>
        <td class="border px-6 py-4 font-semibold">{{ day.todayTopTemperature }} °C</td>
        <td class="border px-6 py-4 font-semibold">{{ day.todayLowestTemperature }} °C</td>
        <td class="border px-6 py-4 font-semibold">{{ day.windSpeed }} m/s</td>
        <td class="border px-6 py-4 font-semibold">{{ day.humidity }} %</td>
        <td class="border px-6 py-4 font-semibold">{{ day.description }}</td>
      </tr>
      </tbody>
    </table>
  </div>
</template>



<script setup>
import axios from 'axios'
import { useAuthStore } from "../stores/auth"
import {ref} from "vue";

const store = useAuthStore()

const city = ref('')
const forecast = ref(null)
const token = store.token;

const getForecast = async () => {
  const token = store.token
  const config = {
    headers: { Authorization: `Bearer ${token}` }
  }
  const response = await axios.get(`http://localhost:8080/api/v1/weather/forecast?city=${city.value}`, config)
  forecast.value = response.data
}
</script>

<style>
table {
  border-spacing: 5px;
  margin: auto;
  width: 50%;
}

</style>
