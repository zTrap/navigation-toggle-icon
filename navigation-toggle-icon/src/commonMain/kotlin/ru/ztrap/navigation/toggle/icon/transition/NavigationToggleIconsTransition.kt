package ru.ztrap.navigation.toggle.icon.transition

import androidx.compose.runtime.Immutable
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Path
import ru.ztrap.bezier.curve.BezierCurve
import ru.ztrap.bezier.curve.BezierScene
import ru.ztrap.navigation.toggle.icon.NavigationToggle

@Immutable
internal sealed interface NavigationToggleIconsTransition {

    fun fillPath(path: Path, progress: Float)

    sealed class Production : NavigationToggleIconsTransition {
        internal abstract val rotationDirection: NavigationToggle.RotationDirection

        internal val rotationPivot: Offset by lazy {
            Offset(
                x = startScene.centerX,
                y = startScene.centerY,
            )
        }

        protected abstract val startScene: BezierScene
        protected abstract val endScene: BezierScene

        protected abstract val topLineStartCurve: BezierCurve
        protected abstract val topLineEndCurve: BezierCurve

        protected abstract val centerLineStartCurve: BezierCurve
        protected abstract val centerLineEndCurve: BezierCurve

        protected abstract val bottomLineStartCurve: BezierCurve
        protected abstract val bottomLineEndCurve: BezierCurve
    }

    @Immutable
    class Static(
        private val iconType: NavigationToggle.IconType,
        sceneScaleFactor: Float,
    ) : NavigationToggleIconsTransition {
        private val arrow = ArrowToBurger(sceneScaleFactor)
        private val burger = BurgerToCross(sceneScaleFactor)
        private val cross = CrossToArrow(sceneScaleFactor)

        override fun fillPath(path: Path, progress: Float) {
            when (iconType) {
                NavigationToggle.IconType.Arrow -> arrow.fillPath(path, 0f)
                NavigationToggle.IconType.Burger -> burger.fillPath(path, 0f)
                NavigationToggle.IconType.Cross -> cross.fillPath(path, 0f)
            }
        }
    }
}