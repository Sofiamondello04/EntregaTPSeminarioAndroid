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

    fun getMovieDetail(id: Int) {
        viewModelScope.launch {
            _loading.value = true
            _error.value = false
            _movieDetails.value = null

            val movieDetails = movieRepository.getMovie(id)

            _loading.value = false

            if (movieDetails != null) {
                Log.d("MovieDetail", "Detalles de la película: $movieDetails")
                _movieDetails.value = movieDetails
                Log.d("MovieDetail", "Detalles de la película: $movieDetails")
            } else {
                _error.value = true
                Log.e("MovieDetail", "Error al obtener los detalles de la película")
            }

        }

    }

    fun getImage() {
        viewModelScope.launch {
            try {

                Log.d("MovieViewModel", "Iniciando la obtención de la configuración de imagen.")
                val image = movieRepository.getImage()
                if (image != null) {
                    Log.d("MovieViewModel", "Configuración de imagen obtenida: $image")
                    _image.value = image
                } else {
                    Log.e("MovieViewModel", "No se pudo obtener la configuración de imagen.")
                }



            } catch (e: Exception) {
                // Maneja el error aquí
                Log.e("MovieViewModel", "Error fetching configuration", e)
            }
        }
    }


}


