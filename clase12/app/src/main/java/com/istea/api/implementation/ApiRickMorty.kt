package com.istea.api.implementation

import com.istea.api.IApiRM
import com.istea.modelo.Personaje
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiRickMorty {

    // builder de nuestro objeto Retrofit

    private fun getRetrofit():Retrofit{

        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://rickandmortyapi.com/")
            .build()
    }

    fun getPersonajeById(id: Int):Call<Personaje>{

        return getRetrofit().create(IApiRM::class.java).getPersonajeById(id)
    }

}