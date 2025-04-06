package com.fayyadh0093.miniproject.ui.theme.screen

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.fayyadh0093.miniproject.R
import com.fayyadh0093.miniproject.data.Resep
import com.fayyadh0093.miniproject.navigation.Screen

@Composable
fun IconPicker(isError: Boolean) {
    if (isError) {
        Icon(imageVector = Icons.Filled.Warning, contentDescription = null)
    }
}

@Composable
fun ErrorHint(isError: Boolean) {
    if (isError) {
        Text(text = stringResource(R.string.alert))
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddRecipeScreen(navController: NavHostController, onAddRecipe: (Resep) -> Unit) {
    var nama by rememberSaveable { mutableStateOf("") }
    var bahan by rememberSaveable { mutableStateOf("") }
    var langkah by rememberSaveable { mutableStateOf("") }
    var namaError by rememberSaveable { mutableStateOf(false) }
    var bahanError by rememberSaveable { mutableStateOf(false) }
    var langkahError by rememberSaveable { mutableStateOf(false) }


    var gambarUri by rememberSaveable { mutableStateOf<String?>(null) }


    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        gambarUri = uri?.toString()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.background(
                    Brush.horizontalGradient(
                        listOf(Color(0xFFFF5722), Color(0xFFFFC107)) // Gradasi merah ke kuning
                    )
                ),
                title = {
                    Text(
                        text = stringResource(R.string.addReceipt),
                        color = MaterialTheme.colorScheme.onSurface
                    )
                },
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
                .padding(18.dp)
        ) {
            OutlinedTextField(
                value = nama,
                onValueChange = { nama = it },
                label = { Text(stringResource(R.string.receipt_name)) },
                trailingIcon = { IconPicker(namaError) },
                supportingText = { ErrorHint(namaError) },
                textStyle = TextStyle(color = MaterialTheme.colorScheme.onSecondary),
                isError = namaError,
                singleLine = true,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = bahan,
                onValueChange = { bahan = it },
                label = { Text(stringResource(R.string.Igredients)) },
                trailingIcon = { IconPicker(bahanError) },
                supportingText = { ErrorHint(bahanError) },
                textStyle = TextStyle(color = MaterialTheme.colorScheme.onSecondary),
                isError = bahanError,
                singleLine = false,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = langkah,
                onValueChange = { langkah = it },
                label = { Text(stringResource(R.string.howtomake)) },
                textStyle = TextStyle(color = MaterialTheme.colorScheme.onSecondary),
                trailingIcon = { IconPicker(langkahError) },
                supportingText = { ErrorHint(langkahError) },
                isError = langkahError,
                singleLine = false,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Text,
                    imeAction = ImeAction.Default

                ),

                modifier = Modifier.fillMaxWidth()
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .background(Color.Transparent, shape = RoundedCornerShape(12.dp))
                    .border(
                        BorderStroke(1.dp,MaterialTheme.colorScheme.onSecondary),
                        shape = RoundedCornerShape(12.dp)
                    )
                    .clickable { launcher.launch("image/*") },
                contentAlignment = Alignment.Center
            ) {
                if (gambarUri == null) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(
                            Icons.AutoMirrored.Default.Send,
                            contentDescription = stringResource(R.string.choose),
                            tint = MaterialTheme.colorScheme.onSecondary
                        )
                        Text(stringResource(R.string.chooseimage), color = MaterialTheme.colorScheme.onSecondary)
                    }
                } else {
                    Image(
                        painter = rememberAsyncImagePainter(gambarUri),
                        contentDescription = stringResource(R.string.RecipeImages),
                        contentScale = ContentScale.Crop,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }



            Spacer(modifier = Modifier.height(16.dp))
//            !langkah.matches(Regex("^[a-zA-Z\\s,.]*$")
            Button(
                onClick = {
                    namaError = nama.isBlank() || !nama.matches(Regex("^[a-zA-Z\\s]*$"))
                    bahanError = bahan.isBlank()
                    langkahError = langkah.isBlank()

                    if (namaError || bahanError || langkahError) return@Button
//                    val  gambar = selectedImageUri?.toString()?.takeIf { it.isNotEmpty() }

                    val resepBaru = Resep(
                        nama = nama,
                        bahan = bahan,
                        langkah = langkah,
                        gambarUri =  gambarUri
                    )
                    onAddRecipe(resepBaru)
                    navController.navigate(Screen.Home.route) {
                        popUpTo(Screen.Home.route) {
                            inclusive = true
                        }
                    }

                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(stringResource(R.string.addReceipt))
            }
        }
    }
}