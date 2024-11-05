package ar.edu.unicen.seminario.ui

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ar.edu.unicen.seminario.databinding.ActivityMovieDetailBinding
import ar.edu.unicen.seminario.databinding.ListItemMovieBinding

import ar.edu.unicen.seminario.ddl.models.Movie
import com.bumptech.glide.Glide


class MovieDetailAdapter(
    private val movies: List<Movie>
) : RecyclerView.Adapter<MovieDetailAdapter.MovieViewHolder>() {

    private var listener: ((Movie) -> Unit)? = null

    fun setOnItemClickListener(listener: (Movie) -> Unit) {
        this.listener = listener
    }

    inner class MovieViewHolder(private val binding: ActivityMovieDetailBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            binding.movieTitle.text = movie.title
            binding.root.setOnClickListener {
                listener?.invoke(movie) // Llama al listener cuando se hace clic
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ActivityMovieDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount(): Int = movies.size
}




