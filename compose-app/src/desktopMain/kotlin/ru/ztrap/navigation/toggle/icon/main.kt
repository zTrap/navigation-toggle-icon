package ru.ztrap.navigation.toggle.icon

import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {
    Window(onCloseRequest = ::exitApplication, title = "navigation-toggle-icon") {
        App()
    }
}