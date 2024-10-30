package ar.edu.unicen.seminario.ddl.data.dto

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
class UserNameDto(
    @SerializedName("first")
    val first: String,
    @SerializedName("last")
    val last: String,


) {

}