package com.ccastro.maas.data.datasource

class RoomLocalDB<T> {

    private var table: MutableList<T> = mutableListOf()

    fun create(data: T){
        table.add(data)
    }

    fun has(element: T): Boolean{
        return element in table
    }

    fun read(attributeName: String, attributeValue: Any): T? {

        for (element in table) {
            val property = element!!::class.java.getDeclaredField(attributeName)
            property.isAccessible = true
            val value = property.get(element)
            if (value == attributeValue) {
                return element
            }
        }
        return null
    }

    fun readById(id: Int) : T?{
        return if(id <= table.size) table[id - 1] else null
    }

    fun getAll(): MutableList<T>{
        return table
    }

}