package ar.edu.unicen.seminario.ddl.data.dto

import androidx.annotation.Keep
import ar.edu.unicen.seminario.ddl.models.Address
import com.google.gson.annotations.SerializedName

@Keep
class LocationDto(
    @SerializedName("street")
    val street: StreetDto,
    @SerializedName("city")
    val city: String,
    @SerializedName("state")
    val state: String,
    @SerializedName("country")
    val country: String,


) {

    fun toAddress(): Address {
        return Address (
            street= street.name,
            streetNumber= street.number,
            city=city,
            state= state,
            country= country
            )


    }
}