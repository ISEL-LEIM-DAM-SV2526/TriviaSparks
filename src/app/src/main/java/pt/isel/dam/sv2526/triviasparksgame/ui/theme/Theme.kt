package pt.isel.dam.sv2526.triviasparksgame.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color


/**
 * ─────────────────────────────────────────────────────────────────────────────
 * TriviaSparks Theme System — Final Entry Point
 * ─────────────────────────────────────────────────────────────────────────────
 *
 * ⚠️ THIS IS THE MAIN THEME CONFIGURATION FILE
 *
 * This file defines:
 * - Light & Dark Material3 color schemes
 * - Extended design system colors (semantic + category palette)
 * - Typography system
 * - Shape system
 *
 * Wrap your app with:
 *
 * ```
 * TriviaSparksTheme {
 *     Screen()
 * }
 * ```
 *
 * All Material3 components automatically inherit these values.
 *
 * ─────────────────────────────────────────────────────────────────────────────
 */

// ─────────────────────────────────────────────────────────────────────────────
// LIGHT COLOR SCHEME
// ─────────────────────────────────────────────────────────────────────────────

/**
 * Light theme color mapping.
 *
 * Maps TriviaSparks design tokens to Material3 roles.
 * All UI components automatically use these values.
 */
private val LightColorScheme = lightColorScheme(

    // Primary — Violet (brand color)
    primary              = Violet400,
    onPrimary            = White,
    primaryContainer     = Violet100,
    onPrimaryContainer   = Violet800,

    // Secondary — Coral (XP / accents)
    secondary            = Coral400,
    onSecondary          = White,
    secondaryContainer   = Coral100,
    onSecondaryContainer = Coral900,

    // Tertiary — Mint (success / easy state)
    tertiary             = MintGreen,
    onTertiary           = White,
    tertiaryContainer    = MintGreenContainer,
    onTertiaryContainer  = Color(0xFF0A4033),

    // Error — Strawberry (hard / destructive)
    error                = Strawberry,
    onError              = White,
    errorContainer       = StrawberryContainer,
    onErrorContainer     = Color(0xFF5C001A),

    // Background — app base surface
    background           = LavenderMist,
    onBackground         = DeepGrape,

    // Surface — cards, sheets
    surface              = SurfaceLight,
    onSurface            = DeepGrape,
    surfaceVariant       = SurfaceVariantLight,
    onSurfaceVariant     = DustyViolet,

    // Inverse surfaces — snackbars, dark overlays
    inverseSurface       = DeepGrape,
    inverseOnSurface     = SoftLavender,
    inversePrimary       = Violet200,

    // Borders
    outline              = LilacBorder,
    outlineVariant       = LilacBorderSubtle,

    // Scrim — modal overlay dimming
    scrim                = ScrimLight,

    surfaceTint          = Violet400
)


// ─────────────────────────────────────────────────────────────────────────────
// DARK COLOR SCHEME
// ─────────────────────────────────────────────────────────────────────────────

/**
 * Dark theme color mapping.
 *
 * Optimised for low-light readability while preserving brand identity.
 */
private val DarkColorScheme = darkColorScheme(

    primary              = Violet400,
    onPrimary            = White,
    primaryContainer     = Color(0xFF3D3580),
    onPrimaryContainer   = Violet100,

    secondary            = Coral400,
    onSecondary          = White,
    secondaryContainer   = Color(0xFF6B2D18),
    onSecondaryContainer = Coral100,

    tertiary             = MintGreen,
    onTertiary           = Color(0xFF003826),
    tertiaryContainer    = Color(0xFF0A5240),
    onTertiaryContainer  = MintGreenContainer,

    error                = Strawberry,
    onError              = White,
    errorContainer       = Color(0xFF5C001A),
    onErrorContainer     = StrawberryContainer,

    background           = MidnightGrape,
    onBackground         = SoftLavender,

    surface              = SurfaceDark,
    onSurface            = SoftLavender,
    surfaceVariant       = SurfaceVariantDark,
    onSurfaceVariant     = MutedViolet,

    inverseSurface       = SoftLavender,
    inverseOnSurface     = DeepGrape,
    inversePrimary       = Violet600,

    outline              = DarkLilacBorder,
    outlineVariant       = DarkLilacBorderSubtle,

    scrim                = ScrimDark,
    surfaceTint          = Violet400
)


// ─────────────────────────────────────────────────────────────────────────────
// EXTENDED DESIGN SYSTEM COLORS
// ─────────────────────────────────────────────────────────────────────────────

/**
 * Custom colors not covered by Material3 ColorScheme.
 *
 * Access via:
 * MaterialTheme.triviasparks.easy
 */
