package com.example.proyecto.ViewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyecto.Models.VerificationLogin
import com.example.proyecto.Models.VerificationOTP
import com.example.proyecto.Services.Services
import com.example.proyecto.Models.NewHostelReservation
import com.example.proyecto.Models.NewServiceReservation
import com.example.proyecto.Utilities.TokenManager
import com.example.proyecto.data.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class GeneralViewModel : ViewModel() {

    // --------------------
    // StateFlows
    // --------------------
    private val _counter = MutableStateFlow(0)
    val counter: StateFlow<Int> = _counter

    private val _phoneNumber = MutableStateFlow("")
    private val _otp = MutableStateFlow("")
    val phoneNumber: StateFlow<String> = _phoneNumber
    val otp: StateFlow<String> = _otp

    private val _loginState = MutableStateFlow<LoginState>(ResultState.Idle)
    val loginState: StateFlow<LoginState> = _loginState

    private val _otpState = MutableStateFlow<OTPTState>(ResultState.Idle)
    val otpState: StateFlow<OTPTState> = _otpState

    private val _hostelListState = MutableStateFlow<HostelListState>(ResultState.Idle)
    val hostelListState: StateFlow<HostelListState> = _hostelListState

    private val _hostelServicesState = MutableStateFlow<HostelServicesState>(ResultState.Idle)
    val hostelServicesState: StateFlow<HostelServicesState> = _hostelServicesState

    private val _myHostelReservationsState = MutableStateFlow<MyReservationsState>(ResultState.Idle)
    val myHostelReservationsState: StateFlow<MyReservationsState> = _myHostelReservationsState

    private val _myServiceReservationsState = MutableStateFlow<ServiceReservationsState>(ResultState.Idle)
    val myServiceReservationsState: StateFlow<ServiceReservationsState> = _myServiceReservationsState

    private val _myUpcomingReservationsState = MutableStateFlow<ServiceReservationsState>(ResultState.Idle)
    val myUpcomingReservationsState: StateFlow<ServiceReservationsState> = _myUpcomingReservationsState

    // --------------------
    // New Reservation States
    // --------------------
    private val _newHostelReservationState = MutableStateFlow<NewHostelReservationState>(ResultState.Idle)
    val newHostelReservationState: StateFlow<NewHostelReservationState> = _newHostelReservationState

    private val _newServiceReservationState = MutableStateFlow<NewServiceReservationState>(ResultState.Idle)
    val newServiceReservationState: StateFlow<NewServiceReservationState> = _newServiceReservationState


    // --------------------
    // Update fields
    // --------------------
    fun updatePhoneNumber(phone: String) { _phoneNumber.value = phone }
    fun updateOTP(otp: String) { _otp.value = otp }

    // --------------------
    // API Calls
    // --------------------
    fun verifyLogin() {
        viewModelScope.launch {
            _loginState.value = ResultState.Loading
            try {
                val request = VerificationLogin(phone_number = _phoneNumber.value)
                val response = Services.instance.verifyLogin(request)
                if (response.isSuccessful && response.body() != null) {
                    _loginState.value = ResultState.Success(response.body()!!)
                } else {
                    _loginState.value = ResultState.Error(response.message())
                }
            } catch (e: Exception) {
                _loginState.value = ResultState.Error("Network error: ${e.message}")
            }
        }
    }

    fun verifyOTP(context: Context) {
        viewModelScope.launch {
            _otpState.value = ResultState.Loading
            try {
                val request = VerificationOTP(code = _otp.value, phone_number = _phoneNumber.value)
                val response = Services.instance.verifyOtp(request)
                if (response.isSuccessful && response.body() != null) {
                    val apiToken = response.body()!!
                    // Save token securely
                    TokenManager(context).saveToken(apiToken.token)

                    _otpState.value = ResultState.Success(apiToken)
                } else {
                    _otpState.value = ResultState.Error(response.message())
                }
            } catch (e: Exception) {
                _otpState.value = ResultState.Error("Network error: ${e.message}")
            }
        }
    }


    fun fetchHostels() {
        viewModelScope.launch {
            _hostelListState.value = ResultState.Loading
            try {
                val response = Services.instance.getHostels()
                if (response.isSuccessful && response.body() != null) {
                    _hostelListState.value = ResultState.Success(response.body()!!)
                } else {
                    _hostelListState.value = ResultState.Error(response.message())
                }
            } catch (e: Exception) {
                _hostelListState.value = ResultState.Error(e.message ?: "Unknown error")
            }
        }
    }

    fun fetchHostelServices() {
        viewModelScope.launch {
            _hostelServicesState.value = ResultState.Loading
            try {
                val response = Services.instance.getHostelServices()
                if (response.isSuccessful && response.body() != null) {
                    _hostelServicesState.value = ResultState.Success(response.body()!!)
                } else {
                    _hostelServicesState.value = ResultState.Error(response.message())
                }
            } catch (e: Exception) {
                _hostelServicesState.value = ResultState.Error(e.message ?: "Unknown error")
            }
        }
    }

    fun fetchMyHostelReservations() {
        viewModelScope.launch {
            _myHostelReservationsState.value = ResultState.Loading
            try {
                val response = Services.instance.getMyReservations()
                if (response.isSuccessful && response.body() != null) {
                    _myHostelReservationsState.value = ResultState.Success(response.body()!!)
                } else {
                    _myHostelReservationsState.value = ResultState.Error(response.message())
                }
            } catch (e: Exception) {
                _myHostelReservationsState.value = ResultState.Error(e.message ?: "Unknown error")
            }
        }
    }

    fun fetchMyServiceReservations() {
        viewModelScope.launch {
            _myServiceReservationsState.value = ResultState.Loading
            try {
                val response = Services.instance.getMyServiceReservations()
                if (response.isSuccessful && response.body() != null) {
                    _myServiceReservationsState.value = ResultState.Success(response.body()!!)
                } else {
                    _myServiceReservationsState.value = ResultState.Error(response.message())
                }
            } catch (e: Exception) {
                _myServiceReservationsState.value = ResultState.Error(e.message ?: "Unknown error")
            }
        }
    }

    fun fetchMyUpcomingServiceReservations() {
        viewModelScope.launch {
            _myUpcomingReservationsState.value = ResultState.Loading
            try {
                val response = Services.instance.getMyUpcomingServiceReservations()
                if (response.isSuccessful && response.body() != null) {
                    _myUpcomingReservationsState.value = ResultState.Success(response.body()!!)
                } else {
                    _myUpcomingReservationsState.value = ResultState.Error(response.message())
                }
            } catch (e: Exception) {
                _myUpcomingReservationsState.value = ResultState.Error(e.message ?: "Unknown error")
            }
        }
    }

    fun createHostelReservation(request: NewHostelReservation) {
        viewModelScope.launch {
            _newHostelReservationState.value = ResultState.Loading
            try {
                val response = Services.instance.createHostelReservation(request)
                if (response.isSuccessful && response.body() != null) {
                    _newHostelReservationState.value = ResultState.Success(response.body()!!)
                } else {
                    _newHostelReservationState.value = ResultState.Error(response.message())
                }
            } catch (e: Exception) {
                _newHostelReservationState.value = ResultState.Error(e.message ?: "Unknown error")
            }
        }
    }

    fun createServiceReservation(request: NewServiceReservation) {
        viewModelScope.launch {
            _newServiceReservationState.value = ResultState.Loading
            try {
                val response = Services.instance.createServiceReservation(request)
                if (response.isSuccessful && response.body() != null) {
                    _newServiceReservationState.value = ResultState.Success(response.body()!!)
                } else {
                    _newServiceReservationState.value = ResultState.Error(response.message())
                }
            } catch (e: Exception) {
                _newServiceReservationState.value = ResultState.Error(e.message ?: "Unknown error")
            }
        }
    }
}
