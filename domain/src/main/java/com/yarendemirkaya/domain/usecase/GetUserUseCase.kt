package com.yarendemirkaya.domain.usecase

import com.yarendemirkaya.core.ResponseState
import com.yarendemirkaya.domain.model.SamplePerson
import com.yarendemirkaya.domain.repo.SampleRepository
import kotlinx.coroutines.flow.Flow


class GetUserUseCase(private val sampleRepository: SampleRepository) {
    suspend operator fun invoke(): Flow<ResponseState<List<SamplePerson>>> {
        return sampleRepository.getUsers()
    }
}

