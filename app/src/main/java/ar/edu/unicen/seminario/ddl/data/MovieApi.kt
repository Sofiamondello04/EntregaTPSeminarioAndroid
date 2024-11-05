package ar.edu.unicen.seminario.ddl.data

import ar.edu.unicen.seminario.ddl.data.dto.Constants
import ar.edu.unicen.seminario.ddl.data.dto.MovieDetailDto
import ar.edu.unicen.seminario.ddl.data.dto.PopularMoviesResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    /*@GET("movie/popular/")
    suspend fun  getPopularMovies(
        @Query("api_key") apiKey: String
    ): Response<MovieResultDto>*/

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("language") language: String = "en-US",
        @Query("page") page: Int = 1,
        @Query("api_key") apiKey: String = Constants.API_KEY
    ): Response<PopularMoviesResponseDto>

    @GET("movie/{movie_id}")
    suspend fun getMovie(
        @Path("movie_id") id: Int,
        @Query("api_key") apiKey: String = Constants.API_KEY
    ): Response<MovieDetailDto>



}

