package ar.edu.unicen.seminario.ddl.data

import ar.edu.unicen.seminario.ddl.models.ActivityRecomendation
import javax.inject.Inject

class BoredRepository  @Inject constructor(
    private val boredRemoteDataSourse: BoredRemoteDataSourse
) {
    suspend fun  getRecomendation (
        participants: Int
    ): ActivityRecomendation? {
        return boredRemoteDataSourse.getRecomendation(participants)

    }
}