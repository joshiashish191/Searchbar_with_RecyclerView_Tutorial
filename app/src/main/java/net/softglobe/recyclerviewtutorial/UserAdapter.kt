package net.softglobe.recyclerviewtutorial

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import net.softglobe.recyclerviewtutorial.databinding.UserLayoutBinding

class UserAdapter : ListAdapter<User, UserAdapter.UserViewHolder>(DiffUsers()) {
    lateinit var binding : UserLayoutBinding
    inner class UserViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        fun bind(user : User) {
            binding.name.text = user.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        binding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), R.layout.user_layout, parent, false)
        return UserViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class DiffUsers : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
            return oldItem == newItem
        }

    }
}