package com.roh.dogdom.module.di


import com.roh.dogdom.navigator.AppNavigationImpl
import com.roh.dogdom.navigator.AppNavigator
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@InstallIn(ActivityComponent::class)
@Module
abstract class NavigationModule () {

    @Binds
    abstract fun binNavigator(impl: AppNavigationImpl) : AppNavigator
}