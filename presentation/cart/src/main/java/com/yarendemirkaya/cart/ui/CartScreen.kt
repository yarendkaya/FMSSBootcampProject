package com.yarendemirkaya.cart.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import com.yarendemirkaya.base.ui.LoadingIndicator
import com.yarendemirkaya.cart.R
import com.yarendemirkaya.cart.ui.components.CartItem
import com.yarendemirkaya.cart.ui.components.CustomCartTopAppBar
import com.yarendemirkaya.cart.ui.components.Information
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CartScreen(viewModel: CartViewModel) {
    val viewState by viewModel.uiState.collectAsState()


    LaunchedEffect(key1 = true) {
        viewModel.getCartMovies()
    }

    Scaffold(
        topBar = {
            CustomCartTopAppBar(onItemClicked = {
                viewModel.deleteAllMovies()
            })
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
                        LoadingIndicator()
                    }

                    viewState.error != null -> {
                    }

                    viewState.movies.isNotEmpty() -> {
                        val showDialog = remember { mutableStateOf(false) }

                        if (showDialog.value) {
                            Information(
                                onConfirm = {
                                    showDialog.value = false
                                },
                                onDismiss = {
                                    showDialog.value = false
                                }
                            )
                        }

                        Column(
                            modifier = Modifier
                                .fillMaxSize()
                        ) {
                            LazyColumn(
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(paddingValues),
                                verticalArrangement = Arrangement.spacedBy(15.dp)
                            ) {
                                items(viewState.movies) { movie ->
                                    CartItem(
                                        movieCartUiModel = movie,
                                        onIncreaseQuantity = {
                                            viewModel.increaseQuantity(movie)
                                        },
                                        onDecreaseQuantity = {
                                            viewModel.deleteMovie(movie.cartIdList.last())
                                        },
                                        onDeleteItem = {
                                            viewModel.viewModelScope.launch {
                                                val jobs = movie.cartIdList.map { cartId ->
                                                    async {
                                                        viewModel.deleteMovie(cartId)
                                                    }
                                                }
                                                jobs.awaitAll()
                                            }
                                        }
                                    )
                                }
                            }
                            val totalPrice = viewState.movies.sumOf { it.price * it.orderAmount }
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(70.dp)
                                    .padding(8.dp)
                                    .clip(RoundedCornerShape(10.dp)),
                                verticalAlignment = Alignment.Bottom
                            ) {
                                Text(
                                    text = "$totalPrice USD",
                                    modifier = Modifier
                                        .weight(0.8f)
                                        .fillMaxHeight()
                                        .background(Color(0xFF151515))
                                        .wrapContentHeight(Alignment.CenterVertically)
                                        .wrapContentWidth(Alignment.CenterHorizontally),
                                    color = Color.White,
                                    fontSize = 20.sp,
                                    fontWeight = FontWeight.Bold,
                                    textAlign = TextAlign.Center
                                )

                                Button(
                                    onClick = {
                                        showDialog.value = true
                                    },
                                    modifier = Modifier
                                        .weight(1.2f)
                                        .fillMaxHeight()
                                        .background(Color(0xFF151515)),
                                    shape = RectangleShape,
                                    colors = ButtonDefaults.buttonColors(
                                        containerColor = Color(0xFFFFA500),
                                        contentColor = Color.White
                                    )
                                ) {
                                    Text(text = "Payment", color = Color.White)
                                }
                            }
                        }
                    }

                    viewState.movies.isEmpty() -> {
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(paddingValues),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "Your cart is empty",
                                color = Color.White,
                                fontSize = 20.sp
                            )
                        }
                    }
                }
            }
        }
    )
}

