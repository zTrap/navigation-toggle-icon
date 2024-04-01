package ru.ztrap.navigation.toggle.icon

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Density
import ru.ztrap.bezier.curve.Point

internal fun Path.moveTo(point: Point): Unit = moveTo(point.x, point.y)

internal fun Path.lineTo(point: Point): Unit = lineTo(point.x, point.y)

@Composable
@ReadOnlyComposable
internal inline fun <T> withDensity(block: Density.() -> T): T {
    return with(LocalDensity.current, block)
}