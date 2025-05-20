<template>
  <v-app>
    <v-main class="pa-4 grey-lighten-4">
      <div class="d-flex justify-space-between align-center mb-4">
        <h1 class="text-h5 font-weight-bold">Vehículos Registrados</h1>
        <v-btn color="primary" prepend-icon="mdi-plus" @click="abrirFormulario">
          Agregar Auto
        </v-btn>
      </div>

      <v-row dense>
        <v-col
            v-for="(carro, index) in autos"
            :key="index"
            cols="12"
            sm="6"
            md="4"
            lg="3"
        >
          <v-card class="elevation-3 rounded-xl card-auto">
            <v-img
                :src="carro.imagen"
                height="160"
                class="rounded-t-xl"
                cover
            ></v-img>

            <v-card-text>
              <h3 class="text-subtitle-1 font-weight-bold">{{ carro.marca }} {{ carro.modelo }}</h3>
              <p class="text-body-2 mb-1">Placa: <strong>{{ carro.placa }}</strong></p>
              <p class="text-body-2 mb-1">Tipo: <strong>{{ carro.tipo }}</strong></p>
              <p class="text-body-2">Color: <span :style="{ color: carro.color }">{{ carro.color }}</span></p>
            </v-card-text>
          </v-card>
        </v-col>
      </v-row>

      <!-- Botón flotante para pantallas chicas -->
      <v-btn
          class="fab"
          color="primary"
          icon="mdi-plus"
          @click="abrirFormulario"
      ></v-btn>

      <!-- Modal de formulario -->
      <v-dialog v-model="modal" max-width="500">
        <v-card class="pa-4">
          <v-card-title>Agregar nuevo auto</v-card-title>
          <v-card-text>
            <v-text-field v-model="nuevoAuto.marca" label="Marca" outlined />
            <v-text-field v-model="nuevoAuto.modelo" label="Modelo" outlined />
            <v-text-field v-model="nuevoAuto.placa" label="Placa" outlined />
            <v-select
                v-model="nuevoAuto.tipo"
                :items="['Sedán', 'Hatchback']"
                label="Tipo"
                outlined
            />
            <v-color-picker
                v-model="nuevoAuto.color"
                show-swatches
                hide-canvas
                label="Color"
                class="mt-2"
            />
            <v-text-field v-model="nuevoAuto.imagen" label="URL de imagen" outlined />
          </v-card-text>
          <v-card-actions>
            <v-spacer />
            <v-btn text @click="modal = false">Cancelar</v-btn>
            <v-btn color="primary" @click="guardarAuto">Guardar</v-btn>
          </v-card-actions>
        </v-card>
      </v-dialog>
    </v-main>
  </v-app>
</template>

<script setup>
import { ref } from 'vue'

const autos = ref([
  {
    marca: 'Nissan',
    modelo: 'Versa',
    placa: 'ABC-123',
    tipo: 'Sedán',
    color: '#1E88E5',
    imagen: 'https://images.unsplash.com/photo-1606166325683-e813d7c5741c?auto=format&fit=crop&w=800&q=80',
  },
  {
    marca: 'Chevrolet',
    modelo: 'Aveo',
    placa: 'XYZ-456',
    tipo: 'Hatchback',
    color: '#43A047',
    imagen: 'https://images.unsplash.com/photo-1609921212023-bad0b9a901d6?auto=format&fit=crop&w=800&q=80',
  },
])

const modal = ref(false)
const nuevoAuto = ref({
  marca: '',
  modelo: '',
  placa: '',
  tipo: '',
  color: '#000000',
  imagen: '',
})

const abrirFormulario = () => {
  modal.value = true
}

const guardarAuto = () => {
  autos.value.push({ ...nuevoAuto.value })
  modal.value = false
  // Limpiar formulario
  nuevoAuto.value = {
    marca: '',
    modelo: '',
    placa: '',
    tipo: '',
    color: '#000000',
    imagen: '',
  }
}
</script>

<style scoped>
.card-auto {
  transition: 0.3s ease;
}
.card-auto:hover {
  transform: scale(1.01);
  box-shadow: 0px 4px 20px rgba(0, 0, 0, 0.1);
}

.fab {
  position: fixed;
  bottom: 24px;
  right: 24px;
  z-index: 100;
  display: none;
}

@media (max-width: 600px) {
  .fab {
    display: inline-flex;
  }
}
</style>