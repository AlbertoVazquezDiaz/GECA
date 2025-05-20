<template>
  <v-dialog
      :model-value="visible"
      @update:model-value="emit('update:visible', $event)"
      max-width="800"
  >
    <v-card class="rounded-xl pa-4 pa-sm-6">
      <!-- Botón cerrar -->
      <v-btn icon class="position-absolute top-0 right-0 mt-2 mr-2" @click="emit('update:visible', false)">
        <v-icon>mdi-close</v-icon>
      </v-btn>

      <!-- Imagen del vehículo -->
      <v-img
          v-if="carro?.imagen"
          :src="carro.imagen"
          height="250"
          width="500"
          class="rounded mb-6 mx-auto"
          cover
      />


      <!-- Información principal -->
      <div class="text-center mb-6">
        <h2 class="text-h5 font-weight-bold mb-1">{{ carro?.marca }} {{ carro?.modelo }}</h2>
        <div class="text-subtitle-2 text-red-darken-2 font-weight-bold">
          Proveedor: {{ carro?.proveedor || 'No registrado' }}
        </div>
      </div>

      <!-- Información técnica -->
      <v-divider class="mb-6" />

      <v-row>
        <v-col cols="12" sm="6">
          <h3 class="text-subtitle-1 font-weight-medium mb-2">Características Generales</h3>
          <ul class="text-body-2 pl-4">
            <li><strong>Placa:</strong> {{ carro?.placa }}</li>
            <li><strong>Tipo:</strong> {{ carro?.tipo }}</li>
            <li><strong>Color:</strong>
              <span class="d-inline-flex align-center ml-1">
                <div
                    class="rounded-circle mr-2"
                    :style="{ backgroundColor: carro?.color, width: '12px', height: '12px' }"
                />
                {{ carro?.color }}
              </span>
            </li>
          </ul>
        </v-col>

        <v-col cols="12" sm="6">
          <h3 class="text-subtitle-1 font-weight-medium mb-2">Detalles Adicionales</h3>
          <p class="text-body-2 text-grey-darken-1">
            Aquí podrías colocar más información técnica o descripción del auto si lo deseas.
            Actualmente estos campos pueden venir del backend más adelante.
          </p>
        </v-col>
      </v-row>
    </v-card>
  </v-dialog>
</template>

<script setup>
const props = defineProps({
  carro: Object,
  visible: Boolean,
})
const emit = defineEmits(['update:visible'])
</script>

<style scoped>
ul {
  list-style-type: disc;
}
</style>
