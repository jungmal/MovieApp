package jungmal.movieapp.ui.components.movie

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import jungmal.movieapp.ui.theme.MovieAppTheme
import jungmal.movieapp.ui.theme.Paddings
import jungmal.movieapp.ui.theme.h5Title

@Composable
fun CategoryRow() {
    Column {
        CategoryTitle("Action")
        LazyRow(
            contentPadding = PaddingValues(
                horizontal = 10.dp
            )
        ) {
            //itemsIndexed()
            item {
                MovieItem()
            }
        }
    }
}

@Composable
fun CategoryTitle(s: String) {
    Text(
        text = "Action",
        modifier = Modifier.padding(vertical = Paddings.large, horizontal = Paddings.extra),
        style = MaterialTheme.typography.h5Title
    )
}

@Preview(showBackground = true)
@Composable
fun CategoryRowPreview() {
    MovieAppTheme {
        CategoryRow()
    }
}
