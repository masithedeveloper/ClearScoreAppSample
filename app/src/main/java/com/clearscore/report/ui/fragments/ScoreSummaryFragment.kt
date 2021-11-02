package com.clearscore.report.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.google.android.material.snackbar.Snackbar
import com.clearscore.report.R
import com.clearscore.report.data.remote.responses.ClientScoreDetailsResponse
import com.clearscore.report.databinding.FragmentScoreSummaryBinding
import com.clearscore.report.other.Status
import com.clearscore.report.ui.viewmodels.ClearScoreViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ScoreSummaryFragment @Inject constructor() : Fragment(R.layout.fragment_score_summary) {

    lateinit var viewModel: ClearScoreViewModel

    private var _binding: FragmentScoreSummaryBinding? = null
    private var clientScoreDetailsResponse: ClientScoreDetailsResponse? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         _binding = FragmentScoreSummaryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(ClearScoreViewModel::class.java)
        subscribeToObservers()
        viewModel.getClientScoreDetails()
        listeners()
    }

    private fun listeners() {
        binding.clientScoreRootLayout.setOnClickListener {
            val navHostFragment =
                activity?.supportFragmentManager?.findFragmentById(R.id.navHostFragment) as NavHostFragment
            navHostFragment.navController.navigate(R.id.action_scoreSummaryFragment_to_scoreDetailFragment)
        }
    }

    private fun subscribeToObservers() {
        viewModel.clientScoreDetailsResponse?.observe(viewLifecycleOwner) {
            it?.getContentIfNotHandled()?.let { result ->
                when (result.status) {
                    Status.SUCCESS -> {
                        clientScoreDetailsResponse = result.data
                        setUpClientScoreUI()
                        binding.progressBar.visibility = View.GONE
                    }
                    Status.ERROR -> {
                        Snackbar.make(
                            binding.root,
                            result.message ?: "An unknown error occurred.",
                            Snackbar.LENGTH_LONG
                        ).show()
                        binding.progressBar.visibility = View.GONE
                    }
                    Status.LOADING -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }
                }
            }
        }
    }

    private fun computePercentage(score: Int?, maxScoreValue: Int?): Float{
        var percentage = 0.0F
        if (score != null) {
            percentage = (score.toFloat()/maxScoreValue!!.toFloat()) * 100
        }
        return percentage
    }

    private fun setUpClientScoreUI(){
        binding.clientScoreProgressBar.setProgress(computePercentage(clientScoreDetailsResponse?.creditReportInfo?.score, clientScoreDetailsResponse?.creditReportInfo?.maxScoreValue))
        binding.scoreValueTextView.text = clientScoreDetailsResponse?.creditReportInfo?.score.toString()
        binding.maxScoreValueTextView.text = String.format("out of %d", clientScoreDetailsResponse?.creditReportInfo?.maxScoreValue)
        binding.scoreValueTextView.setTextColor(binding.clientScoreProgressBar?.foregroundPaint?.color!!)
        binding.clientScoreRootLayout.visibility = View.VISIBLE
    }
}
















