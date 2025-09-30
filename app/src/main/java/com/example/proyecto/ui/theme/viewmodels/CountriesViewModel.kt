package com.example.proyecto.ui.theme.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyecto.models.Country
import com.example.proyecto.data.Networking
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CountriesViewModel : ViewModel() {
    private val _countries = MutableStateFlow<List<Country>>(emptyList())
    val countries: StateFlow<List<Country>> = _countries

    init {
        viewModelScope.launch {
            runCatching<List<Country>> { Networking.countriesApi.getAll() }
                .onSuccess { list -> _countries.value = list.sortedBy { it.name?.common ?: "" } }
                .onFailure { _countries.value = emptyList() }
        }
    }
}
