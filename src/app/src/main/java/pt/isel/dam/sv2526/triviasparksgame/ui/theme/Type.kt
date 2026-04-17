package pt.isel.dam.sv2526.triviasparksgame.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import pt.isel.dam.sv2526.triviasparksgame.R

// ─────────────────────────────────────────────────────────────────────────────
// FONT FAMILY
// ─────────────────────────────────────────────────────────────────────────────

/**
 * Primary font family used across TriviaSparks UI.
 *
 * Includes multiple weights of Nunito for consistent typography scaling.
 *
 * Usage:
 * - Apply globally via MaterialTheme.typography
 * - Used as base font for all text styles in the app
 */
val NunitoFamily = FontFamily(
    Font(R.font.nunito_regular, FontWeight.Normal),
    Font(R.font.nunito_semibold, FontWeight.SemiBold),
    Font(R.font.nunito_bold, FontWeight.Bold),
    Font(R.font.nunito_extrabold, FontWeight.ExtraBold)
)

/**
 * Alias used as the main app font.
 */
val TriviaSparksFont = NunitoFamily

// ─────────────────────────────────────────────────────────────────────────────
// TYPOGRAPHY SCALE
// ─────────────────────────────────────────────────────────────────────────────

/**
 * Global typography system for TriviaSparks.
 *
 * Defines text styles for display, headline, title, body, and label usage
 * following Material 3 conventions with a custom font family.
 *
 * Usage:
 * - Provide to MaterialTheme(typography = TriviaSparksTypography)
 * - Access via MaterialTheme.typography.*
 */
val TriviaSparksTypography = Typography(

    // ── DISPLAY ──────────────────────────────────────────────────────────────
    // Large, impactful text for scores, timers, and highlights

    // Usage: XP totals, large score results, rank highlights
    displayLarge = TextStyle(
        fontFamily = TriviaSparksFont,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 48.sp,
        lineHeight = 52.sp,
        letterSpacing = (-1).sp
    ),

    // Usage: Final score (e.g. "8 / 10"), leaderboard top scores
    displayMedium = TextStyle(
        fontFamily = TriviaSparksFont,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 36.sp,
        lineHeight = 40.sp,
        letterSpacing = (-0.5).sp
    ),
    // Usage: Countdown timer numbers, key numeric indicators
    displaySmall = TextStyle(
        fontFamily = TriviaSparksFont,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 28.sp,
        lineHeight = 34.sp,
        letterSpacing = 0.sp
    ),

    // ── HEADLINE ─────────────────────────────────────────────────────────────
    // Screen titles and primary section headers

    // Usage: App name (splash/onboarding screens)
    headlineLarge = TextStyle(
        fontFamily = TriviaSparksFont,
        fontWeight = FontWeight.Bold,
        fontSize = 28.sp,
        lineHeight = 34.sp,
        letterSpacing = 0.sp
    ),

    // Usage: Quiz titles, greeting text (e.g. "Hello, Alexa")
    headlineMedium = TextStyle(
        fontFamily = TriviaSparksFont,
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),

    // Usage: Section headers, TopAppBar titles
    headlineSmall = TextStyle(
        fontFamily = TriviaSparksFont,
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.sp
    ),

    // ── TITLE ────────────────────────────────────────────────────────────────
    // Card titles, list items, and important content labels

    titleLarge = TextStyle(
        fontFamily = TriviaSparksFont,
        fontWeight = FontWeight.SemiBold,
        fontSize = 16.sp,
        lineHeight = 22.sp,
        letterSpacing = 0.sp
        // Usage: Main question text in quiz screens
    ),

    titleMedium = TextStyle(
        fontFamily = TriviaSparksFont,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.1.sp
        // Usage: Category names, quiz titles in lists/cards
    ),

    titleSmall = TextStyle(
        fontFamily = TriviaSparksFont,
        fontWeight = FontWeight.SemiBold,
        fontSize = 13.sp,
        lineHeight = 18.sp,
        letterSpacing = 0.1.sp
        // Usage: Friend names, creator names, secondary labels
    ),

    // ── BODY ─────────────────────────────────────────────────────────────────
    // Paragraph text, descriptions, and general content

    bodyLarge = TextStyle(
        fontFamily = TriviaSparksFont,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
        // Usage: Form input text, primary descriptions
    ),

    bodyMedium = TextStyle(
        fontFamily = TriviaSparksFont,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 22.sp,
        letterSpacing = 0.25.sp
        // Usage: Quiz descriptions, subtitles, secondary content
    ),

    bodySmall = TextStyle(
        fontFamily = TriviaSparksFont,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 18.sp,
        letterSpacing = 0.4.sp
        // Usage: Metadata, small captions, counts, helper text
    ),

    // ── LABEL ────────────────────────────────────────────────────────────────
    // Buttons, chips, navigation items, and badges

    labelLarge = TextStyle(
        fontFamily = TriviaSparksFont,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        lineHeight = 18.sp,
        letterSpacing = 0.1.sp
        // Usage: Primary buttons (e.g. "Start Quiz", "Join Room")
    ),

    labelMedium = TextStyle(
        fontFamily = TriviaSparksFont,
        fontWeight = FontWeight.Bold,
        fontSize = 12.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
        // Usage: Chips, bottom navigation labels, badges
    ),

    labelSmall = TextStyle(
        fontFamily = TriviaSparksFont,
        fontWeight = FontWeight.Bold,
        fontSize = 11.sp,
        lineHeight = 14.sp,
        letterSpacing = 1.5.sp
        // Usage: Uppercase tags (e.g. "DIFFICULTY", "CATEGORIES")
    )
)