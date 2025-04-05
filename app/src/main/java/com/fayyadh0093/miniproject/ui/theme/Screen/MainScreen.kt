package com.fayyadh0093.miniproject.ui.theme.screen

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Info
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.fayyadh0093.miniproject.R
import com.fayyadh0093.miniproject.model.daftarResep

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(R.string.receipt), color = MaterialTheme.colorScheme.onSurface) },
                modifier = Modifier.background(
                    Brush.horizontalGradient(
                        listOf(Color(0xFFFF5722), Color(0xFFFFC107)) // Gradasi merah ke kuning
                    )
                ),
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = Color.Transparent,
                    titleContentColor = MaterialTheme.colorScheme.onSecondary
                ),
                actions = {
                    IconButton(
                        onClick = { navController.navigate("about") }
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Info,
                            contentDescription = stringResource(R.string.about_us),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }

                    IconButton(
                        onClick = { navController.navigate("tambah") }
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Add,
                            contentDescription = stringResource(R.string.addReceipt),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            )
        }

    ) { innerPadding ->

        LazyColumn(
            modifier = Modifier
            .padding(innerPadding).padding(top = 16.dp)
            .background(MaterialTheme.colorScheme.background
            )) {
            items(daftarResep) { resep ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(13.dp)
                        .clickable { navController.navigate("detail/${resep.nama}") },
                    colors = CardDefaults.cardColors(
                        containerColor = MaterialTheme.colorScheme.surfaceVariant,
                        contentColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Box(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Image(
                            painter = if (!resep.gambarUri.isNullOrEmpty()) {
                                rememberAsyncImagePainter(Uri.parse(resep.gambarUri))
                            } else if (resep.gambarLokal != null) {
                                painterResource(resep.gambarLokal)
                            } else {
                                painterResource(R.drawable.ic_launcher_foreground) // Gambar default jika kosong
                            },
                            contentDescription = stringResource(R.string.RecipeImages),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(180.dp),
                            contentScale = ContentScale.Crop
                        )

                        Text(
                            text = resep.nama,
                            modifier = Modifier
                                .fillMaxWidth()
                                .align(Alignment.BottomStart)
                                .background(
                                    Brush.verticalGradient(
                                        colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.7f))
                                    )
                                )
                                .padding(16.dp),
                            fontWeight = FontWeight.W600,
                            fontSize = 20.sp,
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}




