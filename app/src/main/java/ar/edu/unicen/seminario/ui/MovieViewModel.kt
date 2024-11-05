package ar.edu.unicen.seminario.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ar.edu.unicen.seminario.ddl.data.MovieRepository
import ar.edu.unicen.seminario.ddl.models.Image
import ar.edu.unicen.seminario.ddl.models.Movie
import ar.edu.unicen.seminario.ddl.models.MovieDetail
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

    private val _movieDetails = MutableStateFlow<MovieDetail?>(null)
    val movieDetails= _movieDetails.asStateFlow()

    private val _image = MutableStateFlow<Image?>(null)
    val image = _image.asStateFlow()

    private val _imageLoadingState = MutableStateFlow<Boolean>(false)  // Estado de carga
    val imageLoadingState=  _imageLoadingState.asStateFlow()

    private val _imageError = MutableStateFlow<Boolean>(false)  // Estado de error
    val imageError = _imageError.asStateFlow()

    fun getPopularMovies() {
        _loading.value = true
        _error.value = false

        viewModelScope.launch {
            try {
                val response = movieRepository.getPopularMovies()
                _popularMovies.value = response
                _loading.value = false
            } catch (e: Exception) {
                _error.value = true
                _loading.value = false
            }
        }
    }

    fun getMovieDetail(id: Int) {
        _loading.value = true
        _error.value = false

        viewModelScope.launch {
            try {
                val response = movieRepository.getMovie(id)
                _movieDetails.value = response
                _loading.value = false
            } catch (e: Exception) {
                _error.value = true
                _loading.value = false
            }
        }
    }

    fun getImage() {
        viewModelScope.launch {
            _imageLoadingState.value = true
            try {
                val response = movieRepository.getImage()

                if (response != null) {
                    _image.value = response
                    _imageError.value = false
                } else {
                    _image.value = null
                    _imageError.value = true
                }
            } catch (e: Exception) {
                _image.value = null
                _imageError.value = true
            } finally {
                _imageLoadingState.value = false
            }
        }
    }

}


