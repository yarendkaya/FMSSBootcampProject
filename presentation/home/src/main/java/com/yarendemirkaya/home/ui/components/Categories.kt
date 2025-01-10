package com.yarendemirkaya.home.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.yarendemirkaya.home.R
import com.yarendemirkaya.home.ui.SortType


@Composable
fun Categories(
    categories: List<String>,
    onCategorySelected: (String) -> Unit,
    onFilterClick: (SortType) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Black)
            .padding(top = 8.dp, start = 4.dp, end = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .border(2.dp, Color(0xFFFFA500), shape = RoundedCornerShape(4.dp))
                .padding(4.dp)
        ) {
            DropDownMenu(onFilterClick = onFilterClick)
        }

        val selectedCategory =
            remember { mutableStateOf(categories.first()) } //defensive programming
        LazyRow(
            modifier = Modifier.fillMaxWidth(),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(categories) { category ->
                val isSelected = category == selectedCategory.value
                OutlinedButton(
                    onClick = {
                        if (isSelected) return@OutlinedButton
                        selectedCategory.value = category
                        onCategorySelected(category)
                    },
                    colors = ButtonDefaults.outlinedButtonColors(
                        containerColor = if (isSelected) Color(0xFFFFA500) else Color(0xFF151515),
                        contentColor = if (isSelected) Color.White else Color.Gray,
                    ), shape = RoundedCornerShape(20.dp),
                    border = BorderStroke(2.dp, Color(0xFFFFA500))
                ) {
                    Text(text = category)
                }
            }
        }
    }
}

@Composable
fun DropDownMenu(onFilterClick: (SortType) -> Unit) {
    val isDropDownExpanded = remember { mutableStateOf(false) }
    val itemPosition = remember { mutableIntStateOf(0) }
    val sortTypes = SortType.entries

    Icon(
        painter = painterResource(R.drawable.filter_svgrepo_com),
        contentDescription = "Filter",
        tint = Color.Gray,
        modifier = Modifier
            .background(Color.Black)
            .clickable {
                isDropDownExpanded.value = true
            }
    )
    DropdownMenu(
        modifier = Modifier.background(Color(0xFF151515)),
        expanded = isDropDownExpanded.value,
        onDismissRequest = {
            isDropDownExpanded.value = false
        }
    ) {
        sortTypes.forEachIndexed { index, type ->
            DropdownMenuItem(
                modifier = Modifier.background(Color(0xFF151515)),
                text = {
                    Text(text = type.value, color = Color.Gray)
                },
                onClick = {
                    isDropDownExpanded.value = false
                    itemPosition.intValue = index
                    onFilterClick(type)
                }
            )
        }
    }
}