package com.yarendemirkaya.detail.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlin.math.floor


@Composable
fun ImdbRatingView(
    rating: Double,
    modifier: Modifier = Modifier,
) {
    val fullStars = floor(rating / 2).toInt()
    val hasHalfStar = (rating % 2) >= 0.5

    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {

        repeat(fullStars) {
            Icon(
                painter = painterResource(id = com.yarendemirkaya.detail.R.drawable.ic_full_star),
                contentDescription = "Star",
                tint = Color.Unspecified,
                modifier = Modifier.size(24.dp)
            )
        }


        if (hasHalfStar) {
            Icon(
                painter = painterResource(id = com.yarendemirkaya.detail.R.drawable.ic_half_star),
                contentDescription = "Half Star",
                tint = Color.Unspecified,
                modifier = Modifier.size(24.dp)
            )
        }


        val emptyStars = 5 - fullStars - if (hasHalfStar) 1 else 0
        repeat(emptyStars) {
            Icon(
                painter = painterResource(id = com.yarendemirkaya.detail.R.drawable.ic_star_border),
                contentDescription = "Empty Star",
                tint = Color.Unspecified,
                modifier = Modifier.size(24.dp)
            )
        }

        Spacer(modifier = Modifier.width(8.dp))


        Text(
            text = String.format("%.1f/10", rating),
            fontSize = 14.sp,
            color = Color.Black
        )
    }
}
