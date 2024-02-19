package jungmal.movieapp.features.detail.presentation.screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Scale
import jungmal.movieapp.R
import jungmal.movieapp.features.common.entity.MovieDetailEntity
import jungmal.movieapp.features.detail.presentation.input.DetailViewModelInputs
import jungmal.movieapp.features.detail.presentation.output.MovieDetailState
import jungmal.movieapp.features.detail.presentation.viewmodel.MovieDetailViewModel
import jungmal.movieapp.ui.components.buttons.PrimaryButton
import jungmal.movieapp.ui.components.buttons.SecondaryButton
import jungmal.movieapp.ui.components.movie.MovieMeta
import jungmal.movieapp.ui.theme.Paddings
import jungmal.movieapp.ui.util.getAnnotatedText

@Composable
fun DetailScreenRoute(
    title: String,
    viewModel: MovieDetailViewModel = hiltViewModel(),
    navigateUp: () -> Unit
) {
    LaunchedEffect(key1 = Unit) {
        viewModel.initMovieName(title)
        viewModel.navigateUp = navigateUp
    }

    val feedState: MovieDetailState = viewModel.outputs.detailState.collectAsState().value
    val input: DetailViewModelInputs = viewModel.inputs

    MovieDetailScreen(feedState, input)
}


@Composable
fun MovieDetailScreen(
    detailState: MovieDetailState,
    input: DetailViewModelInputs,
) {
    if (detailState is MovieDetailState.Main) {
        MovieDetail(
            movie = detailState.movieDetailEntity,
            input = input
        )
    }

    BackHandler {
        input.goBackToFeed()
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieDetail(
    movie: MovieDetailEntity,
    input: DetailViewModelInputs
) {
    val scrollState = rememberScrollState()
    Scaffold(
        topBar = {
            TopAppBar(
                title = {},
                modifier = Modifier.requiredHeight(70.dp),
                navigationIcon = {
                    IconButton(onClick = { input.goBackToFeed() }) {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_back),
                            contentDescription = null
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.surface),
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .padding(
                    horizontal = Paddings.extra
                )
                .scrollable(
                    state = scrollState,
                    orientation = Orientation.Vertical
                )
        ) {
            Row {
                // Poster
                BigPoster(
                    movie = movie
                )

                // Meta
                Column(
                    modifier = Modifier.padding(start = Paddings.xlarge)
                ) {
                    // Rating
                    MovieMeta(
                        key = "Rating",
                        value = movie.rating.toString()
                    )

                    // Director
                    MovieMeta(
                        key = "Director",
                        value = movie.directors.joinToString(separator = ", ")
                    )

                    // Starring
                    MovieMeta(
                        key = "Starring",
                        value = movie.actors.joinToString(separator = ", ")
                    )

                    // Genre
                    MovieMeta(
                        key = "Genre",
                        value = movie.genre.joinToString(separator = ", ")
                    )
                }
            }

            // Title
            Text(
                text = getAnnotatedText(text = movie.title),
                modifier = Modifier.padding(
                    top = Paddings.extra,
                    bottom = Paddings.large
                ),
                style = MaterialTheme.typography.titleMedium
            )

            // Desc
            Text(
                text = getAnnotatedText(text = movie.desc),
                modifier = Modifier.padding(
                    bottom = Paddings.xlarge
                ),
                style = MaterialTheme.typography.bodyMedium
            )

            // Rating
            PrimaryButton(
                modifier = Modifier
                    .padding(top = Paddings.xlarge)
                    .fillMaxWidth(),
                text = "Add Rating Score",
                onClick = {
                    input.rateClicked()
                }
            )

            // IMDB Button
            SecondaryButton(
                modifier = Modifier
                    .padding(top = Paddings.xlarge)
                    .fillMaxWidth(),
                text = "OPEN IMDB",
                onClick = {
                    input.openImdbClicked()
                }
            )
        }
    }
}

@Composable
fun BigPoster(
    movie: MovieDetailEntity
) {
    Image(
        painter = rememberAsyncImagePainter(
            ImageRequest
                .Builder(LocalContext.current)
                .data(data = movie.thumbUrl)
                .apply {
                    crossfade(true)
                    scale(Scale.FILL)
                }.build()
        ),
        modifier = Modifier
            .width(180.dp)
            .height(250.dp),
        contentScale = ContentScale.FillHeight,
        contentDescription = "Movie Poster Image"
    )
}
