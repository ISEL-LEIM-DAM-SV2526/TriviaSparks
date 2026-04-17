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

// ─────────────────────────────────────────────────────────────────────────────
// LIGHT COLOUR SCHEME
// Maps Dreamscape colour tokens to Material3 ColorScheme roles.
// Every M3 component automatically uses the correct colour for its role.
// ─────────────────────────────────────────────────────────────────────────────
private val LightColorScheme = lightColorScheme(

    // Primary — violet
    primary                  = Violet400,
    onPrimary                = White,
    primaryContainer         = Violet100,
    onPrimaryContainer       = Violet800,

    // Secondary — coral peach
    secondary                = Coral400,
    onSecondary              = White,
    secondaryContainer       = Coral100,
    onSecondaryContainer     = Coral900,

    // Tertiary — mint green (easy / success semantic)
    tertiary                 = MintGreen,
    onTertiary               = White,
    tertiaryContainer        = MintGreenContainer,
    onTertiaryContainer      = Color(0xFF0A4033),

    // Error — strawberry red (hard difficulty / destructive actions)
    error                    = Strawberry,
    onError                  = White,
    errorContainer           = StrawberryContainer,
    onErrorContainer         = Color(0xFF5C001A),

    // Background — lavender mist
    background               = LavenderMist,
    onBackground             = DeepGrape,

    // Surface — card backgrounds
    surface                  = SurfaceLight,
    onSurface                = DeepGrape,
    surfaceVariant           = SurfaceVariantLight,
    onSurfaceVariant         = DustyViolet,

    // Inverse surface — used for Snackbar
    inverseSurface           = DeepGrape,
    inverseOnSurface         = SoftLavender,
    inversePrimary           = Violet200,

    // Outline — borders and dividers
    outline                  = LilacBorder,
    outlineVariant           = LilacBorderSubtle,

    // Scrim — modal overlay
    scrim                    = ScrimLight,

    // Surface tones
    surfaceTint              = Violet400
)

// ─────────────────────────────────────────────────────────────────────────────
// DARK COLOUR SCHEME
// ─────────────────────────────────────────────────────────────────────────────
private val DarkColorScheme = darkColorScheme(

    // Primary — violet (unchanged — works well on dark)
    primary                  = Violet400,
    onPrimary                = White,
    primaryContainer         = Color(0xFF3D3580),
    onPrimaryContainer       = Violet100,

    // Secondary — coral peach (unchanged)
    secondary                = Coral400,
    onSecondary              = White,
    secondaryContainer       = Color(0xFF6B2D18),
    onSecondaryContainer     = Coral100,

    // Tertiary — mint green (unchanged)
    tertiary                 = MintGreen,
    onTertiary               = Color(0xFF003826),
    tertiaryContainer        = Color(0xFF0A5240),
    onTertiaryContainer      = MintGreenContainer,

    // Error — strawberry (unchanged)
    error                    = Strawberry,
    onError                  = White,
    errorContainer           = Color(0xFF5C001A),
    onErrorContainer         = StrawberryContainer,

    // Background — midnight grape
    background               = MidnightGrape,
    onBackground             = SoftLavender,

    // Surface — dark violet tiles
    surface                  = SurfaceDark,
    onSurface                = SoftLavender,
    surfaceVariant           = SurfaceVariantDark,
    onSurfaceVariant         = MutedViolet,

    // Inverse surface
    inverseSurface           = SoftLavender,
    inverseOnSurface         = DeepGrape,
    inversePrimary           = Violet600,

    // Outline
    outline                  = DarkLilacBorder,
    outlineVariant           = DarkLilacBorderSubtle,

    // Scrim
    scrim                    = ScrimDark,

    // Surface tint — subtle violet overlay on elevated dark surfaces
    surfaceTint              = Violet400
)

