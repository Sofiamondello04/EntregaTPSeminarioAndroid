package ar.edu.unicen.seminario.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ar.edu.unicen.seminario.databinding.ListItemUserBinding
import ar.edu.unicen.seminario.ddl.models.User

class UserAdapter(
    private val users: List<User>
): RecyclerView.Adapter<UserAdapter.UserViewHolder> () {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val layoutInflater=  LayoutInflater.from(parent.context)
        val binding= ListItemUserBinding.inflate(layoutInflater, parent, false)
        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val user= users[position]
        holder.bind(user)
    }
    override fun getItemCount(): Int {
        return users.size
    }

    class UserViewHolder(
        private val binding: ListItemUserBinding
    ): RecyclerView.ViewHolder(binding.root) {

        fun bind(user: User) {
            binding.userName.text= user.name
            binding.userEmail.text= user.email
        }

    }
}



