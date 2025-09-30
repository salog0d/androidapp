package com.example.proyecto.data

import com.example.proyecto.Models.HostelList
import com.example.proyecto.Models.HostelServicesList
import com.example.proyecto.Models.MyHostelReservationList
import com.example.proyecto.Models.MyServiceReservationList
import com.example.proyecto.Models.NewHostelReservation
import com.example.proyecto.Models.NewServiceReservation
import com.example.proyecto.Services.APIToken
import com.example.proyecto.Services.LoginResponse

sealed class ResultState<out T> {
    object Idle : ResultState<Nothing>()
    object Loading : ResultState<Nothing>()
    data class Success<T>(val data: T) : ResultState<T>()
    data class Error(val message: String) : ResultState<Nothing>()
}

typealias LoginState = ResultState<LoginResponse>
typealias OTPTState = ResultState<APIToken>
typealias HostelListState = ResultState<HostelList>
typealias MyReservationsState = ResultState<MyHostelReservationList>
typealias HostelServicesState = ResultState<HostelServicesList>
typealias ServiceReservationsState = ResultState<MyServiceReservationList>
typealias NewHostelReservationState = ResultState<NewHostelReservation>
typealias NewServiceReservationState = ResultState<NewServiceReservation>