// ─────────────────────────────────────────────────────────────────────────────
// EXTENDED COLOURS
// Tokens that have no equivalent role in Material3 ColorScheme.
// Passed via CompositionLocal so any composable can access them.
// ─────────────────────────────────────────────────────────────────────────────
data class DreamscapeExtendedColors(

    // Difficulty colours — the semantic trio
    val easy: Color,
    val easyOnLight: Color,        // darker variant for text on light backgrounds
    val easyContainer: Color,      // chip/badge background fill

    val medium: Color,
    val mediumOnLight: Color,
    val mediumContainer: Color,

    val hard: Color,
    val hardOnLight: Color,
    val hardContainer: Color,

    // Category icon colours
    val categoryScience: Color,
    val categorySports: Color,
    val categoryHistory: Color,
    val categoryTechnology: Color,
    val categoryArt: Color,
    val categoryMusic: Color,
    val categoryFilm: Color,
    val categoryGeography: Color,

    // Score display — large chunky numbers
    val scoreNumber: Color,

    // XP reward colour
    val xpColour: Color,

    // Card shadow colour — used in elevation/glow effects
    val cardGlow: Color
)

private val LightExtendedColors = DreamscapeExtendedColors(
    easy                 = MintGreen,
    easyOnLight          = MintGreenOnLight,
    easyContainer        = MintGreenContainer,

    medium               = SunnyYellow,
    mediumOnLight        = SunnyYellowOnLight,
    mediumContainer      = SunnyYellowContainer,

    hard                 = Strawberry,
    hardOnLight          = StrawberryOnLight,
    hardContainer        = StrawberryContainer,

    categoryScience      = CategoryScience,
    categorySports       = CategorySports,
    categoryHistory      = CategoryHistory,
    categoryTechnology   = CategoryTechnology,
    categoryArt          = CategoryArt,
    categoryMusic        = CategoryMusic,
    categoryFilm         = CategoryFilm,
    categoryGeography    = CategoryGeography,

    scoreNumber          = Violet400,
    xpColour             = Coral400,
    cardGlow             = Violet400
)

private val DarkExtendedColors = DreamscapeExtendedColors(
    // In dark mode, use full-brightness colours directly —
    // the dark background provides enough contrast without needing dimmer variants
    easy                 = MintGreen,
    easyOnLight          = MintGreen,        // full brightness on dark bg
    easyContainer        = Color(0xFF0A5240),

    medium               = SunnyYellow,
    mediumOnLight        = SunnyYellow,
    mediumContainer      = Color(0xFF4A3800),

    hard                 = Strawberry,
    hardOnLight          = Strawberry,
    hardContainer        = Color(0xFF5C001A),

    categoryScience      = CategoryScience,
    categorySports       = CategorySports,
    categoryHistory      = CategoryHistory,
    categoryTechnology   = CategoryTechnology,
    categoryArt          = CategoryArt,
    categoryMusic        = CategoryMusic,
    categoryFilm         = CategoryFilm,
    categoryGeography    = CategoryGeography,

    scoreNumber          = Violet400,
    xpColour             = Coral400,
    cardGlow             = Violet400
)

// ─────────────────────────────────────────────────────────────────────────────
// COMPOSITION LOCAL
// ─────────────────────────────────────────────────────────────────────────────
val LocalTriviaSparksColors = staticCompositionLocalOf { LightExtendedColors }

// ─────────────────────────────────────────────────────────────────────────────
// MAIN THEME COMPOSABLE
// ─────────────────────────────────────────────────────────────────────────────
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
            typography  = TriviaSparksTypography,
            shapes      = TriviaSparksShapes,
            content     = content
        )
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// CONVENIENCE ACCESSOR
// Access extended colours anywhere with: MaterialTheme.dreamscape
// e.g. MaterialTheme.dreamscape.easy
//      MaterialTheme.dreamscape.scoreNumber
//      MaterialTheme.dreamscape.categoryScience
// ─────────────────────────────────────────────────────────────────────────────
val MaterialTheme.triviasparks: DreamscapeExtendedColors
    @Composable
    @ReadOnlyComposable
    get() = LocalTriviaSparksColors.current
