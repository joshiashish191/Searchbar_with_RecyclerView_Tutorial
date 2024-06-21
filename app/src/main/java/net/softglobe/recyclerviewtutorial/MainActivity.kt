package net.softglobe.recyclerviewtutorial

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import net.softglobe.recyclerviewtutorial.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    val usersList = listOf(
        User(1, "Ashish"),
        User(2, "Ramesh"),
        User(3, "Suresh"),
        User(4, "Pankaj"),
        User(5, "Rajesh"),
        User(6, "Ketan"),
        User(7, "Harshit"),
        User(8, "Abhijit"),
        User(9, "Abhishek"),
        User(10, "Poonam"),
        User(11, "Pooja"),
        User(12, "Nikita"),
        User(13, "Manisha"),
        User(14, "Kartik"),
        User(15, "Anjali"),
        User(16, "Lata"))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setRecyclerView(usersList)

        binding.searchView.queryHint = "Search user..."
        binding.searchView.setIconifiedByDefault(false)
        binding.searchView.setOnQueryTextListener(object : OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                if (newText.toString().isEmpty()) {
                    setRecyclerView(usersList)
                } else {
                    val filteredList = mutableListOf<User>()
                    for (user in usersList) {
                        if (user.name.lowercase().contains(newText.toString().lowercase())) {
                            filteredList.add(user)
                        }
                    }
                    setRecyclerView(filteredList)
                }
                return false
            }

        })
    }

    private fun setRecyclerView(usersList : List<User>) {
        binding.rvUsers.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = UserAdapter()
            (this.adapter as UserAdapter).submitList(usersList)
        }
    }
}