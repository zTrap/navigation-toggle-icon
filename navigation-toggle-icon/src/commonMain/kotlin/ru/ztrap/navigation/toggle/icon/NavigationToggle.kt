package ru.ztrap.navigation.toggle.icon

import androidx.compose.runtime.Stable
import kotlin.jvm.JvmInline

public object NavigationToggle {
    public enum class IconType {
        Burger, Arrow, Cross
    }

    @JvmInline
    internal value class RotationDirection internal constructor(private val sign: Byte) {

        @Stable
        fun getSign(isRtl: Boolean): Byte {
            return if (isRtl) sign.rotateLeft(1) else sign
        }

        companion object {
            val Clockwise: RotationDirection = RotationDirection(1)
            val CounterClockwise: RotationDirection = RotationDirection(-1)
        }
    }
}