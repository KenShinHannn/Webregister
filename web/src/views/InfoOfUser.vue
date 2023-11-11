<script setup>
import { ref, onMounted } from "vue";
import { getUser } from "../fetch/getInfo";
import { RouterLink } from "vue-router";

const infos = ref("");
onMounted(async () => {
  infos.value = await getUser();
});
const logout = ()=>{
    localStorage.removeItem("token")
}
</script>

<template>
  <div class="grid-container">
    <div class="center-container">
      <div class="m-auto ">
        <div
          class="border border-[#F0F0F0] mr-20 ml-20 p-5 w rounded-md bg-[#F0F0F0] shadow-lg">
          <div class="mb-3"></div>
          <div class="font-bold text-xl pb-1">Detail of User</div>
          <div><strong>Username:</strong> {{ infos.username }}</div>
          <div><strong>Name:</strong> {{ infos.name }}</div>
          <div><strong>Lastname:</strong> {{ infos.lastname }}</div>
          <div><strong>Email:</strong> {{ infos.email }}</div>
          <div><strong>Gender:</strong> {{ infos.gender }}</div>
          <div class="w-full flex flex-row mt-4"></div>
          
          
            <div @click="logout" class=" text-white flex justify-end">
                <RouterLink to="/" class="border bg-[#6464fc] py-1 px-2 rounded-md">
                    Logout
                </RouterLink>
            </div>
            
          
        </div>
      </div>
      
    </div>
    
  </div>
  <div class="user-profile"></div>
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
