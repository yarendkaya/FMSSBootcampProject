package com.yarendemirkaya.cart.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.skydoves.landscapist.glide.GlideImage
import com.yarendemirkaya.cart.model.MovieCartUiModel

@Composable
fun CartItem(
    movieCartUiModel: MovieCartUiModel,
    onIncreaseQuantity: () -> Unit,
    onDecreaseQuantity: () -> Unit,
    onDeleteItem: () -> Unit
) {
    val image = "http://kasimadalan.pe.hu/movies/images/${movieCartUiModel.image}"
    Row(
        modifier = Modifier
            .height((LocalConfiguration.current.screenHeightDp / 7).dp)
            .padding(start = 16.dp, end = 16.dp)
            .background(Color(0xFF151515))
            .border(2.dp, Color(0xFFFFC107))
            .clip(RoundedCornerShape(4.dp)),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .weight(0.5f)
        ) {
            GlideImage(image, contentScale = ContentScale.Fit, modifier = Modifier.fillMaxSize())
        }

        Spacer(modifier = Modifier.width(16.dp))

        Column(
            modifier = Modifier
                .weight(1.2f)
                .align(Alignment.Top)
                .padding(top = 8.dp, bottom = 8.dp, end = 4.dp),
            verticalArrangement = Arrangement.Top
        ) {
            Column {
                Text(
                    text = movieCartUiModel.name,
                    fontSize = 22.sp,
                    maxLines = 2,
                    color = Color.White
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "$${movieCartUiModel.price}",
                    color = Color.White
                )
            }
            Spacer(modifier = Modifier.height(4.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(onClick = onDecreaseQuantity) {
                    Icon(
                        modifier = Modifier.fillMaxSize(),
                        imageVector = Icons.Default.KeyboardArrowDown,
                        contentDescription = "Minus",
                        tint = Color.White
                    )
                }
                Text(
                    text = movieCartUiModel.orderAmount.toString(),
                    modifier = Modifier.padding(horizontal = 4.dp), color = Color.White
                )
                IconButton(onClick = onIncreaseQuantity) {
                    Icon(
                        modifier = Modifier.fillMaxSize(),
                        imageVector = Icons.Default.KeyboardArrowUp,
                        contentDescription = "Add",
                        tint = Color.White
                    )
                }
                Spacer(modifier = Modifier.weight(0.7f))
                IconButton(
                    onClick = {
                        onDeleteItem()
                    },
                    modifier = Modifier.align(Alignment.CenterVertically)
                ) {
                    Icon(
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .size(25.dp),
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete",
                        tint = Color.White
                    )
                }
            }
        }
    }
}