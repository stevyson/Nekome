package com.chesire.nekome.app.search.host.core

import com.chesire.nekome.app.search.host.core.model.SearchGroup
import com.chesire.nekome.datasource.search.SearchDomain
import com.chesire.nekome.datasource.search.remote.SearchApi
import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import com.github.michaelbull.result.get
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SearchSeriesUseCase @Inject constructor(private val searchApi: SearchApi) {

    suspend operator fun invoke(
        title: String,
        group: SearchGroup
    ): Result<List<SearchDomain>, SearchFailureReason> {
        if (isTitleInvalid(title)) {
            return Err(SearchFailureReason.InvalidTitle)
        }

        val result = withContext(Dispatchers.IO) {
            when (group) {
                SearchGroup.Anime -> searchApi.searchForAnime(title)
                SearchGroup.Manga -> searchApi.searchForManga(title)
            }
        }

        val data = result.get()
        return if (data != null) {
            if (data.isEmpty()) {
                Err(SearchFailureReason.NoSeriesFound)
            } else {
                Ok(data)
            }
        } else {
            Err(SearchFailureReason.NetworkError)
        }
    }

    private fun isTitleInvalid(title: String): Boolean = title.isBlank()
}

sealed interface SearchFailureReason {
    object InvalidTitle : SearchFailureReason
    object NoSeriesFound : SearchFailureReason
    object NetworkError : SearchFailureReason
}
