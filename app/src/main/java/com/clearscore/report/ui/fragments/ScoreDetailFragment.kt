package com.clearscore.report.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.clearscore.report.R
import com.clearscore.report.databinding.FragmentScoreDetailBinding

import com.clearscore.report.ui.viewmodels.ClearScoreViewModel
import com.clearscore.report.utils.CurrencyUtil.Companion.formatCurrency
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ScoreDetailFragment @Inject constructor() : Fragment(R.layout.fragment_score_detail) {

    lateinit var viewModel: ClearScoreViewModel

    private var _binding: FragmentScoreDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_score_detail, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(ClearScoreViewModel::class.java)
        setUpClientDetailsUI()
        handleBackNavigation()
    }

    private fun handleBackNavigation() {
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                findNavController().popBackStack()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(callback)
    }

    private fun setUpClientDetailsUI(){
        binding.statusTextView.text = String.format("Status: %s" , viewModel.clientScoreDetailsResponse.value?.peekContent()?.data?.creditReportInfo?.status)
        binding.hasEverDefaultedTextView.text = String.format("hasEverDefaulted: %s" , if(viewModel.clientScoreDetailsResponse.value?.peekContent()?.data?.creditReportInfo?.hasEverDefaulted == true) "True" else "False")
        binding.hasEverBeenDelinquentTextView.text = String.format("hasEverBeenDelinquent: %s" , if(viewModel.clientScoreDetailsResponse.value?.peekContent()?.data?.creditReportInfo?.hasEverBeenDelinquent== true) "True" else "False")
        binding.percentageCreditUsedTextView.text = String.format("percentageCreditUsed: %d", viewModel.clientScoreDetailsResponse.value?.peekContent()?.data?.creditReportInfo?.percentageCreditUsed)
        binding.changedScoreTextView.text = String.format("ChangedScore: %d" , viewModel.clientScoreDetailsResponse.value?.peekContent()?.data?.creditReportInfo?.changedScore)
        binding.currentShortTermDebtTextView.text = String.format("currentShortTermDebt: R %s" , formatCurrency(viewModel.clientScoreDetailsResponse.value?.peekContent()?.data?.creditReportInfo?.currentShortTermDebt.toString()))
        binding.currentShortTermNonPromotionalDebtTextView.text = String.format("currentShortTermNonPromotionalDebt: R %s" , formatCurrency(viewModel.clientScoreDetailsResponse.value?.peekContent()?.data?.creditReportInfo?.currentShortTermNonPromotionalDebt.toString()))
        binding.currentShortTermCreditLimitTextView.text = String.format("currentShortTermCreditLimit: R %s" , formatCurrency(viewModel.clientScoreDetailsResponse.value?.peekContent()?.data?.creditReportInfo?.currentShortTermCreditLimit.toString()))
        binding.equifaxScoreBandDescriptionTextView.text = String.format("equifaxScoreBandDescription: %s" , viewModel.clientScoreDetailsResponse.value?.peekContent()?.data?.creditReportInfo?.equifaxScoreBandDescription)
        binding.equifaxScoreBandTextView.text = String.format("equifaxScoreBand: %s" , viewModel.clientScoreDetailsResponse.value?.peekContent()?.data?.creditReportInfo?.equifaxScoreBand)
        binding.daysUntilNextReportTextView.text = String.format("daysUntilNextReport: %s" , viewModel.clientScoreDetailsResponse.value?.peekContent()?.data?.creditReportInfo?.daysUntilNextReport)
    }
}