package com.example.proyecto.Models


sealed class HostelServicesListState {
    object Loading : HostelServicesListState()
    data class Success(val data: HostelServicesList) : HostelServicesListState()
    data class Error(val message: String) : HostelServicesListState()
}
data class HostelServicesList(
    val count: Int,
    val next: String,
    val previous: String,
    val results: List<HostelServices>
)