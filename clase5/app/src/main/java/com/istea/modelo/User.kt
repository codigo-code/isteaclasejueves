package com.istea.modelo

import java.io.Serializable


data class User(
    var nombre:String,
    var email:String,
    var celular:String,
    var sexo:String,
    var rol:String
):Serializable