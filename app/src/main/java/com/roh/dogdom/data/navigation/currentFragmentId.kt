package com.roh.dogdom.data.navigation

data class CurrentFragmentId (
    val HomeId: Int = 2131231293,
    val ReleaseId: Int = 2131231294,  // 수정필요
    val CircleId: Int = 2131231295,  // 수정필요
    val MessageId: Int = 2131231288,
    val UserId: Int = 2131231296,  // 수정필요
    val Loginid: Int = 2131230988,
)

data class CurrentFragment (
    val HOME: Int = 0,
    val CIRCLE: Int = 1,
    val RELEASE: Int = 2,
    val MESSAGE: Int = 3,
    val USER: Int = 4,
    val LOGIN: Int = 5,
    )
