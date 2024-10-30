package ar.edu.unicen.seminario.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import ar.edu.unicen.seminario.databinding.ActivityUsersBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.launchIn


@AndroidEntryPoint
class UserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUsersBinding

    private val viewModel by viewModels<UserViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUsersBinding.inflate(layoutInflater)
        setContentView(binding.root)
        subscribeToUi()
        subscribeToViewModel()
    }
    private fun subscribeToUi () {
        binding.loadUsersButton.setOnClickListener {
            viewModel.getUsers(300)
        }
    }

    private fun subscribeToViewModel() {


        viewModel.loading.onEach { loading ->
            if (loading) {
                binding.progressBar.visibility = android.view.View.VISIBLE
            } else {
                binding.progressBar.visibility = android.view.View.INVISIBLE
            }
            binding.loadUsersButton.isEnabled = !loading

        }.launchIn(lifecycleScope)

        viewModel.users.onEach { users ->
            binding.usersList.adapter = UserAdapter(
                users= users ?: emptyList(),
                onUserClick = { user ->
                Toast.makeText(this, "User ${user.completeName} clicked" , Toast.LENGTH_SHORT).show()
                }
            )
        }.launchIn(lifecycleScope)

        viewModel.error.onEach { error ->
            if (error) {
                binding.error.visibility = android.view.View.VISIBLE
            } else {
                binding.error.visibility = android.view.View.INVISIBLE
            }
        }.launchIn(lifecycleScope)
    }


}


