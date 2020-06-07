package com.bigsteptech.wynknewsapp.ui.underconstruction

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bigsteptech.wynknewsapp.R


class UnderConstructionFragment :Fragment() {

    companion object {

        const val TAG = "UnderConstructionFragment"

        fun newInstance(): UnderConstructionFragment {
            val args = Bundle()
            val fragment = UnderConstructionFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_under_construction, container, false)
}