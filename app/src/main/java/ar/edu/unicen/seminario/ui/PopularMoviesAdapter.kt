package ar.edu.unicen.seminario.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ar.edu.unicen.seminario.databinding.ActivityMovieDetailBinding
import ar.edu.unicen.seminario.databinding.ListItemMovieBinding

import ar.edu.unicen.seminario.ddl.models.Movie
import com.bumptech.glide.Glide


class PopularMoviesAdapter(
    private val movies: List<Movie>,
    private val onUserClick: (Movie) -> Unit
): RecyclerView.Adapter<PopularMoviesAdapter.MovieViewHolder> () {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val layoutInflater=  LayoutInflater.from(parent.context)
        val binding= ListItemMovieBinding.inflate(layoutInflater, parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie= movies[position]
        holder.bind(movie)
    }
    override fun getItemCount(): Int {
        return movies.size
    }

    inner class MovieViewHolder(
        private val binding: ListItemMovieBinding
    ): RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie) {

            binding.movieTitle.text = movie.title
           // binding.movieOverview.text = movie.overview
            Glide.with(itemView.context)
                .load("https://image.tmdb.org/t/p/w500${movie.poster_path}")
                .into(binding.moviePoster)


            binding.root.setOnClickListener {
                onUserClick(movie) // Pasar el ID de la película
            }
        }

    }
}






