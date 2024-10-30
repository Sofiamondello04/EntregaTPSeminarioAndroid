package ar.edu.unicen.seminario.ddl.data

import ar.edu.unicen.seminario.ddl.models.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserRemoteDataSourse @Inject constructor(
    private val userApi: UserApi
) {
 /*aca agregar para devolver una clase con posibles errores- mapearlas- ver responde.errorBody*/
    suspend fun  getUsers (
        quantity: Int
    ): List<User>? {
        return withContext(Dispatchers.IO) {
            try {
                val response = userApi.getUsers(quantity)
                return@withContext response.body()?.results?.map {it.toUser()
            }
            }
            catch (e: Exception) {
                e.printStackTrace()
                return@withContext null
            }

        }
    }
}