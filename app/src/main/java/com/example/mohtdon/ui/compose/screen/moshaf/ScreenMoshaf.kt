package com.example.mohtdon.ui.compose.screen.moshaf

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.mohtdon.R
import com.example.mohtdon.navigation.NavigationHandler
import com.example.mohtdon.ui.compose.composable.MohtdonScaffold
import com.example.mohtdon.ui.compose.composable.MohtdonTopAppBarWithOneAction
import com.example.mohtdon.ui.compose.theme.Tajawal
import com.example.mohtdon.ui.compose.theme.color_CardBackground
import com.example.mohtdon.ui.compose.theme.color_GreenText
import com.example.mohtdon.ui.compose.theme.color_HomeRvItemColor5
import com.example.mohtdon.ui.compose.theme.color_OrangeText
import com.example.mohtdon.ui.compose.theme.color_Sec_Blu_5
import com.example.mohtdon.ui.compose.theme.color_Sec_Blue
import com.example.mohtdon.ui.compose.theme.color_TfsserCardColor
import com.example.mohtdon.ui.compose.theme.color_White


@Composable
fun ScreenMoshaf(
    screenMoshafViewModel: ScreenMoshafViewModel = hiltViewModel()
) {
    val state = screenMoshafViewModel.state.collectAsState().value
    ScreenMoshafContent(state = state, interaction = screenMoshafViewModel)
    NavigationHandler(effects = screenMoshafViewModel.effect) { effect, controller ->
        when (effect) {
            is MoshafScreenUiEffect.NavigateUp -> {
                controller.popBackStack()
            }

            is MoshafScreenUiEffect.NavigateToListen -> {
                //TODO
            }

            is MoshafScreenUiEffect.NavigateToRead -> {
                //TODO
            }

            is MoshafScreenUiEffect.NavigateToSurahNotes -> {
                //TODO
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenMoshafContent(
    state: MoshafUiState,
    interaction: MoshafScreenInteraction
) {
    MohtdonScaffold(
        topAppbar = {
            MohtdonTopAppBarWithOneAction(stringResource(id = R.string.moshaf)) {
                Icon(
                    modifier = Modifier
                        .padding(end = 16.dp, top = 8.dp)
                        .rotate(180f)
                        .align(Alignment.CenterStart)
                        .clickable { interaction.onClickBack() },
                    painter = painterResource(id = R.drawable.right_arrow),
                    contentDescription = "",
                    tint = color_White
                )
            }
        },
        isLoading = false,
        isError = false
    ) {
        LazyColumn(
            contentPadding = PaddingValues(vertical = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 72.dp, start = 16.dp, end = 16.dp)
        ) {
            items(state.items) {
                val shape = RoundedCornerShape(CornerSize(12.dp))
                Row(
                    modifier = Modifier
                        .clip(shape)
                        .clickable {
                            interaction.onClickItem(it.id)
                        }
                        .background(color = color_Sec_Blu_5, shape)
                        .padding(horizontal = 16.dp, vertical = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Card(
                        elevation = CardDefaults.cardElevation(defaultElevation = 12.dp),
                        colors = CardDefaults.cardColors(
                            containerColor = color_White,
                            contentColor = color_OrangeText
                        )
                    ) {
                        Image(
                            painterResource(id = it.icon),
                            modifier = Modifier.padding(16.dp),
                            contentDescription = ""
                        )
                    }
                    Text(
                        text = stringResource(id = it.label),
                        fontFamily = Tajawal,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Start,
                        color = color_GreenText,
                        modifier = Modifier
                            .padding(start = 16.dp)
                            .weight(1f)
                    )
                    Image(
                        painterResource(id = R.drawable.rounded_arrow),
                        contentDescription = ""
                    )
                }
            }
        }
    }
}


@Preview(device = "spec:width=360dp,height=800dp,orientation=portrait")
@Composable
fun MoshafTester() {

}