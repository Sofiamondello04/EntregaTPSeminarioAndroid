package ar.edu.unicen.seminario.ddl.data

import android.util.Log
import ar.edu.unicen.seminario.BuildConfig
import ar.edu.unicen.seminario.ddl.models.Movie
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
                    return@withContext response.body()?.results?.map {
                        Log.d("MovieRepository", "Movie ID: ${it.id}, Title: ${it.title}, Overview: ${it.overview}, Poster Path: ${it.poster_path}")
                        Movie(
                            id = it.id,  // Asegúrate de que el id es correcto
                            title = it.title,
                            overview = it.overview,
                            poster_path = it.poster_path
                        )

                    }?: emptyList()

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
    ): Movie? {
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
}