package com.example.graphql


import androidx.lifecycle.MutableLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor() : androidx.lifecycle.ViewModel() {

    val liveData = MutableLiveData<GetMyUserQuery.Data>()

    @Inject
    lateinit var repository: Repository

    fun getAlbum() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = repository.getData().let {
                if (!it.hasErrors()) {
                    it.data?.let { liveData.postValue(it) }
                }
            }

        }
    }

    fun createUser()
    {
        CoroutineScope(Dispatchers.IO).launch {
            repository.createUser("sijan", "Bhattrai@gmail.com", "Shivam_Sundaram")
        }
    }

    fun updateUser()
    {
        CoroutineScope(Dispatchers.IO).launch {
            repository.updateUser()
        }
    }

    fun deleteUser()
    {
        CoroutineScope(Dispatchers.IO).launch {
            repository.deleteUser()
        }
    }
}
