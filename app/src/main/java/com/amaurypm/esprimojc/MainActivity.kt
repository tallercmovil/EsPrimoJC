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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.modifier.modifierLocalProvider
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.amaurypm.esprimojc.ui.PrimeNumberTextField
import com.amaurypm.esprimojc.ui.VerifyButton
import com.amaurypm.esprimojc.ui.theme.EsPrimoJCTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            var number by rememberSaveable {
                mutableStateOf("")
            }

            var enabledButton by rememberSaveable {
                mutableStateOf(false)
            }

            var resultText by rememberSaveable {
                mutableStateOf("")
            }

            EsPrimoJCTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen(
                        modifier = Modifier.padding(innerPadding),
                        number = number,
                        enabledButton = enabledButton,
                        resultText = resultText,
                        { newValue ->
                            number = newValue
                            enabledButton = newValue.isNotEmpty()
                        }, { newResult ->
                            resultText = newResult
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    number: String,
    enabledButton: Boolean,
    resultText: String,
    onNumberChange: (String) -> Unit,
    onResultTextChange: (String) -> Unit
) {

    val siPrimo = stringResource(R.string.si_primo, number)
    val noPrimo = stringResource(R.string.no_primo, number)

    Box(
        modifier = modifier.fillMaxSize()
    ) {

        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(R.drawable.backg),
            contentDescription = stringResource(R.string.backg),
            contentScale = ContentScale.FillBounds
        )

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(
                text = stringResource(R.string.es_primo),
                fontSize = 48.sp,
                fontFamily = FontFamily(Font(R.font.bryndanwrite))
            )
            PrimeNumberTextField(
                value = number,
                onValueChange = { newValue ->
                    if (newValue.length <= 5 && newValue.all { it.isDigit() })
                        onNumberChange(newValue)
                }
            )
            Text(
                text = resultText,
                fontSize = 28.sp,
                fontFamily = FontFamily(Font(R.font.bryndanwrite))
            )

            VerifyButton(
                isEnabled = enabledButton,
                onClick = {
                    if (esPrimo(number.toInt())) {
                        Log.d("APPLOGS", "El número $number sí es primo")
                        onResultTextChange(siPrimo)
                    } else {
                        Log.d("APPLOGS", "El número $number no es primo")
                        onResultTextChange(noPrimo)
                    }
                }
            )
        }


    }
}

fun esPrimo(numero: Int): Boolean {
    if (numero < 2) return false
    if (numero == 2 || numero == 3) return true
    if (numero % 2 == 0 || numero % 3 == 0) return false

    var i = 5
    while (i * i <= numero) {
        if (numero % i == 0 || numero % (i + 2) == 0) return false
        i += 6
    }
    return true
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    EsPrimoJCTheme {
        MainScreen(
            number = "",
            enabledButton = false,
            resultText = "",
            onNumberChange = {},
            onResultTextChange = {}
        )
    }
}