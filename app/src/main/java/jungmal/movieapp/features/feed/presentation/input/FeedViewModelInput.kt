package jungmal.movieapp.features.feed.presentation.input

interface FeedViewModelInput {
    fun openDetail(movieName: String)
    fun openInfoDialog()
    fun refreshFeed()
}