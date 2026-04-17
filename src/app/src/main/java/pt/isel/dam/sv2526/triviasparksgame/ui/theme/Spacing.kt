package pt.isel.dam.sv2526.triviasparksgame.ui.theme
import androidx.compose.ui.unit.dp

// ─────────────────────────────────────────────────────────────────────────────
// SPACING TOKENS
// Use these instead of hardcoded dp values throughout the app.
// Consistent spacing creates visual rhythm across all screens.
// ─────────────────────────────────────────────────────────────────────────────
object Spacing {
    val xs     =  4.dp   // icon-to-label gap, tight label spacing
    val sm     =  8.dp   // badge internal padding, tight row gaps
    val md     = 12.dp   // gap between cards in a list, chip gaps
    val lg     = 16.dp   // card internal padding, row gaps inside cards
    val xl     = 20.dp   // screen horizontal edge padding (standard)
    val xxl    = 24.dp   // section gaps, card top/bottom padding on detail screens
    val xxxl   = 32.dp   // large section separators, hero top padding

    // Semantic aliases — use these in composables for self-documenting code
    val screenEdge        = xl     // 20dp — horizontal padding on all screens
    val cardPadding       = lg     // 16dp — internal card padding
    val cardGap           = md     // 12dp — vertical gap between list cards
    val sectionGap        = xxl    // 24dp — gap between screen sections
    val iconToText        = lg     // 16dp — gap between icon container and text
    val chipHorizontal    = 14.dp  // horizontal padding inside chips
    val chipVertical      =  7.dp  // vertical padding inside chips
    val badgeHorizontal   = 10.dp  // horizontal padding inside count badges
    val badgeVertical     =  4.dp  // vertical padding inside count badges
}

// ─────────────────────────────────────────────────────────────────────────────
// ELEVATION TOKENS
// Used with Modifier.shadow() or Card elevation parameters.
// The game theme uses subtle glow shadows rather than harsh elevation.
// ─────────────────────────────────────────────────────────────────────────────
object Elevation {
    val none    =  0.dp  // flat surfaces — buttons, bottom navigation
    val low     =  2.dp  // slight lift — stat pills, inner cards
    val medium  =  4.dp  // cards on screen — main content cards
    val high    =  8.dp  // floating elements — FAB, bottom sheets
    val overlay = 16.dp  // dialogs, modal overlays
}

// ─────────────────────────────────────────────────────────────────────────────
// ICON SIZES
// ─────────────────────────────────────────────────────────────────────────────
object IconSize {
    val xs   = 16.dp  // inline icons inside text, chip leading icons
    val sm   = 20.dp  // button icons, stat pill icons
    val md   = 24.dp  // standard toolbar and card icons
    val lg   = 32.dp  // large FAB icons
    val xl   = 40.dp  // icon containers in category rows
    val xxl  = 48.dp  // quiz list card icon containers
    val hero = 52.dp  // quiz detail screen hero icon
}

// ─────────────────────────────────────────────────────────────────────────────
// COMPONENT SIZES
// ─────────────────────────────────────────────────────────────────────────────
object ComponentSize {
    val buttonHeight        = 52.dp   // standard button height
    val buttonHeightLarge   = 56.dp   // HomeScreen Start Quiz button
    val avatarSmall         = 40.dp   // leaderboard row avatar
    val avatarMedium        = 48.dp   // HomeScreen header avatar
    val avatarLarge         = 64.dp   // friend card avatar
    val avatarHero          = 80.dp   // profile screen avatar
    val iconContainerSmall  = 40.dp   // category row icon box
    val iconContainerMedium = 48.dp   // quiz list card icon box
    val iconContainerHero   = 100.dp  // quiz detail screen hero circle
    val timerCircle         = 56.dp   // quiz screen countdown ring
    val navBarHeight        = 80.dp   // bottom navigation bar
    val topBarHeight        = 56.dp   // standard top app bar
    val difficultyChipSquare = 90.dp  // category screen square difficulty chips
}