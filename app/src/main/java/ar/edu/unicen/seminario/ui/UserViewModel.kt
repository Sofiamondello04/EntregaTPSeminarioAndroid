package ar.edu.unicen.seminario.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.unicen.seminario.ddl.data.UserRepository
import ar.edu.unicen.seminario.ddl.models.User
import dagger.hilt.android.lifecycle.HiltViewModel

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepository: UserRepository
) : ViewModel() {

    private val _loading = MutableStateFlow(false)
    val loading= _loading.asStateFlow()

    private val _error = MutableStateFlow(false)
    val error= _error.asStateFlow()

    private val _users = MutableStateFlow<List<User>?>(null)
    val users= _users.asStateFlow()

    fun getUsers(
        quantity: Int
    ) {
        viewModelScope.launch {
            _loading.value = true
            _error.value = false
            _users.value = null

            val users = userRepository.getUsers(quantity)

            _loading.value = false
            _users.value = users
            _error.value = users == null

        }
    }


}


