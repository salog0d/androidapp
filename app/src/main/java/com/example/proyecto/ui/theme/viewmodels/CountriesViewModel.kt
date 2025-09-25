package com.example.proyecto.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyecto.Models.Country
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CountriesViewModel : ViewModel() {

    private val _countries: MutableStateFlow<List<Country>> =
        MutableStateFlow(emptyList())
    val countries: StateFlow<List<Country>> = _countries

    init {
        viewModelScope.launch {
            runCatching { Networking.api.getAll() }
                .onSuccess { list: List<Country> ->
                    _countries.value = list.sortedBy { c: Country ->
                        c.name?.common ?: ""
                    }
                }
                .onFailure {
                    _countries.value = emptyList()
                }
        }
    }
}
