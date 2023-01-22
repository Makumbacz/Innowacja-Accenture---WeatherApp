<template>
  <div>
    <h1>Current Weather for {{ city }}</h1>
    <p>Temperature: {{ temperature }}</p>
    <p>Wind Speed: {{ windSpeed }}</p>
    <p>Humidity: {{ humidity }}</p>
    <p>Description: {{ description }}</p>
    <p>Feels Like Temperature: {{ feelsLikeTemperature }}</p>
    <p>Date: {{ dateTime }}</p>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'CurrentWeather',
  data() {
    return {
      city: '',
      temperature: '',
      windSpeed: '',
      humidity: '',
      description: '',
      feelsLikeTemperature: '',
      dateTime: ''
    }
  },
  created() {
    this.getLocation();
  },
  methods: {
    async getLocation() {
      if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(this.getWeatherData);
      } else {
        console.error("Geolocation is not supported by this browser.");
      }
    },
    async getWeatherData(position) {
      console.log(position);
      try {
        const response = await axios.get(`http://localhost:8080/api/v1/weather/current/cords?latitude=${position.coords.latitude}&longitude=${position.coords.longitude}`);
        this.temperature = response.data.temperature;
        this.windSpeed = response.data.windSpeed;
        this.humidity = response.data.humidity;
        this.description = response.data.description;
        this.feelsLikeTemperature = response.data.feelsLikeTemperature;
        this.dateTime = response.data.dateTime;
        this.city = response.data.city;
      } catch (error) {
        console.error(error);
      }
    }
  }
}
</script>

<style scoped>

</style>