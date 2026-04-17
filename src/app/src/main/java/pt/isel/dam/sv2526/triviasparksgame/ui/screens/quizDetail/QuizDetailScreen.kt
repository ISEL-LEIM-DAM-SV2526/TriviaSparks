package pt.isel.dam.sv2526.triviasparksgame.ui.screens.quizDetail

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pt.isel.dam.sv2526.triviasparksgame.R
import pt.isel.dam.sv2526.triviasparksgame.domain.model.DifficultyOption
import pt.isel.dam.sv2526.triviasparksgame.ui.theme.BottomSheetShape
import pt.isel.dam.sv2526.triviasparksgame.ui.theme.ButtonShape
import pt.isel.dam.sv2526.triviasparksgame.ui.theme.ChipShape
import pt.isel.dam.sv2526.triviasparksgame.ui.theme.IconSize
import pt.isel.dam.sv2526.triviasparksgame.ui.theme.Spacing
import pt.isel.dam.sv2526.triviasparksgame.ui.theme.TriviaSparksTheme
import pt.isel.dam.sv2526.triviasparksgame.ui.theme.Violet800
import pt.isel.dam.sv2526.triviasparksgame.ui.theme.triviasparks
import pt.isel.dam.sv2526.triviasparksgame.ui.screens.category.DifficultyChip

// ─────────────────────────────────────────────────────────────────────────────
// DATA MODEL
// Temporary — moved to domain/model/ in Week 5 when ViewModel is introduced.
// Wiki: https://github.com/your-username/trivia-sparks/wiki/Week-5
// ─────────────────────────────────────────────────────────────────────────────

/**
 * Represents the full detail of a quiz displayed on [QuizDetailScreen].
 *
 * This is a temporary UI model living in the screen package.
 * In Week 4 it will be populated from navigation arguments passed from [CategoryScreen].
 * In Week 6 it will be fully replaced by the domain Quiz model from the Open Trivia API.
 *
 * Wiki — data models reference:
 * https://github.com/your-username/trivia-sparks/wiki/App-Trivia-Sparks#data-models
 */
data class QuizDetail(
    val id: Int,                 // Quiz ID — passed as nav arg in Week 4
    val title: String,           // Quiz title, e.g. "Quantum Physics Fun"
    val category: String,        // Uppercase category label, e.g. "SCIENCE & NATURE"
    val questionCount: Int,      // Total available questions
    val xpReward: Int,           // XP awarded on completion
    val description: String,     // "About this quiz" body text
    val difficulty: String       // Selected difficulty — "Easy" | "Medium" | "Hard"
)

// ─────────────────────────────────────────────────────────────────────────────
// STATIC SAMPLE DATA
// Used exclusively by @Preview functions and during Week 2 development.
// Replaced by nav args + ViewModel in Week 4 / Week 5.
// ─────────────────────────────────────────────────────────────────────────────

private val sampleQuizDetail = QuizDetail(
    id            = 1,
    title         = "Quantum Physics Fun",
    category      = "SCIENCE & NATURE",
    questionCount = 15,
    xpReward      = 250,
    description   = "Dive into the fascinating world of quantum mechanics! " +
            "Explore particles, waves, and the mysteries of the " +
            "subatomic world in this playful challenge.",
    difficulty    = "Easy"
)

// ─────────────────────────────────────────────────────────────────────────────
// QUIZ DETAIL SCREEN
// ─────────────────────────────────────────────────────────────────────────────

