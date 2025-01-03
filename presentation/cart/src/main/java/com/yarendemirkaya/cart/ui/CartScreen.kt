package com.yarendemirkaya.cart.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.yarendemirkaya.cart.R
import com.yarendemirkaya.domain.model.MovieCartModel


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CartScreen(viewModel: CartViewModel) {
    val viewState by viewModel.uiState.collectAsState()

    LaunchedEffect(key1 = true) {
        viewModel.getCartMovies("yaren_demirkaya")
    }

    Scaffold(
        topBar = {
            CustomCartTopAppBar()
        },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 104.dp)
                    .background(Color.Black)
            ) {
                when {
                    viewState.isLoading -> {

                    }

                    viewState.error != null -> {

                    }

                    viewState.movies != null -> {
                        LazyColumn(
                            modifier = Modifier
                                .weight(1f)
                                .padding(paddingValues)
                        ) {
                            items(viewState.movies!!) { movie ->
                                CartItem(
                                    movieCartModel = movie,
                                    onIncreaseQuantity = {
                                    },
                                    onDecreaseQuantity = {
                                    },
                                    onDeleteItem = {
                                    }
                                )
                            }
                        }
                        PaymentSection()
                    }

//                    viewState.movies == null -> {
//                        Box(
//                            modifier = Modifier
//                                .fillMaxSize()
//                                .padding(paddingValues),
//                            contentAlignment = Alignment.Center
//                        ) {
//                            Text(text = "Cart is empty", color = Color.White)
//                        }
//                    }
                }
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomCartTopAppBar() {
    TopAppBar(
        title = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
                    .background(Color.Transparent),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier
                        .wrapContentHeight(Alignment.CenterVertically)
                        .wrapContentWidth(Alignment.CenterHorizontally),
                    text = "My Cart",
                    fontSize = 35.sp,
                    color = Color.White
                )
                Spacer(modifier = Modifier.weight(0.4f)) //?
                Icon(
                    modifier = Modifier.size(30.dp),
                    painter = painterResource(R.drawable.delete_svgrepo_com__1_),
                    contentDescription = null,
                    tint = Color.Unspecified,
                )
            }
        }, colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.Black
        ), modifier = Modifier
            .fillMaxSize()
    )
}


@Composable
fun CartItem(
    movieCartModel: MovieCartModel,
    onIncreaseQuantity: () -> Unit,
    onDecreaseQuantity: () -> Unit,
    onDeleteItem: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(modifier = Modifier.weight(0.8f)) {
            Image(
                painter = painterResource(id = com.google.android.material.R.drawable.abc_ic_star_half_black_48dp),
                contentDescription = "Background Image",
                contentScale = ContentScale.None,
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        Column(
            modifier = Modifier
                .weight(1.2f)
                .align(Alignment.Top),
            verticalArrangement = Arrangement.Top
        ) {

            Column {
                Text(
                    text = movieCartModel.name,
                    fontSize = 22.sp,
                    maxLines = 2,
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = "$${String.format("%.2f", movieCartModel.price)}",
                    color = Color.Gray
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
                        contentDescription = "Minus"
                    )
                }
                Text(
                    text = movieCartModel.orderAmount.toString(),
                    modifier = Modifier.padding(horizontal = 4.dp)
                )
                IconButton(onClick = onIncreaseQuantity) {
                    Icon(
                        modifier = Modifier.fillMaxSize(),
                        imageVector = Icons.Default.KeyboardArrowUp,
                        contentDescription = "Add"
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                IconButton(
                    onClick = onDeleteItem,
                    modifier = Modifier.align(Alignment.CenterVertically)
                ) {
                    Icon(
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .size(25.dp),
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete",
                        tint = Color.Gray
                    )
                }
            }
        }
    }
}

@Composable
fun PaymentSection() {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .padding(8.dp)
            .clip(RoundedCornerShape(10.dp)), verticalAlignment = Alignment.Bottom
    ) {
        Text(
            text = "25 USD",
            modifier = Modifier
                .weight(0.8f)
                .fillMaxHeight()
                .background(Color(0xFFFFA500))
                .wrapContentHeight(Alignment.CenterVertically)
                .wrapContentWidth(Alignment.CenterHorizontally),
            color = Color.White,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )

        RectangularButton(
            onClick = { },
            modifier = Modifier
                .weight(1.2f)
                .fillMaxHeight()
        )
    }
}


@Composable
fun RectangularButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        modifier = modifier.background(Color(0xFF151515)),
        shape = RectangleShape,
        colors = ButtonDefaults.buttonColors(
            containerColor = Color(0xFF151515),
            contentColor = Color.White
        )
    ) {
        Text(text = "Payment", color = Color.White)
    }
}

