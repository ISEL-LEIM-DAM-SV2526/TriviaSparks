package pt.isel.dam.sv2526.triviasparksgame.ui.screens.quiz

import android.content.res.Configuration
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pt.isel.dam.sv2526.triviasparksgame.R
import pt.isel.dam.sv2526.triviasparksgame.ui.theme.ButtonShape
import pt.isel.dam.sv2526.triviasparksgame.ui.theme.CardShape
import pt.isel.dam.sv2526.triviasparksgame.ui.theme.ChipShape
import pt.isel.dam.sv2526.triviasparksgame.ui.theme.ComponentSize
import pt.isel.dam.sv2526.triviasparksgame.ui.theme.IconSize
import pt.isel.dam.sv2526.triviasparksgame.ui.theme.Spacing
import pt.isel.dam.sv2526.triviasparksgame.ui.theme.TriviaSparksTheme
import pt.isel.dam.sv2526.triviasparksgame.ui.theme.triviasparks

// ─────────────────────────────────────────────────────────────────────────────
// DATA MODEL
// Temporary — moved to domain/model/ in Week 5 when ViewModel is introduced.
// ─────────────────────────────────────────────────────────────────────────────

/**
 * Represents a single answer option displayed on the Quiz screen.
 *
 * This is a temporary UI model. In Week 5 the answer options will be derived
 * from the domain [Question] model (correctAnswer + incorrectAnswers shuffled).
 *
 * Wiki — data models reference:
 * https://github.com/your-username/trivia-sparks/wiki/App-Trivia-Sparks#data-models
 */
data class AnswerOption(
    val letter: String,  // "A" | "B" | "C" | "D" — derived from index in production
    val text: String     // Answer text, HTML-decoded from the API (see toHtmlDecoded())
)

// ─────────────────────────────────────────────────────────────────────────────
// STATIC SAMPLE DATA
// Used exclusively by @Preview functions and during Week 2 development.
// Replaced by ViewModel + Repository in Week 5.
// ─────────────────────────────────────────────────────────────────────────────

private val sampleAnswers = listOf(
    AnswerOption("A", "Orion Nebula"),
    AnswerOption("B", "Crab Nebula"),
    AnswerOption("C", "Eagle Nebula"),
    AnswerOption("D", "Helix Nebula")
)

// ─────────────────────────────────────────────────────────────────────────────
// QUIZ SCREEN
// ─────────────────────────────────────────────────────────────────────────────

/**
 * Active quiz screen — displays one question at a time with a countdown timer,
 * four answer options, and a running score.
 *
 * Layout pattern: [Scaffold] → [Column] → [QuizTopBar] + [QuizCard] (weight 1f)
 * + [NextQuestionButton]. The card fills all available space between the top bar
 * and the button.
 *
 * **Animation infrastructure — Week 2 onwards:**
 * All `animate*AsState` and `rememberInfiniteTransition` calls are in place now.
 * They fire automatically as soon as the state values change in Week 3. No
 * modifications to this file are needed when connecting real state.
 *
 * **Static values replaced progressively:**
 * - [timeLeft] → Week 3: `LaunchedEffect` ticking every second
 * - [selectedAnswer] → Week 3: `mutableStateOf<String?>(null)`
 * - [questionText], [answers], [correctAnswer] → Week 4: nav args from [QuizDetailScreen]
 * - [score] → Week 5: `QuizViewModel.uiState`
 *
 * Figma design:
 * https://www.figma.com/file/your-figma-link/Trivia-Sparks?node-id=quiz-screen
 *
 * Wiki — Week 2 QuizScreen section:
 * https://github.com/your-username/trivia-sparks/wiki/Week-2#quizscreen
 *
 * @param timeLeft          Seconds remaining on the countdown. Range: 0–[totalTime].
 * @param totalTime         Total seconds for each question. Defaults to 30.
 * @param questionNumber    1-based index of the current question.
 * @param totalQuestions    Total questions in the session.
 * @param questionText      The question to display. HTML-decoded before reaching this screen.
 * @param answers           Four answer options in display order (A, B, C, D).
 * @param selectedAnswer    Letter of the answer the user tapped, or null if not yet answered.
 *                          Hardcoded to "B" this week for the visual demo.
 * @param correctAnswer     Letter of the correct answer.
 *                          Hardcoded to "B" this week. Comes from [Question] model in Week 4.
 * @param score             Running score shown in the top bar. Hardcoded this week.
 * @param quizLevel         Level label shown in the close pill, e.g. "Quiz Level 01".
 * @param quizTitle         Quiz title shown centred in the top bar.
 * @param onClose           Called when the user taps the close pill.
 *                          Wired to [NavController.popBackStack] in Week 4.
 * @param onNextQuestion    Called when the user taps the Next Question button.
 *                          Wired to ViewModel in Week 5.
 */
