package com.example.countryapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.countryapp.ui.model.Country
import com.example.countryapp.R
import com.example.countryapp.ui.theme.Shapes
import com.example.countryapp.ui.theme.turquoise
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
    var index by remember { mutableStateOf(0) }

    return Column(
        modifier = Modifier
            .padding(10.dp),
    ) {

        TextField(
            value = text,
            shape = Shapes.large,
            onValueChange = {
                onTextChange(it)
                index = 0
            },
            placeholder = {
                Text(
                    modifier = Modifier.alpha(0.5F),
                    text = stringResource(id = R.string.search),
                    color = Color.Black
                )
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

        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            listOfResults.forEach {
                index += 1
                SearchResultItem(index, it, onClick)
            }
        }
    }
}

@Composable
fun SearchResultItem(i: Int, country: Country, onClick: (String) -> Unit) {
        Row(modifier = Modifier
            .padding(horizontal = 10.dp, vertical = 5.dp)
            .background(turquoise, shape = RoundedCornerShape(20.dp))
        ) {
            Text(text = "$i = ${country.emojiFlag}  ${country.name}",
                modifier = Modifier
                    .clickable { onClick(country.name) }
                    .padding(20.dp)
                    .align(Alignment.CenterVertically)
            )
        }
}
