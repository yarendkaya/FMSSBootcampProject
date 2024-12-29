package com.yarendemirkaya.sample.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.yarendemirkaya.core.ResponseState
import com.yarendemirkaya.domain.model.SamplePerson
import com.yarendemirkaya.domain.usecase.GetUserUseCase
import com.yarendemirkaya.domain.usecase.SavePersonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SampleViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    private val savePersonUseCase: SavePersonUseCase
) : ViewModel() {

    private val _sampleState = MutableStateFlow(SampleState())
    val sampleState: StateFlow<SampleState> = _sampleState.asStateFlow()


    fun fetchUsers() {
        viewModelScope.launch {
            getUserUseCase().collect {
                when (it) {
                    is ResponseState.Loading -> {
                        _sampleState.value = SampleState(isLoading = true)
                    }

                    is ResponseState.Success -> {
                        _sampleState.value = SampleState(users = it.data)
                    }

                    is ResponseState.Error -> {
                        _sampleState.value = SampleState(error = it.message)
                    }
                }
            }
        }
    }

    fun savePerson(name: String, number: Int) {
        viewModelScope.launch {
            val person = SamplePerson(name = name, number = number, id = 0)
            savePersonUseCase.savePerson(person)
        }
    }

}

data class SampleState(
    val isLoading: Boolean = false, // ?
    val users: List<SamplePerson> = emptyList(),
    val error: String? = null
)
