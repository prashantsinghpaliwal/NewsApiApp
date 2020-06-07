package com.bigsteptech.wynknewsapp.ui.splash

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import com.bigsteptech.wynknewsapp.ui.main.MainActivity
import com.bigsteptech.wynknewsapp.R
import com.bigsteptech.wynknewsapp.di.component.ActivityComponent
import com.bigsteptech.wynknewsapp.ui.base.BaseActivity



class SplashActivity : BaseActivity<SplashViewModel>() {

    companion object {
        const val TAG = "SplashActivity"
    }

    override fun provideLayoutId(): Int = R.layout.activity_splash

    override fun injectDependencies(activityComponent: ActivityComponent) {
        activityComponent.inject(this)
    }

    override fun setupView(savedInstanceState: Bundle?) {
    }

    override fun setupObservers() {
        super.setupObservers()

        viewModel.launchMain.observe(this, Observer {
            it?.run {
                startActivity(Intent(applicationContext, MainActivity::class.java))
            }
        })
    }
}