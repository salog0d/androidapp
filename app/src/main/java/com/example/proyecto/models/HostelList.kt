package com.example.proyecto.models


sealed class HostelListState {
    data class Success(val data: HostelList) : HostelListState()
    data class Error(val message: String) : HostelListState()
    object Loading : HostelListState()
}

data class HostelList(
    val count: Int,
    val next: String,
    val previous: String,
    val hostels: List<Hostel>
)