package com.yarendemirkaya.detail.ui

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.skydoves.landscapist.glide.GlideImage
import com.yarendemirkaya.base.ui.AddCartButton
import com.yarendemirkaya.detail.R
import com.yarendemirkaya.detail.ui.components.ImdbRatingView
import com.yarendemirkaya.domain.model.MovieModel
import com.yarendemirkaya.domain.model.toInsertMovieModel


@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun DetailScreen(
    uiState: UiState,
    movie: MovieModel,
    onToggleFavorite: () -> Unit,
    onAction: (UiAction) -> Unit,
    onBackClick: () -> Unit,
) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
            .verticalScroll(rememberScrollState())
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(LocalConfiguration.current.screenHeightDp.dp / 1.4f)
        ) {
            GlideImage(
                movie.image,
                contentScale = ContentScale.Crop,
                contentDescription = "Movie Image",
                modifier = Modifier.fillMaxSize()
            )
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(125.dp)
                    .align(Alignment.BottomCenter)
                    .background(
                        Brush.verticalGradient(
                            colors = listOf(
                                Color.Transparent,
                                Color.Black
                            )
                        )
                    )
            ) {
                Column(
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(start = 16.dp)
                        .fillMaxHeight()
                ) {
                    Text(
                        text = movie.name,
                        fontWeight = FontWeight.Bold,
                        lineHeight = 40.sp,
                        fontSize = 40.sp,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis,
                        color = Color.White
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(
                        text = "${movie.year}, ${movie.category}",
                        fontSize = 24.sp, color = Color.White
                    )
                    Row(
                        modifier = Modifier.fillMaxSize(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Text(
                            text = movie.rating.toString(),
                            fontSize = 24.sp, color = Color.White
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        ImdbRatingView(movie.rating)
                    }
                }
            }
            Icon(
                painter = painterResource(id = if (uiState.isFavorited) R.drawable.ic_fav_filled else R.drawable.ic_fav),
                tint = Color.White,
                contentDescription = "Favorite",
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(top = 32.dp, end = 24.dp)
                    .size(40.dp)
                    .clickable(onClick = {
                        onToggleFavorite()
                    })
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_back),
                tint = Color.White,
                contentDescription = "Favorite",
                modifier = Modifier
                    .align(Alignment.TopStart)
                    .padding(top = 32.dp, start = 24.dp)
                    .size(40.dp)
                    .clickable {
                        onBackClick()
                    }
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp)
        ) {
            Text(
                text = movie.description, color = Color.White,
                fontSize = 22.sp
            )
            Text(
                text = "Director: ${movie.director}", color = Color.White,
                fontSize = 22.sp
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 4.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    text = "Price: ${movie.price} USD", color = Color((0xFFFFA500)),
                    fontSize = 25.sp
                )
                AddCartButton(onCartClick = {
                    onAction(UiAction.OnAddCartClick(movie.toInsertMovieModel()))
                })
            }
        }
    }
}

//@Composable
//fun MovieDetail(movie: MovieModel, onCartClick: () -> Unit = {}) {
//    Column(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(start = 16.dp)
//    ) {
//        Text(
//            text = movie.description, color = Color.White,
//            fontSize = 22.sp
//        )
//        Text(
//            text = "Director: ${movie.director}", color = Color.White,
//            fontSize = 22.sp
//        )
//
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(end = 4.dp),
//            verticalAlignment = Alignment.CenterVertically,
//            horizontalArrangement = Arrangement.SpaceEvenly
//        ) {
//            Text(
//                text = "Price: ${movie.priceStr} USD", color = Color((0xFFFFA500)),
//                fontSize = 25.sp
//            )
//            AddCartButton(onCartClick = {
//                onCartClick()
//            })
//        }
//    }
//}

