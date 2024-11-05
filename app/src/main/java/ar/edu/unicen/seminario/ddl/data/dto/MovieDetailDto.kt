package ar.edu.unicen.seminario.ddl.data.dto

import androidx.annotation.Keep
import ar.edu.unicen.seminario.ddl.models.MovieDetail
import com.google.gson.annotations.SerializedName

@Keep
class MovieDetailDto(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("poster_path")
    val poster_path: String,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("genres")
    val genres: List<GenreDto>,
    @SerializedName("vote_average")
    val vote_average: Float

) {

    fun toMovieDetail(): MovieDetail {
        return MovieDetail(
            id = id,
            title = title,
            poster_path = poster_path,
            overview = overview,
            genres = listGenres(),
            vote_average =vote_average
        )
    }

    fun listGenres(): List<String>{
        val rdo: MutableList<String> = mutableListOf()
        for (it in genres){
            rdo.add(it.name)
        }
        return rdo
    }

}
