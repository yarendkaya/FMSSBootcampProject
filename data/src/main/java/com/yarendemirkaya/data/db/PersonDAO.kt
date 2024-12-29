package com.yarendemirkaya.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.yarendemirkaya.data.db.entity.PersonEntity

@Dao
interface PersonDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: PersonEntity)

    @Query("SELECT * FROM user_table")
    suspend fun getAllUsers(): List<PersonEntity>

    @Delete
    suspend fun deleteUser(user: PersonEntity)
}