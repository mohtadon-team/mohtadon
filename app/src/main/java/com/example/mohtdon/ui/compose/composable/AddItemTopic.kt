package com.example.mohtdon.ui.compose.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mohtdon.R
import com.example.mohtdon.ui.compose.theme.Tajawal
import com.example.mohtdon.ui.compose.theme.color_MainColor
import com.example.mohtdon.ui.compose.theme.color_White

@Composable
fun AddItemTopic(
    id: Int,
    name: String,
    onClick: (Int) -> Unit,

    ) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(CornerSize(16.dp)))
            .clickable { onClick(id) }
            .fillMaxWidth()
            .height(80.dp)
            .background(color_MainColor)
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = R.drawable.azkar_category),
            contentDescription = "",
            contentScale = ContentScale.Crop
        )
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = name,
                fontFamily = Tajawal,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Start,
                color = color_White,
                softWrap = true
            )
            Image(
                modifier = Modifier.size(24.dp),
                painter = painterResource(id = R.drawable.right_arrow),
                contentDescription = ""
            )
        }
    }
}