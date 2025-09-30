// app/src/main/java/com/example/proyecto/Models/Country.kt
package com.example.proyecto.models

data class Country(
    val name: Name?,
    val idd: Idd?
) {
    val dialCode: String
        get() = listOfNotNull(idd?.root, idd?.suffixes?.firstOrNull()).joinToString("")

    val display: String
        get() = "${name?.common.orEmpty()}  ${dialCode.ifBlank { "" }}"
}

data class Name(val common: String)
data class Idd(val root: String?, val suffixes: List<String>?)
