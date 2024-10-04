package com.amaurypm.esprimojc

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.amaurypm.esprimojc.ui.theme.EsPrimoJCTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EsPrimoJCTheme {

                var number by rememberSaveable {
                    mutableStateOf("")
                }

                MainScreen(number) { value ->
                    number = value
                }


            }
        }
    }
}


@Composable
fun MainScreen(
    number: String,
    onValueChanged: (String) -> Unit
){



    Box(
        modifier = Modifier.fillMaxSize()
    ){
        Image(
            painter = painterResource(R.drawable.backg),
            contentDescription = stringResource(R.string.backg_description),
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {


            Text(
                text = "¿Es primo?",
                fontFamily = FontFamily(Font(R.font.bryndanwrite)),
                fontSize = 50.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )


            PrimeNumberTextField(
                number
            ) {
                onValueChanged(it)
            }

            Button(
                onClick = {
                    if(esPrimo(number.toInt())) Log.d("APPLOGS", "El número $number sí es primo")
                        else Log.d("APPLOGS", "El número $number no es primo")
                }
            ){
                Text(text = "Verificar")
            }



        }


    }
}


@Composable
fun PrimeNumberTextField(value: String, onValueChange: (String) -> Unit) {
    TextField(
        value = value,
        modifier = Modifier.fillMaxWidth(0.8f),
        placeholder = {
            Text(
                text = "Número",
                modifier = Modifier
                    .width(280.dp)
                    .padding(start = 0.dp),
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.bodyMedium
            )
        },
        colors = TextFieldDefaults.colors(
            focusedContainerColor = Color.Transparent,
            unfocusedContainerColor = Color.Transparent,
            disabledContainerColor = Color.Transparent,
        ),
        textStyle = MaterialTheme.typography.bodySmall,
        singleLine = true,
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = KeyboardType.Number // Define el teclado numérico
        ),
        onValueChange = { newValue ->
            if(newValue.all { it.isDigit() } && newValue.length<=5)
                onValueChange(newValue)
        }
    )
}

private fun esPrimo(numero: Int): Boolean {
    if (numero <= 1) return false

    for (i in 2 until numero) {
        if (numero % i == 0) return false
    }

    return true
}

