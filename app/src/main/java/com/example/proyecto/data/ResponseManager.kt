package com.example.proyecto.data

import com.example.proyecto.models.HostelList
import com.example.proyecto.models.HostelServicesList
import com.example.proyecto.models.MyHostelReservationList
import com.example.proyecto.models.MyServiceReservationList
import com.example.proyecto.models.NewHostelReservation
import com.example.proyecto.models.NewServiceReservation
import com.example.proyecto.models.PreRegResponse
import com.example.proyecto.services.APIToken
import com.example.proyecto.services.LoginResponse

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
typealias PreRegistroState = ResultState<PreRegResponse>

