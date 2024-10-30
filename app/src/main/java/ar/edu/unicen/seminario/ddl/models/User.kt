package ar.edu.unicen.seminario.ddl.models

import ar.edu.unicen.seminario.ddl.data.dto.LocationDto
import ar.edu.unicen.seminario.ddl.data.dto.UserNameDto
import ar.edu.unicen.seminario.ddl.data.dto.UserPictureDto

class User(
    val name: String,
    val lastName: String,
    val email: String,
    val phone: String,
    val address: Address,
    val picture:Picture

) {

    val completeName= "$name $lastName"
}

