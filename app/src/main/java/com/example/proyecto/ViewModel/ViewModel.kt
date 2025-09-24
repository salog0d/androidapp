package com.example.proyecto.ViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyecto.Models.HostelList
import com.example.proyecto.Models.HostelServicesList
import com.example.proyecto.Models.MyHostelReservationList
import com.example.proyecto.Models.MyServiceReservationList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class GeneralViewModel : ViewModel() {

    private val _counter = MutableStateFlow(0)
    private val _HostelList = MutableStateFlow<HostelList?>(null)
    private val _HostelServicesList = MutableStateFlow<HostelServicesList?>(null)
    private val _MyHostelReservationList = MutableStateFlow<MyHostelReservationList?>(null)
    private val _MyServiceReservationList = MutableStateFlow<MyServiceReservationList?>(null)
    private val _MyUpcomingReservationList = MutableStateFlow<MyServiceReservationList?>(null)

    val counter: StateFlow<Int> = _counter
    val hostelList: StateFlow<HostelList?> = _HostelList
    val hostelServicesList: StateFlow<HostelServicesList?> = _HostelServicesList
    val myHostelReservationList: StateFlow<MyHostelReservationList?> = _MyHostelReservationList
    val myServiceReservationList: StateFlow<MyServiceReservationList?> = _MyServiceReservationList
    val myUpcomingReservationList: StateFlow<MyServiceReservationList?> = _MyUpcomingReservationList


}
