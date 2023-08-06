package com.example.graphql


import android.util.Log
import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.exception.ApolloException
import com.example.graphql.type.CreateUserInput
import com.example.graphql.type.UpdateUserInput
import javax.inject.Inject


class Repository @Inject constructor(val apolloClient: ApolloClient) {

    suspend fun getUser(id: Int) = apolloClient.query(GetMyUserQuery(id.toString())).execute()


    suspend fun createUser(name: String, email: String, userName: String) {

        try {
            apolloClient.mutation(
                CreateMyUserMutation(
                    CreateUserInput(
                        name = name,
                        username = email,
                        email = userName
                    )
                )
            ).execute()
        } catch (e: ApolloException) {
            Log.d("input error", "${e.message}")
        }

        Log.d("success", "user created successfully")
    }


     fun updateUser(id: String?) {
        try {
            apolloClient.mutation(
                UpdateMyUserMutation(
                    id!!,
                    UpdateUserInput(name = com.apollographql.apollo3.api.Optional.present("Updated Name"))
                )
            )
        } catch (e: ApolloException) {
            Log.d("update", "${e.message}")
        }

        Log.d("update", "update successful")
    }


    suspend fun deleteUser(id: String?) {
        try {
            apolloClient.mutation(DeleteUserMutation(id!!)).execute()
        } catch (e: ApolloException) {
            Log.d("delete", "${e.message}")
        }
        Log.d("delete", "deletion successful")

    }

    suspend fun getAllUser() = apolloClient.query(GetAllUsersQuery()).execute()

}

