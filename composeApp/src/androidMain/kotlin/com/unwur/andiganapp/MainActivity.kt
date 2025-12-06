package com.unwur.andiganapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.tween
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.adaptive.layout.calculatePaneScaffoldDirective
import androidx.compose.material3.adaptive.navigation3.ListDetailSceneStrategy
import androidx.compose.material3.adaptive.navigation3.rememberListDetailSceneStrategy
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import kotlinx.serialization.Serializable

interface Route : NavKey

@Serializable
private object List : Route

@Serializable
private data class Detail(val id: String) : Route

@Serializable
private data object Info : Route

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3AdaptiveApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        setContent {
            val backStack = rememberNavBackStack(List)

            val windowAdaptiveInfo = currentWindowAdaptiveInfo()
            val directive = remember(windowAdaptiveInfo) {
                calculatePaneScaffoldDirective(windowAdaptiveInfo)
                    .copy(horizontalPartitionSpacerSize = 0.dp)
            }
            val listDetailStrategy = rememberListDetailSceneStrategy<NavKey>(directive = directive)

            NavDisplay(
                backStack = backStack,
                onBack = { backStack.removeLastOrNull() },
                sceneStrategy = listDetailStrategy,
                entryProvider = entryProvider {
                    entry<List>(
                        metadata = ListDetailSceneStrategy.listPane(
                            detailPlaceholder = {
                                PlaceholderScreen("Choose a item from the list")
                            }
                        )
                    ) {
                        ListScreen {
                            backStack.add(Detail("ABC"))
                        }
                    }
                    entry<Detail>(
                        metadata = ListDetailSceneStrategy.detailPane()
                    ) { conversation ->
                        DetailScreen {
                            backStack.add(Info)
                        }
                    }
                    entry<Info>(
                        metadata = ListDetailSceneStrategy.extraPane()
                    ) {
                        InfoScreen()
                    }
                },
                transitionSpec = {
                    slideInHorizontally(
                        initialOffsetX = { it },
                        animationSpec = tween(300)
                    ) togetherWith scaleOut(animationSpec = tween(300), targetScale = 0.9f)
                },
                popTransitionSpec = {
                    scaleIn(
                        animationSpec = tween(300),
                        initialScale = 0.9f
                    ) togetherWith slideOutHorizontally(
                        targetOffsetX = { it },
                        animationSpec = tween(300)
                    )
                },
                predictivePopTransitionSpec = {
                    scaleIn(
                        animationSpec = tween(300),
                        initialScale = 0.9f
                    ) togetherWith slideOutHorizontally(
                        targetOffsetX = { it },
                        animationSpec = tween(300)
                    )
                }
            )
        }
    }
}
