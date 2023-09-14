package com.example.superheroes.responses

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Url

interface apiSuperHeroes {

    @GET
    suspend fun getImagen(@Url url:String):Response<listResponse>

    @GET
    suspend fun getInfoHeroe(@Url url:String):Response<heroeResponse>

}