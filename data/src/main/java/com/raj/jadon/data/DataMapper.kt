package com.raj.jadon.data

interface DataMapper<Entity, Dao> {
    suspend fun mapToDao(entity: Entity): Dao
}