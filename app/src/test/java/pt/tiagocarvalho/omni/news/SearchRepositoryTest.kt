package pt.tiagocarvalho.omni.news

import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test
import pt.tiagocarvalho.omni.model.Result
import pt.tiagocarvalho.omni.model.isFailure
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class SearchRepositoryTest {

    private val searchService = mockk<SearchService>()

    private val searchRepository = SearchRepository(searchService)

    @Test
    fun `when searchService fails should return error`() = runBlocking {
        val exception = Exception("A random error")
        coEvery { searchService.search(any()) } throws exception

        val result = searchRepository.search("TERM", SearchFilter.ARTICLES)

        assertTrue(result.isFailure)
        assertEquals(exception.message, (result as Result.Failure).error.message)

    }

}