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
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.res.colorResource
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
import com.amaurypm.esprimojc.ui.theme.MyGreen
import com.amaurypm.esprimojc.ui.theme.Purple40

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {

            var number by rememberSaveable {
                mutableStateOf("")
            }

            var enableButton by rememberSaveable {
                mutableStateOf(false)
            }

            var result by rememberSaveable {
                mutableStateOf("")
            }

            EsPrimoJCTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen(
                        modifier = Modifier.padding(innerPadding),
                        number = number,
                        enableButton = enableButton,
                        result = result,
                        { newNumber ->  //onNumberChange
                            number = newNumber
                            enableButton = number.isNotEmpty()
                        },
                        { resultText -> //onResultChange
                            result = resultText
                        })
                }
            }
        }
    }
}

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    number: String,
    enableButton: Boolean,
    result: String,
    onNumberChange: (String) -> Unit,
    onResultChange: (String) -> Unit
) {

    val si_primo = stringResource(R.string.si_primo, number)
    val no_primo = stringResource(R.string.no_primo, number)



    Box(
        modifier = modifier
    ) {
        Image(
            painter = painterResource(R.drawable.backg),
            contentDescription = "Imagen de fondo",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "¿Es primo?",
                fontSize = 50.sp,
                fontFamily = FontFamily(Font(R.font.bryndanwrite)),
                fontWeight = FontWeight.Bold,
                color = Purple40
            )

            /*TextField(
                value = number,
                onValueChange = { newValue ->
                    onNumberChange(newValue)
                }

            )*/

            Text(
                text = result,
                fontFamily = FontFamily(Font(R.font.bryndanwrite)),
                fontSize = 32.sp
            )


            PrimeNumberTextField(
                value = number
            ) { newValue ->
                onNumberChange(newValue)
            }

            /*Button(

                onClick = {
                    //Aquí manejamos el click de botón
                    if(esPrimo(number.toInt())){
                        Log.d("APPLOGS", "El número $number sí es primo")
                    }else{
                        Log.d("APPLOGS", "El número $number no es primo")
                    }
                }

            ) {
                Text(
                    text = "Verificar",
                    fontSize = 24.sp
                )
            }*/

            VerifyButton(
                isEnabled = enableButton
            ) {
                //Aquí manejamos el click de botón
                if (esPrimo(number.toInt())) {
                    onResultChange(si_primo)

                    Log.d("APPLOGS", "El número $number sí es primo")
                } else {
                    onResultChange(no_primo)
                    Log.d("APPLOGS", "El número $number no es primo")
                }
            }


        }

    }
}

@Composable
fun VerifyButton(isEnabled: Boolean, onClick: () -> Unit) {
    Button(
        colors = ButtonDefaults.buttonColors(containerColor = MyGreen),
        enabled = isEnabled,
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(0.7f)
    ) {
        Text(
            stringResource(R.string.verificar),
            fontFamily = FontFamily(Font(R.font.bryndanwrite)),
            fontSize = 35.sp
        )
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
            if (newValue.length <= 5 && newValue.all { it.isDigit() })
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