data class DreamscapeExtendedColors(

    // Difficulty system (semantic gameplay states)
    val easy: Color,
    val easyOnLight: Color,
    val easyContainer: Color,

    val medium: Color,
    val mediumOnLight: Color,
    val mediumContainer: Color,

    val hard: Color,
    val hardOnLight: Color,
    val hardContainer: Color,

    // Category system (quiz classification colors)
    val categoryScience: Color,
    val categorySports: Color,
    val categoryHistory: Color,
    val categoryTechnology: Color,
    val categoryArt: Color,
    val categoryMusic: Color,
    val categoryFilm: Color,
    val categoryGeography: Color,

    // Game feedback colors
    val scoreNumber: Color,
    val xpColour: Color,

    // Decorative / elevation effects
    val cardGlow: Color
)


// ─────────────────────────────────────────────────────────────────────────────
// LIGHT EXTENDED COLORS
// ─────────────────────────────────────────────────────────────────────────────

private val LightExtendedColors = DreamscapeExtendedColors(

    easy               = MintGreen,
    easyOnLight        = MintGreenOnLight,
    easyContainer      = MintGreenContainer,

    medium             = SunnyYellow,
    mediumOnLight      = SunnyYellowOnLight,
    mediumContainer    = SunnyYellowContainer,

    hard               = Strawberry,
    hardOnLight        = StrawberryOnLight,
    hardContainer      = StrawberryContainer,

    categoryScience    = CategoryScience,
    categorySports     = CategorySports,
    categoryHistory    = CategoryHistory,
    categoryTechnology = CategoryTechnology,
    categoryArt        = CategoryArt,
    categoryMusic      = CategoryMusic,
    categoryFilm       = CategoryFilm,
    categoryGeography  = CategoryGeography,

    scoreNumber        = Violet400,
    xpColour           = Coral400,
    cardGlow           = Violet400
)


// ─────────────────────────────────────────────────────────────────────────────
// DARK EXTENDED COLORS
// ─────────────────────────────────────────────────────────────────────────────

private val DarkExtendedColors = DreamscapeExtendedColors(

    easy               = MintGreen,
    easyOnLight        = MintGreen,
    easyContainer      = Color(0xFF0A5240),

    medium             = SunnyYellow,
    mediumOnLight      = SunnyYellow,
    mediumContainer    = Color(0xFF4A3800),

    hard               = Strawberry,
    hardOnLight        = Strawberry,
    hardContainer      = Color(0xFF5C001A),

    categoryScience    = CategoryScience,
    categorySports     = CategorySports,
    categoryHistory    = CategoryHistory,
    categoryTechnology = CategoryTechnology,
    categoryArt        = CategoryArt,
    categoryMusic      = CategoryMusic,
    categoryFilm       = CategoryFilm,
    categoryGeography  = CategoryGeography,

    scoreNumber        = Violet400,
    xpColour           = Coral400,
    cardGlow           = Violet400
)


// ─────────────────────────────────────────────────────────────────────────────
// COMPOSITION LOCAL
// ─────────────────────────────────────────────────────────────────────────────

/**
 * Internal CompositionLocal storing extended theme colors.
 */
val LocalTriviaSparksColors =
    staticCompositionLocalOf { LightExtendedColors }


// ─────────────────────────────────────────────────────────────────────────────
// THEME ENTRY POINT
// ─────────────────────────────────────────────────────────────────────────────

/**
 * Main TriviaSparks theme wrapper.
 *
 * Provides:
 * - Material3 color scheme
 * - Typography system
 * - Shape system
 * - Extended custom colors
 *
 * Usage:
 * ```
 * TriviaSparksTheme {
 *     App()
 * }
 * ```
 */
@Composable
fun TriviaSparksTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    val extendedColors = if (darkTheme) DarkExtendedColors else LightExtendedColors

    CompositionLocalProvider(
        LocalTriviaSparksColors provides extendedColors
    ) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = TriviaSparksTypography,
            shapes = TriviaSparksShapes,
            content = content
        )
    }
}


// ─────────────────────────────────────────────────────────────────────────────
// EXTENDED ACCESSOR
// ─────────────────────────────────────────────────────────────────────────────

/**
 * Access extended TriviaSparks colors anywhere in UI.
 *
 * Example:
 * MaterialTheme.triviasparks.easy
 * MaterialTheme.triviasparks.scoreNumber
 * MaterialTheme.triviasparks.categoryScience
 */
val MaterialTheme.triviasparks: DreamscapeExtendedColors
    @Composable
    @ReadOnlyComposable
    get() = LocalTriviaSparksColors.current