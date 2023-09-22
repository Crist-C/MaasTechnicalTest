package com.ccastro.maas.presentation.screens.imageCardCorousel


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState

@OptIn(ExperimentalPagerApi::class)
@Composable
fun CorouselCard(modifier: Modifier = Modifier) {

    val pageState = rememberPagerState(
        initialPage = 2
    )
    val sliderImageList = listOf(
        "https://picsum.photos/id/10/200/300",
        "https://picsum.photos/id/11/200/300",
        "https://picsum.photos/id/12/200/300",
        "https://picsum.photos/id/13/200/300",
        "https://picsum.photos/id/14/200/300",
        "https://picsum.photos/id/15/200/300",
        "https://picsum.photos/id/16/200/300",
        "https://picsum.photos/id/17/200/300",
    )
    Column(
        modifier = modifier
            .fillMaxSize()
    ) {
        HorizontalPager(
            count = sliderImageList.size,
            state = pageState,
            contentPadding = PaddingValues(horizontal = 65.dp),
            modifier = Modifier
                .height(350.dp)
        ) {

        }
    }
}


