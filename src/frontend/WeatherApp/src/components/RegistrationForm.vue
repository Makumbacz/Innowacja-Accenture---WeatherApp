<template>
  <div class="p-6">
    <h2 class="text-2xl font-bold mb-4">Register</h2>
    <form @submit.prevent="register">
      <div class="mb-4">
        <label class="block text-gray-700 font-bold mb-2" for="username">Username</label>
        <input v-model="username" type="text" name="username" id="username"
               class="border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"/>
      </div>
      <div class="mb-4">
        <label class="block text-gray-700 font-bold mb-2" for="email">Email</label>
        <input v-model="email" type="email" name="email" id="email"
               class="border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"/>
      </div>
      <div class="mb-4">
        <label class="block text-gray-700 font-bold mb-2" for="password">Password</label>
        <input v-model="password" type="password" name="password" id="password"
               class="border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"/>
      </div>
      <div class="flex items-center justify-between">
        <button type="submit"
                class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline">
          Register
        </button>
      </div>
    </form>
  </div>
</template>


<script setup>

import {reactive} from 'vue';
import {useAuthStore} from '@/stores/auth';

const user = reactive({
  username: '',
  password: '',
  email: '',
});

function onSubmit() {
  if (user.username !== '' && user.password !== '' && user.email !== '') {
    useAuthStore().register(user.username, user.password, user.email);
  }
}
</script>

<style scoped>

</style>