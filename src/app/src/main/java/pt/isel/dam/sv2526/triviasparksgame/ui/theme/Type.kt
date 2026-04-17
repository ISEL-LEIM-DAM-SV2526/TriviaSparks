package pt.isel.dam.sv2526.triviasparksgame.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// ─────────────────────────────────────────────────────────────────────────────
// FONT FAMILY
// ─────────────────────────────────────────────────────────────────────────────
// To use Nunito:
//   1. Download from fonts.google.com/specimen/Nunito
//   2. Place .ttf files in res/font/:
//        nunito_regular.ttf
//        nunito_semibold.ttf
//        nunito_bold.ttf
//        nunito_extrabold.ttf
//   3. Replace FontFamily.Default below with the FontFamily definition
//
// val NunitoFamily = FontFamily(
//     Font(R.font.nunito_regular,   FontWeight.Normal),
//     Font(R.font.nunito_semibold,  FontWeight.SemiBold),
//     Font(R.font.nunito_bold,      FontWeight.Bold),
//     Font(R.font.nunito_extrabold, FontWeight.ExtraBold)
// )

val TriviaSparksFont = FontFamily.Default
// Replace with NunitoFamily once font files are added

// ─────────────────────────────────────────────────────────────────────────────
// TYPOGRAPHY SCALE
// ─────────────────────────────────────────────────────────────────────────────
val TriviaSparksTypography = Typography(

    // ── Display ──────────────────────────────────────────────────────────────
    // Hero numbers: scores on results screen, XP totals, rank display

    displayLarge = TextStyle(
        fontFamily   = TriviaSparksFont,
        fontWeight   = FontWeight.ExtraBold,
        fontSize     = 48.sp,
        lineHeight   = 52.sp,
        letterSpacing = (-1).sp
        // Usage: ResultsScreen score, huge hero stat numbers
    ),

    displayMedium = TextStyle(
        fontFamily   = TriviaSparksFont,
        fontWeight   = FontWeight.ExtraBold,
        fontSize     = 36.sp,
        lineHeight   = 40.sp,
        letterSpacing = (-0.5).sp
        // Usage: Final score "8 / 10", leaderboard top scores
    ),

    displaySmall = TextStyle(
        fontFamily   = TriviaSparksFont,
        fontWeight   = FontWeight.ExtraBold,
        fontSize     = 28.sp,
        lineHeight   = 34.sp,
        letterSpacing = 0.sp
        // Usage: Countdown timer number inside circular ring
    ),

    // ── Headline ─────────────────────────────────────────────────────────────
    // Screen titles and quiz titles

    headlineLarge = TextStyle(
        fontFamily   = TriviaSparksFont,
        fontWeight   = FontWeight.Bold,
        fontSize     = 28.sp,
        lineHeight   = 34.sp,
        letterSpacing = 0.sp
        // Usage: App name on splash / onboarding screens
    ),

    headlineMedium = TextStyle(
        fontFamily   = TriviaSparksFont,
        fontWeight   = FontWeight.Bold,
        fontSize     = 22.sp,
        lineHeight   = 28.sp,
        letterSpacing = 0.sp
        // Usage: QuizDetailScreen quiz title, HomeScreen greeting "Hello, Alexa"
    ),

    headlineSmall = TextStyle(
        fontFamily   = TriviaSparksFont,
        fontWeight   = FontWeight.Bold,
        fontSize     = 18.sp,
        lineHeight   = 24.sp,
        letterSpacing = 0.sp
        // Usage: Section titles "Recent Friends", TopAppBar titles
    ),

    // ── Title ────────────────────────────────────────────────────────────────
    // Card titles, list item names

    titleLarge = TextStyle(
        fontFamily   = TriviaSparksFont,
        fontWeight   = FontWeight.SemiBold,
        fontSize     = 16.sp,
        lineHeight   = 22.sp,
        letterSpacing = 0.sp
        // Usage: Question text on QuizScreen
    ),

    titleMedium = TextStyle(
        fontFamily   = TriviaSparksFont,
        fontWeight   = FontWeight.SemiBold,
        fontSize     = 14.sp,
        lineHeight   = 20.sp,
        letterSpacing = 0.1.sp
        // Usage: Category name in CategoryRow, quiz title in QuizListCard
    ),

    titleSmall = TextStyle(
        fontFamily   = TriviaSparksFont,
        fontWeight   = FontWeight.SemiBold,
        fontSize     = 13.sp,
        lineHeight   = 18.sp,
        letterSpacing = 0.1.sp
        // Usage: Friend card name, creator name in QuizDetailScreen
    ),

    // ── Body ─────────────────────────────────────────────────────────────────
    // Descriptions, quiz text, about sections

    bodyLarge = TextStyle(
        fontFamily   = TriviaSparksFont,
        fontWeight   = FontWeight.Normal,
        fontSize     = 16.sp,
        lineHeight   = 24.sp,
        letterSpacing = 0.5.sp
        // Usage: Login/register form field text
    ),

    bodyMedium = TextStyle(
        fontFamily   = TriviaSparksFont,
        fontWeight   = FontWeight.Normal,
        fontSize     = 14.sp,
        lineHeight   = 22.sp,
        letterSpacing = 0.25.sp
        // Usage: Quiz description, about section text, HomeScreen subtitle
    ),

    bodySmall = TextStyle(
        fontFamily   = TriviaSparksFont,
        fontWeight   = FontWeight.Normal,
        fontSize     = 12.sp,
        lineHeight   = 18.sp,
        letterSpacing = 0.4.sp
        // Usage: Question count subtitle, friend card score, captions
    ),

    // ── Label ────────────────────────────────────────────────────────────────
    // Buttons, chips, badges, bottom navigation, section headings

    labelLarge = TextStyle(
        fontFamily   = TriviaSparksFont,
        fontWeight   = FontWeight.Bold,
        fontSize     = 14.sp,
        lineHeight   = 18.sp,
        letterSpacing = 0.1.sp
        // Usage: Button text — "Start Quiz", "Play Solo", "Join Room"
    ),

    labelMedium = TextStyle(
        fontFamily   = TriviaSparksFont,
        fontWeight   = FontWeight.Bold,
        fontSize     = 12.sp,
        lineHeight   = 16.sp,
        letterSpacing = 0.5.sp
        // Usage: Chip labels (Easy / Medium / Hard), bottom nav labels, badges
    ),

    labelSmall = TextStyle(
        fontFamily   = TriviaSparksFont,
        fontWeight   = FontWeight.Bold,
        fontSize     = 11.sp,
        lineHeight   = 14.sp,
        letterSpacing = 1.5.sp
        // Usage: UPPERCASE section headings — "DIFFICULTY", "CATEGORIES", "ABOUT THIS QUIZ"
    )
)