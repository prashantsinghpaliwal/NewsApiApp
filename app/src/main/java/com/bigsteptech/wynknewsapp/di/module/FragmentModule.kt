package com.bigsteptech.wynknewsapp.di.module

import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.bigsteptech.paytminsider.utils.Constants
import com.bigsteptech.wynknewsapp.data.repository.NewsRepository
import com.bigsteptech.wynknewsapp.ui.base.BaseFragment
import com.bigsteptech.wynknewsapp.ui.home.HomeViewModel
import com.bigsteptech.wynknewsapp.ui.home.adapter.NewsAdapter
import com.bigsteptech.wynknewsapp.utils.ViewModelProviderFactory
import com.bigsteptech.wynknewsapp.utils.network.NetworkHelper
import dagger.Module
import dagger.Provides
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Named

@Module
class FragmentModule(private val fragment: BaseFragment<*>) {

    @Provides
    fun provideLinearLayoutManager(): LinearLayoutManager =
        LinearLayoutManager(fragment.context, LinearLayoutManager.HORIZONTAL, false)

    @Provides
    @Named(Constants.TOP_NEWS_ADAPTER)
    fun provideTopNewsAdapter() = NewsAdapter(
        fragment.context!!,
        ArrayList(),
        false,
        fragment as NewsAdapter.ListItemClickListener
    )


    @Provides
    @Named(Constants.OTHER_ADAPTER)
    fun provideOtherNewsAdapter() = NewsAdapter(
        fragment.context!!,
        ArrayList(),
        true,
        fragment as NewsAdapter.ListItemClickListener
    )

    @Provides
    fun provideHomeViewModel(
        compositeDisposable: CompositeDisposable,
        networkHelper: NetworkHelper,
        newsRepository: NewsRepository
    ): HomeViewModel = ViewModelProviders.of(
        fragment, ViewModelProviderFactory(HomeViewModel::class) {
            HomeViewModel(
                compositeDisposable,
                networkHelper,
                newsRepository
            )
        }).get(HomeViewModel::class.java)
}