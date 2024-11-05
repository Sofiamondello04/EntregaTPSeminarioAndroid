package ar.edu.unicen.seminario.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import ar.edu.unicen.seminario.databinding.ActivityMovieDetailBinding
import com.bumptech.glide.Glide

import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.launchIn


@AndroidEntryPoint
class MovieDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMovieDetailBinding
    private val viewModel by viewModels<MovieViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = intent.getIntExtra("id", -1)
        Log.d("MovieDetailActivity", "ID recibido: $id")  // Log para verificar el ID

        if (id != -1) {
            viewModel.getMovieDetail(id)
            observeMovieDetails()
        }


        binding.backButton.setOnClickListener {
            val intent = Intent(this, PopularMoviesActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            startActivity(intent)
        }
    }
    private fun observeMovieDetails() {
        viewModel.movieDetails.onEach { movie ->
            if (movie != null) {
                binding.movieTitle.text = movie.title
                binding.movieOverview.text = movie.overview
                Glide.with(this)
                    .load("https://image.tmdb.org/t/p/w500${movie.poster_path}")
                    .into(binding.moviePoster)
            } else {
                Toast.makeText(this, "No se encontraron detalles de la película", Toast.LENGTH_SHORT).show()
            }
        }.launchIn(lifecycleScope)
    }
    }










