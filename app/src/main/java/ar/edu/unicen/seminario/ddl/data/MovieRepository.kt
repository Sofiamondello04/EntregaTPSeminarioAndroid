package ar.edu.unicen.seminario.ddl.data

import ar.edu.unicen.seminario.ddl.models.Movie
import javax.inject.Inject

class MovieRepository  @Inject constructor(
    private val movieRemoteDataSourse: MovieRemoteDataSourse
) {


    suspend fun  getPopularMovies (

    ): List<Movie>? {
        return movieRemoteDataSourse.getPopularMovies()

    }


}