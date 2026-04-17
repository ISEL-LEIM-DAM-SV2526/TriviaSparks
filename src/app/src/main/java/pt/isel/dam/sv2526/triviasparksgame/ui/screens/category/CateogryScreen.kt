package pt.isel.dam.sv2526.triviasparksgame.ui.screens.category

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pt.isel.dam.sv2526.triviasparksgame.R
import pt.isel.dam.sv2526.triviasparksgame.domain.model.Category
import pt.isel.dam.sv2526.triviasparksgame.domain.model.DifficultyOption
import pt.isel.dam.sv2526.triviasparksgame.mock.sampleCategories
import pt.isel.dam.sv2526.triviasparksgame.ui.theme.ButtonShape
import pt.isel.dam.sv2526.triviasparksgame.ui.theme.CardShape
import pt.isel.dam.sv2526.triviasparksgame.ui.theme.ChipShape
import pt.isel.dam.sv2526.triviasparksgame.ui.theme.ComponentSize
import pt.isel.dam.sv2526.triviasparksgame.ui.theme.IconBoxShape
import pt.isel.dam.sv2526.triviasparksgame.ui.theme.IconSize
import pt.isel.dam.sv2526.triviasparksgame.ui.theme.SearchBarShape
import pt.isel.dam.sv2526.triviasparksgame.ui.theme.Spacing
import pt.isel.dam.sv2526.triviasparksgame.ui.theme.TriviaSparksTheme
import pt.isel.dam.sv2526.triviasparksgame.ui.theme.Violet800
import pt.isel.dam.sv2526.triviasparksgame.ui.theme.triviasparks


// ─────────────────────────────────────────────────────────────────────────────
// CATEGORY SCREEN
// ─────────────────────────────────────────────────────────────────────────────

/**
 * Quiz Setup / Category screen.
 *
 * Lets the player choose a difficulty level and select a category before
 * starting a quiz. Structured as a vertically scrollable content area with
 * a sticky "Start Quiz" button pinned to the bottom.
 *
 * Layout pattern: [Scaffold] → [Column] → [LazyColumn] (weight 1f) + [StartQuizButton].
 * This keeps the button always visible regardless of scroll position.
 *
 * All data is static this week — connected to real sources progressively:
 * - [categories] → Week 6 (Open Trivia Database API)
 * - [selectedDifficulty] → Week 3 (mutableStateOf)
 * - [searchQuery] → Week 3 (mutableStateOf + derivedStateOf filtering)
 *
 * Figma design:
 * https://www.figma.com/file/your-figma-link/Trivia-Sparks?node-id=category-screen
 *
 * Wiki — Week 2 CategoryScreen section:
 * https://github.com/your-username/trivia-sparks/wiki/Week-2#categoryscreen
 *
 * @param categories          List of categories to display. Defaults to [sampleCategories].
 * @param selectedDifficulty  The currently selected difficulty label. Hardcoded to "Easy"
 *                            this week — becomes [mutableStateOf] in Week 3.
 * @param onClose             Called when the user taps the X button to close the screen.
 *                            Wired to [NavController.popBackStack] in Week 4.
 * @param onCategoryClick     Called when the user taps a category row. Receives the category ID.
 *                            Wired to navigation in Week 4.
 * @param onStartQuiz         Called when the user taps the Start Quiz button.
 *                            Wired to navigation in Week 4.
 */
