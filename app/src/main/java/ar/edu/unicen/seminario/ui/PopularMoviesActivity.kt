package ar.edu.unicen.seminario.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import ar.edu.unicen.seminario.databinding.ActivityPopularMoviesBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.launchIn


@AndroidEntryPoint
class PopularMoviesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPopularMoviesBinding
    private val viewModel by viewModels<MovieViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPopularMoviesBinding.inflate(layoutInflater)
        setContentView(binding.root)
        subscribeToViewModel()
        loadMovies()

        binding.retryButton.setOnClickListener {
            loadMovies()  // Reintentar la carga de películas populares
        }
    }

    private fun subscribeToViewModel() {
        viewModel.loading.onEach { loading ->
            if (loading) {
                binding.progressBar.visibility = android.view.View.VISIBLE
            } else {
                binding.progressBar.visibility = android.view.View.INVISIBLE
            }
        }.launchIn(lifecycleScope)

        viewModel.popularMovies.onEach { movies ->
            if(movies!= null) {
                binding.recyclerView.layoutManager = LinearLayoutManager(this)
                binding.recyclerView.adapter = PopularMoviesAdapter(movies ?: emptyList()) { movie ->

                    Log.d(
                        "PopularMoviesAdapter",
                        "Clicked movie ID: ${movie.id}"
                    )  // Log para depurar
                    val intent = Intent(this, MovieDetailActivity::class.java).apply {
                        putExtra("id", movie.id)
                        Log.d("PopularMoviesActivity", "ID de la película: ${movie.id}")
                      //  putExtra("movie_title", movie.title)
                       // putExtra("movie_overview", movie.overview)
                      //  putExtra("movie_poster_path", movie.poster_path)
                    }
                    startActivity(intent)
                }
            }
        }.launchIn(lifecycleScope)

        viewModel.error.onEach { error ->
            if (error) {
                binding.errorView.visibility = android.view.View.VISIBLE
                binding.retryButton.visibility = android.view.View.VISIBLE
            } else {
                binding.errorView.visibility = android.view.View.INVISIBLE
                binding.retryButton.visibility = android.view.View.INVISIBLE
            }
        }.launchIn(lifecycleScope)
    }
    private fun loadMovies() {
        viewModel.getPopularMovies()
    }

    }






