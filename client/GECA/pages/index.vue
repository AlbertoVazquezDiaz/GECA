<template>
  <v-app>
    <v-main>
      <v-container class="pa-4 bg-grey-lighten-4" fluid>
        <div class="d-flex justify-space-between align-center mb-6">
          <div>
            <h1 class="text-h4 font-weight-bold">Catálogo de Autos</h1>
            <p class="text-subtitle-2 text-grey-darken-1">Explora y gestiona los vehículos registrados.</p>
          </div>
          <v-btn
              color="primary"
              prepend-icon="mdi-plus"
              class="hidden-sm-and-down"
              @click="modalAgregar = true"
          >
            Agregar auto
          </v-btn>
        </div>

        <v-row class="gy-6">
          <v-col
              v-for="(carro, index) in autos"
              :key="index"
              cols="12"
              sm="6"
              md="4"
              lg="3"
          >
            <AutoCard :carro="carro" @verDetalle="mostrarDetalle(carro)" />
          </v-col>
        </v-row>

        <!-- FAB en móviles -->
        <v-btn
            class="fab"
            icon
            color="primary"
            @click="modalAgregar = true"
        >
          <v-icon>mdi-plus</v-icon>
        </v-btn>

        <!-- Modales -->
        <AutoDialog
            :carro="autoSeleccionado"
            :visible="modalDetalle"
            @update:visible="modalDetalle = $event"
        />
        <AutoForm
            :visible="modalAgregar"
            @update:visible="modalAgregar = $event"
            @agregar="guardarAuto"
        />
      </v-container>
    </v-main>
  </v-app>
</template>

<script setup>
import { ref } from 'vue'
import AutoCard from '@/components/AutoCard.vue'
import AutoDialog from '@/components/AutoDialog.vue'
import AutoForm from '@/components/AutoForm.vue'

const autos = ref([
  {
    marca: 'Toyota',
    modelo: 'Corolla',
    placa: 'A123BC',
    tipo: 'Sedán',
    color: 'Rojo',
    imagen: 'https://www.toyota.mx/adobe/dynamicmedia/deliver/dm-aid--cec02355-ea6c-4f4c-9a4c-f79bdb39184e/corolla-hev-version-v2.png?preferwebp=true&quality=85',
  },
])

const modalDetalle = ref(false)
const modalAgregar = ref(false)
const autoSeleccionado = ref(null)

const mostrarDetalle = (carro) => {
  autoSeleccionado.value = carro
  modalDetalle.value = true
}

const guardarAuto = (nuevo) => {
  autos.value.push(nuevo)
}
</script>

<style scoped>
.fab {
  position: fixed;
  bottom: 24px;
  right: 24px;
  z-index: 999;
  display: none;
}
@media (max-width: 768px) {
  .fab {
    display: inline-flex;
  }
}
</style>
