package com.example.countryapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.countryapp.ui.model.Country
import com.example.countryapp.R
import com.example.countryapp.ui.model.Currency
import com.example.countryapp.ui.theme.Shapes
import com.example.countryapp.ui.theme.turquoise
import com.example.countryapp.ui.theme.*
import com.example.countryapp.ui.viewmodel.CountrySearchViewModel

@Composable
fun SearchBarScreen(viewModel: CountrySearchViewModel = hiltViewModel(), onClick: (String) -> Unit) {
    val searchText by viewModel.searchText.collectAsState()
    val countries by viewModel.countries.collectAsState()
    SearchBar(text = searchText , onTextChange = viewModel::onSearchTextChange, countries, onClick)
}

@Composable
fun SearchBar(
    text: String,
    onTextChange: (String) -> Unit,
    listOfResults: List<Country>,
    onClick: (String) -> Unit,
) {

    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp),
    ) {

        TextField(
            value = text,
            modifier = Modifier.fillMaxWidth(),
            shape = Shapes.large,
            onValueChange = {
                onTextChange(it)
            },
            placeholder = {
                Text(
                    modifier = Modifier.alpha(0.5F),
                    text = stringResource(id = R.string.search),
                    color = Color.Black)
            },
            singleLine = true,
            leadingIcon = {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = stringResource(id = R.string.search_icon_content_description),
                )
            },
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        LazyColumn(modifier = Modifier.fillMaxWidth() ) {
            items(listOfResults) {
                SearchResultItem(it , onClick)
            }
        }
    }
}

@Composable
fun SearchResultItem(country: Country, onClick: (String) -> Unit) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 5.dp)
            .background(turquoise, shape = RoundedCornerShape(20.dp))
            .clickable { onClick(country.name) }
        ) {
            Text(text = "${country.emojiFlag}  ${country.name}",
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(20.dp)

            )
        }
}

@Preview
@Composable
private fun SearchBarPreview() {
   SearchBar(text = "", onTextChange = {}, listOfResults = emptyList(), onClick = {})
}

@Preview
@Composable
private fun ResultItemPreview() {
    SearchResultItem(Country(name = "Name", capital = "Capital", flag = "", emojiFlag = "", area = 23.2, population = 55, currencies = listOf(), languages = listOf() ), onClick = { })
}