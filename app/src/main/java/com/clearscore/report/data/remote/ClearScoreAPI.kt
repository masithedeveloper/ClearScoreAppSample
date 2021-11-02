package com.clearscore.report.data.remote

import com.clearscore.report.data.remote.responses.ClientScoreDetailsResponse
import com.clearscore.report.other.Constants.CLIENT_SCORE_DETAILS_END_POINT
import retrofit2.Response
import retrofit2.http.GET

interface ClearScoreAPI {

    @GET(CLIENT_SCORE_DETAILS_END_POINT)
    suspend fun fetchClientScoreDetails(): Response<ClientScoreDetailsResponse>

}