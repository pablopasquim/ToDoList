package com.example.aula14

import retrofit2.Call
import retrofit2.http.GET

interface ApiService {

    @GET("todos")
    fun getUsers() : Call<List<ToDos>>
}