package com.example.graphql


import android.util.Log
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.Optional
import com.apollographql.apollo3.exception.ApolloException
import com.example.graphql.type.UpdateUserInput
import java.util.*


import javax.inject.Inject
import kotlin.jvm.optionals.getOrNull


class Repository @Inject constructor(val apolloClient: ApolloClient) {

    suspend fun getData() = apolloClient.query(GetMyUserQuery("3")).execute()

    suspend fun createUser(name: String, email: String, userName: String) : Boolean {

        val response = try {
            apolloClient.mutation(
                CreateUserMutation(
                    name = name,
                    email = email,
                    username = userName
                )
            ).execute()

        }
        catch (e : ApolloException)
        {
            Log.d("input error", "error input")
            return false
        }

        if (response.hasErrors())
        {
            Log.d("response error", "response error")
            return false
        }

        Log.d("success", "no error while input")

        return true
    }

//
    suspend fun updateUser()
    {
        val updateResponse = try {
            apolloClient.mutation(UpdateMyUserMutation("123", UpdateUserInput(name = com.apollographql.apollo3.api.Optional.present("Updated Name") )))
        }
        catch (e : ApolloException)
        {
            Log.d("update error", "update error ")
        }

        Log.d("updated", "updated")
    }


    suspend fun deleteUser()
    {
        val deleteResponse = apolloClient.mutation(DeleteUserMutation("12")).execute()
        if(!deleteResponse.hasErrors())
        {
            Log.d("deleted","deletion successful")
        }

    }

}

