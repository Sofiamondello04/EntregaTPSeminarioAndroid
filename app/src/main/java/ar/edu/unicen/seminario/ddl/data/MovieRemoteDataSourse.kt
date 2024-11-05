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
                if (response.isSuccessful) {
                    return@withContext response.body()?.results?.map {it.toMovie()}?: emptyList()
                } else {
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
                val response = movieApi.getConfiguration()
                if (response.isSuccessful) {
                    return@withContext response.body()?.images
                } else {
                  return@withContext null
                }
            } catch (e: Exception) {
                e.printStackTrace()
                return@withContext null
            }
        }
    }
}