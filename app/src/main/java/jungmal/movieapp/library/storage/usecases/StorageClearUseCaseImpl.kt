package jungmal.movieapp.library.storage.usecases

import jungmal.movieapp.library.storage.Storage
import javax.inject.Inject

class StorageClearUseCaseImpl @Inject constructor(
    private val storage: Storage
) : StorageClearUseCase {
    override fun invoke() {
        storage.clear()
    }
}
