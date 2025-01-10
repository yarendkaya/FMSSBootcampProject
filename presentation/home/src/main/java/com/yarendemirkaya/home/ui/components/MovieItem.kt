package com.yarendemirkaya.home.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.skydoves.landscapist.glide.GlideImage
import com.yarendemirkaya.base.R
import com.yarendemirkaya.domain.model.MovieModel


@Composable
fun MovieItem(
    movie: MovieModel,
    onMovieClick: (MovieModel) -> Unit,
    onCartClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .clickable { onMovieClick(movie) },
        verticalArrangement = Arrangement.Top
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(6.dp))
        ) {
            GlideImage(movie.image, contentScale = ContentScale.FillWidth)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(95.dp)
                    .align(Alignment.TopEnd)
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Black,
                                Color.Transparent
                            )
                        )
                    )
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .align(Alignment.TopStart),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                Text(
                    text = movie.rating.toString(),
                    textAlign = TextAlign.End,
                    modifier = Modifier
                        .padding(8.dp),
                    color = Color.White, fontFamily = FontFamily(
                        Font(com.yarendemirkaya.home.R.font.roboto_bold)
                    )
                )
                Icon(
                    painter = painterResource(id = com.yarendemirkaya.home.R.drawable.star_svgrepo_com),
                    contentDescription = null,
                    tint = Color.Unspecified,
                    modifier = Modifier.padding(end = 2.dp, bottom = 3.dp)
                )
            }
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(bottomStart = 8.dp, bottomEnd = 8.dp))
                .background(Color(0xFF151515))
        ) {
            Text(
                text = movie.name,
                fontSize = 25.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, end = 8.dp),
                color = Color.White,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontFamily = FontFamily(
                    Font(com.yarendemirkaya.home.R.font.roboto_bold)
                )
            )
            Text(
                modifier = Modifier.padding(start = 8.dp),
                text = movie.priceStr,
                fontSize = 18.sp,
                color = Color.White,
                fontFamily = FontFamily(
                    Font(com.yarendemirkaya.home.R.font.roboto_bold)
                )
            )
            AddCartButton(onCartClick)
        }
    }
}







