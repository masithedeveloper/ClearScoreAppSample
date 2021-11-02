package com.clearscore.report.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.clearscore.report.ui.fragments.ScoreSummaryFragment
import com.clearscore.report.ui.fragments.ScoreDetailFragment
import javax.inject.Inject

class TestClearScoreFragmentFactory @Inject constructor(
) : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when (className) {
            ScoreDetailFragment::class.java.name -> ScoreDetailFragment()
            ScoreSummaryFragment::class.java.name -> ScoreSummaryFragment()
            else -> super.instantiate(classLoader, className)
        }
    }
}