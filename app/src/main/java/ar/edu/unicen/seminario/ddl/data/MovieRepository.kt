package ar.edu.unicen.seminario.ddl.data

import ar.edu.unicen.seminario.ddl.models.Movie
import ar.edu.unicen.seminario.ddl.models.MovieDetail
import javax.inject.Inject

class MovieRepository  @Inject constructor(
    private val movieRemoteDataSourse: MovieRemoteDataSourse
) {


    suspend fun  getPopularMovies (

    ): List<Movie>? {
        return movieRemoteDataSourse.getPopularMovies()

    }

    suspend fun getMovie(
        id: Int
    ) : MovieDetail? {
        return movieRemoteDataSourse.getMovie(id)
    }


}