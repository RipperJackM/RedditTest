package com.example.reddittest.utils

interface Mapper<E, M> {

    fun mapToEntity(model: M): E

    fun mapToModel(entity: E): M

    fun mapToEntity(models: List<M>) = models.map { mapToEntity(it) }

    fun mapToModel(entities: List<E>) = entities.map { mapToModel(it) }
}