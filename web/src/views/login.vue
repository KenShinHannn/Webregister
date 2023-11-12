<script setup>
import { ref } from "vue";
import router from '../router';
import { RouterLink } from "vue-router";
const localhost = "http://localhost:8080"
const user_username = ref("");
const user_password = ref("");
const statuscode = ref("");

const login = async () => {
  try {
    const res = await fetch(`${localhost}/api/authenticate`, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({
        username: user_username.value,
        password: user_password.value,
      })
    });
    if (res.ok) {
        console.log("111");
      const data = await res.json();
      statuscode.value = data.status
      const token = data.token;
      localStorage.setItem('token', token);
      router.push("/user");
    } else {
      if (res.status === 404) {
        statuscode.value = 404
      }
      if (res.status === 401) {
        statuscode.value = 401
      }
    }
  } catch (err) {
    alert(`Unable to edit information  : this error ${err}`);
  }
}
</script>
 
<template>
    <div class="grid-container">
      <div class="center-container">
        <div class="m-auto">
          <div class="border border-[#F0F0F0] mr-20 ml-20 p-5 w rounded-md bg-[#F0F0F0] shadow-lg">
            <div class="mb-3">
            <div v-if="statuscode === 404" class=" text-red-700 border border-red-700 bg-red-300 rounded-md text-center">The specified username DOES NOT exist ❌</div>
            <div v-if="statuscode === 401" class=" text-red-700 border border-red-700 bg-red-300 rounded-md text-center">Password is incorrect ❌</div>
            <div v-if="statuscode === 200" class=" text-green-600 border border-green-700 bg-green-300 rounded-md text-center">Login success</div>
            </div>
            <div class="font-bold text-xl pb-1">Login</div>
            <div>
              Username:
              <input
                type="text"
                class="w-full form-control rounded-md bg-white shadow-lg  pl-1"
                v-model.trim="user_username"
                minlength="1"
                maxlength="45"
                required
              />
            </div>
            <div>Password:
             <input
                type="password"
                class="w-full form-control rounded-md bg-white shadow-lg  pl-1"
                v-model.trim="user_password"
                minlength="8"
                maxlength="14"
                required
              />
            </div>
            <div class="w-full flex flex-row mt-4">
              <button
                @click="login"
                class=" border bg-[#3939fa] text-white rounded-md w-20 h-10 mr-5  "
              >
                Login
              </button>
              <div>
                If you don't have a account <br>
                you can <RouterLink to="/reg" class="text-blue-500 underline">register</RouterLink>  here!
              </div>
             
            </div>
          </div>
        </div>
      </div>
    </div>
  </template>
  
  <style scoped>
  .center-container {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
  }
  .m-auto {
    margin: auto;
  }
  </style>