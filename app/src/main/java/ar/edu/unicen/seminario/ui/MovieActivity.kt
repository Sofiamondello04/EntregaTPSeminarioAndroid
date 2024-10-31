package ar.edu.unicen.seminario.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import ar.edu.unicen.seminario.databinding.ActivityMoviesBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.launchIn


@AndroidEntryPoint
class MovieActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMoviesBinding

    private val viewModel by viewModels<MovieViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMoviesBinding.inflate(layoutInflater)
        setContentView(binding.root)


        subscribeToViewModel()
        loadMovies()
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
            binding.recyclerView.layoutManager = LinearLayoutManager(this)
            binding.recyclerView.adapter = MovieAdapter(movies ?: emptyList()) { movie ->
                Toast.makeText(this, "${movie.title} clicked", Toast.LENGTH_SHORT).show()
            }
        }.launchIn(lifecycleScope)

            viewModel.error.onEach { error ->
                if (error) {
                    binding.errorView.visibility = android.view.View.VISIBLE
                } else {
                    binding.errorView.visibility = android.view.View.INVISIBLE
                }
            }.launchIn(lifecycleScope)
        }
    private fun loadMovies() {
        viewModel.getPopularMovies()
    }

    }






