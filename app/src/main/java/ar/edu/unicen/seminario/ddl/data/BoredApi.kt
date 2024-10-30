package ar.edu.unicen.seminario.ddl.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface BoredApi {

    @GET("activity")
    suspend fun  getRecomendation(
        @Query("participants")
        participants: Int
    ): Response<ActivityRecomendationDto>



}