package pt.tiagocarvalho.omni.news

import pt.tiagocarvalho.omni.model.Error
import pt.tiagocarvalho.omni.model.Result
import javax.inject.Inject

class SearchUseCase @Inject constructor(
    private val searchRepository: SearchRepository
) {

    suspend operator fun invoke(
        term: String,
        searchFilter: SearchFilter
    ): Result<List<News>, Error> {
        return searchRepository.search(term, searchFilter)
    }
}
