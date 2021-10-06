package pt.tiagocarvalho.omni.news

import javax.inject.Inject

class SearchUseCase @Inject constructor(
    private val searchRepository: SearchRepository
) {

    suspend operator fun invoke(term: String, searchFilter: SearchFilter): List<News> {
        return searchRepository.search(term, searchFilter)
    }
}
