package com.example.proyecto.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyecto.Models.HostelList
import com.example.proyecto.Models.HostelListState
import com.example.proyecto.Models.HostelServicesList
import com.example.proyecto.Models.HostelServicesListState
import com.example.proyecto.Models.MyHostelReservationList
import com.example.proyecto.Models.MyHostelReservationListState
import com.example.proyecto.Models.MyServiceReservationList
import com.example.proyecto.Models.MyServiceReservationListState
import com.example.proyecto.Services.Services
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class GeneralViewModel : ViewModel() {

    private val _counter = MutableStateFlow(0)
    private val _HostelListState = MutableStateFlow<HostelListState?>(HostelListState.Loading)
    private val _HostelServicesListState = MutableStateFlow<HostelServicesListState?>(
        HostelServicesListState.Loading)
    private val _MyHostelReservationListState = MutableStateFlow<MyHostelReservationListState?>(
        MyHostelReservationListState.Loading)
    private val _MyServiceReservationListState = MutableStateFlow<MyServiceReservationListState?>(
        MyServiceReservationListState.Loading)
    private val _MyUpcomingReservationListState = MutableStateFlow<MyServiceReservationListState?>(
        MyServiceReservationListState.Loading)

    val counter: StateFlow<Int> = _counter
    val hostelList: StateFlow<HostelListState?> = _HostelListState
    val hostelServicesList: StateFlow<HostelServicesListState?> = _HostelServicesListState
    val myHostelReservationList: StateFlow<MyHostelReservationListState?> = _MyHostelReservationListState
    val myServiceReservationList: StateFlow<MyServiceReservationListState?> = _MyServiceReservationListState
    val myUpcomingReservationList: StateFlow<MyServiceReservationListState?> = _MyUpcomingReservationListState

    fun fetchHostels() {
        viewModelScope.launch {
            _HostelListState.value = HostelListState.Loading
            try {
                val response = Services.instance.getHostels()
                _HostelListState.value = HostelListState.Success(response)
            } catch (e: Exception) {
                _HostelListState.value = HostelListState.Error(e.message ?: "Unknown error")
            }
        }
    }

    fun fetchHostelServices() {
        viewModelScope.launch {
            _HostelServicesListState.value = HostelServicesListState.Loading
            try {
                val response = Services.instance.getHostelServices()
                _HostelServicesListState.value = HostelServicesListState.Success(response)
            } catch (e: Exception) {
                _HostelServicesListState.value = HostelServicesListState.Error(e.message ?: "Unknown error")
            }
        }
    }

    fun fetchMyHostelReservations() {
        viewModelScope.launch {
            _MyHostelReservationListState.value = MyHostelReservationListState.Loading
            try {
                val response = Services.instance.getMyReservations()
                _MyHostelReservationListState.value = MyHostelReservationListState.Success(response)
            } catch (e: Exception) {
                _MyHostelReservationListState.value = MyHostelReservationListState.Error(e.message ?: "Unknown error")
            }
        }
    }

    fun fetchMyServiceReservations() {
        viewModelScope.launch {
            _MyServiceReservationListState.value = MyServiceReservationListState.Loading
            try {
                val response = Services.instance.getMyServiceReservations()
                _MyServiceReservationListState.value = MyServiceReservationListState.Success(response)
            } catch (e: Exception) {
                _MyServiceReservationListState.value = MyServiceReservationListState.Error(e.message ?: "Unknown error")
            }
        }
    }

    fun fetchMyUpcomingServiceReservations() {
        viewModelScope.launch {
            _MyUpcomingReservationListState.value = MyServiceReservationListState.Loading
            try {
                val response = Services.instance.getMyUpcomingServiceReservations()
                _MyUpcomingReservationListState.value = MyServiceReservationListState.Success(response)
            } catch (e: Exception) {
                _MyUpcomingReservationListState.value = MyServiceReservationListState.Error(e.message ?: "Unknown error")
            }
        }
    }


}
