<template>
  <v-dialog :model-value="visible" max-width="500" @update:model-value="emit('update:visible', $event)">
    <v-card class="pa-4">
      <v-card-title class="text-h6 font-weight-bold">Agregar nuevo auto</v-card-title>
      <v-card-text>
        <v-text-field v-model="form.marca" label="Marca" outlined dense :error="submitted && !form.marca" />
        <v-text-field v-model="form.modelo" label="Modelo" outlined dense :error="submitted && !form.modelo" />
        <v-text-field v-model="form.placa" label="Placa" outlined dense :error="submitted && !form.placa" />
        <v-select
            v-model="form.tipo"
            :items="['Sedán', 'Hatchback']"
            label="Tipo"
            outlined
            dense
            :error="submitted && !form.tipo"
        />
        <v-text-field
            v-model="form.color"
            label="Color (ej. rojo, negro)"
            outlined
            dense
            :error="submitted && !form.color"
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
import { reactive, ref } from 'vue'

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
})

const submitted = ref(false)

const guardar = () => {
  submitted.value = true

  // Verificamos campos requeridos
  if (!form.marca || !form.modelo || !form.placa || !form.tipo || !form.color) return

  emit('agregar', { ...form })
  emit('update:visible', false)

  // Limpiar campos
  form.marca = ''
  form.modelo = ''
  form.placa = ''
  form.tipo = ''
  form.color = ''
  submitted.value = false
}
</script>
