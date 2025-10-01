package com.example.proyecto.components

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import com.example.proyecto.R

data class NavItem(
    val label: String,
    val icon: ImageVector,
    val route: String
)

@Preview(showBackground = true)
@Composable
fun BottomNavMenu(
    startRoute: String = "reservas",
    onRouteSelected: (String) -> Unit = {}
) {
    val items = listOf(
        NavItem("Albergues", Icons.Filled.Home, "albergues"),
        NavItem("Reservas", Icons.Filled.DateRange, "reservas"),
        NavItem("Historial", Icons.Filled.History, "historial"),
        NavItem("Mi Cuenta", Icons.Filled.Person, "cuenta")
    )

    var selected by remember { mutableStateOf(startRoute) }

    NavigationBar(containerColor = Color.White) {
        items.forEach { item ->
            NavigationBarItem(
                selected = selected == item.route,
                onClick = {
                    selected = item.route
                    onRouteSelected(item.route)
                },
                icon = { Icon(imageVector = item.icon, contentDescription = item.label) },
                label = { Text(item.label, fontSize = 12.sp) },
                alwaysShowLabel = true,
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = colorResource(R.color.white),
                    unselectedIconColor = colorResource(R.color.pantone_cool_gray_8),
                    selectedTextColor = colorResource(R.color.pantone_302),
                    unselectedTextColor = colorResource(R.color.pantone_cool_gray_8),
                    indicatorColor = colorResource(R.color.pantone_320)
                )
            )
        }
    }
}
