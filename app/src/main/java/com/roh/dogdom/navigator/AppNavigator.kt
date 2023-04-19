package com.roh.dogdom.navigator


enum class Screens {
    BUTTONS,
    LOGS
}

interface AppNavigator {
    // Navigate to a given screen.
    fun navigateTo(screen: Screens)
}