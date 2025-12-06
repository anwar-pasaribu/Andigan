package com.unwur.andiganapp

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "Andigan",
        alwaysOnTop = true
    ) {
        App()
    }
}