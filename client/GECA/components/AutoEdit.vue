<template>
  <v-dialog :model-value="visible" @update:model-value="emit('update:visible', $event)" max-width="500">
    <v-card class="pa-4">
      <v-card-title class="text-h6 font-weight-bold">Editar auto</v-card-title>
      <v-card-text>
        <v-text-field v-model="form.brand" label="Marca" outlined dense />
        <v-text-field v-model="form.model" label="Modelo" outlined dense />
        <v-text-field v-model="form.numberCar" label="Placa" outlined dense disabled />
        <v-select
            v-model="form.type"
            :items="['Sedán', 'Hatchback']"
            label="Tipo"
            outlined
            dense
        />
        <v-text-field
            v-model="form.color"
            label="Color"
            outlined
            dense
        />
      </v-card-text>
      <v-card-actions>
        <v-spacer />
        <v-btn text @click="emit('update:visible', false)">Cancelar</v-btn>
        <v-btn color="primary" @click="guardar">Guardar cambios</v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>

<script setup>
import { reactive, watch } from 'vue'

const props = defineProps({
  visible: Boolean,
  carro: Object,
})
const emit = defineEmits(['update:visible', 'actualizar'])

const form = reactive({
  brand: '',
  model: '',
  numberCar: '',
  type: '',
  color: '',
})

// Cuando cambia el carro seleccionado, actualizamos el formulario
watch(
    () => props.carro,
    (carro) => {
      if (carro) {
        form.brand = carro.brand
        form.model = carro.model
        form.numberCar = carro.numberCar // ✅ correctamente asignado
        form.type = carro.type
        form.color = carro.color
      }
    },
    { immediate: true }
)

const guardar = () => {
  emit('actualizar', {
    brand: form.brand,
    model: form.model,
    type: form.type,
    color: form.color,
    numberCar: form.numberCar, // Se puede incluir aunque esté bloqueado
  })
  emit('update:visible', false)
}
</script>
