package com.example.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ui.theme.*
import com.example.viewmodel.AppSection

@Composable
fun KidsBottomNavBar(
    currentSection: AppSection,
    onSectionSelected: (AppSection) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .navigationBarsPadding(),
        color = Color.White,
        shadowElevation = 12.dp,
        shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 4.dp, vertical = 6.dp),
            horizontalArrangement = Arrangement.SpaceAround,
            verticalAlignment = Alignment.CenterVertically
        ) {
            AppSection.values().forEach { section ->
                val isSelected = currentSection == section
                val (shortLabel, icon) = when (section) {
                    AppSection.ALPHABET -> "ABC" to "🔤"
                    AppSection.COUNTING -> "1-100" to "🔢"
                    AppSection.MATH_TABLES -> "Tables" to "✖️"
                    AppSection.HINDI_ALPHABET -> "हिन्दी" to "अ"
                    AppSection.STORIES -> "Stories" to "📖"
                    AppSection.RHYMES -> "Rhymes" to "🎵"
                }

                Box(
                    modifier = Modifier
                        .weight(1f)
                        .minimumInteractiveComponentSize()
                        .clip(RoundedCornerShape(14.dp))
                        .background(if (isSelected) KidsPrimary.copy(alpha = 0.12f) else Color.Transparent)
                        .clickable { onSectionSelected(section) }
                        .padding(vertical = 6.dp, horizontal = 2.dp)
                        .testTag("nav_tab_${section.name.lowercase()}"),
                    contentAlignment = Alignment.Center
                ) {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = icon,
                            fontSize = if (isSelected) 22.sp else 18.sp
                        )
                        Spacer(modifier = Modifier.height(2.dp))
                        Text(
                            text = shortLabel,
                            style = MaterialTheme.typography.labelSmall.copy(
                                fontWeight = if (isSelected) FontWeight.ExtraBold else FontWeight.Medium,
                                fontSize = 11.sp,
                                color = if (isSelected) KidsPrimary else KidsTextSecondary
                            ),
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}
