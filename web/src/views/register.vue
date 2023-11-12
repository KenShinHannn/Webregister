<script setup>
import { ref, onMounted } from "vue";
import { getUser } from "../fetch/getInfo";
import router from "../router";
import { RouterLink } from "vue-router";

const localhost = "http://localhost:8080";

const user_username = ref();
const user_password = ref();
const user_lastname = ref();
const user_confirm_password = ref();
const user_name = ref();
const user_email = ref();
const user_gender = ref();

const matchPass = ref(false);
const passwordValidate = ref(false);
const confirmPasswordValidate = ref(false);

const emailValidate = ref(false);
const uniqueUsername = ref(false);
const uniqueEmail = ref(false);
const nullOfUser = ref(false)
const nullOfGender = ref()

const registerUser = async () => {
  
  uniqueUsername.value = false;
  matchPass.value = false;
  passwordValidate.value = false;
  confirmPasswordValidate.value = false;
  emailValidate.value = false;
  uniqueEmail.value = false;
  nullOfUser.value = false
  nullOfGender.value = false

  try {
    const specialCharacter = /[!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?]+/;
    const uppercaseLetter = /[A-Z]/;
    const lowercaseLetter = /[a-z]/;
    const emailValidator = /^[a-zA-Z0-9]+(?:\.[a-zA-Z0-9]+)*@[a-zA-Z0-9]+(?:\.[a-zA-Z0-9]+)*$/;

    if(user_gender.value === undefined){
      nullOfGender.value = true
    }
    if(user_name.value === undefined){
      nullOfUser.value = true
    }
    if (!emailValidator.test(user_email.value)) {
      emailValidate.value = true;
    }
    if (
      !specialCharacter.test(user_password.value) ||
      !uppercaseLetter.test(user_password.value) ||
      !lowercaseLetter.test(user_password.value) ||
      user_password.value === undefined ||
      user_password.value.length < 8 ||
      user_password.length > 14 ||
      user_password.value === undefined
    ) {
      passwordValidate.value = true;
    }
    if (
      !specialCharacter.test(user_confirm_password.value) ||
      !uppercaseLetter.test(user_confirm_password.value) ||
      !lowercaseLetter.test(user_confirm_password.value) ||
      user_confirm_password.value === undefined ||
      user_confirm_password.value.length < 8 ||
      user_confirm_password.length > 14 ||
      user_confirm_password.value === undefined
    ) {
      confirmPasswordValidate.value = true;
    }

    if (user_password.value != user_confirm_password.value) {
      matchPass.value = true;
    }

    if (
      (specialCharacter.test(user_password.value) ||
        uppercaseLetter.test(user_password.value) ||
        lowercaseLetter.test(user_password.value) ||
        !user_password.value === undefined ||
        !user_password.value.length < 8 ||
        !user_password.length > 14 ||
        specialCharacter.test(user_confirm_password.value) ||
        uppercaseLetter.test(user_confirm_password.value) ||
        lowercaseLetter.test(user_confirm_password.value) ||
        !user_confirm_password.value === undefined ||
        !user_confirm_password.value.length < 8 ||
        !user_confirm_password.length > 14) &&
      user_password.value != user_confirm_password.value
    ) {
      matchPass.value = true;
    }

    const res = await fetch(`${localhost}/api/register`, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({
        username: user_username.value,
        password: user_password.value,
        name: user_name.value,
        lastname: user_lastname.value,
        email: user_email.value,
        gender: user_gender.value,
      }),
    }).then(async (res) => {
      uniqueUsername.value = false;
      uniqueEmail.value = false;
      if (res.status === 200) {
        router.push("/");
      } else {
        const errorMessage = await res.json();
        errorMessage.detail.forEach((error) => {

          if (error.field === "username") {
            return (uniqueUsername.value = true);
          }
          if (error.field === "email") {
            return (uniqueEmail.value = true);
          }
        });
      }
    });
  } catch (error) {
    console.log(`Error : ${error}`);
  }
};
</script>

<template>
  <div class="grid-container">
    <div class="center-container">
      <div class="m-auto">
        <div
          class="border border-[#F0F0F0] mr-20 ml-20 p-5 w rounded-md bg-[#F0F0F0] shadow-lg"
        >
          <div class="font-bold text-xl pb-1">Register Detail:</div>

          <div>
            Username: <span class="text-red-500">*</span
            ><span v-if="uniqueUsername" class=" text-red-500"
              >username does not unique</span>

            <input
              type="text"
              class="w-full form-control rounded-md bg-white shadow-lg pl-1"
              v-model.trim="user_username"
              minlength="1"
              maxlength="45"
              required
            />
          </div>
          <div>
            Password: <span class="text-red-500">*</span
            ><span
              v-if="passwordValidate"
              class="text-red-500"
              >must be 8-14 characters long, at least 1 of uppercase, lowercase,
              number and special characters </span
            ><span v-if="matchPass" class="text-red-500 "
              >The password DOES NOT match</span
            >
            <input
              type="password"
              class="w-full form-control rounded-md bg-white shadow-lg  pl-1"
              v-model.trim="user_password"
              minlength="8"
              maxlength="14"
              required
            />
          </div>
          <div>
            Confirm password:
            <span
              v-if="confirmPasswordValidate"
              class="text-red-500 "
              >must be 8-14 characters long, at least 1 of uppercase, lowercase,
              number and special characters</span
            >
            <input
              type="password"
              class="w-full form-control rounded-md bg-white shadow-lg  pl-1"
              v-model.trim="user_confirm_password"
              minlength="8"
              maxlength="14"
              required
            />
          </div>
          <div>
            Name: <span class="text-red-500">*</span>
            <span
              v-if="nullOfUser"
              class="text-red-500 "
              >name does not empty</span
            >
            <input
              type="text"
              class="w-full form-control rounded-md bg-white shadow-lg ann-name pl-1"
              v-model.trim="user_name"
              minlength="1"
              maxlength="100"
              required
            />
          </div>
          <div>
            Lastname:

            <input
              type="text"
              class="w-full form-control rounded-md bg-white shadow-lg ann-name pl-1"
              v-model.trim="user_lastname"
              minlength="1"
              maxlength="100"
              required
            />
          </div>
          <div>
            Email: <span class="text-red-500">*</span
            ><span v-if="uniqueEmail" class=" text-red-500"
              >email does not unique</span
            >
            <span v-if="emailValidate" class=" text-red-500"
              >email does not valid</span
            >
            <input
              type="email"
              class="w-full form-control rounded-md bg-white shadow-lg ann-email pl-1"
              v-model.trim="user_email"
              minlength="1"
              maxlength="150"
              required
            />
          </div>

          <div class="dropdown pt-5">
            <div class="">Gender: 
              <span v-if="nullOfGender" class=" text-red-500"
              >Gender does not empty</span></div>
            <div>
              <select
                v-model="user_gender"
                name="gender"
                id="gender"
                class="border border-black rounded-md bg-white shadow-lg ann-role"
              >
              
                <option value="Male">Male</option>
                <option value="Female">Female</option>
                <option value="Other">Other</option>
              </select>
            </div>
          </div>

          <div class="w-full flex flex-row mt-4 justify-between">
            <button
              @click="registerUser"
              class="btnSubmit border bg-[#6464fc] text-white rounded-md w-20 h-10 mr-5"
            >
              Register
            </button>

            <RouterLink
              to="/"
              class="border bg-red-500 text-white rounded-md w-20 h-10 mr-5 flex items-center justify-center"
            >
              cancel
            </RouterLink>
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