@Composable
fun QuizScreen(
    timeLeft: Int            = 12,
    totalTime: Int           = 30,
    questionNumber: Int      = 3,
    totalQuestions: Int      = 10,
    questionText: String     = "Which nebula is often called the \u201cNursery of Stars\u201d?",
    answers: List<AnswerOption> = sampleAnswers,
    selectedAnswer: String?  = "B",    // hardcoded for visual demo — mutableStateOf in Week 3
    correctAnswer: String    = "B",    // hardcoded for visual demo — from Question model in Week 4
    score: Int               = 240,
    quizLevel: String        = "Quiz Level 01",
    quizTitle: String        = "Quantum Physics Fun",
    onClose: () -> Unit      = {},
    onNextQuestion: () -> Unit = {}
) {
    Scaffold(
        containerColor = MaterialTheme.colorScheme.background
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            // ── Top bar ────────────────────────────────────────────────────
            QuizTopBar(
                quizLevel = quizLevel,
                quizTitle = quizTitle,
                score     = score,
                onClose   = onClose,
                modifier  = Modifier.padding(
                    horizontal = Spacing.screenEdge,
                    vertical   = Spacing.lg
                )
            )

            // ── Main card — fills all remaining space ──────────────────────
            QuizCard(
                modifier          = Modifier
                    .weight(1f)
                    .padding(horizontal = Spacing.screenEdge),
                timeLeft          = timeLeft,
                totalTime         = totalTime,
                questionNumber    = questionNumber,
                totalQuestions    = totalQuestions,
                questionText      = questionText,
                answers           = answers,
                selectedAnswer    = selectedAnswer,
                correctAnswer     = correctAnswer
            )

            // ── Next Question button — pinned below the card ───────────────
            NextQuestionButton(
                onClick  = onNextQuestion,
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
 * Custom top bar for the Quiz screen.
 *
 * Three elements in a [Row]:
 * 1. Close pill (left) — "× [quizLevel]" tappable pill to exit the quiz.
 * 2. Quiz title (centre) — [Modifier.weight] fills remaining space and allows wrapping.
 * 3. Score pill (right) — coin icon + current score on a [MaterialTheme.colorScheme.secondary] background.
 *
 * Implemented as a plain [Row] — no [androidx.compose.material3.TopAppBar] because the
 * pill-based layout does not fit the standard slot structure.
 *
 * Figma: https://www.figma.com/file/your-figma-link/Trivia-Sparks?node-id=quiz-top-bar
 *
 * @param quizLevel   Level label inside the close pill, e.g. "Quiz Level 01".
 * @param quizTitle   Quiz title shown centred between the two pills.
 * @param score       Current score value shown in the right pill.
 * @param onClose     Called when the user taps the close pill.
 * @param modifier    Applied to the outermost [Row] element.
 */
@Composable
private fun QuizTopBar(
    quizLevel: String,
    quizTitle: String,
    score: Int,
    onClose: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier          = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        // Close pill — tappable, exits the quiz
        Row(
            modifier = Modifier
                .background(
                    color = MaterialTheme.colorScheme.surface,
                    shape = ChipShape
                )
                .clickable(onClick = onClose)
                .padding(horizontal = Spacing.md, vertical = Spacing.sm),
            verticalAlignment     = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(Spacing.xs)
        ) {
            Icon(
                painter            = painterResource(R.drawable.ic_close),
                contentDescription = "Close quiz",
                tint               = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier           = Modifier.size(14.dp)
            )
            Text(
                text  = quizLevel,
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        // Quiz title — weight(1f) centres it between the two pills
        // maxLines = 2 allows wrapping for long quiz names
        Text(
            text       = quizTitle,
            style      = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            color      = MaterialTheme.colorScheme.onBackground,
            textAlign  = TextAlign.Center,
            maxLines   = 2,
            modifier   = Modifier
                .weight(1f)
                .padding(horizontal = Spacing.sm)
        )

        // Score pill — coral background, coin icon, score number
        Row(
            modifier = Modifier
                .background(
                    color = MaterialTheme.colorScheme.secondary,
                    shape = ChipShape
                )
                .padding(horizontal = Spacing.md, vertical = Spacing.sm),
            verticalAlignment     = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(Spacing.xs)
        ) {
            Icon(
                painter            = painterResource(R.drawable.ic_animals),
                contentDescription = null,
                tint               = Color.White,
                modifier           = Modifier.size(14.dp)
            )
            Text(
                text       = "$score",
                style      = MaterialTheme.typography.labelMedium,
                fontWeight = FontWeight.Bold,
                color      = Color.White
            )
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// QUIZ CARD
// ─────────────────────────────────────────────────────────────────────────────

/**
 * White content card containing the timer, question, and answer options.
 *
 * The card fills all available vertical space between the top bar and
 * the Next Question button. Content is vertically scrollable inside the card
 * to handle very long question texts on smaller screens.
 *
 * @param timeLeft          Seconds remaining — drives the timer animation.
 * @param totalTime         Total seconds per question — used to compute progress ratio.
 * @param questionNumber    1-based current question index.
 * @param totalQuestions    Total questions in the session.
 * @param questionText      The question text to display.
 * @param answers           Four answer options.
 * @param selectedAnswer    Letter of the tapped answer, or null.
 * @param correctAnswer     Letter of the correct answer.
 * @param modifier          Applied to the outermost [Card] element.
 */
@Composable
private fun QuizCard(
    timeLeft: Int,
    totalTime: Int,
    questionNumber: Int,
    totalQuestions: Int,
    questionText: String,
    answers: List<AnswerOption>,
    selectedAnswer: String?,
    correctAnswer: String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier.fillMaxWidth(),
        shape    = CardShape,
        colors   = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(horizontal = Spacing.xl, vertical = Spacing.xxl),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(Spacing.xl)
        ) {
            // ── Animated countdown timer ───────────────────────────────────
            AnimatedTimerRing(
                timeLeft  = timeLeft,
                totalTime = totalTime
            )

            // ── Question counter — "QUESTION 3 • 10" ──────────────────────
            QuestionCounter(
                current = questionNumber,
                total   = totalQuestions
            )

            // ── Question text ──────────────────────────────────────────────
            Text(
                text      = questionText,
                style     = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold,
                color     = MaterialTheme.colorScheme.onSurface,
                textAlign = TextAlign.Center,
                lineHeight = MaterialTheme.typography.titleLarge.fontSize * 1.4f
            )

            Spacer(modifier = Modifier.height(Spacing.xs))

            // ── Answer options ─────────────────────────────────────────────
            Column(
                modifier            = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.spacedBy(Spacing.md)
            ) {
                answers.forEach { answer ->
                    AnswerOptionCard(
                        answer         = answer,
                        isSelected     = answer.letter == selectedAnswer,
                        isCorrect      = answer.letter == correctAnswer,
                        hasAnswered    = selectedAnswer != null,
                        onClick        = { /* becomes: selectedAnswer = answer.letter in Week 3 */ }
                    )
                }
            }
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// ANIMATED TIMER RING
// ─────────────────────────────────────────────────────────────────────────────

/**
 * Animated circular countdown timer ring with a centred time display.
 *
 * Built with [Canvas] for precise control over stroke width, round caps, and
 * the sweep direction. Three layers of animation run simultaneously:
 *
 * **1. Progress animation** (`animateFloatAsState`)
 * The ring arc sweeps smoothly from the previous progress value to the new one
 * over 800ms with a [LinearEasing] curve — creates a fluid tick-by-tick feel.
 *
 * **2. Colour transition** (`animateColorAsState`)
 * The ring and number colour shifts through three stages as time runs out:
 * - > 50% remaining → [MaterialTheme.colorScheme.secondary] (coral) — safe
 * - 20–50% remaining → `dreamscape.medium` (yellow) — hurry
 * - ≤ 20% remaining → `dreamscape.hard` (red) — danger
 * Transitions are smooth over 600ms so the colour shift is never jarring.
 *
 * **3. Critical pulse** (`rememberInfiniteTransition`)
 * When time drops to ≤ 20%, the entire ring pulses with a subtle scale oscillation
 * (1.0x → 1.08x → 1.0x, repeating every 400ms). Applied via [graphicsLayer] for
 * GPU-accelerated scaling — no recomposition triggered by the pulse.
 *
 * **4. Number crossfade** (`AnimatedContent`)
 * When [timeLeft] changes, the number exits with a fade+scale-out and the new number
 * enters with a fade+scale-in. Duration is 200ms — visible but not distracting.
 *
 * These animations are **Week-3-ready**: they are triggered by changes to [timeLeft].
 * Once `LaunchedEffect` decrements [timeLeft] in Week 3, all four animations fire
 * automatically without any changes to this composable.
 *
 * Wiki — timer animation explained:
 * https://github.com/your-username/trivia-sparks/wiki/Week-2#quizscreen
 *
 * @param timeLeft   Current seconds remaining. Drives all four animations.
 * @param totalTime  Total seconds for the question — used to compute the progress ratio.
 * @param modifier   Applied to the outermost [Box] element.
 */
@Composable
private fun AnimatedTimerRing(
    timeLeft: Int,
    totalTime: Int,
    modifier: Modifier = Modifier
) {
    val ext = MaterialTheme.triviasparks

    // ── 1. Smooth progress animation ──────────────────────────────────────
    // Animates the arc sweep from the previous to the new progress value.
    // LinearEasing matches real clock time — feels like actual seconds passing.
    val animatedProgress by animateFloatAsState(
        targetValue   = timeLeft.toFloat() / totalTime.toFloat(),
        animationSpec = tween(durationMillis = 800, easing = LinearEasing),
        label         = "timer_progress"
    )

    // ── 2. Colour transition ───────────────────────────────────────────────
    // Derives target colour from time remaining, then animates the transition.
    val targetColour = when {
        timeLeft > (totalTime * 0.5f) -> MaterialTheme.colorScheme.secondary  // coral — safe
        timeLeft > (totalTime * 0.2f) -> ext.medium                            // yellow — hurry
        else                          -> ext.hard                               // red — danger
    }
    val ringColour by animateColorAsState(
        targetValue   = targetColour,
        animationSpec = tween(durationMillis = 600),
        label         = "timer_colour"
    )

    // ── 3. Critical pulse ──────────────────────────────────────────────────
    // Pulses scale when ≤ 20% time remains. Uses graphicsLayer — GPU-accelerated,
    // does not trigger recomposition. InfiniteTransition runs indefinitely.
    val isCritical = timeLeft <= (totalTime * 0.2f)
    val infiniteTransition = rememberInfiniteTransition(label = "timer_pulse")
    val pulseScale by infiniteTransition.animateFloat(
        initialValue  = 1f,
        targetValue   = if (isCritical) 1.08f else 1f,
        animationSpec = infiniteRepeatable(
            animation  = tween(durationMillis = 400, easing = FastOutSlowInEasing),
            repeatMode = RepeatMode.Reverse
        ),
        label = "pulse_scale"
    )

    val trackColour = MaterialTheme.colorScheme.surfaceVariant

    Box(
        modifier         = modifier
            .size(116.dp)
            .graphicsLayer {
                // GPU-accelerated scale — applied at the graphics layer, no recomposition
                scaleX = pulseScale
                scaleY = pulseScale
            },
        contentAlignment = Alignment.Center
    ) {
        // ── Ring drawn on Canvas ───────────────────────────────────────────
        Canvas(modifier = Modifier.fillMaxSize()) {
            val strokeWidth = 8.dp.toPx()
            val radius      = (size.minDimension / 2f) - (strokeWidth / 2f)
            val centre      = Offset(size.width / 2f, size.height / 2f)

            // Track — full 360° background ring in a muted colour
            drawCircle(
                color  = trackColour,
                radius = radius,
                center = centre,
                style  = Stroke(width = strokeWidth, cap = StrokeCap.Round)
            )

            // Progress arc — starts at top (−90° = 12 o'clock), sweeps clockwise
            // StrokeCap.Round gives a pill-shaped tip at both ends of the arc
            if (animatedProgress > 0f) {
                drawArc(
                    color      = ringColour,
                    startAngle = -90f,
                    sweepAngle = 360f * animatedProgress,
                    useCenter  = false,
                    style      = Stroke(width = strokeWidth, cap = StrokeCap.Round),
                    size       = Size(radius * 2f, radius * 2f),
                    topLeft    = Offset(centre.x - radius, centre.y - radius)
                )
            }
        }

        // ── 4. Number with crossfade on change ─────────────────────────────
        // AnimatedContent detects when timeLeft changes and plays enter/exit transitions.
        // Exits with scale-out + fade, enters with scale-in + fade — subtle and satisfying.
        AnimatedContent(
            targetState  = timeLeft,
            transitionSpec = {
                (fadeIn(tween(200)) + scaleIn(tween(200), initialScale = 0.8f)) togetherWith
                        (fadeOut(tween(150)) + scaleOut(tween(150), targetScale = 1.15f))
            },
            label = "timer_number"
        ) { displayTime ->
            Text(
                text       = "$displayTime",
                style      = MaterialTheme.typography.displaySmall,
                fontWeight = FontWeight.ExtraBold,
                color      = ringColour   // number colour always matches the ring
            )
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// QUESTION COUNTER
// ─────────────────────────────────────────────────────────────────────────────

/**
 * Displays the current question position as "QUESTION [current] • [total]".
 *
 * [current] is shown in [MaterialTheme.colorScheme.primary] to visually distinguish
 * the active question number from the muted total count.
 *
 * @param current   1-based index of the current question.
 * @param total     Total number of questions in the session.
 * @param modifier  Applied to the outermost [Row] element.
 */
@Composable
private fun QuestionCounter(
    current: Int,
    total: Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier          = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text  = "QUESTION",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(modifier = Modifier.width(Spacing.sm))
        Text(
            text       = "$current",
            style      = MaterialTheme.typography.labelSmall,
            fontWeight = FontWeight.Bold,
            color      = MaterialTheme.colorScheme.primary  // highlighted — active question
        )
        Spacer(modifier = Modifier.width(Spacing.sm))
        Text(
            text  = "•",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(modifier = Modifier.width(Spacing.sm))
        Text(
            text  = "$total",
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// ANSWER OPTION CARD
// ─────────────────────────────────────────────────────────────────────────────

/**
 * Animated answer option row card.
 *
 * Supports four visual states, all animated with `animateColorAsState`:
 * - **Default** — white background, subtle outline border, muted letter badge.
 * - **Selected correct** (`isSelected && isCorrect`) — primary tinted background,
 *   primary border (2dp), primary letter badge, checkmark icon on the right.
 * - **Selected wrong** (`isSelected && !isCorrect`) — error tinted background,
 *   error border (2dp), error letter badge, no checkmark.
 * - **Disabled** (`hasAnswered && !isSelected`) — reduced opacity, no border change.
 *   The correct answer keeps its default style so it is identifiable.
 *
 * **Animation note:** all `animateColorAsState` calls respond to changes in
 * [isSelected] and [isCorrect]. Once Week 3 connects `selectedAnswer` state,
 * tapping an option will smoothly transition all colours simultaneously.
 * No changes to this composable are needed in Week 3.
 *
 * Wiki — answer option animation:
 * https://github.com/your-username/trivia-sparks/wiki/Week-2#quizscreen
 *
 * @param answer        The answer option data (letter + text).
 * @param isSelected    True when this option is the one the user tapped.
 * @param isCorrect     True when this option is the correct answer.
 * @param hasAnswered   True when the user has tapped any option — disables all options.
 * @param onClick       Called when the user taps the card. No-op this week.
 * @param modifier      Applied to the outermost [Card] element.
 */
@Composable
fun AnswerOptionCard(
    answer: AnswerOption,
    isSelected: Boolean,
    isCorrect: Boolean,
    hasAnswered: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    // ── Animated colour values ─────────────────────────────────────────────
    // Derived from the three boolean parameters — change instantly when state changes.
    // All use tween(300) for a smooth 300ms transition between states.

    val targetBorderColour = when {
        isSelected && isCorrect   -> MaterialTheme.colorScheme.primary
        isSelected && !isCorrect  -> MaterialTheme.colorScheme.error
        else                      -> MaterialTheme.colorScheme.outlineVariant
    }
    val borderColour by animateColorAsState(
        targetValue   = targetBorderColour,
        animationSpec = tween(durationMillis = 300),
        label         = "answer_border_${answer.letter}"
    )

    val targetBgColour = when {
        isSelected && isCorrect   -> MaterialTheme.colorScheme.primary.copy(alpha = 0.10f)
        isSelected && !isCorrect  -> MaterialTheme.colorScheme.error.copy(alpha = 0.08f)
        else                      -> MaterialTheme.colorScheme.surface
    }
    val bgColour by animateColorAsState(
        targetValue   = targetBgColour,
        animationSpec = tween(durationMillis = 300),
        label         = "answer_bg_${answer.letter}"
    )

    val targetBadgeColour = when {
        isSelected && isCorrect   -> MaterialTheme.colorScheme.primary
        isSelected && !isCorrect  -> MaterialTheme.colorScheme.error
        else                      -> MaterialTheme.colorScheme.surfaceVariant
    }
    val badgeColour by animateColorAsState(
        targetValue   = targetBadgeColour,
        animationSpec = tween(durationMillis = 300),
        label         = "answer_badge_${answer.letter}"
    )

    val targetBadgeTextColour = when {
        isSelected -> Color.White
        else       -> MaterialTheme.colorScheme.onSurfaceVariant
    }
    val badgeTextColour by animateColorAsState(
        targetValue   = targetBadgeTextColour,
        animationSpec = tween(durationMillis = 300),
        label         = "answer_badge_text_${answer.letter}"
    )

    // Border width: 2dp when selected, 1dp otherwise
    val borderWidth = if (isSelected) 2.dp else 1.dp

    // Reduced opacity after answering — only unselected options fade
    val cardAlpha = if (hasAnswered && !isSelected) 0.55f else 1f

    Card(
        onClick  = onClick,
        enabled  = !hasAnswered,   // disable all taps after an answer is selected
        modifier = modifier
            .fillMaxWidth()
            .graphicsLayer { alpha = cardAlpha }  // smooth fade via graphicsLayer
            .border(width = borderWidth, color = borderColour, shape = CardShape),
        shape  = CardShape,
        colors = CardDefaults.cardColors(containerColor = bgColour),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Row(
            modifier              = Modifier
                .fillMaxWidth()
                .padding(horizontal = Spacing.lg, vertical = Spacing.md),
            verticalAlignment     = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(Spacing.lg)
        ) {
            // Letter badge — circle with animated fill colour
            Box(
                modifier         = Modifier
                    .size(ComponentSize.answerBadge)
                    .background(color = badgeColour, shape = CircleShape),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text       = answer.letter,
                    style      = MaterialTheme.typography.labelMedium,
                    fontWeight = FontWeight.Bold,
                    color      = badgeTextColour
                )
            }

            // Answer text — fills remaining space
            Text(
                text       = answer.text,
                style      = MaterialTheme.typography.titleMedium,
                color      = MaterialTheme.colorScheme.onSurface,
                modifier   = Modifier.weight(1f)
            )

            // Checkmark icon — only shown when selected AND correct
           /* if (isSelected && isCorrect) {
                Icon(
                    painter            = painterResource(R.drawable.ic_celebrities),
                    contentDescription = "Correct answer",
                    tint               = MaterialTheme.colorScheme.primary,
                    modifier           = Modifier.size(IconSize.md)
                )
            }*/
        }
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// NEXT QUESTION BUTTON
// ─────────────────────────────────────────────────────────────────────────────

/**
 * Full-width button to advance to the next question.
 *
 * Sits outside the quiz card on the [MaterialTheme.colorScheme.background] surface,
 * making it visually distinct from the card content.
 *
 * Uses [MaterialTheme.colorScheme.surface] fill with [MaterialTheme.colorScheme.primary]
 * text — a contained-but-low-emphasis style that does not compete with the answer options.
 *
 * In Week 5 this will be disabled until [selectedAnswer] is not null, preventing
 * players from skipping questions without answering.
 *
 * Figma: https://www.figma.com/file/your-figma-link/Trivia-Sparks?node-id=next-question-button
 *
 * @param onClick   Called when the user taps the button.
 * @param modifier  Applied to the outermost [Button] element.
 */
@Composable
private fun NextQuestionButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Button(
        onClick  = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(ComponentSize.buttonHeightLarge),  // 56dp
        shape  = ButtonShape,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.surface,
            contentColor   = MaterialTheme.colorScheme.primary
        ),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.outline)
    ) {
        Text(
            text       = "Next Question",
            style      = MaterialTheme.typography.labelLarge,
            fontWeight = FontWeight.Bold
        )
    }
}

// ─────────────────────────────────────────────────────────────────────────────
// PREVIEWS
// ─────────────────────────────────────────────────────────────────────────────

@Preview(showBackground = true, name = "Quiz — light, answer selected")
@Composable
fun QuizScreenLightPreview() {
    TriviaSparksTheme(darkTheme = false) {
        QuizScreen()
    }
}

@Preview(
    showBackground = true,
    name           = "Quiz — dark, answer selected",
    uiMode         = Configuration.UI_MODE_NIGHT_YES
)
@Composable
fun QuizScreenDarkPreview() {
    TriviaSparksTheme(darkTheme = true) {
        QuizScreen()
    }
}

@Preview(showBackground = true, name = "Quiz — no answer yet")
@Composable
fun QuizScreenNoAnswerPreview() {
    TriviaSparksTheme(darkTheme = false) {
        QuizScreen(selectedAnswer = null)
    }
}

@Preview(showBackground = true, name = "Quiz — critical time (≤ 6s)")
@Composable
fun QuizScreenCriticalTimePreview() {
    TriviaSparksTheme(darkTheme = false) {
        QuizScreen(timeLeft = 5, selectedAnswer = null)
    }
}

@Preview(showBackground = true, name = "Timer — safe (coral)")
@Composable
fun TimerSafePreview() {
    TriviaSparksTheme {
        Box(
            modifier         = Modifier.size(140.dp).background(MaterialTheme.colorScheme.surface),
            contentAlignment = Alignment.Center
        ) {
            AnimatedTimerRing(timeLeft = 22, totalTime = 30)
        }
    }
}

@Preview(showBackground = true, name = "Timer — critical (red)")
@Composable
fun TimerCriticalPreview() {
    TriviaSparksTheme {
        Box(
            modifier         = Modifier.size(140.dp).background(MaterialTheme.colorScheme.surface),
            contentAlignment = Alignment.Center
        ) {
            AnimatedTimerRing(timeLeft = 4, totalTime = 30)
        }
    }
}