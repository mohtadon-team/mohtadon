package com.example.mohtdon.ui.compose.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.mohtdon.R

@Composable
fun MohtdonError(
    modifier: Modifier = Modifier,
){
    Column(modifier = modifier) {
        Image(
            painter = painterResource(id = R.drawable.error),
            contentDescription = ""
        )
    }
}