package com.example.proyecto.Models


sealed class MyServiceReservationListState {
    object Loading : MyServiceReservationListState()
    data class Success(val data: MyServiceReservationList) : MyServiceReservationListState()
    data class Error(val message: String) : MyServiceReservationListState()
}
data class MyServiceReservationList(
    val myServiceReservations: List<MyServiceReservations>
)
