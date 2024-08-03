package com.harrrshith.moowe.ui.navigation

import kotlinx.serialization.Serializable

sealed class NavigationDestinations {
    @Serializable
    data object Onboard: NavigationDestinations()
    @Serializable
    data object Home: NavigationDestinations(){
        @Serializable
        data object Discover: NavigationDestinations()
        @Serializable
        data object Explore: NavigationDestinations()
        @Serializable
        data object Search: NavigationDestinations()
        @Serializable
        data object Profile: NavigationDestinations()
    }
}