package com.example.countryapp.ui.viewmodel

import androidx.lifecycle.*
import com.example.countryapp.data.CountryRepository
import com.example.countryapp.ui.model.Country
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CountryDetailViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val repository: CountryRepository) : ViewModel() {

    private val name: String? = savedStateHandle["country_name"]

    private val _country = MutableLiveData<Country?>()
    val country: LiveData<Country?>
        get() = _country

    init {
        viewModelScope.launch {
            name?.let {
                val selected = repository.getCountry(it)
                _country.value = selected
            }
        }
    }
}