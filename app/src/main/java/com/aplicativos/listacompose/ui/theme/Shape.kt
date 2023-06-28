package com.aplicativos.listacompose.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Shapes
import androidx.compose.ui.unit.dp

/**
 * Created by João Bosco on 26/06/2023.
 */

val Shapes = Shapes(
    small = RoundedCornerShape(4.dp),
    medium = RoundedCornerShape(4.dp),
    large = RoundedCornerShape(0.dp)
)

val ShapeEditText = Shapes(
    small = RoundedCornerShape(10.dp),
    medium = RoundedCornerShape(20.dp),
    large = RoundedCornerShape(30.dp)
)

val ShapeCardPriority = Shapes(
    small = RoundedCornerShape(30.dp),
    medium = RoundedCornerShape(50.dp),
    large = RoundedCornerShape(100.dp)
)