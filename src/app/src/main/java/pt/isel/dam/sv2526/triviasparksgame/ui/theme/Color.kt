package pt.isel.dam.sv2526.triviasparksgame.ui.theme

import androidx.compose.ui.graphics.Color

// ─────────────────────────────────────────────────────────────────────────────
// PRIMARY — Soft Violet
// ─────────────────────────────────────────────────────────────────────────────
val Violet400          = Color(0xFF8B7FE8)  // primary — buttons, active states, links
val Violet600          = Color(0xFF6C5FD6)  // primary pressed / darker variant
val Violet100          = Color(0xFFD4CFFA)  // primary container light
val Violet200          = Color(0xFFAFA9EC)  // primary container mid
val Violet800          = Color(0xFF3C3489)  // on primary container dark
val Violet900          = Color(0xFF2A2260)  // deepest violet

// ─────────────────────────────────────────────────────────────────────────────
// SECONDARY — Coral Peach
// ─────────────────────────────────────────────────────────────────────────────
val Coral400           = Color(0xFFFF8C69)  // secondary — XP badges, secondary actions
val Coral600           = Color(0xFFE06A46)  // secondary pressed
val Coral100           = Color(0xFFFFD9CD)  // secondary container light
val Coral900           = Color(0xFF5A2210)  // on secondary container dark

// ─────────────────────────────────────────────────────────────────────────────
// DIFFICULTY / SEMANTIC
// ─────────────────────────────────────────────────────────────────────────────
val MintGreen          = Color(0xFF6ECFB0)  // easy difficulty — success states
val MintGreenOnLight   = Color(0xFF3AAB87)  // easy text on white/light backgrounds
val MintGreenContainer = Color(0xFFBFF5E5)  // easy chip background

val SunnyYellow        = Color(0xFFFFD166)  // medium difficulty — warning states
val SunnyYellowOnLight = Color(0xFFB8922A)  // medium text on white/light backgrounds
val SunnyYellowContainer = Color(0xFFFFF0BA) // medium chip background

val Strawberry         = Color(0xFFFF6B8A)  // hard difficulty — error states
val StrawberryOnLight  = Color(0xFFC42050)  // hard text on white/light backgrounds
val StrawberryContainer = Color(0xFFFFD6E0) // hard chip background

// ─────────────────────────────────────────────────────────────────────────────
// BACKGROUND
// ─────────────────────────────────────────────────────────────────────────────
val LavenderMist       = Color(0xFFF5F3FF)  // light background — purple dreamlike tint
val MidnightGrape      = Color(0xFF12101F)  // dark background — deep purple-black

// ─────────────────────────────────────────────────────────────────────────────
// SURFACE / CARDS
// ─────────────────────────────────────────────────────────────────────────────
val SurfaceLight       = Color(0xFFFFFFFF)  // light card — pure white
val SurfaceVariantLight = Color(0xFFF0EEFF) // light secondary card / inner sections
val SurfaceDark        = Color(0xFF1E1B35)  // dark card — violet tile
val SurfaceVariantDark = Color(0xFF252245)  // dark secondary card / inner sections

// ─────────────────────────────────────────────────────────────────────────────
// TEXT
// ─────────────────────────────────────────────────────────────────────────────
val DeepGrape          = Color(0xFF1E1642)  // primary text — light mode
val DustyViolet        = Color(0xFF7B7599)  // secondary text — light mode
val SoftLavender       = Color(0xFFEDE9FF)  // primary text — dark mode
val MutedViolet        = Color(0xFF9E9BBF)  // secondary text — dark mode

// ─────────────────────────────────────────────────────────────────────────────
// BORDER
// ─────────────────────────────────────────────────────────────────────────────
val LilacBorder        = Color(0xFFD8D4F0)  // light border — card borders, dividers
val LilacBorderSubtle  = Color(0xFFEBE8F8)  // light border subtle — dividers
val DarkLilacBorder    = Color(0xFF3D3860)  // dark border
val DarkLilacBorderSubtle = Color(0xFF2A2750) // dark border subtle

// ─────────────────────────────────────────────────────────────────────────────
// CATEGORY ICON COLOURS
// ─────────────────────────────────────────────────────────────────────────────
val CategoryScience    = Color(0xFF4A90D9)  // blue
val CategorySports     = Coral400           // coral (reuses secondary)
val CategoryHistory    = SunnyYellow        // yellow (reuses medium difficulty)
val CategoryTechnology = Violet400          // violet (reuses primary)
val CategoryArt        = Strawberry         // pink (reuses hard difficulty)
val CategoryMusic      = MintGreen          // mint (reuses easy difficulty)
val CategoryFilm       = Color(0xFFFF9F43)  // orange
val CategoryGeography  = Color(0xFF48DBFB)  // teal

// ─────────────────────────────────────────────────────────────────────────────
// UTILITY
// ─────────────────────────────────────────────────────────────────────────────
val White              = Color(0xFFFFFFFF)
val Black              = Color(0xFF000000)
val Transparent        = Color(0x00000000)

// Overlay colours
val ScrimLight         = Color(0x991E1642)  // modal scrim — light mode
val ScrimDark          = Color(0xCC12101F)  // modal scrim — dark mode