package com.yarendemirkaya.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.yarendemirkaya.domain.model.SamplePerson

@Entity(tableName = "user_table")
data class PersonEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val number: Int
)

fun PersonEntity.toDomainModel(): SamplePerson {
    return SamplePerson(id = this.id, name = this.name, number = this.number)
}

fun SamplePerson.toEntityModel(): PersonEntity {
    return PersonEntity(id = this.id, name = this.name, number = this.number)
}