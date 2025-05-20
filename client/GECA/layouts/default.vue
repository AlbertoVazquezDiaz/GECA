<template>
  <v-app>
    <!-- Navbar global -->
    <Navbar />

    <!-- Contenido principal -->
    <v-main class="bg-grey-lighten-4">
      <v-container
          class="py-6"
          style="max-width: 1280px"
      >
        <slot />
      </v-container>
    </v-main>
  </v-app>
</template>

<script setup>
import Navbar from '@/components/Navbar.vue'
</script>
