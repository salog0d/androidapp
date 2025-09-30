package com.example.proyecto.models


sealed class MyHostelReservationListState {
    object Loading : MyHostelReservationListState()
    data class Success(val data: MyHostelReservationList) : MyHostelReservationListState()
    data class Error(val message: String) : MyHostelReservationListState()
}
data class MyHostelReservationList(
    val myHostelReservations: List<MyHostelReservations>
)
