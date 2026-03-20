package com.amaurypm.esprimojc.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.amaurypm.esprimojc.R

@Composable
fun PrimeNumberTextField(value: String, onValueChange: (String) -> Unit) {
    TextField(
        value = value,
        modifier = Modifier.fillMaxWidth(0.8f),
        placeholder = {
            Text(
                text = stringResource(R.string.hint),
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
            onValueChange(newValue)
        }
    )
}
