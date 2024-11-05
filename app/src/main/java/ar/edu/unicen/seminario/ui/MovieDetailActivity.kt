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

            showLoading()


            viewModel.getMovieDetail(id)
            viewModel.getImage()
            observeMovieDetails()
            observeImage()

            observeError()
        }


        binding.backButton.setOnClickListener {
            val intent = Intent(this, PopularMoviesActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP
            startActivity(intent)
        }

        binding.retryButton.setOnClickListener {
            if (id != -1) {
                // Reintentar cargar los detalles de la película
                showLoading()
                viewModel.getMovieDetail(id)
                viewModel.getImage()
            }
        }
    }
    private fun observeMovieDetails() {
        viewModel.movieDetails.onEach { movie ->
            if (movie != null) {

                showLoading()


                binding.movieTitle.text = movie.title
                binding.movieOverview.text = movie.overview
                binding.movieGenres.text = movie.genres.joinToString(", ")
                binding.movieVoteAverage.text = movie.vote_average.toString()


               /* val posterUrl = "https://image.tmdb.org/t/p/w500${movie.poster_path}"


                Glide.with(this)
                    .load(posterUrl)
                    .into(binding.moviePoster)*/
            }
        }.launchIn(lifecycleScope)
    }

    private fun observeImage() {
        viewModel.image.onEach { config ->
            config?.let {
                // Asegúrate de que los valores estén disponibles
                val baseUrl = it.base_url.replace("http://", "https://")
                val posterSize = it.poster_sizes?.firstOrNull() ?: "w500"  // Si no tienes el tamaño, usa uno por defecto

                // Ahora construimos la URL completa para el póster
                val movieDetail = viewModel.movieDetails.value
                movieDetail?.let { movie ->
                    val posterPath = movie.poster_path
                    val posterUrl = "$baseUrl/t/p/$posterSize$posterPath"

                    Log.d("MovieDetailActivity", "URL del póster: $posterUrl")

                    // Cargar la imagen con Glide
                    Glide.with(this)
                        .load(posterUrl)
                        .into(binding.moviePoster)
                }
            }
        }.launchIn(lifecycleScope)
    }

    private fun observeError() {
        viewModel.error.onEach { error ->
            if (error) {
                // Mostrar vista de error y botón de reintento
                binding.errorView.visibility = android.view.View.VISIBLE
                binding.retryButton.visibility = android.view.View.VISIBLE
            } else {
                // Ocultar vista de error cuando la carga sea exitosa
                binding.errorView.visibility = android.view.View.INVISIBLE
                binding.retryButton.visibility = android.view.View.INVISIBLE
            }
        }.launchIn(lifecycleScope)
    }

   /* private fun showLoading(isLoading: Boolean) {
        // Mostrar u ocultar el indicador de carga
        binding.progressBar.visibility = if (isLoading) android.view.View.VISIBLE else android.view.View.INVISIBLE
    }*/

    private fun showLoading() {
        viewModel.loading.onEach { loading ->
            if (loading) {
                binding.progressBar.visibility = android.view.View.VISIBLE
            } else {
                binding.progressBar.visibility = android.view.View.INVISIBLE
            }
        }.launchIn(lifecycleScope)
    }
    }










