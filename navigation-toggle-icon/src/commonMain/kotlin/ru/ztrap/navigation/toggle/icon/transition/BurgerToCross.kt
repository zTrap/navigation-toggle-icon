package ru.ztrap.navigation.toggle.icon.transition

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Path
import ru.ztrap.bezier.curve.BezierCurve
import ru.ztrap.bezier.curve.BezierScene
import ru.ztrap.bezier.curve.Point
import ru.ztrap.navigation.toggle.icon.NavigationToggle
import ru.ztrap.navigation.toggle.icon.NavigationToggle.RotationDirection.Companion.Clockwise
import ru.ztrap.navigation.toggle.icon.PROGRESS_APPLICABLE_VALUES
import ru.ztrap.navigation.toggle.icon.lineTo
import ru.ztrap.navigation.toggle.icon.moveTo
import ru.ztrap.navigation.toggle.icon.transition.scene.createBurgerScene
import ru.ztrap.navigation.toggle.icon.transition.scene.createCrossScene

@Immutable
internal class BurgerToCross(sceneScaleFactor: Float) : NavigationToggleIconsTransition.Production() {
    override val startScene: BezierScene = createBurgerScene(sceneScaleFactor)
    override val endScene: BezierScene = createCrossScene(sceneScaleFactor)

    override val rotationDirection: NavigationToggle.RotationDirection = Clockwise

    override val topLineStartCurve: BezierCurve = BezierCurve(
        start = Point(x = startScene.left, y = startScene.top),
        end = Point(x = endScene.left, y = endScene.top),
    )

    override val topLineEndCurve: BezierCurve = BezierCurve(
        start = Point(x = startScene.right, y = startScene.top),
        end = Point(x = endScene.right, y = endScene.bottom),
    )

    override val centerLineStartCurve: BezierCurve = BezierCurve(
        start = Point(x = startScene.left, y = startScene.centerY),
        end = Point(x = endScene.centerX, y = endScene.centerY),
    )

    override val centerLineEndCurve: BezierCurve = BezierCurve(
        start = Point(x = startScene.right, y = startScene.centerY),
        end = Point(x = endScene.centerX, y = endScene.centerY),
    )

    override val bottomLineStartCurve: BezierCurve = BezierCurve(
        start = Point(x = startScene.left, y = startScene.bottom),
        end = Point(x = endScene.left, y = endScene.bottom),
    )

    override val bottomLineEndCurve: BezierCurve = BezierCurve(
        start = Point(x = startScene.right, y = startScene.bottom),
        end = Point(x = endScene.right, y = endScene.top),
    )

    override fun fillPath(path: Path, progress: Float) {
        val topLineStart = topLineStartCurve.calculate(progress)
        val topLineEnd = topLineEndCurve.calculate(progress)

        path.moveTo(topLineStart)
        path.lineTo(topLineEnd)

        if (progress <= 0.5f) {
            val resolvedProgress = (progress * 2).coerceIn(PROGRESS_APPLICABLE_VALUES)
            val centerLineStart = centerLineStartCurve.calculate(resolvedProgress)
            val centerLineEnd = centerLineEndCurve.calculate(resolvedProgress)

            path.moveTo(centerLineStart)
            path.lineTo(centerLineEnd)
        }

        val bottomLineStart = bottomLineStartCurve.calculate(progress)
        val bottomLineEnd = bottomLineEndCurve.calculate(progress)

        path.moveTo(bottomLineStart)
        path.lineTo(bottomLineEnd)
    }
}