package com.aplicativos.listacompose.util

import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.aplicativos.listacompose.ui.theme.White

/**
 * Created by João Bosco on 26/06/2023.
 */

@Composable
fun EdgeButton(
    onClick: () -> Unit,
    modifier: Modifier,
    text: String
) {
    Button(
        onClick,
        modifier,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = Color.Gray,
            contentColor = White
        )
    ) {
        Text(
            text = text,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )
    }
}