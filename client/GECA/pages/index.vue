<template>
  <v-app>
    <v-main>
      <v-container class="pa-4 bg-grey-lighten-4" fluid>
        <!-- Encabezado -->
        <div class="d-flex justify-space-between align-center mb-6">
          <div>
            <h1 class="text-h4 font-weight-bold">Catálogo de Autos</h1>
            <p class="text-subtitle-2 text-grey-darken-1">
              Explora y gestiona los vehículos registrados.
            </p>
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

        <!-- Grid de autos -->
        <v-row class="gy-6">
          <v-col
              v-for="(carro, index) in autos"
              :key="index"
              cols="12"
              sm="6"
              md="4"
              lg="3"
          >
            <AutoCard
                :carro="carro"
                @verDetalle="mostrarDetalle(carro)"
                @eliminar="eliminarAuto(carro)"
            />
          </v-col>
        </v-row>

        <!-- Botón flotante móvil -->
        <v-btn class="fab" icon color="primary" @click="modalAgregar = true">
          <v-icon>mdi-plus</v-icon>
        </v-btn>

        <!-- Modales -->
        <AutoDialog
            :carro="autoSeleccionado"
            :visible="modalDetalle"
            @update:visible="modalDetalle = $event"
            @editar="abrirEdicion"
        />

        <AutoForm
            :visible="modalAgregar"
            @update:visible="modalAgregar = $event"
            @agregar="guardarAuto"
        />
        <AutoEdit
            :visible="modalEditar"
            :carro="autoSeleccionado"
            @update:visible="modalEditar = $event"
            @actualizar="actualizarAuto"
        />
      </v-container>
    </v-main>
  </v-app>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import AutoCard from '/components/AutoCard.vue'
import AutoDialog from '/components/AutoDialog.vue'
import AutoForm from '/components/AutoForm.vue'
import AutoEdit from '/components/AutoEdit.vue'
import { useCarsApi } from '/composables/useCarsApi'

const autos = ref([])
const modalDetalle = ref(false)
const modalAgregar = ref(false)
const modalEditar = ref(false)
const autoSeleccionado = ref(null)

const api = useCarsApi()

const mostrarDetalle = (carro) => {
  autoSeleccionado.value = carro
  modalDetalle.value = true
}

const abrirEdicion = () => {
  modalDetalle.value = false
  modalEditar.value = true
}

const guardarAuto = async (nuevo) => {
  try {
    const creado = await api.create({
      brand: nuevo.marca,
      model: nuevo.modelo,
      type: nuevo.tipo,
      color: nuevo.color,
    })
    autos.value.push(creado)
  } catch (e) {
    console.error('Error al guardar auto:', e)
  }
}

const actualizarAuto = async (editado) => {
  try {
    const id = autoSeleccionado.value.id_car
    const actualizado = await api.update(id, {
      brand: editado.brand,
      model: editado.model,
      type: editado.type,
      color: editado.color,
    })

    // Actualizar en el array
    const idx = autos.value.findIndex(a => a.id_car === id)
    if (idx !== -1) autos.value[idx] = actualizado
  } catch (e) {
    console.error('Error al actualizar auto:', e)
  }
}

const eliminarAuto = async (carro) => {
  if (confirm(`¿Eliminar el auto ${carro.brand} ${carro.model}?`)) {
    try {
      await api.remove(carro.id_car)
      autos.value = autos.value.filter(a => a.id_car !== carro.id_car)
    } catch (e) {
      console.error('Error al eliminar auto:', e)
    }
  }
}

onMounted(async () => {
  try {
    autos.value = await api.getAll()
  } catch (e) {
    console.error('Error al cargar autos:', e)
  }
})
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
