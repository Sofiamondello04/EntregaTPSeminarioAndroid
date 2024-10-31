package ar.edu.unicen.seminario.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.unicen.seminario.BuildConfig
import ar.edu.unicen.seminario.ddl.data.MovieRepository
import ar.edu.unicen.seminario.ddl.models.Movie
import dagger.hilt.android.lifecycle.HiltViewModel

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class MovieViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {

    private val _loading = MutableStateFlow(false)
    val loading= _loading.asStateFlow()

    private val _error = MutableStateFlow(false)
    val error= _error.asStateFlow()

    private val _popularMovies = MutableStateFlow<List<Movie>?>(null)
    val popularMovies= _popularMovies.asStateFlow()

    fun getPopularMovies (){
        viewModelScope.launch {
            _loading.value = true
            _error.value = false
            _popularMovies.value = null


            val popularMovies = movieRepository.getPopularMovies()

            _loading.value = false

            if (popularMovies != null) {
                _popularMovies.value = popularMovies
            } else {
                _error.value = true
            }



        }
    }


}

