package pt.isel.dam.sv2526.triviasparksgame.ui.theme

/**
 * ─────────────────────────────────────────────────────────────────────────────
 * TriviaSparks THEME — QUICK REFERENCE
 * ─────────────────────────────────────────────────────────────────────────────
 *
 * ⚠️ THIS FILE IS DOCUMENTATION ONLY
 *
 * This Kotlin file contains no runtime logic and is NOT meant to be imported.
 * It exists purely as a reference guide for how to use the app theme system.
 *
 * Use this file when you're unsure which design token (color, typography,
 * shape, spacing, etc.) should be applied to a UI element.
 *
 * ─────────────────────────────────────────────────────────────────────────────
 *
 * HOW TO ACCESS THEME TOKENS IN COMPOSABLES
 *
 * Material 3 standard tokens:
 *
 * MaterialTheme.colorScheme.primary            → violet primary actions
 * MaterialTheme.colorScheme.onPrimary          → text/icons on primary
 * MaterialTheme.colorScheme.surface            → card backgrounds
 * MaterialTheme.colorScheme.onSurface          → main card text
 * MaterialTheme.colorScheme.onSurfaceVariant   → subtitles / muted text
 * MaterialTheme.colorScheme.background         → screen background
 * MaterialTheme.colorScheme.onBackground       → primary screen text
 * MaterialTheme.colorScheme.outline            → borders / dividers
 * MaterialTheme.colorScheme.secondary          → accent / XP / secondary CTA
 * MaterialTheme.colorScheme.error              → errors / hard difficulty
 * MaterialTheme.colorScheme.tertiary           → success / easy states
 *
 * ─────────────────────────────────────────────────────────────────────────────
 *
 * EXTENDED DREAMSCAPE TOKENS
 *
 * MaterialTheme.dreamscape.easy              → mint green (easy state)
 * MaterialTheme.dreamscape.easyOnLight       → text on light backgrounds
 * MaterialTheme.dreamscape.medium            → sunny yellow (medium state)
 * MaterialTheme.dreamscape.mediumOnLight     → text on light backgrounds
 * MaterialTheme.dreamscape.hard              → strawberry red (hard state)
 * MaterialTheme.dreamscape.hardOnLight       → text on light backgrounds
 * MaterialTheme.dreamscape.scoreNumber       → score/XP large numbers
 * MaterialTheme.dreamscape.xpColour          → XP badges / rewards
 *
 * Category colors:
 * - categoryScience  → blue
 * - categorySports   → coral
 * - categoryHistory  → yellow
 *
 * (See DreamscapeExtendedColors for full palette)
 *
 * ─────────────────────────────────────────────────────────────────────────────
 *
 * TYPOGRAPHY TOKENS
 *
 * MaterialTheme.typography.displayMedium   → score numbers, big results
 * MaterialTheme.typography.headlineMedium   → quiz titles, screen titles
 * MaterialTheme.typography.titleMedium      → cards, list items
 * MaterialTheme.typography.bodyMedium       → descriptions, paragraphs
 * MaterialTheme.typography.labelLarge       → primary buttons
 * MaterialTheme.typography.labelSmall       → uppercase section labels
 *
 * ─────────────────────────────────────────────────────────────────────────────
 *
 * SHAPES
 *
 * MaterialTheme.shapes.extraLarge → 24dp cards
 * MaterialTheme.shapes.medium     → 16dp buttons / inputs
 * MaterialTheme.shapes.small      → chips / small elements
 *
 * Custom shapes:
 * CardShape, ButtonShape, ChipShape → reusable design shapes
 * DreamscapeRadius.Card            → 24.dp base radius
 *
 * ─────────────────────────────────────────────────────────────────────────────
 *
 * SPACING SYSTEM
 *
 * Spacing.screenEdge   → 20.dp screen padding
 * Spacing.cardPadding  → 16.dp inside cards
 * Spacing.cardGap      → 12.dp between cards
 * Spacing.sectionGap   → 24.dp between sections
 *
 * ─────────────────────────────────────────────────────────────────────────────
 *
 * COMPONENT SIZES
 *
 * ComponentSize.buttonHeight   → 52.dp
 * ComponentSize.avatarMedium   → 48.dp
 * ComponentSize.timerCircle    → 56.dp
 * IconSize.md                 → 24.dp standard icons
 *
 * ─────────────────────────────────────────────────────────────────────────────
 *
 * COLOR USAGE GUIDE (WHAT TO USE WHERE)
 *
 * Background screen        → colorScheme.background
 * Card background          → colorScheme.surface
 * Subtle containers        → colorScheme.surfaceVariant
 * Primary buttons          → colorScheme.primary
 * Button text              → colorScheme.onPrimary
 * Selected chip background → colorScheme.primaryContainer
 * Main text                → onBackground / onSurface
 * Secondary text           → onSurfaceVariant
 * Borders                  → outline
 * Dividers                 → outlineVariant
 * XP / secondary CTA       → secondary
 * Error / hard difficulty  → error
 * Easy difficulty         → dreamscape.easy
 * Medium difficulty       → dreamscape.medium
 * Score numbers           → dreamscape.scoreNumber
 *
 * ─────────────────────────────────────────────────────────────────────────────
 *
 * DIFFICULTY CHIP PATTERN
 *
 * Example of consistent difficulty styling:
 *
 * @Composable
 * fun DifficultyChip(difficulty: String, isSelected: Boolean) {
 *     val ext = MaterialTheme.dreamscape
 *
 *     val (chipColor, textColor) = when (difficulty.lowercase()) {
 *         "easy"   -> ext.easy   to ext.easyOnLight
 *         "medium" -> ext.medium to ext.mediumOnLight
 *         "hard"   -> ext.hard   to ext.hardOnLight
 *         else     -> MaterialTheme.colorScheme.outline to
 *                     MaterialTheme.colorScheme.onSurfaceVariant
 *     }
 *
 *     Text(
 *         text = difficulty,
 *         style = MaterialTheme.typography.labelMedium,
 *         color = if (isSelected) textColor
 *                 else MaterialTheme.colorScheme.onSurfaceVariant,
 *         modifier = Modifier
 *             .background(
 *                 color = if (isSelected) chipColor.copy(alpha = 0.15f)
 *                         else Color.Transparent,
 *                 shape = ChipShape
 *             )
 *             .border(
 *                 width = if (isSelected) 1.5.dp else 1.dp,
 *                 color = if (isSelected) chipColor.copy(alpha = 0.6f)
 *                         else MaterialTheme.colorScheme.outline,
 *                 shape = ChipShape
 *             )
 *             .padding(
 *                 horizontal = Spacing.chipHorizontal,
 *                 vertical = Spacing.chipVertical
 *             )
 *     )
 * }
 *
 * ─────────────────────────────────────────────────────────────────────────────
 *
 * CATEGORY ICON CONTAINER PATTERN
 *
 * @Composable
 * fun CategoryIconContainer(color: Color, icon: ImageVector) {
 *     Box(
 *         modifier = Modifier
 *             .size(ComponentSize.iconContainerSmall)
 *             .background(
 *                 color = color.copy(alpha = 0.15f),
 *                 shape = IconBoxShape
 *             ),
 *         contentAlignment = Alignment.Center
 *     ) {
 *         Icon(
 *             imageVector = icon,
 *             contentDescription = null,
 *             tint = color,
 *             modifier = Modifier.size(IconSize.xl)
 *         )
 *     }
 * }
 *
 * ─────────────────────────────────────────────────────────────────────────────
 *
 * SECTION LABEL PATTERN (UPPERCASE)
 *
 * @Composable
 * fun SectionLabel(text: String) {
 *     Text(
 *         text = text.uppercase(),
 *         style = MaterialTheme.typography.labelSmall,
 *         color = MaterialTheme.colorScheme.onSurfaceVariant
 *     )
 * }
 *
 * ─────────────────────────────────────────────────────────────────────────────
 *
 * DARK MODE PREVIEWS (ALWAYS INCLUDE BOTH)
 *
 * @Preview(showBackground = true, name = "Light")
 * @Preview(
 *     showBackground = true,
 *     name = "Dark",
 *     uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES
 * )
 * @Composable
 * fun MyComposablePreview() {
 *     TriviaSparksTheme {
 *         MyComposable()
 *     }
 * }
 *
 * ─────────────────────────────────────────────────────────────────────────────
 */