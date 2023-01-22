<template>
  <div>
    <h2>Forecast for 5 days!</h2>
    <form @submit.prevent="getForecast">
      <label for="city">City:</label>
      <input type="text" v-model="city" id="city" required>
      <button type="submit">Get Forecast</button>
    </form>
    <table v-if="forecast">
      <thead>
      <tr>
        <th>Date</th>
        <th>Top Temperature</th>
        <th>Lowest Temperature</th>
        <th>Wind Speed</th>
        <th>Humidity</th>
      </tr>
      </thead>
      <tbody>
      <tr v-for="day in forecast" :key="day.date">
        <td>{{ day.date }}</td>
        <td>{{ day.todayTopTemperature }}</td>
        <td>{{ day.todayLowestTemperature }}</td>
        <td>{{ day.windSpeed }}</td>
        <td>{{ day.humidity }}</td>
      </tr>
      </tbody>
    </table>
    <div>
      <router-view/>
    </div>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  data() {
    return {
      city: '',
      forecast: null
    }
  },
  methods: {
    async getForecast() {
      const response = await axios.get(`http://localhost:8080/api/v1/weather/forecast?city=${this.city}`)
      this.forecast = response.data
    },

  }
}
</script>
<style>
table {
  border-spacing: 5px;
  margin: auto;
  width: 50%;
}
</style>
