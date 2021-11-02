package com.clearscore.report.ui.fragments

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import javax.inject.Inject

class ClearScoreFragmentFactory @Inject constructor(
) : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when (className) {
            ScoreSummaryFragment::class.java.name -> ScoreSummaryFragment()
            ScoreDetailFragment::class.java.name -> ScoreDetailFragment()
            else -> super.instantiate(classLoader, className)
        }
    }
}