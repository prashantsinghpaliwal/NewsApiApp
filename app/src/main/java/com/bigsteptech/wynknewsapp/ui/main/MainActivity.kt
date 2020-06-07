package com.bigsteptech.wynknewsapp.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.bigsteptech.wynknewsapp.R
import com.bigsteptech.wynknewsapp.di.component.ActivityComponent
import com.bigsteptech.wynknewsapp.ui.base.BaseActivity
import com.bigsteptech.wynknewsapp.ui.home.HomeFragment
import com.bigsteptech.wynknewsapp.ui.underconstruction.UnderConstructionFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<MainViewModel>() {

    companion object {
        const val TAG = "MainActivity"
    }

    private var activeFragment: Fragment? = null

    override fun provideLayoutId(): Int = R.layout.activity_main


    override fun injectDependencies(activityComponent: ActivityComponent) =
        activityComponent.inject(this)

    override fun setupView(savedInstanceState: Bundle?) {

        showHome()

        bottomNavigation.run {
            itemIconTintList = null
            setOnNavigationItemSelectedListener {
                when (it.itemId) {
                    R.id.itemHome -> {

                        showHome()
                        true
                    }
                    R.id.itemSearch -> {
                        showUnderConstruction()
                        true
                    }

                    R.id.itemAccounts -> {
                        showUnderConstruction()
                        true
                    }
                    else -> false
                }
            }
        }
    }

    private fun showHome() {
        if (activeFragment is HomeFragment) return

        val fragmentTransaction = supportFragmentManager.beginTransaction()

        var fragment = supportFragmentManager.findFragmentByTag(HomeFragment.TAG) as HomeFragment?

        if (fragment == null) {
            fragment = HomeFragment.newInstance()
            fragmentTransaction.add(R.id.containerFragment, fragment, HomeFragment.TAG)
        } else {
            fragmentTransaction.show(fragment)
        }

        if (activeFragment != null) fragmentTransaction.hide(activeFragment as Fragment)

        fragmentTransaction.commit()

        activeFragment = fragment
    }

    private fun showUnderConstruction() {
        if (activeFragment is UnderConstructionFragment) return

        val fragmentTransaction = supportFragmentManager.beginTransaction()

        var fragment = supportFragmentManager.findFragmentByTag(UnderConstructionFragment.TAG) as UnderConstructionFragment?

        if (fragment == null) {
            fragment = UnderConstructionFragment.newInstance()
            fragmentTransaction.add(R.id.containerFragment, fragment, UnderConstructionFragment.TAG)
        } else {
            fragmentTransaction.show(fragment)
        }

        if (activeFragment != null) fragmentTransaction.hide(activeFragment as Fragment)

        fragmentTransaction.commit()

        activeFragment = fragment
    }

}