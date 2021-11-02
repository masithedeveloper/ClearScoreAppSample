package com.clearscore.report.repositories

import androidx.lifecycle.MutableLiveData
import com.clearscore.report.data.remote.responses.ClientScoreDetailsResponse
import com.clearscore.report.other.Resource

class FakeClearScoreRepositoryAndroidTest : ClientScoreRepository {

    private val clientScoreDetailsResponse = mutableListOf<ClientScoreDetailsResponse>()
    private val observableClientScoreDetailsResponse =
        MutableLiveData<List<ClientScoreDetailsResponse>>(clientScoreDetailsResponse)

    private var shouldReturnNetworkError = false

    fun setShouldReturnNetworkError(value: Boolean) {
        shouldReturnNetworkError = value
    }

    override suspend fun fetchClientScoreDetails(): Resource<ClientScoreDetailsResponse> {
        return if (shouldReturnNetworkError) {
            Resource.error("Error", null)
        } else {
            Resource.success(ClientScoreDetailsResponse(null, null, null, null, null, null))
        }
    }
}











