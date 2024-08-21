package com.example.planets.screen

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.planets.R
import com.example.planets.model.PlanetInfo
import com.example.planets.ui.theme.contentTextColor
import com.example.planets.ui.theme.primaryTextColor


@Composable
fun DetailsView(
    planetInfo: PlanetInfo,
    isVisible: Boolean,
    goBack: () -> Unit
) {


    val scrollState = rememberScrollState()
    val scale by animateFloatAsState(
        targetValue = if (isVisible) 1f else 0.5f
    )
    val alpha by animateFloatAsState(
        targetValue = if (isVisible) 1f else 0f
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(20.dp)
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
        ) {
            Spacer(modifier = Modifier.height(300.dp))

            Text(
                text = planetInfo.name,
                style = TextStyle(
                    fontSize = 55.sp,
                    fontFamily = FontFamily.Default,
                    color = primaryTextColor,
                    fontWeight = FontWeight.Bold
                )
            )

            Text(
                text = "Solar System",
                style = TextStyle(
                    fontSize = 30.sp,
                    fontFamily = FontFamily.Default,
                    color = primaryTextColor,
                    fontWeight = FontWeight.Light
                )
            )

            Divider(color = Color.Black.copy(alpha = 0.38f))

            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = planetInfo.description,
                style = TextStyle(
                    fontSize = 20.sp,
                    fontFamily = FontFamily.Default,
                    color = contentTextColor,
                    fontWeight = FontWeight.Normal
                ),
                modifier = Modifier
                    .fillMaxWidth(0.95f)
                    .height(140.dp)
                    .verticalScroll(rememberScrollState())
            )

            Spacer(modifier = Modifier.height(30.dp))

            Divider(color = Color.Black.copy(alpha = 0.38f))

            Spacer(modifier = Modifier.height(15.dp))

            Text(
                text = "Gallery",
                style = TextStyle(
                    fontSize = 24.sp,
                    fontFamily = FontFamily.Default,
                    color = contentTextColor,
                    fontWeight = FontWeight.Light
                ),
                textAlign = TextAlign.Center,
                maxLines = 40,
                overflow = TextOverflow.Ellipsis
            )

            Spacer(modifier = Modifier.height(15.dp))

            LazyRow(
                modifier = Modifier.height(250.dp)
            ) {
                itemsIndexed(planetInfo.images) { index, imageUrl ->
                    Card(
                        modifier = Modifier
                            .padding(8.dp)
                            .size(200.dp),
                        shape = RoundedCornerShape(24.dp)
                    ) {
                        AsyncImage(
                            model = imageUrl,
                            contentDescription = null,
                            modifier = Modifier.fillMaxSize(),
                            contentScale = ContentScale.Crop
                        )
                    }
                }
            }
        }

        Image(
            painter = painterResource(id = planetInfo.iconImage),
            contentDescription = null,
            modifier = Modifier
                .size(400.dp)
                .align(Alignment.TopEnd)
                .offset((85).dp, 0.dp)
                .graphicsLayer(
                    scaleX = scale,
                    scaleY = scale,
                    alpha = alpha
                )
        )

        Text(
            text = planetInfo.position.toString(),
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 247.sp,
                color = Color.Gray.copy(alpha = 0.2f)
            ),
            modifier = Modifier
                .align(Alignment.TopStart)
                .offset(12.dp, 60.dp)
        )

        IconButton(
            onClick = goBack,
            modifier = Modifier.align(Alignment.TopStart)
        ) {
            Icon(
                Icons.Filled.ArrowBack,
                contentDescription = "Back"
            )
        }
    }
}

@Composable
@Preview
fun DetailsViewPreview() {
    DetailsView(
        planetInfo = PlanetInfo(
            1,
            name = "mercury",
            iconImage = R.drawable.mercury,
            description = "Mercury loooooooong text",
            images = listOf()
        ), isVisible = true
    ) {

    }
}