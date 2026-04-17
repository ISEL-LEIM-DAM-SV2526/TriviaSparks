package pt.isel.dam.sv2526.triviasparksgame.ui.theme

import androidx.compose.ui.graphics.Color

/**
 * ─────────────────────────────────────────────────────────────────────────────
 * TriviaSparks Color System — Design Tokens
 * ─────────────────────────────────────────────────────────────────────────────
 *
 *
 * These colors define the entire TriviaSparks visual identity system.
 * They are used through MaterialTheme.colorScheme and custom extensions
 * (e.g. MaterialTheme.dreamscape).
 *
 * Do not use hardcoded colors in UI — always reference these tokens.
 *
 * ─────────────────────────────────────────────────────────────────────────────
 *
 * PRIMARY — VIOLET (Core Brand Color)
 *
 * Used for:
 * - Primary buttons
 * - Active states
 * - Key interactive elements
 * - Navigation highlights
 */
val Violet400 = Color(0xFF8B7FE8) // primary
val Violet600 = Color(0xFF6C5FD6) // pressed / active
val Violet100 = Color(0xFFD4CFFA) // container (light)
val Violet200 = Color(0xFFAFA9EC) // container (mid)
val Violet800 = Color(0xFF3C3489) // on-container (dark text/bg)
val Violet900 = Color(0xFF2A2260) // deepest brand tone

// ─────────────────────────────────────────────────────────────────────────────
// SECONDARY — CORAL PEACH
//
// Used for:
// - XP rewards
// - Secondary actions
// - Highlight accents
// ─────────────────────────────────────────────────────────────────────────────
val Coral400 = Color(0xFFFF8C69)
val Coral600 = Color(0xFFE06A46)
val Coral100 = Color(0xFFFFD9CD)
val Coral900 = Color(0xFF5A2210)

// ─────────────────────────────────────────────────────────────────────────────
// DIFFICULTY / SEMANTIC COLORS
//
// Used for:
// - Quiz difficulty indicators
// - Success / warning / error states
// - Feedback chips and badges
// ─────────────────────────────────────────────────────────────────────────────
val MintGreen            = Color(0xFF6ECFB0) // easy / success
val MintGreenOnLight     = Color(0xFF3AAB87)
val MintGreenContainer   = Color(0xFFBFF5E5)

val SunnyYellow          = Color(0xFFFFD166) // medium / warning
val SunnyYellowOnLight   = Color(0xFFB8922A)
val SunnyYellowContainer = Color(0xFFFFF0BA)

val Strawberry           = Color(0xFFFF6B8A) // hard / error
val StrawberryOnLight    = Color(0xFFC42050)
val StrawberryContainer  = Color(0xFFFFD6E0)

// ─────────────────────────────────────────────────────────────────────────────
// BACKGROUNDS
//
// Used for:
// - Screen backgrounds
// - App surfaces (light/dark themes)
// ─────────────────────────────────────────────────────────────────────────────
val LavenderMist   = Color(0xFFF5F3FF) // light mode background
val MidnightGrape  = Color(0xFF12101F) // dark mode background

// ─────────────────────────────────────────────────────────────────────────────
// SURFACES (CARDS / PANELS)
//
// Used for:
// - Cards
// - Containers
// - Elevated UI sections
// ─────────────────────────────────────────────────────────────────────────────
val SurfaceLight       = Color(0xFFFFFFFF)
val SurfaceVariantLight = Color(0xFFF0EEFF)

val SurfaceDark        = Color(0xFF1E1B35)
val SurfaceVariantDark = Color(0xFF252245)

// ─────────────────────────────────────────────────────────────────────────────
// TEXT COLORS
//
// Used for:
// - Primary and secondary text in light/dark mode
// ─────────────────────────────────────────────────────────────────────────────
val DeepGrape     = Color(0xFF1E1642) // primary text (light mode)
val DustyViolet   = Color(0xFF7B7599) // secondary text (light mode)

val SoftLavender  = Color(0xFFEDE9FF) // primary text (dark mode)
val MutedViolet   = Color(0xFF9E9BBF) // secondary text (dark mode)

// ─────────────────────────────────────────────────────────────────────────────
// BORDERS / DIVIDERS
//
// Used for:
// - Card outlines
// - Separators
// - Subtle UI structure
// ─────────────────────────────────────────────────────────────────────────────
val LilacBorder            = Color(0xFFD8D4F0)
val LilacBorderSubtle     = Color(0xFFEBE8F8)
val DarkLilacBorder       = Color(0xFF3D3860)
val DarkLilacBorderSubtle = Color(0xFF2A2750)

// ─────────────────────────────────────────────────────────────────────────────
// CATEGORY COLORS
//
// Used for:
// - Category icons
// - Category chips
// - Visual grouping of quiz topics
// ─────────────────────────────────────────────────────────────────────────────
val CategoryScience    = Color(0xFF4A90D9)
val CategorySports     = Coral400
val CategoryHistory    = SunnyYellow
val CategoryTechnology = Violet400
val CategoryArt        = Strawberry
val CategoryMusic      = MintGreen
val CategoryFilm       = Color(0xFFFF9F43)
val CategoryGeography  = Color(0xFF48DBFB)

// ─────────────────────────────────────────────────────────────────────────────
// UTILITY COLORS
//
// Used for:
// - Generic UI needs
// - Debug / overlays / system usage
// ─────────────────────────────────────────────────────────────────────────────
val White       = Color(0xFFFFFFFF)
val Black       = Color(0xFF000000)
val Transparent = Color(0x00000000)

// ─────────────────────────────────────────────────────────────────────────────
// OVERLAYS / SCRIM
//
// Used for:
// - Dialog backgrounds
// - Bottom sheets
// - Modal dimming effects
// ─────────────────────────────────────────────────────────────────────────────
val ScrimLight = Color(0x991E1642)
val ScrimDark  = Color(0xCC12101F)