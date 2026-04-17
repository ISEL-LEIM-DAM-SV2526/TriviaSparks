package pt.isel.dam.sv2526.triviasparksgame.ui.theme

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

/**
 * ─────────────────────────────────────────────────────────────────────────────
 * TriviaSparks Shapes System — Design Tokens
 * ─────────────────────────────────────────────────────────────────────────────
 *
 * ⚠️ THIS FILE DEFINES THE SHAPE SYSTEM FOR THE ENTIRE APP
 *
 * Shapes control corner radius consistency across Material3 components.
 * This ensures all UI elements (cards, buttons, chips, dialogs) follow
 * a unified visual language.
 *
 * ─────────────────────────────────────────────────────────────────────────────
 *
 * MATERIAL 3 SHAPES MAPPING
 *
 * These shapes are automatically used by Material3 components unless overridden.
 *
 * extraSmall → chips, badges, pills
 * small      → tooltips, snackbars, compact UI
 * medium     → buttons, text fields, FABs, small cards
 * large      → dialogs, bottom sheets, navigation drawers
 * extraLarge → main cards, game tiles, quiz cards
 */
val TriviaSparksShapes = Shapes(

    extraSmall = RoundedCornerShape(percent = 50), // pill shape (chips, badges)
    small = RoundedCornerShape(8.dp),         // subtle rounding
    medium = RoundedCornerShape(16.dp),        // standard components
    large = RoundedCornerShape(20.dp),        // overlays / dialogs
    extraLarge = RoundedCornerShape(24.dp)         // main cards / game tiles
)

/**
 * ─────────────────────────────────────────────────────────────────────────────
 * RAW RADIUS TOKENS
 * ─────────────────────────────────────────────────────────────────────────────
 *
 * These are raw dimension values used when MaterialTheme shapes are not
 * suitable (e.g. Modifier.clip(), background(), border()).
 *
 * Usage:
 * - Modifier.clip(RoundedCornerShape(TriviaSparksRadius.Card))
 * - Custom UI components outside MaterialShape system
 */
object TriviaSparksRadius {

    val Card      = 24.dp  // main content cards (quiz tiles)
    val Button    = 16.dp  // all buttons
    val Chip      = 99.dp  // fully pill-shaped chips / badges
    val IconBox   = 12.dp  // category icon containers
    val SearchBar = 99.dp  // pill-shaped search input
    val Dialog    = 20.dp  // dialogs / bottom sheets radius
    val Avatar    = 999.dp // fully circular avatars
    val Tag       = 8.dp   // small labels, tags, version chips
}

/**
 * ─────────────────────────────────────────────────────────────────────────────
 * NAMED SHAPE OBJECTS (CONVENIENCE API)
 * ─────────────────────────────────────────────────────────────────────────────
 *
 * Predefined RoundedCornerShape instances for direct composable usage.
 * Avoids repeating dp values in UI code.
 *
 * Usage:
 * - Modifier.clip(CardShape)
 * - Modifier.background(shape = ChipShape)
 */
val CardShape      = RoundedCornerShape(TriviaSparksRadius.Card)
val ButtonShape    = RoundedCornerShape(TriviaSparksRadius.Button)
val ChipShape      = RoundedCornerShape(TriviaSparksRadius.Chip)
val IconBoxShape   = RoundedCornerShape(TriviaSparksRadius.IconBox)
val SearchBarShape = RoundedCornerShape(TriviaSparksRadius.SearchBar)
val AvatarShape    = RoundedCornerShape(TriviaSparksRadius.Avatar)

/**
 * Bottom sheet shape with only top corners rounded.
 *
 * Used for:
 * - Modal bottom sheets
 * - Sliding panels
 * - Full-width overlays
 */
val BottomSheetShape = RoundedCornerShape(
    topStart    = TriviaSparksRadius.Dialog,
    topEnd      = TriviaSparksRadius.Dialog,
    bottomStart = 0.dp,
    bottomEnd   = 0.dp
)