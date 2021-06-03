package com.istea.modelo

import java.io.Serializable

data class Personaje(val name: String,
                     val status: String,
                     val gender: String,
                     val species:String,
                     val image: String) : Serializable