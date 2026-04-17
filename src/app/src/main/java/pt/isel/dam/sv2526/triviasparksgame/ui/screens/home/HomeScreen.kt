package pt.isel.dam.sv2526.triviasparksgame.ui.screens.home

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.TextAutoSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnit.Companion
import androidx.compose.ui.unit.dp
import pt.isel.dam.sv2526.triviasparksgame.R
import pt.isel.dam.sv2526.triviasparksgame.domain.model.Friend
import pt.isel.dam.sv2526.triviasparksgame.domain.model.QuizItem
import pt.isel.dam.sv2526.triviasparksgame.mock.bottomNavItems
import pt.isel.dam.sv2526.triviasparksgame.mock.quizItems
import pt.isel.dam.sv2526.triviasparksgame.mock.sampleFriends
import pt.isel.dam.sv2526.triviasparksgame.ui.theme.AvatarShape
import pt.isel.dam.sv2526.triviasparksgame.ui.theme.ButtonShape
import pt.isel.dam.sv2526.triviasparksgame.ui.theme.CardShape
import pt.isel.dam.sv2526.triviasparksgame.ui.theme.CategoryScience
import pt.isel.dam.sv2526.triviasparksgame.ui.theme.ChipShape
import pt.isel.dam.sv2526.triviasparksgame.ui.theme.ComponentSize
import pt.isel.dam.sv2526.triviasparksgame.ui.theme.IconBoxShape
import pt.isel.dam.sv2526.triviasparksgame.ui.theme.IconSize
import pt.isel.dam.sv2526.triviasparksgame.ui.theme.Spacing
import pt.isel.dam.sv2526.triviasparksgame.ui.theme.TriviaSparksTheme
import pt.isel.dam.sv2526.triviasparksgame.ui.theme.triviasparks

// ─────────────────────────────────────────────────────────────────────────────
// HOME SCREEN
// ─────────────────────────────────────────────────────────────────────────────

/**
 * The main entry point of the app after login.
 *
 * Displays the user's greeting, score and rank stats, a quick-start button,
 * a horizontal row of recent friends, and a vertical list of the latest quizzes.
 * All data is static this week - connected to real sources progressively:
 * - [userName], [totalScore], [rank] → Future: Firebase Auth and Firestore
 * - [friends] list → Future: Firestore friends collection
 *
 * Figma design:
 * https://www.figma.com/design/JLQCo8SrXd27RnUmIhQ4CS/Trivia-Sparks-Game?node-id=35-1773&t=IavXa8KmlvfHgGYF-1
 *
 * Wiki — HomeScreen section:
 * https://github.com/your-username/trivia-sparks/wiki/Week-2#homescreen
 *
 * @param userName              Display name shown in the greeting. Defaults to "Alex".
 * @param totalScore            Formatted score string shown in the score pill, e.g. "2,450".
 * @param rank                  Formatted global rank string shown in the rank pill, e.g. "#12".
 * @param friends               List of friends shown in the Recent Friends row.
 * @param onStartQuiz           Called when the user taps the Start Quiz button.
 * @param onSeeAllFriends       Called when the user taps "See all" in the friends section.
 * @param onSeeAllQuizzes       Called when the user taps "See all" in the quizzes section.
 * @param onQuizClick           Called when the user taps a quiz card. Receives the quiz ID.
 * @param onNotificationClick   Called when the user taps the notification bell icon.
 * @param onNavSelected         Called when the user taps a bottom navigation tab.
 *                              Receives the route string. Wired to NavController in Week 4.
 */
