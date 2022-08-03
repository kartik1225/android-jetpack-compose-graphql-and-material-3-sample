package com.example.learningcompose.ui.homeActivity

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.learningcompose.ui.homeActivity.tabs.NavGraphs
import com.example.learningcompose.ui.theme.AppTheme
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.navigation.navigate

class HomeScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    Screen()
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun Screen(vm: HomeActivityVM = viewModel()) {
    val navController = rememberNavController()

    Scaffold(
        topBar = { AppBar() },
        bottomBar = { BottomBar(navController = navController) },

    ) {
        Box(Modifier.padding(it)) {
            DestinationsNavHost(navController = navController, navGraph = NavGraphs.root)
        }
    }
}

@Composable
private fun AppBar() {
    CenterAlignedTopAppBar(
      title = { Text("Learning Compose") },
    )
}

@Composable
private fun BottomBar(vm: HomeActivityVM = viewModel(), navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar {
        val items = HomeBottomNavigationData.values()
        items.forEach { item ->
            NavigationBarItem(
                selected = currentDestination?.hierarchy?.any {
                    it.route?.contains(item.direction.route)
                        ?: false
                } == true,
                onClick = {
                    navController.navigate(item.direction) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = { Icon(painterResource(id = item.icon), contentDescription = item.title) },
                label = {
                    Text(item.title)
                },
            )
        }
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun HomeScreenPreview() {
    AppTheme {
        Screen()
    }
}

@Preview
@Composable
private fun HomeScreenPreviewLight() {
    AppTheme {
        Screen()
    }
}

