package ar.edu.unicen.seminario.ddl.data.dto

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
class StreetDto(
    @SerializedName("number")
    val number: Int,
    @SerializedName("name")
    val name: String,



) {



}