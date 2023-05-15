<template>
  <div class="p-6">
    <form class="bg-white p-8 rounded-md shadow-md" @submit.prevent="onSubmit">
      <div class="mb-4">
        <label class="block text-gray-700 text-sm font-bold mb-2" for="username">Username</label>
        <input v-model="user.username" type="text" name="username" id="username" placeholder="Username"
               class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"/>
        <div v-if="showErrors.value && user.username === ''" class="text-red-500 text-xs mt-1">Username is required</div>
      </div>
      <div class="mb-4">
        <label class="block text-gray-700 text-sm font-bold mb-2" for="email">Email</label>
        <input v-model="user.email" type="email" name="email" id="email" placeholder="Email"
               class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"/>
        <div v-if="showErrors.value && user.email === ''" class="text-red-500 text-xs mt-1">Email is required</div>
      </div>
      <div class="mb-4">
        <label class="block text-gray-700 text-sm font-bold mb-2" for="password">Password</label>
        <input v-model="user.password" type="password" name="password" id="password" placeholder="Password"
               class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"/>
        <div v-if="showErrors.value && user.password === ''" class="text-red-500 text-xs mt-1">Password is required</div>
      </div>
      <div class="flex items">
        <button
            class="bg-green-500 hover:bg-green-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline"
            type="submit">
          Sign In
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

const showErrors = reactive({
  value: false,
});

function onSubmit() {
  if (user.username !== '' && user.password !== '' && user.email !== '') {
    useAuthStore().register(user.username, user.password, user.email).catch(
        showErrors.value = true,
        user.username ='',
        user.password ='',
        user.email ='',
    );
  }
}
</script>

<style scoped>

</style>
