package com.example.restaurantproyectdam.ui.components.ordercomponents

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.MailOutline
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material.icons.outlined.ThumbUp
import androidx.compose.ui.graphics.vector.ImageVector

enum class OrderTabs(
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
    val text: String
) {
    Pending(
        unselectedIcon = Icons.Outlined.MailOutline,
        selectedIcon = Icons.Filled.Email,
        text = "Pending"
    ),
    Ready(
        unselectedIcon = Icons.Outlined.ThumbUp,
        selectedIcon = Icons.Filled.ThumbUp,
        text = "Ready"
    ),
    Delivered(
        unselectedIcon = Icons.Outlined.ShoppingCart,
        selectedIcon = Icons.Filled.ShoppingCart,
        text = "Delivered"
    )
}