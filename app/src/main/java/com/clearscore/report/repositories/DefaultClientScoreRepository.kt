package com.clearscore.report.repositories

import com.clearscore.report.data.remote.ClearScoreAPI
import com.clearscore.report.data.remote.responses.ClientScoreDetailsResponse
import com.clearscore.report.other.Constants.NO_INTERNET
import com.clearscore.report.other.Constants.SOMETHING_WENT_WRONG
import com.clearscore.report.other.Resource
import javax.inject.Inject

class DefaultClientScoreRepository @Inject constructor(private val clearScoreAPI: ClearScoreAPI) :
    ClientScoreRepository {

    override suspend fun fetchClientScoreDetails(): Resource<ClientScoreDetailsResponse> {
        return try {
            val response = clearScoreAPI.fetchClientScoreDetails()
            if (response.isSuccessful) {
                response.body()?.let {
                    return@let Resource.success(it)
                } ?: Resource.error(SOMETHING_WENT_WRONG, null)
            } else {
                Resource.error(SOMETHING_WENT_WRONG, null)
            }
        } catch (e: Exception) {
            Resource.error(NO_INTERNET, null)
        }
    }
}














