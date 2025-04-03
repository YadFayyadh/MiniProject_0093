package com.fayyadh0093.miniproject.data

data class Resep(
    val nama: String,
    val bahan: String,
    val langkah: String,
    val gambarUri: String? = null,
    val gambarLokal: Int? = null
)
