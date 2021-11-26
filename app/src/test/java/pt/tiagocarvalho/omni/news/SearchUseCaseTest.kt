package pt.tiagocarvalho.omni.news

import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.Test
import pt.tiagocarvalho.omni.model.Result
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class SearchUseCaseTest {

    private var searchRepository = mockk<SearchRepository>()

    private var getBingeUseCase = SearchUseCase(searchRepository)

    @Test
    fun `given SearchUseCase should complete`() = runBlocking {
        val news = listOf(News.Article("id", "imageUrl", "title"))

        coEvery { searchRepository.search(any(), any()) } returns Result.Success(news)

        val result = getBingeUseCase("term", SearchFilter.ARTICLES)

        assertTrue(result is Result.Success)
        assertEquals(news, result.value)
    }

}
