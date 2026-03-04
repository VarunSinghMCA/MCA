package com.example.campusconnectapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.campusconnectapp.components.BottomNavigationBar
import com.example.campusconnectapp.components.NavigationDrawerContent
import com.example.campusconnectapp.navigation.Screen
import com.example.campusconnectapp.screens.*
import com.example.campusconnectapp.ui.theme.CampusConnectAppTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CampusConnectAppTheme {
                CampusConnectApp()
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CampusConnectApp() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    
    // Determine if bottom navigation should be shown
    val showBottomNav = when {
        currentRoute == Screen.Welcome.route -> false
        currentRoute?.startsWith("event_details") == true -> false
        else -> true
    }
    
    // Determine if drawer should be enabled
    val drawerGesturesEnabled = currentRoute != Screen.Welcome.route
    
    ModalNavigationDrawer(
        drawerState = drawerState,
        gesturesEnabled = drawerGesturesEnabled,
        drawerContent = {
            NavigationDrawerContent(
                navController = navController,
                currentRoute = currentRoute,
                onCloseDrawer = {
                    scope.launch {
                        drawerState.close()
                    }
                }
            )
        }
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            bottomBar = {
                if (showBottomNav) {
                    BottomNavigationBar(
                        navController = navController,
                        currentRoute = currentRoute
                    )
                }
            }
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = Screen.Welcome.route,
                modifier = Modifier.padding(innerPadding)
            ) {
                composable(Screen.Welcome.route) {
                    WelcomeScreen(
                        onProceedClick = {
                            navController.navigate(Screen.Home.route) {
                                popUpTo(Screen.Welcome.route) { inclusive = true }
                            }
                        }
                    )
                }
                
                composable(Screen.Home.route) {
                    HomeScreen(
                        onMenuClick = {
                            scope.launch {
                                drawerState.open()
                            }
                        }
                    )
                }
                
                composable(Screen.Profile.route) {
                    ProfileScreen(
                        onMenuClick = {
                            scope.launch {
                                drawerState.open()
                            }
                        }
                    )
                }
                
                composable(Screen.Notifications.route) {
                    NotificationsScreen(
                        onMenuClick = {
                            scope.launch {
                                drawerState.open()
                            }
                        }
                    )
                }
                
                composable(Screen.Departments.route) {
                    DepartmentsScreen(
                        onMenuClick = {
                            scope.launch {
                                drawerState.open()
                            }
                        },
                        onDepartmentClick = { departmentName ->
                            navController.navigate(Screen.EventDetails.createRoute(departmentName))
                        }
                    )
                }
                
                composable(
                    route = Screen.EventDetails.route,
                    arguments = listOf(
                        navArgument("departmentName") {
                            type = NavType.StringType
                        }
                    )
                ) { backStackEntry ->
                    val departmentName = backStackEntry.arguments?.getString("departmentName") ?: ""
                    EventDetailsScreen(
                        departmentName = departmentName,
                        onBackClick = {
                            navController.navigateUp()
                        }
                    )
                }
            }
        }
    }
}