@Composable
fun CategoryScreen(
    categories: List<Category>   = sampleCategories,
    selectedDifficulty: String   = "Easy",
    onClose: () -> Unit          = {},
    onCategoryClick: (Int) -> Unit = {},
    onStartQuiz: () -> Unit      = {}
) {
    Scaffold(
        containerColor = MaterialTheme.colorScheme.background
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            // Scrollable content — takes all available space above the button
            LazyColumn(
                modifier       = Modifier.weight(1f),
                contentPadding = PaddingValues(bottom = Spacing.lg)
            ) {
                // ── Top bar ────────────────────────────────────────────────
                item {
                    CategoryTopBar(onClose = onClose)
                }

                // ── Difficulty selector ────────────────────────────────────
                item {
                    DifficultySection(
                        selectedDifficulty = selectedDifficulty,
                        modifier = Modifier.padding(
                            horizontal = Spacing.screenEdge,
                            vertical   = Spacing.xl
                        )
                    )
                }

                // ── Categories section label ───────────────────────────────
                item {
                    Text(
                        text     = "CATEGORIES",
                        style    = MaterialTheme.typography.labelSmall,
                        color    = MaterialTheme.colorScheme.onSurfaceVariant,
                        modifier = Modifier.padding(
                            horizontal = Spacing.screenEdge,
                            vertical   = Spacing.sm
                        )
                    )
                }

                // ── Search bar ─────────────────────────────────────────────
                item {
                    CategorySearchBar(
                        modifier = Modifier.padding(
                            horizontal = Spacing.screenEdge,
                            vertical   = Spacing.sm
                        )
                    )
                }

                item { Spacer(modifier = Modifier.height(Spacing.sm)) }

                // ── Category rows ──────────────────────────────────────────
                items(
                    items = categories,
                    key   = { it.id }  // stable key — always required on items()
                ) { category ->
                    CategoryRow(
                        category = category,
                        onClick  = { onCategoryClick(category.id) },
                        modifier = Modifier.padding(
                            horizontal = Spacing.screenEdge,
                            vertical   = Spacing.xs
                        )
                    )
                }
            }

            // ── Start Quiz button — pinned to bottom ───────────────────────
            StartQuizButton(
                onClick  = onStartQuiz,
                modifier = Modifier.padding(
                    horizontal = Spacing.screenEdge,
                    vertical   = Spacing.xl
                )
            )
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// TOP BAR
// ─────────────────────────────────────────────────────────────────────────────

/**
 * Custom top bar for the Category/Quiz Setup screen.
 *
 * Uses an X (close) button on the left and the title "Quiz Setup" in
 * [MaterialTheme.colorScheme.primary]. Implemented as a plain [Row] —
 * no [androidx.compose.material3.TopAppBar] because this screen has a
 * modal/bottom-sheet feel where X is semantically correct over a back arrow.
 *
 * Figma: https://www.figma.com/file/your-figma-link/Trivia-Sparks?node-id=category-top-bar
 *
 * @param onClose   Called when the user taps the X button.
 * @param modifier  Applied to the outermost [Row] element.
 */
@Composable
private fun CategoryTopBar(
    onClose: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier          = modifier
            .fillMaxWidth()
            .padding(horizontal = Spacing.screenEdge)
            .padding(top = Spacing.xl, bottom = Spacing.sm),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Close button — X icon, not a back arrow
        // This screen has modal semantics (Quiz Setup) rather than linear navigation
        IconButton(
            onClick  = onClose,
            modifier = Modifier.size(ComponentSize.avatarMedium)  // 48dp touch target
        ) {
            Icon(
                painter            = painterResource(R.drawable.ic_close),
                contentDescription = "Close",
                tint               = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier           = Modifier.size(IconSize.md)
            )
        }

        Spacer(modifier = Modifier.width(Spacing.sm))

        Text(
            text       = "Quiz Setup",
            style      = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            color      = MaterialTheme.colorScheme.primary
        )
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// DIFFICULTY SECTION
// ─────────────────────────────────────────────────────────────────────────────

/**
 * Difficulty section containing the "DIFFICULTY" label and three [DifficultyChip]s.
 *
 * The three chips — Easy, Medium, Hard — are arranged in a [Row] with equal spacing.
 * Only one chip is selected at a time, determined by [selectedDifficulty].
 *
 * This week [selectedDifficulty] is a hardcoded parameter.
 * In Week 3 it becomes a `remember { mutableStateOf("Easy") }` hoisted to [CategoryScreen].
 *
 * Wiki — difficulty chips explained:
 * https://github.com/your-username/trivia-sparks/wiki/Week-2#difficulty-chips--three-values-per-chip
 *
 * @param selectedDifficulty  The label of the currently selected difficulty chip.
 * @param modifier            Applied to the outermost [Column] element.
 */
@Composable
private fun DifficultySection(
    selectedDifficulty: String,
    modifier: Modifier = Modifier
) {
    // Build difficulty options using extended Dreamscape tokens
    // Each option holds its colour and the darker onLight variant for text contrast
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
            onLightColour = ext.mediumOnLight  // #B8922A — never white on yellow
        ),
        DifficultyOption(
            label         = "Hard",
            iconRes       = R.drawable.ic_flame,
            colour        = ext.hard,
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

        Row(
            modifier              = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(Spacing.md)
        ) {
            difficultyOptions.forEach { option ->
                DifficultyChip(
                    option     = option,
                    isSelected = option.label == selectedDifficulty,
                    onClick    = { /* becomes: selectedDifficulty = option.label in Week 3 */ },
                    modifier   = Modifier.weight(1f)
                )
            }
        }
    }
}

/**
 * Square-ish difficulty chip with a centred icon and label below.
 *
 * Visual states:
 * - **Selected**: [option.colour] border (2dp), icon in [option.colour],
 *   label in [option.onLightColour].
 * - **Unselected**: transparent border, icon in [option.colour] at 70% opacity,
 *   label in [MaterialTheme.colorScheme.onSurfaceVariant].
 *
 * The `onLightColour` variant is used for the selected label — not the base colour —
 * because the base colours (especially yellow for Medium) fail WCAG AA contrast on a
 * white card background.
 *
 * Marked `public` because it is also used on [QuizDetailScreen].
 * Will be moved to `ui/components/` when needed there.
 *
 * Figma: https://www.figma.com/file/your-figma-link/Trivia-Sparks?node-id=difficulty-chip
 *
 * Wiki — difficulty chip colour rules:
 * https://github.com/your-username/trivia-sparks/wiki/Week-2#difficulty-chips--three-values-per-chip
 *
 * @param option      The difficulty option data — label, icon resource, colours.
 * @param isSelected  True when this chip is the currently active difficulty.
 * @param onClick     Called when the user taps the chip.
 *                    Does nothing this week — wired to state in Week 3.
 * @param modifier    Applied to the outermost [Card] element.
 */
@Composable
fun DifficultyChip(
    option: DifficultyOption,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val borderColour  = if (isSelected) option.colour else Color.Transparent
    val iconTint      = if (isSelected) option.colour else option.colour.copy(alpha = 0.6f)
    val labelColour   = if (isSelected) option.onLightColour
    else MaterialTheme.colorScheme.onSurfaceVariant

    Card(
        onClick  = onClick,
        modifier = modifier
            .height(ComponentSize.difficultyChipSquare)   // 90dp
            .border(
                width  = if (isSelected) 2.dp else 0.dp,
                color  = borderColour,
                shape  = MaterialTheme.shapes.medium       // 16dp corners
            ),
        shape  = MaterialTheme.shapes.medium,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Column(
            modifier              = Modifier
                .fillMaxSize()
                .padding(Spacing.md),
            horizontalAlignment   = Alignment.CenterHorizontally,
            verticalArrangement   = Arrangement.Center
        ) {
            Icon(
                painter            = painterResource(option.iconRes),
                contentDescription = option.label,
                tint               = iconTint,
                modifier           = Modifier.size(28.dp)
            )
            Spacer(modifier = Modifier.height(Spacing.xs))
            Text(
                text       = option.label,
                style      = MaterialTheme.typography.labelMedium,
                fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                color      = labelColour
            )
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// SEARCH BAR
// ─────────────────────────────────────────────────────────────────────────────

/**
 * Pill-shaped search input for filtering categories by name.
 *
 * This week the field is **static** — [value] is always empty and [onValueChange]
 * does nothing. The field renders and focuses correctly but typing has no effect.
 *
 * In Week 3 this composable is connected to real state:
 * ```kotlin
 * var query by remember { mutableStateOf("") }
 * CategorySearchBar(value = query, onValueChange = { query = it })
 * ```
 * The composable itself does not change — only the call site.
 *
 * Uses [SearchBarShape] (99dp pill) and [MaterialTheme.colorScheme.surface] background,
 * consistent with the pill-shaped stat pills and chips in the Dreamscape system.
 *
 * @param modifier  Applied to the [OutlinedTextField] element.
 */
@Composable
private fun CategorySearchBar(
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value         = "",         // static this week — becomes mutableStateOf in Week 3
        onValueChange = { },        // no-op this week — becomes { query = it } in Week 3
        placeholder   = {
            Text(
                text  = "Search categories...",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        },
        leadingIcon = {
            Icon(
                painter            = painterResource(R.drawable.ic_search),
                contentDescription = null,
                tint               = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier           = Modifier.size(IconSize.sm)  // 20dp
            )
        },
        singleLine = true,
        modifier   = modifier.fillMaxWidth(),
        shape      = SearchBarShape,  // 99dp pill
        colors     = OutlinedTextFieldDefaults.colors(
            unfocusedBorderColor = Color.Transparent,
            focusedBorderColor   = MaterialTheme.colorScheme.primary,
            unfocusedContainerColor = MaterialTheme.colorScheme.surface,
            focusedContainerColor   = MaterialTheme.colorScheme.surface
        )
    )
}

// ─────────────────────────────────────────────────────────────────────────────
// CATEGORY ROW
// ─────────────────────────────────────────────────────────────────────────────

/**
 * Tappable row card displaying a category's icon, name, and question count badge.
 *
 * Layout: [CategoryIconBox] (fixed 40dp) + name column ([Modifier.weight]) + [QuestionCountBadge].
 * The [Modifier.weight] on the name ensures long category names never push the badge off screen.
 *
 * Badge fill logic:
 * - Count > 200: [MaterialTheme.colorScheme.primaryContainer] fill with
 *   [MaterialTheme.colorScheme.onPrimaryContainer] text — featured/popular category.
 * - Count ≤ 200: [MaterialTheme.colorScheme.surfaceVariant] fill with
 *   [MaterialTheme.colorScheme.onSurfaceVariant] text — standard category.
 *
 * Figma: https://www.figma.com/file/your-figma-link/Trivia-Sparks?node-id=category-row
 *
 * Wiki — category row explained:
 * https://github.com/your-username/trivia-sparks/wiki/Week-2#concepts-applied-here-2
 *
 * @param category  The category data to display.
 * @param onClick   Called when the user taps the row card.
 * @param modifier  Applied to the outermost [Card] element.
 */
@Composable
private fun CategoryRow(
    category: Category,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick  = onClick,
        modifier = modifier.fillMaxWidth(),
        shape    = CardShape,
        colors   = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colorScheme.outline
        )
    ) {
        Row(
            modifier              = Modifier
                .fillMaxWidth()
                .padding(horizontal = Spacing.lg, vertical = Spacing.md),
            verticalAlignment     = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(Spacing.iconToText)
        ) {
            // Category icon container — washed background with full-colour icon
            CategoryIconBox(
                iconRes  = category.iconRes,
                iconTint = category.iconTint
            )

            // Name — weight(1f) prevents overflow on long names
            Text(
                text       = category.name,
                style      = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
                color      = MaterialTheme.colorScheme.onSurface,
                maxLines   = 1,
                overflow   = TextOverflow.Ellipsis,
                modifier   = Modifier.weight(1f)
            )

            // Question count badge
            QuestionCountBadge(count = category.questionCount)
        }
    }
}

/**
 * Rounded square icon container with a washed background and full-colour icon.
 *
 * Background fill is [iconTint] at 15% opacity — subtle enough not to compete with
 * neighbouring elements while still communicating the category's colour identity.
 * The icon itself uses [iconTint] at full opacity.
 *
 * Marked `public` because this component is reused on [HomeScreen] (QuizListCard)
 * and will be moved to `ui/components/` when extracted.
 *
 * Wiki — icon container pattern:
 * https://github.com/your-username/trivia-sparks/wiki/Week-2#category-icon-containers
 *
 * @param iconRes   Drawable resource ID for the category icon.
 * @param iconTint  Category accent colour — see Dreamscape category token table.
 * @param modifier  Applied to the outermost [Box] element.
 */
@Composable
fun CategoryIconBox(
    iconRes: Int,
    iconTint: Color,
    modifier: Modifier = Modifier
) {
    Box(
        modifier         = modifier
            .size(ComponentSize.iconContainerSmall)   // 40dp
            .background(
                color = iconTint.copy(alpha = 0.15f),
                shape = IconBoxShape                   // 12dp corners
            ),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            painter            = painterResource(iconRes),
            contentDescription = null,
            tint               = iconTint,
            modifier           = Modifier.size(IconSize.xs)
        )
    }
}

/**
 * Pill-shaped badge showing the number of available questions in a category.
 *
 * Fill colour indicates popularity:
 * - **> 200 questions**: [MaterialTheme.colorScheme.primaryContainer] (violet fill)
 *   — visually signals a content-rich category.
 * - **≤ 200 questions**: [MaterialTheme.colorScheme.surfaceVariant] (neutral fill).
 *
 * @param count     Number of questions available. Determines the badge fill colour.
 * @param modifier  Applied to the outermost [Box] element.
 */
@Composable
private fun QuestionCountBadge(
    count: Int,
    modifier: Modifier = Modifier
) {
    val isFeatured   = count > 200
    val bgColour     = if (isFeatured) MaterialTheme.colorScheme.primaryContainer
    else            MaterialTheme.colorScheme.surfaceVariant
    val textColour   = if (isFeatured) MaterialTheme.colorScheme.onPrimaryContainer
    else            MaterialTheme.colorScheme.onSurfaceVariant

    Box(
        modifier         = modifier.background(color = bgColour, shape = ChipShape),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text       = "$count",
            style      = MaterialTheme.typography.labelMedium,
            fontWeight = FontWeight.Bold,
            color      = textColour,
            modifier   = Modifier.padding(
                horizontal = Spacing.badgeHorizontal,  // 10dp
                vertical   = Spacing.badgeVertical     //  4dp
            )
        )
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// START QUIZ BUTTON
// ─────────────────────────────────────────────────────────────────────────────

/**
 * Full-width primary CTA button that starts the quiz with the selected configuration.
 *
 * Uses [Violet800] (`#3C3489`) instead of [MaterialTheme.colorScheme.primary] (`#8B7FE8`).
 * The darker variant creates stronger contrast against the light [background] token —
 * signalling that this is the final, committed action on the screen.
 *
 * In Week 4 [onClick] will navigate to [QuizScreen] passing the selected category
 * ID and difficulty as arguments.
 *
 * Figma: https://www.figma.com/file/your-figma-link/Trivia-Sparks?node-id=start-quiz-button
 *
 * @param onClick   Called when the user taps the button.
 * @param modifier  Applied to the outermost [Button] element.
 */
@Composable
private fun StartQuizButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick  = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(ComponentSize.buttonHeightLarge),   // 56dp
        shape  = ButtonShape,
        colors = ButtonDefaults.buttonColors(
            containerColor = Violet800,    // #3C3489 — deeper than primary for strong CTA
            contentColor   = Color.White
        )
    ) {
        Icon(
            painter            = painterResource(R.drawable.ic_play),
            contentDescription = null,
            modifier           = Modifier.size(IconSize.sm)   // 20dp
        )
        Spacer(modifier = Modifier.width(Spacing.sm))
        Text(
            text       = "Start Quiz",
            style      = MaterialTheme.typography.labelLarge,
            fontWeight = FontWeight.Bold
        )
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// PREVIEWS
// ─────────────────────────────────────────────────────────────────────────────

@Preview(showBackground = true, name = "Category — light")
@Composable
fun CategoryScreenLightPreview() {
    TriviaSparksTheme(darkTheme = false) {
        CategoryScreen()
    }
}

@Preview(
    showBackground = true,
    name           = "Category — dark",
    uiMode         = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun CategoryScreenDarkPreview() {
    TriviaSparksTheme(darkTheme = true) {
        CategoryScreen()
    }
}

@Preview(showBackground = true, name = "DifficultyChip — Easy selected")
@Composable
fun DifficultyChipEasyPreview() {
    TriviaSparksTheme(darkTheme = false) {
        val ext = MaterialTheme.triviasparks
        DifficultyChip(
            option = DifficultyOption(
                label         = "Easy",
                iconRes       = R.drawable.ic_star_fill,
                colour        = ext.easy,
                onLightColour = ext.easyOnLight
            ),
            isSelected = true,
            onClick    = {}
        )
    }
}

@Preview(showBackground = true, name = "CategoryRow — Science")
@Composable
fun CategoryRowPreview() {
    TriviaSparksTheme(darkTheme = false) {
        CategoryRow(
            category = sampleCategories[0],
            onClick  = {},
            modifier = Modifier.padding(Spacing.screenEdge)
        )
    }
}