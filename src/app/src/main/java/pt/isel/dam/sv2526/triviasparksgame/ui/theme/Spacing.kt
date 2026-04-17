package pt.isel.dam.sv2526.triviasparksgame.ui.theme
import androidx.compose.ui.unit.dp

/**
 * ─────────────────────────────────────────────────────────────────────────────
 * TriviaSparks Design System — Layout Tokens
 * ─────────────────────────────────────────────────────────────────────────────
 *
 * ⚠️ THIS FILE DEFINES ALL LAYOUT SCALES FOR THE APP
 *
 * These tokens ensure consistent spacing, sizing, and elevation across all UI.
 * Never use raw dp values in UI — always reference these tokens.
 *
 * ─────────────────────────────────────────────────────────────────────────────
 *
 * SPACING SYSTEM
 *
 * Defines all spacing rules used for padding, margins, and layout gaps.
 *
 * Usage:
 * - Spacing.cardPadding
 * - Spacing.sectionGap
 * - Spacing.screenEdge
 */
object Spacing {

    // Base spacing scale
    val xs  = 4.dp   // tight spacing (icons, labels)
    val sm  = 8.dp   // small gaps (badges, chips)
    val md  = 12.dp  // list gaps, compact spacing
    val lg  = 16.dp  // standard padding (cards, rows)
    val xl  = 20.dp  // screen horizontal padding
    val xxl = 24.dp  // section spacing
    val xxxl = 32.dp // large section breaks

    // Semantic aliases (preferred in UI code)
    val screenEdge      = xl
    val cardPadding     = lg
    val cardGap         = md
    val sectionGap      = xxl
    val iconToText      = lg

    val chipHorizontal  = 14.dp
    val chipVertical    = 7.dp

    val badgeHorizontal = 10.dp
    val badgeVertical   = 4.dp
}

/**
 * ─────────────────────────────────────────────────────────────────────────────
 * ELEVATION SYSTEM
 * ─────────────────────────────────────────────────────────────────────────────
 *
 * Defines depth using shadow elevation values.
 * TriviaSparks uses subtle elevation rather than heavy Material shadows.
 *
 * Usage:
 * - Card(elevation = Elevation.medium)
 * - Modifier.shadow(Elevation.high)
 */
object Elevation {

    val none    = 0.dp   // flat UI elements
    val low     = 2.dp   // subtle lift (pills, inner cards)
    val medium  = 4.dp   // standard cards
    val high    = 8.dp   // floating UI (FAB, bottom sheets)
    val overlay = 16.dp  // dialogs and modals
}

/**
 * ─────────────────────────────────────────────────────────────────────────────
 * ICON SIZES
 * ─────────────────────────────────────────────────────────────────────────────
 *
 * Standardized icon sizing for consistent visual rhythm.
 *
 * Usage:
 * - Modifier.size(IconSize.md)
 * - Toolbar icons, buttons, category icons
 */
object IconSize {

    val xs   = 16.dp // inline text icons
    val sm   = 20.dp // buttons, small indicators
    val md   = 24.dp // standard icons
    val lg   = 32.dp // large actions / FAB icons
    val xl   = 40.dp // category / feature icons
    val xxl  = 48.dp // card icon containers
    val hero = 52.dp // large featured icons
}

/**
 * ─────────────────────────────────────────────────────────────────────────────
 * COMPONENT SIZE SYSTEM
 * ─────────────────────────────────────────────────────────────────────────────
 *
 * Defines fixed sizes for reusable UI components.
 *
 * Usage:
 * - Modifier.height(ComponentSize.buttonHeight)
 * - Modifier.size(ComponentSize.avatarMedium)
 */
object ComponentSize {

    val buttonHeight      = 52.dp
    val buttonHeightLarge = 56.dp

    val avatarSmall  = 40.dp
    val avatarMedium = 48.dp
    val avatarLarge  = 64.dp
    val avatarHero   = 80.dp

    val iconContainerSmall  = 40.dp
    val iconContainerMedium = 48.dp
    val iconContainerHero   = 100.dp

    val timerCircle = 56.dp

    val navBarHeight = 80.dp
    val topBarHeight = 56.dp

    val difficultyChipSquare = 90.dp

    val answerBadge = 36.dp
}