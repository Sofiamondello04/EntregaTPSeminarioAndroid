package ar.edu.unicen.seminario.ddl.data.dto

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class PopularMoviesResponseDto(
    @SerializedName("results")
    val results: List<PopularMoviesDto>
)
