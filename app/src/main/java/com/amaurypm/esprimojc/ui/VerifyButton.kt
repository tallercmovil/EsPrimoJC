package com.amaurypm.esprimojc.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp
import com.amaurypm.esprimojc.R
import com.amaurypm.esprimojc.ui.theme.MyGreen

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