/**
 * Quiz Detail / pre-game screen.
 *
 * Shows the quiz's hero illustration, title, category, info pills (question count
 * + XP), description, difficulty selector, and two action buttons.
 *
 * **Layout pattern:**
 * [Scaffold] → [Column] →
 *   [QuizDetailHero] (fixed height, full bleed) →
 *   [QuizDetailCard] (weight 1f, scrollable, overlaps hero by 20dp) →
 *   [QuizDetailButtons] (pinned to bottom, outside the card)
 *
 * The card uses a negative [Modifier.offset] of −20dp to visually overlap the
 * hero illustration, creating a bottom-sheet feel without a modal overlay.
 *
 * **Static values replaced progressively:**
 * - [quiz] data → Week 4: populated from [NavController] arguments
 * - [selectedDifficulty] → Week 3: `mutableStateOf("Easy")`
 * - [onPlaySolo], [onPlayWithFriends] → Week 4: navigate to [QuizScreen]
 * - [onBack] → Week 4: `navController.popBackStack()`
 *
 * Figma design:
 * https://www.figma.com/file/your-figma-link/Trivia-Sparks?node-id=quiz-detail-screen
 *
 * Wiki — Week 2 QuizDetailScreen section:
 * https://github.com/your-username/trivia-sparks/wiki/Week-2#quizdetailscreen
 *
 * @param quiz                The quiz data to display. Defaults to [sampleQuizDetail].
 * @param selectedDifficulty  Currently selected difficulty. Hardcoded to [quiz.difficulty]
 *                            this week — becomes `mutableStateOf` in Week 3.
 * @param onBack              Called when the user taps the back arrow.
 *                            Wired to [NavController.popBackStack] in Week 4.
 * @param onPlaySolo          Called when the user taps "Play Solo".
 *                            Navigates to [QuizScreen] in Week 4.
 * @param onPlayWithFriends   Called when the user taps "Play with Friends".
 *                            Navigates to the Multiplayer lobby in Week 11.
 */
