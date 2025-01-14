package com.chesire.nekome.core.preferences.flags

import android.content.Context
import androidx.annotation.StringRes
import com.chesire.nekome.core.R

/**
 * Themes that the application can be to set to use.
 */
enum class Theme(val value: Int, @StringRes val stringId: Int) {
    System(-1, R.string.settings_theme_system),
    Dark(2, R.string.settings_theme_dark),
    Light(1, R.string.settings_theme_light),
    DynamicDark(3, R.string.settings_theme_dynamic_dark),
    DynamicLight(4, R.string.settings_theme_dynamic_light);

    companion object {
        /**
         * Gets a map of [value] to the string acquired from the [stringId].
         */
        fun getValueMap(context: Context) = values()
            .associate { it.value to context.getString(it.stringId) }

        /**
         * Gets the [Theme] from a given [value], the [value] should be the value field
         * in the the enum class, but as a string. If this is null or cannot be found then [System]
         * will be returned as a default.
         */
        fun fromValue(value: String): Theme {
            return value.toIntOrNull()?.let { intValue ->
                values().find { it.value == intValue } ?: System
            } ?: System
        }
    }
}
