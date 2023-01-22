<template>
  <div>
    <h2>Get suggested activities for today!</h2>
    <form @submit.prevent="getActivities">
      <label for="city">City:</label>
      <input type="text" v-model="city" id="city" required>
      <button type="submit">Get Activities</button>
    </form>
    <ul v-if="activities">
      <li v-for="activity in activities" :key="activity.name">
        <h3>{{ activity.name }}</h3>
        <p>{{ activity.description }}</p>
      </li>
    </ul>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  data() {
    return {
      city: '',
      activities: null
    }
  },
  methods: {
    async getActivities() {
      const response = await axios.get(`http://localhost:8080/api/v1/activity/suggestion?city=${this.city}`)
      this.activities = response.data
    },
    async getWeather() {
      const response = await axios.get(`http://localhost:8080/api/v1/weather/current?city=${this.city}`)
      this.activities = response.data
    }
  }
}
</script>


<style>

</style>
