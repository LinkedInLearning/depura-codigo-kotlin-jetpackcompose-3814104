package com.example.countryapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.countryapp.R
import com.example.countryapp.getCurrenciesString
import com.example.countryapp.ui.model.Country
import com.example.countryapp.ui.model.Currency
import com.example.countryapp.ui.theme.CountryAppTheme
import com.example.countryapp.ui.theme.lightTurquoise
import com.example.countryapp.ui.viewmodel.CountryDetailViewModel


@Composable
fun DetailScreen(viewModel: CountryDetailViewModel = hiltViewModel()) {
    val country =  viewModel.country.observeAsState().value
    country?.let { DetailsView(country = it) }
}

@Composable
fun DetailsView(country: Country) {
    Column(modifier = Modifier.padding(top = 20.dp, start = 20.dp, end = 20.dp)) {
        //First part
        Row(modifier = Modifier
            .padding(bottom = 20.dp)
            .clip(RoundedCornerShape(10.dp)) // clip to the circle shape
            .background(lightTurquoise)
            .fillMaxWidth()
        ) {
            AsyncImage(
                model = country.flag,
                contentDescription = stringResource(id = R.string.flag_description),
                modifier = Modifier
                    .size(100.dp)
                    .align(Alignment.CenterVertically)
                    .padding(5.dp),
                contentScale = ContentScale.Inside
            )
                Text(
                    text = country.name ?: "",
                    fontSize = 20.sp,
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .padding(start = 20.dp)
                )
        }
        Text(text = "- Capital: ${country.capital}",
            fontSize = 18.sp,
            modifier = Modifier.padding(5.dp))
        Text(text = "- Area: ${country.area} km2",
            fontSize = 18.sp,
            modifier = Modifier.padding(5.dp))
        Text(text = "- Population: ${country.population}",
            fontSize = 18.sp,
            modifier = Modifier.padding(5.dp))
        Text(text = "- Currency: ${country.getCurrenciesString()}",
            fontSize = 18.sp,
            modifier = Modifier.padding(5.dp))
        Text(text = "- Languages: ${country.languages}",
            fontSize = 18.sp,
            modifier = Modifier.padding(5.dp))
    }
}


@Preview
@Composable
private fun SearchBarPreview() {
    CountryAppTheme() {
        DetailsView(country = Country("Bulgaria", capital = "Sofia", flag = "https://flagcdn.com/w320/bg.png", emojiFlag = "", area = 1000.5, population = 100000, currencies = listOf<Currency>() ,languages = listOf("")))
    }
}