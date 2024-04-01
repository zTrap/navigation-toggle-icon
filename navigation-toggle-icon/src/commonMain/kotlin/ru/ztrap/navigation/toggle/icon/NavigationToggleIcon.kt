package ru.ztrap.navigation.toggle.icon

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Canvas
import androidx.compose.material.LocalContentAlpha
import androidx.compose.material.LocalContentColor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.role
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import kotlin.math.floor
import ru.ztrap.navigation.toggle.icon.NavigationToggle.IconType.Arrow
import ru.ztrap.navigation.toggle.icon.NavigationToggle.IconType.Burger
import ru.ztrap.navigation.toggle.icon.NavigationToggle.IconType.Cross
import ru.ztrap.navigation.toggle.icon.transition.ArrowToBurger
import ru.ztrap.navigation.toggle.icon.transition.ArrowToCross
import ru.ztrap.navigation.toggle.icon.transition.BurgerToArrow
import ru.ztrap.navigation.toggle.icon.transition.BurgerToCross
import ru.ztrap.navigation.toggle.icon.transition.CrossToArrow
import ru.ztrap.navigation.toggle.icon.transition.CrossToBurger
import ru.ztrap.navigation.toggle.icon.transition.NavigationToggleIconsTransition

private const val ROTATION_DEGREES = 180f
private const val PROGRESS_MAX = 1f
private const val PROGRESS_MIN = 0f
private val StrokeWidth = 2.dp
private val SceneSize = 24.dp

internal val PROGRESS_APPLICABLE_VALUES = PROGRESS_MIN..PROGRESS_MAX

@Composable
public fun NavigationToggleIcon(
    modifier: Modifier = Modifier,
    toggled: Boolean,
    startIconType: NavigationToggle.IconType,
    endIconType: NavigationToggle.IconType = startIconType,
    contentDescription: String?,
    animationSpec: AnimationSpec<Float> = spring(),
    color: Color = LocalContentColor.current.copy(alpha = LocalContentAlpha.current),
) {
    val progress by animateFloatAsState(
        targetValue = if (toggled) PROGRESS_MAX else PROGRESS_MIN,
        animationSpec = animationSpec,
    )

    NavigationToggleIcon(
        modifier = modifier,
        color = color,
        progress = progress,
        startIconType = startIconType,
        endIconType = endIconType,
        contentDescription = contentDescription,
    )
}

@Composable
public fun NavigationToggleIcon(
    modifier: Modifier = Modifier,
    progress: Float,
    startIconType: NavigationToggle.IconType,
    endIconType: NavigationToggle.IconType = startIconType,
    contentDescription: String?,
    color: Color = LocalContentColor.current.copy(alpha = LocalContentAlpha.current),
) {
    val resolvedProgress = progress.coerceIn(PROGRESS_APPLICABLE_VALUES)
    val sceneSizePx = withDensity { SceneSize.roundToPx() }
    var componentSizePx by remember { mutableStateOf(IntSize(sceneSizePx, sceneSizePx)) }
    val sizeRatio = componentSizePx.width / sceneSizePx.toFloat()
    val sceneScaleFactor = withDensity { sizeRatio * density }
    val transition = remember(startIconType, endIconType, sceneScaleFactor) {
        getTransition(startIconType, endIconType, sceneScaleFactor)
    }
    val strokeWidthPx = withDensity { floor(StrokeWidth.toPx() * sizeRatio) }
    val stroke = Stroke(width = strokeWidthPx, cap = StrokeCap.Round)
    val path = remember { Path() }
    val isRtl = LocalLayoutDirection.current == LayoutDirection.Rtl

    val semantics = if (contentDescription != null) {
        Modifier.semantics {
            this.contentDescription = contentDescription
            this.role = Role.Image
        }
    } else {
        Modifier
    }

    Canvas(
        modifier = modifier
            .onSizeChanged { componentSizePx = it }
            .then(semantics),
        onDraw = {
            path.rewind()
            transition.fillPath(path, resolvedProgress)

            withTransform(
                transformBlock = {
                    if (transition is NavigationToggleIconsTransition.Production) {
                        rotate(
                            degrees = ROTATION_DEGREES * resolvedProgress * transition.rotationDirection.getSign(isRtl),
                            pivot = transition.rotationPivot,
                        )
                    }

                    if (isRtl) {
                        scale(-1f, 1f, Offset.Zero)
                        translate(-componentSizePx.width.toFloat(), 0f)
                    }
                },
                drawBlock = { drawPath(path, color, style = stroke) },
            )
        },
    )
}

private fun getTransition(
    startIconType: NavigationToggle.IconType,
    endIconType: NavigationToggle.IconType,
    sceneScaleFactor: Float,
): NavigationToggleIconsTransition {
    return when {
        startIconType == Burger && endIconType == Arrow -> BurgerToArrow(sceneScaleFactor)
        startIconType == Burger && endIconType == Cross -> BurgerToCross(sceneScaleFactor)
        startIconType == Arrow && endIconType == Burger -> ArrowToBurger(sceneScaleFactor)
        startIconType == Arrow && endIconType == Cross -> ArrowToCross(sceneScaleFactor)
        startIconType == Cross && endIconType == Arrow -> CrossToArrow(sceneScaleFactor)
        startIconType == Cross && endIconType == Burger -> CrossToBurger(sceneScaleFactor)
        else -> NavigationToggleIconsTransition.Static(startIconType, sceneScaleFactor)
    }
}