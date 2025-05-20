<!-- components/AutoCard.vue -->
<template>
  <v-card
      class="car-card rounded-xl elevation-2"
      @click="emit('verDetalle')"
  >
    <!-- Botón eliminar -->
    <v-btn
        icon
        size="small"
        color="red"
        class="position-absolute top-0 right-0 mt-4 mr-4 z-10"
        @click.stop="emit('eliminar')"
    >
      <v-icon size="20">mdi-delete</v-icon>
    </v-btn>

    <!-- Imagen / ícono decorativo -->
    <v-container class="d-flex justify-center align-center grey-lighten-2" style="height: 160px;">
      <v-icon size="64">mdi-car</v-icon>
    </v-container>

    <!-- Info -->
    <v-card-text class="py-4">
      <div class="text-h6 font-weight-medium mb-1">
        {{ carro.brand }} {{ carro.model }}
      </div>
      <div class="text-caption text-grey-darken-1 mb-2">
        Placa: <strong>{{ carro.plate || carro.placa }}</strong>
      </div>
      <div class="d-flex justify-space-between align-center">
        <v-chip
            :color="carro.type === 'Hatchback' ? 'green-lighten-4' : 'blue-lighten-4'"
            :text-color="carro.type === 'Hatchback' ? 'green-darken-2' : 'blue-darken-2'"
            variant="elevated"
            size="small"
        >
          {{ carro.type }}
        </v-chip>
        <div class="d-flex align-center">
          <div
              class="rounded-circle mr-1"
              :style="{ backgroundColor: carro.color, width: '12px', height: '12px' }"
          />
          <span class="text-caption">{{ carro.color }}</span>
        </div>
      </div>
    </v-card-text>
  </v-card>

</template>

<script setup>
defineProps(['carro'])
const emit = defineEmits(['verDetalle'])
</script>

<style scoped>
.car-card {
  transition: transform 0.2s ease-in-out, box-shadow 0.2s ease-in-out;
  cursor: pointer;
}
.car-card:hover {
  transform: scale(1.015);
  box-shadow: 0 6px 24px rgba(0, 0, 0, 0.08);
}
</style>
