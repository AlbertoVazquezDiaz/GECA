const baseUrl = 'http://localhost:8080/api/cars'

export const useCarsApi = () => {
    const getAll = async () => {
        const { data, error } = await useFetch(baseUrl)
        if (error.value) throw new Error('Error al cargar autos')
        return data.value || []
    }

    const create = async (car) => {
        const { data, error } = await useFetch(baseUrl, {
            method: 'POST',
            body: car,
        })
        if (error.value) throw new Error('Error al guardar auto')
        return data.value
    }

    const update = async (id, car) => {
        const { data, error } = await useFetch(`${baseUrl}/${id}`, {
            method: 'PUT',
            body: car,
        })
        if (error.value) throw new Error('Error al actualizar auto')
        return data.value
    }

    const remove = async (id) => {
        const { error } = await useFetch(`${baseUrl}/${id}`, {
            method: 'DELETE',
        })
        if (error.value) throw new Error('Error al eliminar auto')
    }

    return { getAll, create, update, remove }
}
