package com.example.proyecto.Models


sealed class MyHostelReservationListState {
    object Loading : MyHostelReservationListState()
    data class Success(val data: MyHostelReservationList) : MyHostelReservationListState()
    data class Error(val message: String) : MyHostelReservationListState()
}
data class MyHostelReservationList(
    val myHostelReservations: List<MyHostelReservations>
)
