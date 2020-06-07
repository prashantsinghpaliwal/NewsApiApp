package com.bigsteptech.wynknewsapp.di.component

import com.bigsteptech.wynknewsapp.di.FragmentScope
import com.bigsteptech.wynknewsapp.di.module.FragmentModule
import com.bigsteptech.wynknewsapp.ui.home.HomeFragment

import dagger.Component

@FragmentScope
@Component(
    dependencies = [ApplicationComponent::class],
    modules = [FragmentModule::class]
)
interface FragmentComponent {

    fun inject(fragment: HomeFragment)
}