/*package ar.edu.unicen.seminario.old.BoredActivity.ddl.data

import ar.edu.unicen.seminario.old.BoredActivity.ddl.models.ActivityRecomendation
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class BoredRemoteDataSourse @Inject constructor(
    private val boredApi: BoredApi
) {
 /*aca agregar para devolver una clase con posibles errores- mapearlas- ver responde.errorBody*/
    suspend fun  getRecomendation (
        participants: Int
    ): ActivityRecomendation? {
        return withContext(Dispatchers.IO) {
            try {
                val response = boredApi.getRecomendation(participants)
                val activityRecomendation =response.body()?.toActivityRecomendation()
                return@withContext activityRecomendation
            }
            catch (e: Exception) {
                e.printStackTrace()
                return@withContext null
            }

        }
    }
}*/