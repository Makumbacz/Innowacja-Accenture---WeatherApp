<template>
  <div class="p-6 rounded-md bg-white shadow-md">
    <h2 class="text-2xl font-bold mb-6">Get suggested activities for today!</h2>
    <form @submit.prevent="getActivities" class="flex items-center mb-6">
      <label for="city" class="mr-4 text-lg font-medium text-gray-700">City:</label>
      <input type="text" v-model="city" id="city" required class="p-2 border border-gray-300 rounded-md flex-1 focus:outline-none focus:border-blue-500">
      <button type="submit" class="ml-4 bg-green-500 hover:bg-green-600 text-white py-2 px-4 rounded">Get Activities</button>
    </form>
    <ul v-if="activities">
      <li v-for="activity in activities" :key="activity.name" class="mb-4">
        <h3 class="text-lg font-medium text-gray-800">{{ activity.name }}</h3>
        <p class="text-gray-600 mt-2">{{ activity.description }}</p>
      </li>
    </ul>
  </div>
</template>


<script setup>
import axios from 'axios'
import {ref} from "vue";
import {useAuthStore} from "../stores/auth";

const store = useAuthStore()
const token = store.token;

const city = ref('')
const activities = ref(null)

async function getActivities() {
  const config = {
    headers: { Authorization: `Bearer ${token}` }
  }
  const response = await axios.get(`http://localhost:8080/api/v1/activity/suggestion?city=${city.value}`,config)
  activities.value = response.data
}

async function getWeather() {
  const config = {
    headers: { Authorization: `Bearer ${token}` }
  }
  const response = await axios.get(`http://localhost:8080/api/v1/weather/current?city=${city.value}`,config)
  activities.value = response.data
}
</script>


<style>

</style>
