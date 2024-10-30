package ar.edu.unicen.seminario.ddl.data

import ar.edu.unicen.seminario.ddl.data.dto.UserDto
import ar.edu.unicen.seminario.ddl.data.dto.UserResultDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface UserApi {

   /* @GET("api/")
    suspend fun  getUsers(
        @Query("results")
        quantity: Int
    ): Response<List<UserDto>>*/

   @GET("")
   suspend fun  getUser(
   ): Response<UserResultDto>

    @GET("api/")
    suspend fun  getUsers(
        @Query("results")
        quantity: Int
    ): Response<UserResultDto>

}

