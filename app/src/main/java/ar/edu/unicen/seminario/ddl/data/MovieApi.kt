package ar.edu.unicen.seminario.ddl.data

import ar.edu.unicen.seminario.ddl.data.dto.ImageDto
import ar.edu.unicen.seminario.ddl.Constants
import ar.edu.unicen.seminario.ddl.data.dto.MovieDetailDto
import ar.edu.unicen.seminario.ddl.data.dto.PopularMoviesResponseDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("page") page: Int = 1,
       // @Query("api_key") apiKey: String = Constants.API_KEY
    ): Response<PopularMoviesResponseDto>

    @GET("movie/{movie_id}")
    suspend fun getMovie(
        @Path("movie_id") id: Int,
        //@Query("api_key") apiKey: String = Constants.API_KEY
    ): Response<MovieDetailDto>

    @GET("configuration")
    suspend fun getConfiguration(
      //  @Query("api_key") apiKey: String = Constants.API_KEY
    ): Response<ImageDto>



}

