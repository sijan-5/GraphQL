package com.example.graphql

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var button : Button
    lateinit var updateButton : Button
    lateinit var deleteButton : Button
    private val viewModel: MyViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myObserver()
        button = findViewById(R.id.myButton)
        updateButton = findViewById(R.id.updateButton)
        deleteButton = findViewById(R.id.deleteButton)
        button.setOnClickListener {

            viewModel.createUser()
        }
        updateButton.setOnClickListener {
            viewModel.updateUser()
        }

        deleteButton.setOnClickListener {
            viewModel.deleteUser()
        }
    }

    fun myObserver()
    {
        lifecycleScope.launch {

            viewModel.apply {
                getAlbum()
            }
            viewModel.liveData.observe(this@MainActivity){
                Log.d("this", it.toString())
            }
        }
    }
}

