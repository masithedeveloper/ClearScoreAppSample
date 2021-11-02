package com.clearscore.report.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.clearscore.report.data.remote.responses.ClientScoreDetailsResponse
import com.clearscore.report.other.Event
import com.clearscore.report.other.Resource
import com.clearscore.report.repositories.ClientScoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClearScoreViewModel @Inject constructor(
    private val repository: ClientScoreRepository
) : ViewModel() {

    private val _clientScoreDetailsResponse = MutableLiveData<Event<Resource<ClientScoreDetailsResponse>>>()
    val clientScoreDetailsResponse: LiveData<Event<Resource<ClientScoreDetailsResponse>>> = _clientScoreDetailsResponse

    fun addClientScoreDetailsResponse(response: Resource<ClientScoreDetailsResponse>) {
        _clientScoreDetailsResponse.value = Event(response)
    }

    fun getClientScoreDetails() {
        _clientScoreDetailsResponse.value = Event(Resource.loading(null))
        viewModelScope.launch {
            val response = repository.fetchClientScoreDetails()
            addClientScoreDetailsResponse(response)
        }
    }
}