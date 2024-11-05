package ar.edu.unicen.seminario.ddl.data

import android.util.Log
import ar.edu.unicen.seminario.BuildConfig
import ar.edu.unicen.seminario.ddl.models.Image
import ar.edu.unicen.seminario.ddl.models.Movie
import ar.edu.unicen.seminario.ddl.models.MovieDetail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieRemoteDataSourse @Inject constructor(
    private val movieApi: MovieApi
) {

    suspend fun  getPopularMovies (

    ): List<Movie>? {
        return withContext(Dispatchers.IO) {
            try {

                val response = movieApi.getPopularMovies()
                Log.e("MovieApi", "Error: ${response.code()} - ${response.errorBody()?.string()}")

                if (response.isSuccessful) {
                    // Procesa la respuesta aquí
                    // return@withContext response.body()?.results?.map {it.toMovie()
                    return@withContext response.body()?.results?.map {it.toMovie()}?: emptyList()
                      //  Log.d("MovieRepository", "Movie ID: ${it.id}, Title: ${it.title}, Poster Path: ${it.poster_path}")

                } else {
                    Log.e("MovieRemoteDataSourse", "Error: ${response.code()} - ${response.message()}")
                    return@withContext null
                }
            }
            catch (e: Exception) {

                e.printStackTrace()
                return@withContext null
            }

        }
    }

    suspend fun  getMovie (
        id: Int
    ): MovieDetail? {
        return withContext(Dispatchers.IO) {
            try {

                val response = movieApi.getMovie(id)
                return@withContext response.body()?.toMovieDetail()
            }
            catch (e: Exception) {
                e.printStackTrace()
                return@withContext null
            }

        }
    }

    suspend fun getImage(

    ): Image? {
        return withContext(Dispatchers.IO) {
            try {
                // Llamada a la API de configuración para obtener la base_url y el tamaño de la imagen

                // Log para verificar que estamos llamando al endpoint correctamente
                Log.d("MovieRemoteDataSource", "Llamando al endpoint de configuración para obtener imágenes.")

                val response = movieApi.getConfiguration()
                if (response.isSuccessful) {
                    Log.d("MovieRemoteDataSource", "Configuración de imagen obtenida exitosamente: ${response.body()}")
                    return@withContext response.body()?.images
                } else {
                    Log.e("MovieRemoteDataSource", "Error: ${response.code()} - ${response.message()}")
                    return@withContext null
                }
            } catch (e: Exception) {
                e.printStackTrace()
                return@withContext null
            }
        }
    }
}