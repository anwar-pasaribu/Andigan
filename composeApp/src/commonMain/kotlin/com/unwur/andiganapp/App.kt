package com.unwur.andiganapp

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.jetbrains.compose.resources.painterResource

import andigan.composeapp.generated.resources.Res
import andigan.composeapp.generated.resources.compose_multiplatform
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.consumeWindowInsets
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Scaffold
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
fun App() {
    MaterialTheme {
        MainScreen()
    }
}

@Composable
fun MainScreen() {
    Scaffold {
        var showContent by remember { mutableStateOf(false) }
        Column(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .safeContentPadding()
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Button(onClick = { showContent = !showContent }) {
                Text("Click me now!")
            }
            AnimatedVisibility(showContent) {
                val greeting = remember { Greeting().greet() }
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Image(painterResource(Res.drawable.compose_multiplatform), null)
                    Text("Compose: $greeting")
                }
            }
        }
    }
}

@Composable
fun ListScreen(onClick: () -> Unit = {}) {
    Scaffold { innerPaddingValues ->
        val listPadding = PaddingValues(
            start = 8.dp, top = innerPaddingValues.calculateTopPadding(),
            end = 8.dp, bottom = innerPaddingValues.calculateBottomPadding()
        )
        val items = remember { (1..25).map { "List $it" } }
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = listPadding,
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            items(items = items) {
                ElevatedCard(
                    onClick = onClick,
                ) {
                    Text(modifier = Modifier.fillMaxWidth().padding(16.dp), text = it)
                }
            }
        }
    }
}

@Composable
fun DetailScreen(onClick: () -> Unit = {}) {
    Scaffold {
        val items = remember { (1..100).map { "Detail $it" } }
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            stickyHeader {
                ElevatedCard(
                    modifier = Modifier.fillMaxWidth().height(72.dp),
                    colors = CardDefaults.elevatedCardColors(
                        containerColor = MaterialTheme.colorScheme.primaryContainer
                    ),
                    onClick = onClick
                ) {
                    Text(modifier = Modifier.fillMaxWidth().padding(16.dp), text = "Detail Screen")
                }
            }
            items(items = items) {
                ElevatedCard(
                    onClick = onClick
                ) {
                    Text(modifier = Modifier.fillMaxWidth().padding(16.dp), text = it)
                }
            }
        }
    }
}

@Composable
fun InfoScreen() {
    Scaffold {
        val items = remember { (1..100).map { "Info $it" } }
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            contentPadding = PaddingValues(horizontal = 8.dp, vertical = 4.dp),
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            stickyHeader {
                ElevatedCard(
                    modifier = Modifier.fillMaxWidth().height(72.dp),
                    colors = CardDefaults.elevatedCardColors(
                        containerColor = MaterialTheme.colorScheme.primary
                    )
                ) {
                    Text(modifier = Modifier.fillMaxWidth().padding(16.dp), text = "Info Screen")
                }
            }
            items(items = items) {
                ElevatedCard() {
                    Text(modifier = Modifier.fillMaxWidth().padding(16.dp), text = it)
                }
            }
        }
    }
}

@Composable
fun PlaceholderScreen(text: String) {
    Scaffold {
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text)
        }
    }
}


@Composable
@Preview(widthDp = 320, heightDp = 320)
fun MainScreenPrev() {
    MaterialTheme {
        MainScreen()
    }
}

@Composable
@Preview(widthDp = 320, heightDp = 640)
fun ListScreenPrev() {
    MaterialTheme {
        ListScreen()
    }
}

@Composable
@Preview(widthDp = 320, heightDp = 640)
fun DetailScreenPrev() {
    MaterialTheme {
        DetailScreen()
    }
}

@Composable
@Preview(widthDp = 320, heightDp = 640)
fun InfoScreenPrev() {
    MaterialTheme {
        InfoScreen()
    }
}