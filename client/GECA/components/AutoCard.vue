<!-- components/AutoCard.vue -->
<template>
  <v-card
      class="car-card rounded-xl elevation-2"
      @click="emit('verDetalle')"
  >
    <v-img :src="carro.imagen" height="160" class="rounded-t-xl" cover />
    <v-card-text class="py-4">
      <div class="text-h6 font-weight-medium mb-1">
        {{ carro.marca }} {{ carro.modelo }}
      </div>
      <div class="text-caption text-grey-darken-1 mb-2">
        Placa: <strong>{{ carro.placa }}</strong>
      </div>
      <div class="d-flex justify-space-between align-center">
        <v-chip
            :color="carro.tipo === 'Hatchback' ? 'green-lighten-4' : 'blue-lighten-4'"
            :text-color="carro.tipo === 'Hatchback' ? 'green-darken-2' : 'blue-darken-2'"
            variant="elevated"
            size="small"
        >
          {{ carro.tipo }}
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
