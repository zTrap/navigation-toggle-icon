package ru.ztrap.navigation.toggle.icon

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import ru.ztrap.navigation.toggle.icon.NavigationToggle.IconType.Arrow
import ru.ztrap.navigation.toggle.icon.NavigationToggle.IconType.Burger
import ru.ztrap.navigation.toggle.icon.NavigationToggle.IconType.Cross

@Composable
fun App() {
    MaterialTheme {
        Column {
            var slowTarget by remember { mutableStateOf(false) }
            var normalTarget by remember { mutableStateOf(false) }
            val slowProgress by animateFloatAsState(slowTarget, 5000) { slowTarget = !slowTarget }
            val normalProgress by animateFloatAsState(normalTarget, 300) { normalTarget = !normalTarget }

            Indicators(slowProgress)
            Indicators(normalProgress)

            LaunchedEffect(Unit) {
                slowTarget = !slowTarget
                normalTarget = !normalTarget
            }
        }
    }
}

@Composable
private fun animateFloatAsState(
    animateTowardsEnd: Boolean,
    durationMillis: Int,
    finishedListener: (Float) -> Unit,
): State<Float> {
    return animateFloatAsState(
        targetValue = if (animateTowardsEnd) 1f else 0f,
        animationSpec = tween(
            delayMillis = 1000,
            durationMillis = durationMillis,
        ),
        finishedListener = finishedListener,
    )
}

@Composable
private fun Indicators(progress: Float) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        NavigationToggleIcon(
            modifier = Modifier.size(72.dp),
            startIconType = Burger,
            endIconType = Arrow,
            progress = progress,
            contentDescription = null,
        )

        NavigationToggleIcon(
            modifier = Modifier.size(72.dp),
            startIconType = Arrow,
            endIconType = Cross,
            progress = progress,
            contentDescription = null,
        )

        NavigationToggleIcon(
            modifier = Modifier.size(72.dp),
            startIconType = Cross,
            endIconType = Burger,
            progress = progress,
            contentDescription = null,
        )
    }
}