@Composable
fun QuizDetailScreen(
    quiz: QuizDetail                = sampleQuizDetail,
    selectedDifficulty: String      = quiz.difficulty,
    onBack: () -> Unit              = {},
    onPlaySolo: () -> Unit          = {},
    onPlayWithFriends: () -> Unit   = {}
) {
    Scaffold(
        // White surface — the card panel fills most of the screen
        containerColor = MaterialTheme.colorScheme.surface
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            // ── Hero illustration — fixed height, full bleed ───────────────
            QuizDetailHero(
                onBack   = onBack,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(240.dp)
            )

            // ── White card panel — overlaps hero by 20dp ──────────────────
            // Scrollable content: category, title, pills, description, difficulty
            QuizDetailCard(
                quiz               = quiz,
                selectedDifficulty = selectedDifficulty,
                modifier           = Modifier
                    .weight(1f)
                    .offset(y = (-20).dp)  // slides up over the hero illustration
            )

            // ── Action buttons — always visible at the bottom ─────────────
            QuizDetailButtons(
                onPlaySolo          = onPlaySolo,
                onPlayWithFriends   = onPlayWithFriends,
                modifier            = Modifier.padding(
                    horizontal = Spacing.screenEdge,
                    vertical   = Spacing.xl
                )
            )
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// HERO SECTION
// ─────────────────────────────────────────────────────────────────────────────

/**
 * Full-bleed hero illustration section at the top of the screen.
 *
 * Displays a decorative illustrated image (character + science motif) on a
 * lavender/violet background. The back arrow sits as a ghost overlay in the
 * top-left corner using a [Box] with [Alignment.TopStart].
 *
 * The image fills the entire hero area using [ContentScale.Crop].
 * Replace [R.drawable.ic_quiz_detail_hero] with your illustrated asset.
 *
 * Figma: https://www.figma.com/file/your-figma-link/Trivia-Sparks?node-id=quiz-detail-hero
 *
 * @param onBack    Called when the user taps the back arrow.
 * @param modifier  Applied to the outermost [Box] element.
 *                  Caller sets width and height (typically `fillMaxWidth().height(240.dp)`).
 */
@Composable
private fun QuizDetailHero(
    onBack: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        // Hero illustration — fills the full box
        // Replace with your illustrated asset. The image should have a
        // lavender/violet background built into the illustration itself.
        Image(
            painter            = painterResource(R.drawable.il_science),
            contentDescription = "Quiz illustration",
            contentScale       = ContentScale.Crop,
            modifier           = Modifier.fillMaxSize()
        )

        // Back arrow — ghost overlay, top-left corner
        // Semi-transparent surface pill keeps the icon readable over any illustration
        IconButton(
            onClick  = onBack,
            modifier = Modifier
                .padding(Spacing.lg)
                .align(Alignment.TopStart)
                .background(
                    color = MaterialTheme.colorScheme.surface.copy(alpha = 0.80f),
                    shape = RoundedCornerShape(50)
                )
                .size(40.dp)
        ) {
            Icon(
                painter            = painterResource(R.drawable.ic_close),
                contentDescription = "Back",
                tint               = MaterialTheme.colorScheme.onSurface,
                modifier           = Modifier.size(IconSize.md)
            )
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// DETAIL CARD
// ─────────────────────────────────────────────────────────────────────────────

/**
 * White scrollable card panel containing all quiz metadata.
 *
 * Uses [BottomSheetShape] (top corners 20dp, bottom corners 0dp) so the card
 * slides up over the hero illustration with a bottom-sheet aesthetic.
 * The caller applies a negative y-offset (`Modifier.offset(y = (-20).dp)`)
 * to create the visual overlap effect.
 *
 * Content sections, top to bottom:
 * 1. Category label — [MaterialTheme.colorScheme.primary], uppercase, [labelSmall]
 * 2. Quiz title — [headlineLarge], bold
 * 3. Info pills row — question count (outline) + XP reward (coral tint)
 * 4. About section — label + body description
 * 5. Difficulty section — label + three horizontal [DifficultyChip]s
 *
 * @param quiz                The quiz data to display.
 * @param selectedDifficulty  The currently selected difficulty label.
 * @param modifier            Applied to the outermost [Surface] element.
 */
@Composable
private fun QuizDetailCard(
    quiz: QuizDetail,
    selectedDifficulty: String,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier.fillMaxWidth(),
        shape    = BottomSheetShape,
        color    = MaterialTheme.colorScheme.surface
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = Spacing.screenEdge)
                .padding(top = Spacing.xxl, bottom = Spacing.md),
            verticalArrangement = Arrangement.spacedBy(Spacing.lg)
        ) {
            // ── Category label ─────────────────────────────────────────────
            Text(
                text  = quiz.category,
                style = MaterialTheme.typography.labelSmall,
                color = MaterialTheme.colorScheme.primary
            )

            // ── Quiz title ─────────────────────────────────────────────────
            Text(
                text       = quiz.title,
                style      = MaterialTheme.typography.headlineLarge,
                fontWeight = FontWeight.Bold,
                color      = MaterialTheme.colorScheme.onSurface
            )

            // ── Info pills row ─────────────────────────────────────────────
            QuizInfoPills(
                questionCount = quiz.questionCount,
                xpReward      = quiz.xpReward
            )

            // ── About section ──────────────────────────────────────────────
            QuizAboutSection(description = quiz.description)

            // ── Difficulty section ─────────────────────────────────────────
            QuizDifficultySection(selectedDifficulty = selectedDifficulty)
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// INFO PILLS
// ─────────────────────────────────────────────────────────────────────────────

/**
 * Row of two info pills showing the question count and XP reward.
 *
 * **Question count pill** — outline style (transparent fill, `outline` border).
 * Neutral visual weight — informational only.
 *
 * **XP reward pill** — coral tint fill ([MaterialTheme.colorScheme.secondary] at 15% opacity)
 * with a matching border. The warm colour signals reward and draws the eye more than
 * the neutral question count pill.
 *
 * Figma: https://www.figma.com/file/your-figma-link/Trivia-Sparks?node-id=quiz-info-pills
 *
 * @param questionCount  Number of questions in the quiz.
 * @param xpReward       XP points awarded on completion.
 * @param modifier       Applied to the outermost [Row] element.
 */
@Composable
private fun QuizInfoPills(
    questionCount: Int,
    xpReward: Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier              = modifier,
        horizontalArrangement = Arrangement.spacedBy(Spacing.sm),
        verticalAlignment     = Alignment.CenterVertically
    ) {
        // Question count — outline style, neutral
        Row(
            modifier = Modifier
                .border(
                    width  = 1.dp,
                    color  = MaterialTheme.colorScheme.outline,
                    shape  = ChipShape
                )
                .padding(horizontal = Spacing.md, vertical = Spacing.sm),
            verticalAlignment     = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(Spacing.xs)
        ) {
            Icon(
                painter            = painterResource(R.drawable.ic_questions),
                contentDescription = null,
                tint               = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier           = Modifier.size(14.dp)
            )
            Text(
                text  = "$questionCount Questions",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        // XP reward — coral tint, reward signal
        Row(
            modifier = Modifier
                .background(
                    color = MaterialTheme.colorScheme.secondary.copy(alpha = 0.15f),
                    shape = ChipShape
                )
                .border(
                    width  = 1.dp,
                    color  = MaterialTheme.colorScheme.secondary.copy(alpha = 0.5f),
                    shape  = ChipShape
                )
                .padding(horizontal = Spacing.md, vertical = Spacing.sm),
            verticalAlignment     = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(Spacing.xs)
        ) {
            Icon(
                painter            = painterResource(R.drawable.ic_star_fill),
                contentDescription = null,
                tint               = MaterialTheme.colorScheme.secondary,
                modifier           = Modifier.size(14.dp)
            )
            Text(
                text       = "$xpReward XP",
                style      = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Bold,
                color      = MaterialTheme.colorScheme.secondary
            )
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// ABOUT SECTION
// ─────────────────────────────────────────────────────────────────────────────

/**
 * "About this quiz" section with an uppercase label and body description text.
 *
 * The label uses [MaterialTheme.typography.labelSmall] with
 * [MaterialTheme.colorScheme.onSurfaceVariant] — consistent with all other
 * uppercase section headings in the app ("DIFFICULTY", "CATEGORIES").
 *
 * The description uses a relaxed [lineHeight] for comfortable reading on
 * multi-line quiz descriptions.
 *
 * @param description  The quiz description text.
 * @param modifier     Applied to the outermost [Column] element.
 */
@Composable
private fun QuizAboutSection(
    description: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier            = modifier,
        verticalArrangement = Arrangement.spacedBy(Spacing.sm)
    ) {
        Text(
            text  = "ABOUT THIS QUIZ",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Text(
            text       = description,
            style      = MaterialTheme.typography.bodyMedium,
            color      = MaterialTheme.colorScheme.onSurfaceVariant,
            lineHeight = MaterialTheme.typography.bodyMedium.fontSize * 1.6f
        )
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// DIFFICULTY SECTION
// ─────────────────────────────────────────────────────────────────────────────

/**
 * "Difficulty" section with an uppercase label and three horizontal [DifficultyChip]s.
 *
 * Reuses [DifficultyChip] from `ui/screens/category/CategoryScreen.kt`.
 * The chips here use the **pill** shape (`ChipShape`) which is the same component
 * but rendered horizontally rather than as the square card used on [CategoryScreen].
 * The pill style fits this context better — it takes less vertical space and the
 * detail screen already has other visual elements competing for attention.
 *
 * This week [selectedDifficulty] is a hardcoded parameter.
 * In Week 3 it becomes `remember { mutableStateOf(quiz.difficulty) }` hoisted
 * to [QuizDetailScreen].
 *
 * Figma: https://www.figma.com/file/your-figma-link/Trivia-Sparks?node-id=quiz-detail-difficulty
 *
 * Wiki — difficulty chips:
 * https://github.com/your-username/trivia-sparks/wiki/Week-2#difficulty-chips--three-values-per-chip
 *
 * @param selectedDifficulty  The currently highlighted difficulty label.
 * @param modifier            Applied to the outermost [Column] element.
 */
@Composable
private fun QuizDifficultySection(
    selectedDifficulty: String,
    modifier: Modifier = Modifier
) {
    val ext = MaterialTheme.triviasparks

    val difficultyOptions = listOf(
        DifficultyOption(
            label         = "Easy",
            iconRes       = R.drawable.ic_star_fill,
            colour        = ext.easy,
            onLightColour = ext.easyOnLight
        ),
        DifficultyOption(
            label         = "Medium",
            iconRes       = R.drawable.ic_light,
            colour        = ext.medium,
            onLightColour = ext.mediumOnLight
        ),
        DifficultyOption(
            label = "Hard",
            iconRes = R.drawable.ic_flame,
            colour = ext.hard,
            onLightColour = ext.hardOnLight
        )
    )

    Column(
        modifier            = modifier,
        verticalArrangement = Arrangement.spacedBy(Spacing.md)
    ) {
        Text(
            text  = "DIFFICULTY",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )

        // Pill-style chips in a horizontal Row — different visual from the
        // square chips on CategoryScreen but the same DifficultyChip composable
        Row(horizontalArrangement = Arrangement.spacedBy(Spacing.sm)) {
            difficultyOptions.forEach { option ->
                DifficultyChip(
                    option     = option,
                    isSelected = option.label == selectedDifficulty,
                    onClick    = { /* becomes: selectedDifficulty = option.label in Week 3 */ }
                )
            }
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// ACTION BUTTONS
// ─────────────────────────────────────────────────────────────────────────────

/**
 * Row of two action buttons pinned below the detail card.
 *
 * The two buttons have intentionally different visual weights:
 * - **Play Solo** — [OutlinedButton] with [MaterialTheme.colorScheme.primary] border and text.
 *   Lower emphasis — solo play is the secondary action.
 * - **Play with Friends** — filled [Button] with [Violet800] (`#3C3489`) background.
 *   Highest emphasis — multiplayer is the featured action.
 *   Uses the deeper violet (not `primary`) to match the strong CTA style from [CategoryScreen].
 *
 * Both buttons share equal width via [Modifier.weight].
 *
 * In Week 4 both lambdas navigate to their respective screens.
 * In Week 11 [onPlayWithFriends] navigates to the Multiplayer lobby.
 *
 * Figma: https://www.figma.com/file/your-figma-link/Trivia-Sparks?node-id=quiz-detail-buttons
 *
 * @param onPlaySolo          Called when the user taps "Play Solo".
 * @param onPlayWithFriends   Called when the user taps "Play with Friends".
 * @param modifier            Applied to the outermost [Row] element.
 */
@Composable
private fun QuizDetailButtons(
    onPlaySolo: () -> Unit,
    onPlayWithFriends: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier              = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(Spacing.md)
    ) {
        // Play Solo — outlined, lower emphasis
        OutlinedButton(
            onClick  = onPlaySolo,
            modifier = Modifier
                .weight(1f)
                .height(52.dp),
            shape  = ButtonShape,
            border = BorderStroke(
                width = 1.5.dp,
                color = MaterialTheme.colorScheme.primary
            ),
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = MaterialTheme.colorScheme.primary
            )
        ) {
            Text(
                text       = "Play Solo",
                style      = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.Bold
            )
        }

        // Play with Friends — filled, highest emphasis, people icon
        Button(
            onClick  = onPlayWithFriends,
            modifier = Modifier
                .weight(1f)
                .height(52.dp),
            shape  = ButtonShape,
            colors = ButtonDefaults.buttonColors(
                containerColor = Violet800,   // #3C3489 — deep violet, strong CTA
                contentColor   = Color.White
            )
        ) {
            Icon(
                painter            = painterResource(R.drawable.ic_multiplayer),
                contentDescription = null,
                modifier           = Modifier.size(IconSize.sm)   // 20dp
            )
            Spacer(modifier = Modifier.width(Spacing.xs))
            Text(
                text       = "Play with Friends",
                style      = MaterialTheme.typography.labelLarge,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// PREVIEWS
// ─────────────────────────────────────────────────────────────────────────────

@Preview(showBackground = true, name = "QuizDetail — light, Easy selected")
@Composable
fun QuizDetailScreenLightPreview() {
    TriviaSparksTheme(darkTheme = false) {
        QuizDetailScreen()
    }
}

@Preview(
    showBackground = true,
    name           = "QuizDetail — dark, Easy selected",
    uiMode         = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun QuizDetailScreenDarkPreview() {
    TriviaSparksTheme(darkTheme = true) {
        QuizDetailScreen()
    }
}

@Preview(showBackground = true, name = "QuizDetail — Hard selected")
@Composable
fun QuizDetailScreenHardPreview() {
    TriviaSparksTheme(darkTheme = false) {
        QuizDetailScreen(selectedDifficulty = "Hard")
    }
}

@Preview(showBackground = true, name = "Info pills")
@Composable
fun QuizInfoPillsPreview() {
    TriviaSparksTheme(darkTheme = false) {
        QuizInfoPills(
            questionCount = 15,
            xpReward      = 250,
            modifier      = Modifier.padding(Spacing.screenEdge)
        )
    }
}