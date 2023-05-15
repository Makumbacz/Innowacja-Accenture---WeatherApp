<template>
  <div class="p-6">
  <form class="bg-white p-8 rounded-md shadow-md" @submit.prevent="onSubmit">
    <div class="mb-4">
      <label for="username" class="block text-gray-700 text-sm font-bold mb-2">Username</label>
      <input type="text" id="username" placeholder="Username" v-model="user.username"
             class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline"
             >
      <div v-if="showErrors.value && user.username === ''" class="text-red-500 text-xs mt-1">Username is required</div>
    </div>
    <div class="mb-6">
      <label for="password" class="block text-gray-700 text-sm font-bold mb-2">Password</label>
      <input type="password" id="password" placeholder="Password" v-model="user.password"
             class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 mb-3 leading-tight focus:outline-none focus:shadow-outline"
             >
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

import { reactive, ref } from 'vue';
import { useAuthStore } from '@/stores/auth';
const user = reactive({
  username: '',
  password: '',
});
const showErrors = reactive({
  value: false,
});

function onSubmit() {
  showErrors.value = true;

  if(user.username !== '' && user.password !== ''){
    useAuthStore().login(user.username, user.password).catch(
        user.username ='',
        user.password ='',
    );
  }
}
</script>

<style scoped>

</style>
