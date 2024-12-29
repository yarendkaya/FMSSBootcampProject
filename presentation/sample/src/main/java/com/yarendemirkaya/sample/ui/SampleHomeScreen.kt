package com.yarendemirkaya.sample.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.yarendemirkaya.domain.model.SamplePerson
import com.yarendemirkaya.sample.R


@Composable
fun SampleHomeScreen(viewModel: SampleViewModel) {
    val viewState by viewModel.sampleState.collectAsState()

    LaunchedEffect(key1 = true) {
        viewModel.fetchUsers()
    }

    when {
        viewState.isLoading -> {
            // Show loading indicator
        }

        viewState.error != null -> {
            // Show error message
            Text(text = viewState.error!!)
        }

        viewState.users.isNotEmpty() -> {
            LazyColumn {
                items(viewState.users) { user ->
                    UserItem(
                        user = user,
                        onDeleteClick = {})
                }
            }
        }
    }
}

@Composable
fun UserItem(user: SamplePerson, onDeleteClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surface)
            .padding(25.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            TextField(value = user.name, onValueChange = { })
            TextField(value = user.number.toString(), onValueChange = { })
        }
        Button(onClick = onDeleteClick, modifier = Modifier.size(25.dp)) {
            Icon(
                painter = painterResource(R.drawable.ic_delete),
                contentDescription = "Delete"
            )
        }
    }
}

@Composable
fun FloatingActionBtn(
    navController: NavController
) {
    FloatingActionButton(
        modifier = Modifier.size(45.dp),
        onClick = { navController.navigate("add") },
        content = {
            Icon(
                painter = painterResource(id = R.drawable.plus_circle_svgrepo_com),
                contentDescription = "Add"
            )
        })
}



