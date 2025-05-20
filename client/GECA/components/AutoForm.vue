<template>
  <v-dialog :model-value="visible" @update:model-value="emit('update:visible', $event)" max-width="500">
    <v-card class="pa-4">
      <v-card-title class="text-h6 font-weight-bold">Agregar nuevo auto</v-card-title>
      <v-card-text>
        <v-text-field v-model="form.marca" label="Marca" outlined dense />
        <v-text-field v-model="form.modelo" label="Modelo" outlined dense />
        <v-text-field v-model="form.placa" label="Placa" outlined dense />
        <v-select
            v-model="form.tipo"
            :items="['Sedán', 'Hatchback']"
            label="Tipo"
            outlined
            dense
        />
        <v-text-field
            v-model="form.color"
            label="Color (ej. rojo, negro)"
            outlined
            dense
        />
        <v-text-field
            v-model="form.imagen"
            label="URL de imagen"
            outlined
            dense
        />
        <v-text-field
            v-model="form.proveedor"
            label="Proveedor"
            outlined
            dense
            class="mt-2"
        />
        <v-text-field
            v-model="form.detalles"
            label="Detalles adicionales"
            outlined
            dense
            class="mt-2"
        />

      </v-card-text>
      <v-card-actions>
        <v-spacer />
        <v-btn text @click="emit('update:visible', false)">Cancelar</v-btn>
        <v-btn color="primary" @click="guardar">Guardar</v-btn>
      </v-card-actions>
    </v-card>
  </v-dialog>
</template>

<script setup>
import { reactive } from 'vue'

const props = defineProps({
  visible: Boolean,
})
const emit = defineEmits(['update:visible', 'agregar'])

const form = reactive({
  marca: '',
  modelo: '',
  placa: '',
  tipo: '',
  color: '',
  imagen: '',
  proveedor: '',
  detalles: ''
})

const guardar = () => {
  emit('agregar', { ...form })
  emit('update:visible', false)
  // Limpiar
  form.marca = ''
  form.modelo = ''
  form.placa = ''
  form.tipo = ''
  form.color = ''
  form.imagen = ''
  form.proveedor = ''
  form.detalles = ''
}
</script>
