package ar.edu.unicen.seminario.ddl.models

import ar.edu.unicen.seminario.ddl.data.dto.LocationDto
import ar.edu.unicen.seminario.ddl.data.dto.UserNameDto
import ar.edu.unicen.seminario.ddl.data.dto.UserPictureDto

class Address(
    val street: String,
    val streetNumber: Int,
    val city: String,
    val state: String,
    val country: String


) {
}

