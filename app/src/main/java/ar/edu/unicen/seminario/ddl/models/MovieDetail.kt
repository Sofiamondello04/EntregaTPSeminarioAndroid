package ar.edu.unicen.seminario.ddl.models


data class MovieDetail(
    val id: Int,
    val title: String,
    val poster_path: String,
    val overview: String,
    val genres: List<String>, // Lista de géneros con id y name
    val vote_average: Float
) {


}