@Composable
fun HomeScreen(
    userName: String          = "Alex",
    totalScore: String        = "2,450",
    rank: String              = "#12",
    friends: List<Friend>     = sampleFriends,
    onStartQuiz: () -> Unit   = {},
    onSeeAllFriends: () -> Unit = {},
    onSeeAllQuizzes: () -> Unit = {},
    onQuizClick: (String) -> Unit = {},
    onNotificationClick: () -> Unit = {},
    onNavSelected: (String) -> Unit = {}
) {
    Scaffold(
        containerColor = MaterialTheme.colorScheme.background,
        bottomBar = {
            HomeBottomBar(
                selectedRoute = "home",
                onNavSelected = onNavSelected
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentPadding = PaddingValues(bottom = Spacing.xxl)
        ) {

            // ── Top bar ────────────────────────────────────────────────────
            item {
                HomeTopBar(
                    userAvatarRes       = R.drawable.ic_avatar1,
                    onNotificationClick = onNotificationClick
                )
            }

            // ── Greeting ───────────────────────────────────────────────────
            item {
                HomeGreeting(
                    userName  = userName,
                    modifier  = Modifier.padding(
                        horizontal = Spacing.screenEdge,
                        vertical   = Spacing.xl
                    )
                )
            }

            // ── Stat pills ─────────────────────────────────────────────────
            item {
                HomeStatRow(
                    score    = totalScore,
                    rank     = rank,
                    modifier = Modifier.padding(horizontal = Spacing.screenEdge)
                )
            }

            // ── Start Quiz button ──────────────────────────────────────────
            item {
                StartQuizButton(
                    onClick  = onStartQuiz,
                    modifier = Modifier
                        .padding(horizontal = Spacing.screenEdge)
                        .padding(top = Spacing.xl)
                )
            }

            // ── Recent Friends ─────────────────────────────────────────────
            item {
                SectionHeader(
                    title    = "Recent Friends",
                    onSeeAll = onSeeAllFriends,
                    modifier = Modifier.padding(
                        horizontal = Spacing.screenEdge,
                        vertical   = Spacing.xxl
                    )
                )
            }
            item {
                FriendsRow(
                    friends  = friends,
                    modifier = Modifier.padding(bottom = Spacing.sm)
                )
            }

            // ── Latest Quizzes ─────────────────────────────────────────────
            item {
                SectionHeader(
                    title    = "Latest Quizzes",
                    onSeeAll = onSeeAllQuizzes,
                    modifier = Modifier.padding(
                        horizontal = Spacing.screenEdge,
                        vertical   = Spacing.xxl
                    )
                )
            }

            items(quizItems) { quiz ->
                QuizListCard(
                    title      = quiz.title,
                    subtitle   = quiz.subtitle,
                    iconRes    = quiz.iconRes,
                    iconTint   = quiz.iconTint,
                    onClick    = { onQuizClick(quiz.id) },
                    modifier   = Modifier.padding(horizontal = Spacing.screenEdge)
                        .padding(bottom = Spacing.md)
                )
            }

        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// TOP BAR
// ─────────────────────────────────────────────────────────────────────────────

/**
 * Custom top bar for the Home screen — avatar left, brand title centred, bell right.
 *
 * Implemented as a plain [Row] rather than [androidx.compose.material3.TopAppBar]
 * because TopAppBar imposes a slot structure that does not match this layout.
 * The title uses [Modifier.weight] to centre itself between the two fixed-size elements.
 *
 * Figma: https://www.figma.com/file/your-figma-link/Trivia-Sparks?node-id=home-top-bar
 *
 * Wiki — custom top bar explained:
 * https://github.com/your-username/trivia-sparks/wiki/Week-2#homescreen
 *
 * @param userAvatarRes         Drawable resource ID for the user's avatar image.
 * @param onNotificationClick   Called when the user taps the notification bell.
 * @param modifier              Applied to the outermost [Row] element.
 */
@Composable
private fun HomeTopBar(
    userAvatarRes: Int,
    onNotificationClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = Spacing.screenEdge)
            .padding(top = Spacing.xxl, bottom = Spacing.sm),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // clip() before background — shape must be established before the fill is applied
        Image(
            painter            = painterResource(userAvatarRes),
            contentDescription = stringResource(R.string.home_top_bar_avatar_image_cd),
            modifier           = Modifier
                .size(ComponentSize.avatarMedium)
                .clip(AvatarShape)
        )

        // weight(1f) fills the remaining space between the two fixed-size elements
        // textAlign = Center centres the text within that filled space
        Text(
            text       = stringResource(R.string.app_name),
            style      = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.ExtraBold,
            color      = MaterialTheme.colorScheme.primary,
            modifier   = Modifier.weight(1f),
            textAlign  = TextAlign.Center
        )

        IconButton(
            onClick  = onNotificationClick,
            modifier = Modifier.size(ComponentSize.avatarMedium).background(MaterialTheme.colorScheme.surfaceVariant, shape = CircleShape)
        ) {
            Icon(
                painter            = painterResource(R.drawable.ic_bell),
                contentDescription = stringResource(R.string.home_top_bar_notification_icon_cd),
                tint               = MaterialTheme.colorScheme.primary,
                modifier           = Modifier.size(IconSize.md)
            )
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// GREETING
// ─────────────────────────────────────────────────────────────────────────────

/**
 * Two-line greeting shown below the top bar.
 *
 * Line 1: "Hello, {userName}!" — [MaterialTheme.typography.headlineMedium] Bold.
 * Line 2: Subtitle — [MaterialTheme.typography.bodyMedium] in [MaterialTheme.colorScheme.onSurfaceVariant].
 *
 * @param userName  The user's display name interpolated into the greeting.
 * @param modifier  Applied to the outermost [Column] element.
 */
@Composable
private fun HomeGreeting(
    userName: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier            = modifier,
        verticalArrangement = Arrangement.spacedBy(Spacing.xs)
    ) {
        Text(
            text       = "Hello, $userName!",
            style      = MaterialTheme.typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            color      = MaterialTheme.colorScheme.onBackground
        )
        Text(
            text  = "Ready to boost your score?",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// STAT PILLS ROW
// ─────────────────────────────────────────────────────────────────────────────

/**
 * Row of two stat pills showing the user's total score and global rank.
 *
 * The two pills use different background tints intentionally:
 * - Score pill: [MaterialTheme.colorScheme.secondary] at 12% opacity (coral wash).
 *   Warm colour draws the eye first — score is the primary motivator.
 * - Rank pill: [MaterialTheme.colorScheme.surfaceVariant] (lavender).
 *   Neutral — rank is secondary information.
 *
 * Figma: https://www.figma.com/file/your-figma-link/Trivia-Sparks?node-id=stat-pills
 *
 * Wiki — theme decision explained:
 * https://github.com/your-username/trivia-sparks/wiki/Week-2#stat-pills--two-different-tints
 *
 * @param score     Formatted score string, e.g. "2,450".
 * @param rank      Formatted rank string, e.g. "#12".
 * @param modifier  Applied to the outermost [Row] element.
 */
@Composable
private fun HomeStatRow(
    score: String,
    rank: String,
    modifier: Modifier = Modifier
) {
    Row(
        modifier              = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(Spacing.md)
    ) {
        StatPill(
            modifier  = Modifier.weight(1f),
            iconRes   = R.drawable.ic_trophy,
            iconTint  = MaterialTheme.colorScheme.secondary,
            label     = score,
            pillColor = MaterialTheme.colorScheme.secondary.copy(alpha = 0.12f)
        )
        StatPill(
            modifier  = Modifier.weight(1f),
            iconRes   = R.drawable.ic_crown,
            iconTint  = MaterialTheme.colorScheme.primary,
            label     = rank,
            pillColor = MaterialTheme.colorScheme.primaryContainer
        )
    }
}

/**
 * A single pill-shaped stat indicator with a leading icon and a text label.
 *
 * Shape is always [ChipShape] (pill / 99dp corners).
 * Background [pillColor] is provided by the caller — this composable has no opinion on colour.
 *
 * @param iconRes     Drawable resource ID for the leading icon.
 * @param iconTint    Tint colour applied to the icon.
 * @param label       The value displayed to the right of the icon.
 * @param pillColor   Background fill colour for the pill container.
 * @param modifier    Applied to the outermost [Row] element.
 */
@Composable
private fun StatPill(
    iconRes: Int,
    iconTint: Color,
    label: String,
    pillColor: Color,
    modifier: Modifier = Modifier
) {
    Row(
        modifier              = modifier
            .background(color = pillColor, shape = ChipShape)
            .padding(horizontal = Spacing.lg, vertical = Spacing.md),
        verticalAlignment     = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Icon(
            painter            = painterResource(iconRes),
            contentDescription = null,
            tint               = iconTint,
            modifier           = Modifier.size(IconSize.sm)
        )
        Spacer(modifier = Modifier.width(Spacing.sm))
        Text(
            text       = label,
            style      = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color      = MaterialTheme.colorScheme.onBackground
        )
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// START QUIZ BUTTON
// ─────────────────────────────────────────────────────────────────────────────

/**
 * Full-width primary action button that launches the quiz flow.
 *
 * Uses [MaterialTheme.colorScheme.primary] fill and [ButtonShape] (16dp corners).
 * The rocket icon sits to the right of the label inside the button content slot.
 *
 * In Week 4 [onClick] will be connected to [NavController] to navigate to
 * the Category screen.
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
            .height(ComponentSize.buttonHeightLarge),
        shape  = ButtonShape,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor   = MaterialTheme.colorScheme.onPrimary
        )
    ) {
        Text(
            text       = "Start Quiz",
            style      = MaterialTheme.typography.labelLarge,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.width(Spacing.sm))
        Icon(
            painter            = painterResource(R.drawable.ic_spaceship),
            contentDescription = null,
            modifier           = Modifier.size(IconSize.sm)
        )
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// SECTION HEADER
// ─────────────────────────────────────────────────────────────────────────────

/**
 * A two-element row with a bold section title on the left and a "See all" link on the right.
 *
 * Reused in the Recent Friends and Latest Quizzes sections of [HomeScreen].
 * Marked `public` because it is also used on other screens — lives in `ui/components/`
 * once it is needed in more than one screen file.
 *
 * @param title     The section label, e.g. "Recent Friends".
 * @param onSeeAll  Called when the user taps the "See all" link.
 * @param modifier  Applied to the outermost [Row] element.
 */
@Composable
fun SectionHeader(
    title: String,
    onSeeAll: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier              = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment     = Alignment.CenterVertically
    ) {
        Text(
            text       = title,
            style      = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            color      = MaterialTheme.colorScheme.onBackground
        )
        Text(
            text     = "See all",
            style    = MaterialTheme.typography.bodyMedium,
            color    = MaterialTheme.colorScheme.primary,
            modifier = Modifier.clickable(onClick = onSeeAll)
        )
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// FRIENDS ROW
// ─────────────────────────────────────────────────────────────────────────────

/**
 * Horizontally scrollable row of [FriendCard] items.
 *
 * Uses [LazyRow] to only compose cards currently visible on screen.
 * [contentPadding] adds breathing room at both scroll ends without clipping
 * the first or last card at the screen edges.
 *
 * @param friends   The list of friends to display.
 * @param modifier  Applied to the outermost [LazyRow] element.
 */
@Composable
private fun FriendsRow(
    friends: List<Friend>,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier              = modifier,
        contentPadding        = PaddingValues(horizontal = Spacing.screenEdge),
        horizontalArrangement = Arrangement.spacedBy(Spacing.md)
    ) {
        items(friends) { friend ->
            FriendCard(friend = friend)
        }
    }
}



/**
 * Card showing a friend's avatar, display name, and total score.
 *
 * Fixed width of 100dp. Avatar is clipped to [AvatarShape] (fully circular).
 * Score is shown in [MaterialTheme.colorScheme.primary] to visually link it
 * to the score pill colour on the main screen.
 *
 * Note: [Modifier.clip] must appear before any [Modifier.background] in the chain.
 * The clip shape must be established before the background is painted — otherwise
 * the background fills a rectangle regardless of the shape.
 *
 * Wiki — clip before background rule:
 * https://github.com/your-username/trivia-sparks/wiki/Week-2#concepts-applied-here
 *
 * @param friend    The friend data to display.
 * @param modifier  Applied to the outermost [Card] element.
 */
@Composable
private fun FriendCard(
    friend: Friend,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.width(100.dp),
        shape    = CardShape,
        colors   = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        border = BorderStroke(
            width = 1.dp,
            color = MaterialTheme.colorScheme.outline
        )
    ) {
        Column(
            modifier            = Modifier
                .fillMaxWidth()
                .padding(Spacing.md),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(Spacing.sm)
        ) {
            Image(
                painter            = painterResource(friend.avatarRes),
                contentDescription = "${friend.name}'s avatar",
                modifier           = Modifier
                    .size(ComponentSize.avatarLarge)
                    .clip(AvatarShape)  // clip FIRST — then background fills the clipped area
            )

            Text(
                text       = friend.name,
                color      = MaterialTheme.colorScheme.onSurface,
                style      = MaterialTheme.typography.titleSmall,
                fontWeight = FontWeight.SemiBold,
                textAlign = TextAlign.Center,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Text(
                text  = friend.score,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// QUIZ LIST CARD
// ─────────────────────────────────────────────────────────────────────────────

/**
 * Tappable card representing a quiz in the Latest Quizzes list.
 *
 * Layout: coloured icon container (left, fixed size) + title/subtitle column
 * ([Modifier.weight] to fill remaining space). Long titles are truncated with
 * [TextOverflow.Ellipsis] — never break to a second line.
 *
 * Icon container uses [iconTint] at 15% opacity for the background and 100%
 * for the icon — washed background with a full-colour icon on top.
 * See the Dreamscape category colour table for [iconTint] values.
 *
 * Figma: https://www.figma.com/file/your-figma-link/Trivia-Sparks?node-id=quiz-list-card
 *
 * Wiki — icon container pattern:
 * https://github.com/your-username/trivia-sparks/wiki/Week-2#category-icon-containers
 *
 * @param title     Quiz title shown in [MaterialTheme.typography.titleMedium].
 * @param subtitle  Formatted as "{count} Questions • {difficulty}".
 * @param iconRes   Category icon drawable resource ID.
 * @param iconTint  Category accent colour — used for both the icon and the container background.
 * @param onClick   Called when the user taps the card.
 * @param modifier  Applied to the outermost [Card] element.
 */
@Composable
private fun QuizListCard(
    title: String,
    subtitle: String,
    iconRes: Int,
    iconTint: Color,
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
                .padding(Spacing.cardPadding),
            verticalAlignment     = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(Spacing.iconToText)
        ) {
            // 15% opacity background — full-colour icon on top
            Box(
                modifier         = Modifier
                    .size(ComponentSize.iconContainerMedium)
                    .background(color = iconTint.copy(alpha = 0.15f), shape = IconBoxShape),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter            = painterResource(iconRes),
                    contentDescription = null,
                    tint               = iconTint,
                    modifier           = Modifier.size(IconSize.md)
                )
            }

            // weight(1f) — takes all remaining space, preventing overflow of long titles
            Column(
                modifier            = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(Spacing.xs)
            ) {
                Text(
                    text       = title,
                    style      = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold,
                    color      = MaterialTheme.colorScheme.onSurface,
                    maxLines   = 1,
                    overflow   = TextOverflow.Ellipsis
                )
                Text(
                    text     = subtitle,
                    style    = MaterialTheme.typography.bodySmall,
                    color    = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// BOTTOM NAVIGATION BAR
// ─────────────────────────────────────────────────────────────────────────────


/**
 * Bottom navigation bar with four tabs: HOME, QUIZ, LEADERBOARDS, PROFILE.
 *
 * The selected tab is highlighted with [MaterialTheme.colorScheme.primaryContainer]
 * as the indicator and a filled icon variant. Unselected tabs use outlined icons
 * and [MaterialTheme.colorScheme.onSurfaceVariant].
 *
 * [tonalElevation] is 0dp — the Dreamscape theme is flat. The surface colour alone
 * distinguishes the bar from the screen background.
 *
 * The [onNavSelected] lambda does nothing this week — wired to [NavController] in Week 4.
 *
 * Figma: https://www.figma.com/file/your-figma-link/Trivia-Sparks?node-id=bottom-nav
 *
 * Wiki — NavigationBar and Week 4 wiring:
 * https://github.com/your-username/trivia-sparks/wiki/Week-2#navigationbar
 *
 * @param selectedRoute   Route string of the currently active tab.
 * @param onNavSelected   Called when the user taps a tab. Receives the tab's route string.
 */
@Composable
private fun HomeBottomBar(
    selectedRoute: String,
    onNavSelected: (String) -> Unit
) {
    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surface,
        tonalElevation = 0.dp
    ) {
        bottomNavItems.forEach { item ->
            val isSelected = item.route == selectedRoute
            NavigationBarItem(
                selected = isSelected,
                onClick  = { onNavSelected(item.route) },
                icon     = {
                    Icon(
                        painter = painterResource(
                            if (isSelected) item.iconSelectedRes
                            else            item.iconUnselectedRes
                        ),
                        contentDescription = item.label,
                        modifier           = Modifier.size(IconSize.md)
                    )
                },
                label = {
                    Text(
                        text  = item.label,
                        style = MaterialTheme.typography.labelMedium,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                },
                alwaysShowLabel = true,
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor   = MaterialTheme.colorScheme.onPrimaryContainer,
                    selectedTextColor   = MaterialTheme.colorScheme.primary,
                    indicatorColor      = MaterialTheme.colorScheme.primaryContainer,
                    unselectedIconColor = MaterialTheme.colorScheme.onSurfaceVariant,
                    unselectedTextColor = MaterialTheme.colorScheme.onSurfaceVariant
                )
            )
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// PREVIEWS
// ─────────────────────────────────────────────────────────────────────────────

@Preview(showBackground = true, name = "Home — light")
@Composable
fun HomeScreenLightPreview() {
    TriviaSparksTheme(darkTheme = false) {
        HomeScreen()
    }
}

@Preview(
    showBackground = true,
    name           = "Home — dark",
    uiMode         = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun HomeScreenDarkPreview() {
    TriviaSparksTheme(darkTheme = true) {
        HomeScreen()
    }
}

@Preview(showBackground = true, name = "FriendCard — light")
@Composable
fun FriendCardPreview() {
    TriviaSparksTheme(darkTheme = false) {
        FriendCard(
            friend   = Friend("Sarah", "1.2k", R.drawable.ic_avatar5),
            modifier = Modifier.width(100.dp)
        )
    }
}

@Preview(showBackground = true, name = "QuizListCard — light")
@Composable
fun QuizListCardPreview() {
    TriviaSparksTheme(darkTheme = false) {
        QuizListCard(
            title    = "Quantum Physics",
            subtitle = "15 Questions • Advanced",
            iconRes  = R.drawable.ic_science,
            iconTint = CategoryScience,
            onClick  = {}
        )
    }
}