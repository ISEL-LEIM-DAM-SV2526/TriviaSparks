package pt.isel.dam.sv2526.triviasparksgame.ui.theme


import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Shapes
import androidx.compose.ui.unit.dp

// ─────────────────────────────────────────────────────────────────────────────
// MATERIAL3 SHAPES
// Maps TriviaSparks radius tokens to the M3 Shapes scale so that Material3
// components (Button, Card, Chip, Dialog, etc.) automatically use the
// correct corners without any per-composable override.
// ─────────────────────────────────────────────────────────────────────────────
val TriviaSparksShapes = Shapes(

    // extraSmall — chips, badges, stat pills, small tags
    // Material3 uses this for: AssistChip, FilterChip, SuggestionChip, InputChip
    extraSmall = RoundedCornerShape(percent = 50),   // fully pill

    // small — tooltips, snackbars, search bar
    small = RoundedCornerShape(8.dp),

    // medium — buttons, text fields, icon containers, small cards
    // Material3 uses this for: Button, OutlinedButton, TextField, FloatingActionButton
    medium = RoundedCornerShape(16.dp),

    // large — bottom sheets top edge, dialogs, navigation drawer
    // Material3 uses this for: AlertDialog, ModalBottomSheet, NavigationDrawer
    large = RoundedCornerShape(20.dp),

    // extraLarge — main content cards, game tiles, quiz cards
    // Material3 uses this for: Card (we override the default 12dp to 24dp)
    extraLarge = RoundedCornerShape(24.dp)
)

// ─────────────────────────────────────────────────────────────────────────────
// DIRECT RADIUS TOKENS
// Use these when you need the raw dp value without going through MaterialTheme,
// e.g. in Modifier.clip(), background(), or border() calls.
// ─────────────────────────────────────────────────────────────────────────────
object TriviaSparksRadius {
    val Card       = 24.dp   // main cards — game tile feel
    val Button     = 16.dp   // all buttons
    val Chip       = 99.dp   // fully pill — difficulty chips, badges, stat pills
    val IconBox    = 12.dp   // category icon containers
    val SearchBar  = 99.dp   // pill-shaped search inputs
    val Dialog     = 20.dp   // bottom sheets, alert dialogs (top corners only)
    val Avatar     = 999.dp  // fully circular — profiles, friend cards
    val Tag        = 8.dp    // small inline labels, version tags
}

// ─────────────────────────────────────────────────────────────────────────────
// NAMED SHAPE OBJECTS
// Convenience shapes for direct use in composables without dp arithmetic.
// ─────────────────────────────────────────────────────────────────────────────
val CardShape      = RoundedCornerShape(TriviaSparksRadius.Card)
val ButtonShape    = RoundedCornerShape(TriviaSparksRadius.Button)
val ChipShape      = RoundedCornerShape(TriviaSparksRadius.Chip)
val IconBoxShape   = RoundedCornerShape(TriviaSparksRadius.IconBox)
val SearchBarShape = RoundedCornerShape(TriviaSparksRadius.SearchBar)
val AvatarShape    = RoundedCornerShape(TriviaSparksRadius.Avatar)

// Bottom sheet — only top two corners are rounded
val BottomSheetShape = RoundedCornerShape(
    topStart     = TriviaSparksRadius.Dialog,
    topEnd       = TriviaSparksRadius.Dialog,
    bottomStart  = 0.dp,
    bottomEnd    = 0.dp
)
