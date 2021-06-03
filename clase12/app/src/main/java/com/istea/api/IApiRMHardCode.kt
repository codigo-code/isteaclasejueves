package com.istea.api

import com.istea.modelo.Personaje
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

// ideal para consumir un endpoint nos traiga valores pero que no haga falta remplazar la url
interface IApiRMHardCode {

    @GET(".")
    fun getPersonaje() : Call<Personaje>

    // generamos la comunicacion contra el endpoint de rick and morty

    companion object{

        // implementando el patron creacional builder

        fun create()  : IApiRMHardCode{
            val retrofit = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://rickandmortyapi.com/api/character/1/")
                .build()
            return retrofit.create(IApiRMHardCode::class.java)
        }
    }
}