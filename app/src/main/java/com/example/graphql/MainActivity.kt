package com.example.graphql

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.graphql.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    val adapter = UserAdapter() {
        viewModel.deleteUser(it)
    }

    private val viewModel: MyViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        setUpRecyclerView()
        dataObserver()

    }

    private fun dataObserver() {
        lifecycleScope.launch {

            viewModel.apply {
                getUser(3)
                createUser("ABC", "ABC08@gmail.com", "ABC800")
                updateUser("123")
                getAllUsers()

            }
            viewModel.liveData.observe(this@MainActivity) {
                Log.d("this", it.toString())
            }

            val userInfoList = mutableListOf<UserInfo>()

            viewModel.allUserData.observe(this@MainActivity) {
                it.users?.data?.map {
                    it?.let {
                        userInfoList.add(UserInfo(it.id, it.name, it.email, it.address?.street))
                    }
                }
                adapter.submitList(userInfoList)
            }
        }
    }

    private fun setUpRecyclerView() {
        binding.userInfoRecyclerView.apply {
            layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            adapter = this@MainActivity.adapter
        }
    }
}


data class UserInfo(val id: String?, val name: String?, val email: String?, val address: String?)

