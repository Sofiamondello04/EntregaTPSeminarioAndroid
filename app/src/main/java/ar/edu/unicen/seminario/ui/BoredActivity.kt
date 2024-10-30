package ar.edu.unicen.seminario.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.viewModels
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.lifecycleScope
import ar.edu.unicen.seminario.databinding.ActivityBoredBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.delay


@AndroidEntryPoint
class BoredActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBoredBinding

    private val viewModel by viewModels<BoredViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBoredBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel.loading.onEach { loading ->
            if (loading) {
                binding.progressBar.visibility = android.view.View.VISIBLE
                binding.recommendActivityButton.visibility = android.view.View.INVISIBLE
                binding.activityInformation.visibility= android.view.View.INVISIBLE
            } else {
                binding.progressBar.visibility = android.view.View.INVISIBLE
                binding.recommendActivityButton.visibility = android.view.View.VISIBLE
                binding.activityInformation.visibility= android.view.View.VISIBLE

            }

        }.launchIn(lifecycleScope)

        viewModel.recomendation.onEach { recomendation ->

            binding.activityName.text = recomendation?.activity.orEmpty()
            binding.activityType.text = recomendation?.info?.type.orEmpty()
            binding.activityParticipants.text= recomendation?.info?.participants?.toString().orEmpty()
        }.launchIn(lifecycleScope)

        viewModel.error.onEach { error ->
            if (error) {
                binding.error.visibility =android.view.View.VISIBLE
            }
            else {
                binding.error.visibility =android.view.View.INVISIBLE
            }
        }.launchIn(lifecycleScope)

        binding.participanteEditText.addTextChangedListener { text->
            val isValid: Boolean = text.toString().toIntOrNull() != null
            binding.recommendActivityButton.isEnabled = isValid
        }

        binding.recommendActivityButton.setOnClickListener {
           val participants = binding.participanteEditText.text.toString().toIntOrNull()
            if (participants != null) {
                viewModel.getRecomendation(participants)
            }


        }




    }

}


