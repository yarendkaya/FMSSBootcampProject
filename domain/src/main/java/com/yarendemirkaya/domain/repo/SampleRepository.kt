package com.yarendemirkaya.domain.repo

import com.yarendemirkaya.core.ResponseState
import com.yarendemirkaya.domain.model.SamplePerson
import kotlinx.coroutines.flow.Flow

interface SampleRepository {
    suspend fun getUsers(): Flow<ResponseState<List<SamplePerson>>>
    suspend fun savePerson(person: SamplePerson)
}