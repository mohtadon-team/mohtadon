package com.example.mohtdon.ui.compose.composable

import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mohtdon.R
import com.example.mohtdon.ui.compose.theme.Tajawal
import com.example.mohtdon.ui.compose.theme.color_White
import com.example.mohtdon.ui.compose.theme.color_White_60

@Composable
fun SearchNotVisible(
    label: String,
    visibility: Boolean,
    onClickBack: () -> Unit,
    onToggleSearch: () -> Unit
){
    AnimatedVisibility(visible = visibility,
        enter = fadeIn(
            animationSpec = tween(300)
        ),
        exit = fadeOut(
            animationSpec = tween(300)
        )
    ) {
        Row(modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
        ){
            Image(
                modifier = Modifier
                    .size(24.dp)
                    .rotate(180f)
                    .clickable { onClickBack() },
                painter = painterResource(id = R.drawable.right_arrow),
                contentDescription = ""
            )
            Text(
                text =  label,
                fontFamily = Tajawal,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.weight(1f),
                color = color_White
            )
            Image(
                modifier = Modifier
                    .size(24.dp)
                    .clickable { onToggleSearch() },
                painter = painterResource(id = R.drawable.search),
                contentDescription = ""
            )
        }
    }
}

@Composable
fun SearchVisible(
    visibility: Boolean,
    value: String,
    onToggleSearch: () -> Unit,
    onSearchValueChange: (String) -> Unit,
    onClickSearch: (String) -> Unit
){
    AnimatedVisibility(visible = visibility,
        enter = slideInHorizontally(
            animationSpec = tween(300)
        ),
        exit = slideOutHorizontally(
            animationSpec = tween(300)
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                modifier = Modifier
                    .size(24.dp)
                    .padding(end = 8.dp)
                    .rotate(180f)
                    .clickable { onToggleSearch() },
                painter = painterResource(id = R.drawable.close_ic),
                contentDescription = ""
            )
            val shape = RoundedCornerShape(CornerSize(16.dp))
            Row(
                modifier = Modifier
                    .border(border = BorderStroke(1.dp, color = color_White_60), shape)
                    .clip(shape)
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(end = 16.dp)
                ,
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End,
            ) {
                TextField(
                    modifier = Modifier
                        .weight(1f)
                        .padding(horizontal = 8.dp),
                    value = value,
                    onValueChange = onSearchValueChange,
                    colors = TextFieldDefaults.colors(
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        focusedPlaceholderColor = color_White_60,
                        unfocusedPlaceholderColor = color_White_60,
                        focusedTextColor = color_White,
                        unfocusedTextColor = color_White
                    )
                )
                Image(
                    modifier = Modifier
                        .size(24.dp)
                        .clickable { onClickSearch(value) },
                    painter = painterResource(id = R.drawable.search),
                    contentDescription = ""
                )
            }

        }
    }
}