package com.example.graphql


import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.apollographql.apollo3.exception.ApolloException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor() : androidx.lifecycle.ViewModel() {

    val liveData by lazy { MutableLiveData<GetMyUserQuery.Data>() }
    val allUserData by lazy { MutableLiveData<GetAllUsersQuery.Data>() }

    @Inject
    lateinit var repository: Repository

    fun getUser(id: Int) {
        viewModelScope.launch {
            repository.getUser(id).let {
                try {
                    liveData.postValue(it.data)
                } catch (e: ApolloException) {
                    Log.d("getUser", "${e.message}")
                }
            }
        }
    }

    fun createUser(name: String, email: String, userName: String) {
        viewModelScope.launch {
            repository.createUser(name, email, userName)
        }
    }

    fun updateUser(id: String?) = repository.updateUser(id)


    fun deleteUser(id: String?) {
        viewModelScope.launch {
            repository.deleteUser(id)
        }
    }

    fun getAllUsers() {
        viewModelScope.launch {
            repository.getAllUser().let {
                try {
                    allUserData.postValue(it.data)
                } catch (e: ApolloException) {
                    Log.d("get all user", "${e.message}")
                }
            }
        }
    }
}
