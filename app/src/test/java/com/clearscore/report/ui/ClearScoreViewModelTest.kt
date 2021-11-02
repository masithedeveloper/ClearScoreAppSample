package com.clearscore.report.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.clearscore.report.MainCoroutineRule
import com.clearscore.report.other.Status
import com.clearscore.report.ui.viewmodels.ClearScoreViewModel
import com.google.common.truth.Truth.assertThat
import com.clearscore.report.data.remote.responses.ClientScoreDetailsResponse
import com.clearscore.report.getOrAwaitValueTest
import com.clearscore.report.other.Resource
import com.clearscore.report.repositories.FakeClientScoreRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class ClearScoreViewModelTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var viewModel: ClearScoreViewModel

    @Before
    fun setup() {
        viewModel = ClearScoreViewModel(FakeClientScoreRepository())
    }


    @Test
    fun `insert response with invalid response, returns error`() {
        viewModel.addClientScoreDetailsResponse(Resource(Status.ERROR, null, ""))
        val value = viewModel.clientScoreDetailsResponse.getOrAwaitValueTest()
        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.ERROR)
    }

    @Test
    fun `insert response with valid response, returns success`() {
        val response = Resource(Status.SUCCESS, ClientScoreDetailsResponse(null, null, null, null, null, null) , "")
        viewModel.addClientScoreDetailsResponse(response)

        val value = viewModel.clientScoreDetailsResponse.getOrAwaitValueTest()

        assertThat(value.getContentIfNotHandled()?.status).isEqualTo(Status.SUCCESS)
    }

    @Test
    fun `insert response with valid response and verify data, returns error`() {
        val response1 = ClientScoreDetailsResponse(null, null, null, null, null, null)
        val response = Resource(Status.SUCCESS, response1, "")
        viewModel.addClientScoreDetailsResponse(response)

        val value = viewModel.clientScoreDetailsResponse.getOrAwaitValueTest()

        assertThat(value.getContentIfNotHandled()?.data?.creditReportInfo).isNotEqualTo("Test")
    }
}













