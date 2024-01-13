package com.example.firstcomposeactivity.models

import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavigationModel(var name: String,
                                 val route: String,
                                 val icon: ImageVector,
                                 val badgeCount: Int = 0)
