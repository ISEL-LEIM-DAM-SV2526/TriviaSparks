package pt.isel.dam.sv2526.triviasparksgame.ui.theme

// ─────────────────────────────────────────────────────────────────────────────
// TriviaSparks THEME — QUICK REFERENCE
// ─────────────────────────────────────────────────────────────────────────────
//
// This file is documentation only — no code to import.
// Read it when you're unsure which token to use for a given UI element.
//
// ─────────────────────────────────────────────────────────────────────────────
//
// HOW TO ACCESS TOKENS IN COMPOSABLES
//
// Standard Material3 tokens:
//   MaterialTheme.colorScheme.primary         → violet fill
//   MaterialTheme.colorScheme.onPrimary       → white text on violet
//   MaterialTheme.colorScheme.surface         → card background
//   MaterialTheme.colorScheme.onSurface       → main text on cards
//   MaterialTheme.colorScheme.onSurfaceVariant → muted text, subtitles
//   MaterialTheme.colorScheme.background      → screen background
//   MaterialTheme.colorScheme.onBackground    → main text on screen
//   MaterialTheme.colorScheme.outline         → card borders, dividers
//   MaterialTheme.colorScheme.secondary       → coral peach accents
//   MaterialTheme.colorScheme.error           → strawberry / hard difficulty
//   MaterialTheme.colorScheme.tertiary        → mint green / easy / success
//
// Extended Dreamscape tokens:
//   MaterialTheme.dreamscape.easy             → #6ECFB0 mint green
//   MaterialTheme.dreamscape.easyOnLight      → #3AAB87 (text on white bg)
//   MaterialTheme.dreamscape.medium           → #FFD166 sunny yellow
//   MaterialTheme.dreamscape.mediumOnLight    → #B8922A (text on white bg)
//   MaterialTheme.dreamscape.hard             → #FF6B8A strawberry
//   MaterialTheme.dreamscape.hardOnLight      → #C42050 (text on white bg)
//   MaterialTheme.dreamscape.scoreNumber      → Violet400 (large score displays)
//   MaterialTheme.dreamscape.xpColour         → Coral400 (XP badges)
//   MaterialTheme.dreamscape.categoryScience  → #4A90D9 blue
//   MaterialTheme.dreamscape.categorySports   → Coral400
//   MaterialTheme.dreamscape.categoryHistory  → SunnyYellow
//   (see DreamscapeExtendedColors for the full list)
//
// Typography:
//   MaterialTheme.typography.displayMedium    → score numbers
//   MaterialTheme.typography.headlineMedium   → quiz titles, screen titles
//   MaterialTheme.typography.titleMedium      → card titles, list items
//   MaterialTheme.typography.bodyMedium       → descriptions, body text
//   MaterialTheme.typography.labelLarge       → button text
//   MaterialTheme.typography.labelSmall       → UPPERCASE section headings
//
// Shapes:
//   MaterialTheme.shapes.extraLarge           → 24dp — main cards
//   MaterialTheme.shapes.medium               → 16dp — buttons, text fields
//   MaterialTheme.shapes.extraSmall           → pill — chips, badges
//   CardShape, ButtonShape, ChipShape         → direct shape objects
//   DreamscapeRadius.Card                     → 24.dp (raw dp value)
//
// Spacing:
//   Spacing.screenEdge                        → 20.dp horizontal screen padding
//   Spacing.cardPadding                       → 16.dp internal card padding
//   Spacing.cardGap                           → 12.dp gap between list cards
//   Spacing.sectionGap                        → 24.dp gap between sections
//
// Component sizes:
//   ComponentSize.buttonHeight                → 52.dp
//   ComponentSize.avatarMedium                → 48.dp
//   ComponentSize.timerCircle                 → 56.dp
//   IconSize.md                               → 24.dp standard icon
//
// ─────────────────────────────────────────────────────────────────────────────
//
// COLOUR ROLE REFERENCE — which token for which UI element
//
// Screen background         → colorScheme.background
// Card background           → colorScheme.surface
// Inner section / stat pill → colorScheme.surfaceVariant
// Primary button fill       → colorScheme.primary
// Text on primary button    → colorScheme.onPrimary
// Selected chip fill        → colorScheme.primaryContainer
// Text on selected chip     → colorScheme.onPrimaryContainer
// Main text (headings)      → colorScheme.onBackground / onSurface
// Secondary text (subtitles)→ colorScheme.onSurfaceVariant
// Card border               → colorScheme.outline
// Subtle divider            → colorScheme.outlineVariant
// XP badge / secondary CTA  → colorScheme.secondary
// Snackbar background       → colorScheme.inverseSurface
// Snackbar text             → colorScheme.inverseOnSurface
// Error / hard difficulty   → colorScheme.error
// Easy difficulty           → dreamscape.easy (or colorScheme.tertiary)
// Medium difficulty         → dreamscape.medium
// Score display number      → dreamscape.scoreNumber
// Category icon colour      → dreamscape.category* (e.g. dreamscape.categoryScience)
//
// ─────────────────────────────────────────────────────────────────────────────
//
// DIFFICULTY CHIP PATTERN
//
// @Composable
// fun DifficultyChip(difficulty: String, isSelected: Boolean) {
//     val ext = MaterialTheme.dreamscape
//     val (chipColour, textColour) = when (difficulty.lowercase()) {
//         "easy"   -> ext.easy   to ext.easyOnLight
//         "medium" -> ext.medium to ext.mediumOnLight
//         "hard"   -> ext.hard   to ext.hardOnLight
//         else     -> MaterialTheme.colorScheme.outline to
//                     MaterialTheme.colorScheme.onSurfaceVariant
//     }
//     Text(
//         text = difficulty,
//         style = MaterialTheme.typography.labelMedium,
//         color = if (isSelected) textColour
//                 else MaterialTheme.colorScheme.onSurfaceVariant,
//         modifier = Modifier
//             .background(
//                 color = if (isSelected) chipColour.copy(alpha = 0.15f)
//                         else Color.Transparent,
//                 shape = ChipShape
//             )
//             .border(
//                 width = if (isSelected) 1.5.dp else 1.dp,
//                 color = if (isSelected) chipColour.copy(alpha = 0.6f)
//                         else MaterialTheme.colorScheme.outline,
//                 shape = ChipShape
//             )
//             .padding(
//                 horizontal = Spacing.chipHorizontal,
//                 vertical   = Spacing.chipVertical
//             )
//     )
// }
//
// ─────────────────────────────────────────────────────────────────────────────
//
// CATEGORY ICON CONTAINER PATTERN
//
// @Composable
// fun CategoryIconContainer(colour: Color, icon: ImageVector) {
//     Box(
//         modifier = Modifier
//             .size(ComponentSize.iconContainerSmall)
//             .background(
//                 color = colour.copy(alpha = 0.15f),
//                 shape = IconBoxShape
//             ),
//         contentAlignment = Alignment.Center
//     ) {
//         Icon(
//             imageVector        = icon,
//             contentDescription = null,
//             tint               = colour,
//             modifier           = Modifier.size(IconSize.xl)
//         )
//     }
// }
//
// ─────────────────────────────────────────────────────────────────────────────
//
// UPPERCASE SECTION HEADING PATTERN
//
// @Composable
// fun SectionLabel(text: String) {
//     Text(
//         text          = text.uppercase(),
//         style         = MaterialTheme.typography.labelSmall,
//         color         = MaterialTheme.colorScheme.onSurfaceVariant,
//         // letterSpacing is already 1.5sp in labelSmall — no override needed
//     )
// }
//
// ─────────────────────────────────────────────────────────────────────────────
//
// DARK MODE PREVIEWS — always add both
//
// @Preview(showBackground = true, name = "Light")
// @Preview(
//     showBackground = true,
//     name = "Dark",
//     uiMode = android.content.res.Configuration.UI_MODE_NIGHT_YES
// )
// @Composable
// fun MyComposablePreview() {
//     TriviaSparksTheme {
//         MyComposable()
//     }
// }
//
// ─────────────────────────────────────────────────────────────────────────────