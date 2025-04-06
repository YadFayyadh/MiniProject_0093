package com.fayyadh0093.miniproject.ui.theme.screen

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material3.Button
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.fayyadh0093.miniproject.R
import com.fayyadh0093.miniproject.model.daftarResep


private fun shareData(context: Context, message: String, imageUri: String?) {
    val shareIntent = Intent(Intent.ACTION_SEND).apply {
        type = if (imageUri != null) "image/*" else "text/plain"
        putExtra(Intent.EXTRA_TEXT, message)

        imageUri?.let {
            putExtra(Intent.EXTRA_STREAM, Uri.parse(it))
            addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
        }
    }

    if (shareIntent.resolveActivity(context.packageManager) != null) {
        context.startActivity(Intent.createChooser(shareIntent, "Bagikan Resep"))
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(namaResep: String, navController: NavHostController) {

    val resep = daftarResep.lastOrNull { it.nama == namaResep }


    val context = LocalContext.current
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(R.string.detailR), color = MaterialTheme.colorScheme.onSurface) },
                modifier = Modifier.background(
                    Brush.horizontalGradient(
                        listOf(Color(0xFFFF5722), Color(0xFFFFC107)) // Gradasi merah ke kuning
                    )
                ),
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Rounded.ArrowBack,
                            contentDescription = stringResource(R.string.back),
                            tint = MaterialTheme.colorScheme.onSurface
                        )
                    }
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = Color.Transparent,
                    titleContentColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            resep?.let {

                if (!resep.gambarUri.isNullOrEmpty()) {
                    Image(
                        painter = rememberAsyncImagePainter(Uri.parse(resep.gambarUri)),
                        contentDescription = stringResource(R.string.RecipeImages),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                    )
                } else if (resep.gambarLokal != null) {
                    Image(
                        painter = painterResource(resep.gambarLokal),
                        contentDescription = stringResource(R.string.RecipeImages),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                    )
                }
                else {
                    Text(stringResource(R.string.notfound), modifier = Modifier.padding(10.dp))
                }
//
                Text(text = resep.nama, fontSize = 24.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(10.dp))
                Text(text = stringResource(R.string.ingredients, resep.bahan),  modifier = Modifier.padding(10.dp))
                Text(text = stringResource(R.string.step, resep.langkah), modifier = Modifier.padding(10.dp))
            } ?: Text(stringResource(R.string.notfound), modifier = Modifier.padding(10.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 13.dp),
                contentAlignment = Alignment.Center
            ){


            Button(
                onClick = {
                    if (resep != null) {
                        shareData(
                            context = context,
                            message = context.getString(R.string.share_template, resep.nama , resep.bahan, resep.langkah ),
                            imageUri = resep.gambarUri
                        )
                    }
                },
                modifier = Modifier.padding(top = 13.dp),
                contentPadding = PaddingValues(horizontal = 32.dp, vertical = 16.dp)

            ){
                Text(text = stringResource(R.string.share))
            }
            }
        }
    }
}


