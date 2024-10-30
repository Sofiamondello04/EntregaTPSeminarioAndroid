package ar.edu.unicen.seminario.ddl.data

import androidx.annotation.Keep
import ar.edu.unicen.seminario.ddl.models.ActivityRecomendation
import ar.edu.unicen.seminario.ddl.models.ActivityRecomendationInfo
import com.google.gson.annotations.SerializedName

@Keep
class ActivityRecomendationDto(
    @SerializedName("key")
    val id: String,
    @SerializedName("activity")
    val activity: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("participants")
    val participants: Int,
    @SerializedName("price")
    val price: Double,
    @SerializedName("link")
    val link: String,
    @SerializedName("accessibility")
    val accessibility: Double
) {

    fun toActivityRecomendation(): ActivityRecomendation {
        return ActivityRecomendation(
            id= id,
            activity = activity,
            info = ActivityRecomendationInfo(
                type= type,
                participants = participants,
                price = price,
                accessibility= accessibility,
                link= link


            )

        )
    }
}