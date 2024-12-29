package com.yarendemirkaya.data.repo

import com.yarendemirkaya.core.ResponseState
import com.yarendemirkaya.data.db.AppDatabase
import com.yarendemirkaya.data.db.entity.toDomainModel
import com.yarendemirkaya.data.db.entity.toEntityModel
import com.yarendemirkaya.domain.model.SamplePerson
import com.yarendemirkaya.domain.repo.SampleRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class SampleRepoImpl @Inject constructor(private val database: AppDatabase) : SampleRepository {
    override suspend fun getUsers(): Flow<ResponseState<List<SamplePerson>>> = flow {
        try {
            val users = database.personDao().getAllUsers().map { it.toDomainModel() }
            if (users.isNotEmpty()) {
                emit(ResponseState.Success(users))
            } else {
                emit(ResponseState.Error("No data found"))
            }
        } catch (e: Exception) {
            emit(ResponseState.Error(e.message ?: "An error occurred"))
        }
    }.flowOn(Dispatchers.IO)


    override suspend fun savePerson(person: SamplePerson) {
        database.personDao().insertUser(person.toEntityModel())
    }
}