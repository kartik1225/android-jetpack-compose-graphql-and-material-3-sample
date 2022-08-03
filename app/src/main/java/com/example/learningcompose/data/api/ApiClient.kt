package com.example.learningcompose.data.api

import com.apollographql.apollo3.ApolloClient

class ApiClient {
    companion object {
        private const val BASE_URL = "https://rickandmortyapi.com/graphql"
        fun apolloClient(): ApolloClient {
            return ApolloClient.Builder()
                .serverUrl(BASE_URL)
                .build()
        }

    }
}