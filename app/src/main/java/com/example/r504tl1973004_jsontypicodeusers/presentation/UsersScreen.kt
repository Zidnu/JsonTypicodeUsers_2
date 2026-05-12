package com.example.r504tl1973004_jsontypicodeusers.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.r504tl1973004_jsontypicodeusers.R

@Composable
fun UsersScreenRoot(modifier: Modifier = Modifier) {
    // Dagger Hilt
    val vm = viewModel<UsersScreenViewModel>(factory = UsersScreenViewModel.createFactory())
    val state by vm.state.collectAsStateWithLifecycle()

    UsersScreen(state = state)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UsersScreen(modifier: Modifier = Modifier, state: UsersState) {
    Scaffold(topBar = {
        TopAppBar(title = {
            Text(stringResource(R.string.users))
        })
    }) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues), contentAlignment = Alignment.Center
        ) {
            when {
                state.loading -> CircularProgressIndicator()
                state.error != null -> Text(state.error)
                else -> UsersList(users = state.items)
            }
        }

    }
}

@Composable
fun UsersList(modifier: Modifier = Modifier, users: List<User>) {
    LazyColumn(modifier = modifier.fillMaxSize()) {
        items(items = users, key = { user -> user.id }) { user ->
            Card(modifier = Modifier.fillParentMaxWidth()) {
                Text(user.email)
            }
        }
    }
}