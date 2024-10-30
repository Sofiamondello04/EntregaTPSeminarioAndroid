package ar.edu.unicen.seminario.ddl.models



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

