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



import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.r504tl1973004_jsontypicodeusers.presentation.AddUserScreen
import com.example.r504tl1973004_jsontypicodeusers.presentation.AddUserScreenRoot
import com.example.r504tl1973004_jsontypicodeusers.presentation.UsersScreenRoot
import com.example.r504tl1973004_jsontypicodeusers.ui.theme.R504TL1973004_JsonTypicodeUsersTheme



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            R504TL1973004_JsonTypicodeUsersTheme {


                val drawerState = rememberDrawerState(initialValue = DrawerValue.Open)
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
                                    Icon(Icons.Default.Home, contentDescription = stringResource(R.string.users))
                                },
                                onClick = {},
                                selected = true
                            )
                        }
                    }, drawerState = drawerState
                ) {
                    NavHost(navController = navController, startDestination= "users") {
                        composable("users") {
                            UsersScreenRoot(onNavigateAddUser = {
                                navController.navigate("addUser")
                            })
                        }
                        composable(route = "addUser") {
                            AddUserScreenRoot(onBackClick = {
                                navController.navigateUp()
                            })

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