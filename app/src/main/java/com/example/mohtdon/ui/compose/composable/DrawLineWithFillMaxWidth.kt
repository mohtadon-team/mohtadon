package com.example.mohtdon.ui.compose.composable

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import com.example.mohtdon.ui.compose.theme.color_MainColor

@Composable
fun DrawLineWithFillMaxWidth() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxSize()
        ) {
            val canvasWidth = size.width
            val startPoint = Offset(0f, size.height / 2)
            val endPoint = Offset(canvasWidth, size.height / 2)
            drawLine(
                color = color_MainColor,
                start = startPoint,
                end = endPoint,
            )
        }
    }
}