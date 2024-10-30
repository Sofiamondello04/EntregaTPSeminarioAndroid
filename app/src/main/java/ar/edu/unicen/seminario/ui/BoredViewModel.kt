package ar.edu.unicen.seminario.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.unicen.seminario.ddl.data.BoredRepository
import ar.edu.unicen.seminario.ddl.models.ActivityRecomendation
import dagger.hilt.android.lifecycle.HiltViewModel

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class BoredViewModel @Inject constructor(
    private val boredRepository: BoredRepository
) : ViewModel() {

    private val _loading = MutableStateFlow(false)
    val loading= _loading.asStateFlow()

    private val _error = MutableStateFlow(false)
    val error= _error.asStateFlow()

    private val _recomendation = MutableStateFlow<ActivityRecomendation?>(null)
    val recomendation= _recomendation.asStateFlow()

    fun getRecomendation(
        participants: Int
    ) {
        viewModelScope.launch {
            _loading.value = true
            _error.value = false
            _recomendation.value = null

            val recomendation = boredRepository.getRecomendation(participants)

            _loading.value = false
            _recomendation.value = recomendation
            _error.value = recomendation == null

        }
    }


}


