package com.bigsteptech.wynknewsapp.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bigsteptech.paytminsider.utils.Constants
import com.bigsteptech.wynknewsapp.R
import com.bigsteptech.wynknewsapp.data.remote.response.Articles
import com.bigsteptech.wynknewsapp.di.component.FragmentComponent
import com.bigsteptech.wynknewsapp.ui.base.BaseFragment
import com.bigsteptech.wynknewsapp.ui.detail.DetailsActivity
import com.bigsteptech.wynknewsapp.ui.home.adapter.NewsAdapter
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject
import javax.inject.Named

class HomeFragment : BaseFragment<HomeViewModel>(),NewsAdapter.ListItemClickListener {

    companion object {

        const val TAG = "HomeFragment"

        fun newInstance(): HomeFragment {
            val args = Bundle()
            val fragment = HomeFragment()
            fragment.arguments = args
            return fragment
        }
    }

    @Inject
    lateinit var otherLinearLayoutManager: LinearLayoutManager

    @Inject
    lateinit var topNewsLinearLayoutManager: LinearLayoutManager

    @field:[Inject Named(Constants.TOP_NEWS_ADAPTER)]
    lateinit var topNewsAdapter: NewsAdapter

    @field:[Inject Named(Constants.OTHER_ADAPTER)]
    lateinit var otherNewsAdapter: NewsAdapter

    private var isFirstLoad = true


    override fun provideLayoutId(): Int = R.layout.fragment_home

    override fun injectDependencies(fragmentComponent: FragmentComponent) =
        fragmentComponent.inject(this)

    override fun setupView(view: View) {
        top_news_list.adapter = topNewsAdapter
        top_news_list.layoutManager = topNewsLinearLayoutManager

        other_news_list.adapter=otherNewsAdapter
        other_news_list.layoutManager = otherLinearLayoutManager

        viewModel.fetchTopHeadLines("1", "10")
        viewModel.fetchOtherNews("1", "10")
    }

    override fun setupObservers() {
        super.setupObservers()

        viewModel.topNewsListLiveData.observe(this, Observer {

            it?.let {
                top_news_label.visibility = View.VISIBLE
                top_news_list.visibility = View.VISIBLE

                topNewsAdapter.updateList(it)

                if (isFirstLoad) {
                    scrollView.smoothScrollTo(0, 0)
                    isFirstLoad = false
                }

            }
        })

        viewModel.otherNewsListLiveData.observe(this, Observer {

            it?.let {
                other_news_label.visibility = View.VISIBLE
                other_news_list.visibility = View.VISIBLE

                otherNewsAdapter.updateList(it)
            }

        })

        viewModel.shimmerLiveData.observe(this, Observer {

            it?.let {
                shimmerFrameLayout.stopShimmer()
                shimmerFrameLayout.visibility = View.INVISIBLE
            }

        })

        viewModel.errorScreenLiveData.observe(this, Observer {

            it?.let {
                error_layout.visibility = View.VISIBLE
            }
        })
    }

    override fun onResume() {
        super.onResume()
        shimmerFrameLayout.startShimmer()
    }

    override fun onPause() {
        shimmerFrameLayout.stopShimmer()
        super.onPause()

    }

    override fun onItemClick(articles: Articles) {
        Log.v("HomeFragment", "articles $articles")

        val intent= Intent(context,DetailsActivity::class.java)
        intent.putExtra("title",articles.title)
        intent.putExtra("author",articles.author)
        intent.putExtra("description",articles.description)
        intent.putExtra("publishedAt",articles.publishedAt)
        intent.putExtra("url",articles.url)
        intent.putExtra("urlToImage",articles.urlToImage)

        startActivity(intent)
    }
}