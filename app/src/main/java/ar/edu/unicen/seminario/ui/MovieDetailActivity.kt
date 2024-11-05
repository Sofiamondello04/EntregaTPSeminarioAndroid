package ar.edu.unicen.seminario.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log

import androidx.appcompat.app.AppCompatActivity
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import ar.edu.unicen.seminario.R
import ar.edu.unicen.seminario.databinding.ActivityMovieDetailBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

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
            }
        }.launchIn(lifecycleScope)
    }

    private fun observeImage() {
        viewModel.image.onEach { config ->
            config?.let {
                val baseUrl = "https://image.tmdb.org"
                val movieDetail = viewModel.movieDetails.value

                movieDetail?.let { movie ->
                    val backdrop_path = movie.backdrop_path
                    val posterUrl = "$baseUrl/t/p/w500$backdrop_path"

                    Glide.with(this)
                        .load(posterUrl)
                        .fitCenter()
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(binding.moviePoster)
                }
            }
        }.launchIn(lifecycleScope)
    }

    private fun observeError() {
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










