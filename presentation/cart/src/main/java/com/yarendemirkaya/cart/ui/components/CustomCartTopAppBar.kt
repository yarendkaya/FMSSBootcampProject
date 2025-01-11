package com.yarendemirkaya.cart.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yarendemirkaya.cart.R

@Composable
fun CustomCartTopAppBar(onItemClicked: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(Color.Transparent),

    ) {
        Text(
            modifier = Modifier
                .padding(start = 8.dp)
                .wrapContentHeight(Alignment.CenterVertically)
                .wrapContentWidth(Alignment.CenterHorizontally),
            text = "My Cart",
            fontSize = 35.sp,
            color = Color.White
        )
        Spacer(modifier = Modifier.weight(0.4f)) //?
        Icon(
            modifier = Modifier
                .size(30.dp)
                .padding(end=4.dp)
                .clickable {
                    onItemClicked()
                },
            painter = painterResource(R.drawable.delete_svgrepo_com__1_),
            contentDescription = null,
            tint = Color.Unspecified,
        )
    }
}
