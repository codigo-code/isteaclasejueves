package com.istea.api

import com.istea.modelo.Personaje
import retrofit2.Call
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Path

interface IApiRM {

    @GET("api/character/{id}")
    fun getPersonajeById(@Path("id") id: Int): Call<Personaje>

}