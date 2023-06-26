package com.aplicativos.listacompose.util

import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import com.aplicativos.listacompose.ui.theme.Black
import com.aplicativos.listacompose.ui.theme.LightBlue
import com.aplicativos.listacompose.ui.theme.ShapeEditText
import com.aplicativos.listacompose.ui.theme.White

/**
 * Created by João Bosco on 26/06/2023.
 */

@Composable
fun BackgroundText(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier,
    label: String,
    maxLines: Int,
    keyboardType: KeyboardType
) {

    OutlinedTextField(
        value = value,
        onValueChange,
        modifier,
        label = {
            Text(text = label)
        },
        maxLines = maxLines,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = Black,
            focusedBorderColor = LightBlue,
            focusedLabelColor = LightBlue,
            backgroundColor = White,
            cursorColor = LightBlue
        ),
        shape = ShapeEditText.small,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType
        )
    )
}