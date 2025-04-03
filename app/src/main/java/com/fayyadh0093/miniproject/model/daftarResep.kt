package com.fayyadh0093.miniproject.model

import androidx.compose.runtime.mutableStateListOf
import com.fayyadh0093.miniproject.R
import com.fayyadh0093.miniproject.data.Resep

val daftarResep =
    mutableStateListOf(
        Resep(
            nama = "Nasi Goreng",
            bahan = "Nasi, Kecap, Bawang Merah, Bawang Putih, Telur",
            langkah = "1. Tumis bawang merah dan bawang putih.\n2. Masukkan telur, aduk hingga matang.\n3. Tambahkan nasi dan kecap, aduk rata.\n4. Sajikan dengan acar dan kerupuk.",
            gambarLokal = R.drawable.nasigoreng
        )
    )