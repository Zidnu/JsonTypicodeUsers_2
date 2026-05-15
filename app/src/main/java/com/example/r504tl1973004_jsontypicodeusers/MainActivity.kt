package com.example.r504tl1973004_jsontypicodeusers

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Icon
//import androidx.compose.material3.Icon
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
//import androidx.navigation.NavHost
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.r504tl1973004_jsontypicodeusers.presentation.AddUserScreenRoot
import com.example.r504tl1973004_jsontypicodeusers.presentation.UsersScreenRoot
import com.example.r504tl1973004_jsontypicodeusers.presentation.UsersScreenViewModel
import com.example.r504tl1973004_jsontypicodeusers.ui.theme.R504TL1973004_JsonTypicodeUsersTheme



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            R504TL1973004_JsonTypicodeUsersTheme {

                val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
                val navController = rememberNavController()

                ModalNavigationDrawer(
                    // elementeillä on oletuspadding

                    // tämä on drawerin sisältö
                    drawerContent = {
                        ModalDrawerSheet {
                            Spacer(modifier = Modifier.height(16.dp))
                            NavigationDrawerItem(
                                label = {
                                    Text(stringResource(R.string.users))
                                },
                                icon = {
                                    Icon(
                                        Icons.Default.Home,
                                        contentDescription = stringResource(R.string.users)
                                    )
                                },
                                onClick = {},
                                selected = true
                            )
                        }
                    }, drawerState = drawerState
                ) {
                    NavHost(navController = navController, startDestination = "users_feature") {
                        navigation(startDestination = "users", route="users_feature") {
                            composable("users") {

                                val vm = it.sharedViewModel<UsersScreenViewModel>(
                                    factory = UsersScreenViewModel.createFactory(),
                                    navController = navController
                                )

                                UsersScreenRoot(onNavigateAddUser = {
                                    navController.navigate("addUser")
                                }, vm = vm)
                            }
                            composable(route = "addUser") {

                                val vm = it.sharedViewModel<UsersScreenViewModel>(
                                    factory = UsersScreenViewModel.createFactory(),
                                    navController = navController
                                )

                                AddUserScreenRoot(onBackClick = {
                                    navController.navigateUp()
                                }, vm = vm)
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    R504TL1973004_JsonTypicodeUsersTheme {
        Greeting("Android")
    }
}

@Composable
inline fun <reified T : ViewModel> NavBackStackEntry.sharedViewModel(
    navController: NavHostController,
    factory: ViewModelProvider.Factory? = null
): T {

    val navGraphRoute = destination.parent?.route ?: return viewModel(factory = factory)

    val parentEntry = remember(this) {
        navController.getBackStackEntry(navGraphRoute)
    }

    return viewModel(factory = factory, viewModelStoreOwner = parentEntry)
}