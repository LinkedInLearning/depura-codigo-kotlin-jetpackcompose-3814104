package com.example.countryapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.countryapp.data.CountryRepository
import com.example.countryapp.ui.model.Country
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject


@HiltViewModel
class CountrySearchViewModel @Inject constructor(private val repository: CountryRepository) : ViewModel() {

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    val countries = searchText.mapLatest { text -> repository.getCountries(text) }
        .stateIn(viewModelScope,
            SharingStarted.WhileSubscribed(1000),
            emptyList<Country>())

    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }


}