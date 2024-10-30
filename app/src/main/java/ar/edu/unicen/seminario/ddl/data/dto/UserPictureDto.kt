package ar.edu.unicen.seminario.ddl.data.dto

import androidx.annotation.Keep
import ar.edu.unicen.seminario.ddl.models.Picture
import com.google.gson.annotations.SerializedName

@Keep
class UserPictureDto(
    @SerializedName("large")
    val large: String,
    @SerializedName("medium")
    val medium: String,
    @SerializedName("thumbnail")
    val thumbnail: String

) {

    fun toPicture(): Picture {
        return Picture(
            large = large,
            medium = medium,
            thumbnail = thumbnail
            )


    }
}