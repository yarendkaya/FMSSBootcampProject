package com.yarendemirkaya.home.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.yarendemirkaya.home.R

@Composable
fun CustomSearchBar(onSearchQueryChange: (String) -> Unit) {
    val searchQuery = remember { mutableStateOf("") }

    TextField(
        value = searchQuery.value,
        onValueChange = {
            searchQuery.value = it
            onSearchQueryChange(it)
        },
        placeholder = {
            Text(text = "Search...", color = Color.Gray)
        },
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp)),
        trailingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.ic_search),
                contentDescription = "Search Icon",
                tint = Color.Unspecified
            )
        },
        singleLine = true,
        colors = (TextFieldDefaults.colors(
            focusedContainerColor = Color(0xFF151515),
            unfocusedContainerColor = Color(0xFF151515),
            cursorColor = Color(0xFFFFC107),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            errorIndicatorColor = Color.Transparent,
            focusedTrailingIconColor = Color.Yellow,
            unfocusedTrailingIconColor = Color.Yellow,
            focusedTextColor = Color(0xFFFFC107)
        ))
    )
}