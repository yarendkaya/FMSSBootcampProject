package com.yarendemirkaya.domain.usecase

import com.yarendemirkaya.domain.model.SamplePerson
import com.yarendemirkaya.domain.repo.SampleRepository

class SavePersonUseCase(private val sampleRepository: SampleRepository) {
    suspend fun savePerson(person: SamplePerson) {
        sampleRepository.savePerson(person)
    }
}