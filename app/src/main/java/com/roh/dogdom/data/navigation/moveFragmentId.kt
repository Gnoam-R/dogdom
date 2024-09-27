package com.roh.dogdom.data.navigation

import com.roh.dogdom.R

data class MvFragment (
    val Loginid: Int = R.id.loginFragment,
    val HomeId: Int = R.id.homeFragment,
    val MessageId: Int = R.id.messageFragment,
    val PersonalId: Int = R.id.personalFragment,
    val ReleaseId: Int = R.id.releaseFragment,
    val ReleaseDynamicId: Int = R.id.releaseDynamicFragment,
)

data class NextFragmentId (
    val HOME: Int = 1,
    val CIRCLE: Int = 2,
    val RELEASE: Int = 3,
    val MESSAGE: Int = 4,
    val PERSONAL: Int = 5,
    val LOGIN: Int = 6,
    val RELEASE_DYNAMIC: Int = 7,
)