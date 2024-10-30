package ar.edu.unicen.seminario.ddl.data.dto

import androidx.annotation.Keep
import ar.edu.unicen.seminario.ddl.models.User
import com.google.gson.annotations.SerializedName

@Keep
class UserDto(
    @SerializedName("name")
    val name: UserNameDto,
    @SerializedName("email")
    val email: String,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("location")
    val location: LocationDto,
    @SerializedName("picture")
    val picture: UserPictureDto

) {

    fun toUser(): User {
        return User(
            name= name.first,
            lastName= name.last,
            email= email,
            phone= phone,
            address= location.toAddress(),
            picture= picture.toPicture()
            )


    }
}