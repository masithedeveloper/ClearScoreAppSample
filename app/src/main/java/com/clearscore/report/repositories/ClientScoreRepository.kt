package com.clearscore.report.repositories

import com.clearscore.report.data.remote.responses.ClientScoreDetailsResponse
import com.clearscore.report.other.Resource

interface ClientScoreRepository {

    suspend fun fetchClientScoreDetails(): Resource<ClientScoreDetailsResponse>

}