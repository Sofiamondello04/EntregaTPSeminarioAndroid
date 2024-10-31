package ar.edu.unicen.seminario.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ar.edu.unicen.seminario.R
import ar.edu.unicen.seminario.databinding.ListItemMovieBinding

import ar.edu.unicen.seminario.ddl.models.Movie
import com.bumptech.glide.Glide


class MovieAdapter(
    private val movies: List<Movie>,
    private val onUserClick: (Movie) ->Unit
): RecyclerView.Adapter<MovieAdapter.UserViewHolder> () {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val layoutInflater=  LayoutInflater.from(parent.context)
        val binding= ListItemMovieBinding.inflate(layoutInflater, parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user= movies[position]
        holder.bind(user)
    }
    override fun getItemCount(): Int {
        return movies.size
    }

    inner class UserViewHolder(
        private val binding: ListItemMovieBinding
    ): RecyclerView.ViewHolder(binding.root) {

        fun bind(movie: Movie) {
            /*binding.userName.text= movie.name
            binding.userEmail.text= movie.email*/

            Glide.with(itemView.context)
                .load("https://image.tmdb.org/t/p/w500${movie.poster_path}") // Asegúrate de que la URL esté bien formada
                .into(binding.moviePoster)


            binding.root.setOnClickListener {
                onUserClick(movie)
            }
        }

    }
}



