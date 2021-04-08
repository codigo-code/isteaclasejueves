package com.istea.clase3.modelo

import java.io.Serializable
// si tienen que pasar clases entre actividades deben implementar Serializable nada mas :)
data class User(var name:String, var pass:String): Serializable