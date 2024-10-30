/*package ar.edu.unicen.seminario.old.BoredActivity.ddl.data

import ar.edu.unicen.seminario.old.BoredActivity.ddl.data.dto.ActivityRecomendationDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface BoredApi {

    @GET("activity")
    suspend fun  getRecomendation(
        @Query("participants")
        participants: Int
    ): Response<ActivityRecomendationDto>



}*/