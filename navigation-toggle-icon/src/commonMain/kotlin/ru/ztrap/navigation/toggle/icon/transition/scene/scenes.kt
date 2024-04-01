package ru.ztrap.navigation.toggle.icon.transition.scene

import ru.ztrap.bezier.curve.BezierScene

/** 24dp x 24dp scene with 3dp horizontal padding and 7dp vertical */
internal fun createBurgerScene(scaleFactor: Float): BezierScene {
    return BezierScene(
        left = 3f * scaleFactor,
        top = 7f * scaleFactor,
        right = 21f * scaleFactor,
        bottom = 17f * scaleFactor,
    )
}

/** 24dp x 24dp scene with 5dp padding */
internal fun createArrowScene(scaleFactor: Float): BezierScene {
    return BezierScene(
        left = 5f * scaleFactor,
        top = 5f * scaleFactor,
        right = 19f * scaleFactor,
        bottom = 19f * scaleFactor,
    )
}

/** 24dp x 24dp scene with 6dp padding */
internal fun createCrossScene(scaleFactor: Float): BezierScene {
    return BezierScene(
        left = 6f * scaleFactor,
        top = 6f * scaleFactor,
        right = 18f * scaleFactor,
        bottom = 18f * scaleFactor,
    )
}