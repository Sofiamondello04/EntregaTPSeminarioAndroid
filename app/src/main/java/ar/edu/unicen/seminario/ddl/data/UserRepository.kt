package ar.edu.unicen.seminario.ddl.data

import ar.edu.unicen.seminario.ddl.models.User
import javax.inject.Inject

class UserRepository  @Inject constructor(
    private val userRemoteDataSourse: UserRemoteDataSourse
) {

    /*suspend fun getUser (
        return userRemoteDataSourse.getUser()
    ): User?*/

    suspend fun  getUsers (
        quantity: Int
    ): List<User>? {
        return userRemoteDataSourse.getUsers(quantity)

    }


}