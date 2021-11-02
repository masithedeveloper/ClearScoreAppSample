package com.clearscore.report.data.remote.responses

import com.google.gson.annotations.SerializedName

data class ClientScoreDetailsResponse (
    @SerializedName("accountIDVStatus") val accountIDVStatus : String?,
    @SerializedName("creditReportInfo") val creditReportInfo : CreditReportInfo?,
    @SerializedName("dashboardStatus") val dashboardStatus : String?,
    @SerializedName("personaType") val personaType : String?,
    @SerializedName("coachingSummary") val coachingSummary : CoachingSummary?,
    @SerializedName("augmentedCreditScore") val augmentedCreditScore : String?
)

