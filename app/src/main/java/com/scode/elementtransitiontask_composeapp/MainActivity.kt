package com.scode.elementtransitiontask_composeapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.scode.elementtransitiontask_composeapp.screens.DetailScreen
import com.scode.elementtransitiontask_composeapp.screens.ListScreen
import com.scode.elementtransitiontask_composeapp.ui.theme.ElementTransitionTaskComposeAppTheme

@OptIn(ExperimentalSharedTransitionApi::class)
class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ElementTransitionTaskComposeAppTheme {
                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = { Text(text = "Product List") },
                            modifier = Modifier.background(color = Color.White)
                        )
                    }
                ) { paddingValues ->
                    Surface(
                        color = MaterialTheme.colorScheme.background,
                        modifier = Modifier.padding(paddingValues).fillMaxSize()
                    ) {
                        val navController = rememberNavController()
                        SharedTransitionLayout {
                            NavHost(
                                navController = navController,
                                startDestination = "list"
                            ) {
                                composable("list") {
                                    ListScreen(
                                        onItemClick = { resId, text, desc ->
                                            navController.navigate("detail/$resId/$text/$desc")
                                        },
                                        animatedVisibilityScope = this
                                    )
                                }
                                composable(
                                    route = "detail/{resId}/{text}/{desc}",
                                    arguments = listOf(
                                        navArgument("resId") {
                                            type = NavType.IntType
                                        },
                                        navArgument("text") {
                                            type = NavType.StringType
                                        },
                                        navArgument("desc") {
                                            type = NavType.StringType
                                        }
                                    )
                                ) {
                                    val resId = it.arguments?.getInt("resId") ?: 0
                                    val text = it.arguments?.getString("text") ?: ""
                                    val desc = it.arguments?.getString("desc") ?: ""
                                    DetailScreen(
                                        resId = resId,
                                        text = text,
                                        desc = desc,
                                        animatedVisibilityScope = this
                                    )
                                }
                            }
                        }
                    }
                }

            }
        }
    }
}

