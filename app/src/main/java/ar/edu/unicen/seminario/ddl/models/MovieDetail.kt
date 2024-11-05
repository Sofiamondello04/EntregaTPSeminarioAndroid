package ar.edu.unicen.seminario.ddl.models


data class MovieDetail(
    val id: Int,
    val title: String,
    val backdrop_path: String,
    val overview: String,
    val genres: List<String>,
    val vote_average: Float
) {


}

