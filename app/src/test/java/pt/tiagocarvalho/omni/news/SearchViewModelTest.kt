package pt.tiagocarvalho.omni.news

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.asFlow
import app.cash.turbine.test
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import org.junit.Rule
import org.junit.Test
import pt.tiagocarvalho.omni.model.Result
import pt.tiagocarvalho.omni.utils.MainCoroutineRule
import pt.tiagocarvalho.omni.utils.runBlockingTest
import kotlin.test.assertEquals
import kotlin.test.assertTrue
import kotlin.time.ExperimentalTime


@ExperimentalTime
class SearchViewModelTest {

    @get:Rule
    val liveDataRule = InstantTaskExecutorRule()

    @get:Rule
    val coroutineRule = MainCoroutineRule()

    private var searchUseCase = mockk<SearchUseCase>()

    private val viewModel = SearchViewModel(searchUseCase)

    @Test
    fun `when search is called should fetch results`() = coroutineRule.runBlockingTest {
        val articles = createArticles()

        coEvery { searchUseCase(any(), any()) } returns Result.Success(articles)

        viewModel.search("Search Term", true)

        viewModel.news.asFlow().test {
            //initial state
            val initial = awaitItem()

            //second emition
            val result = awaitItem()

            assertTrue { initial.isEmpty() }
            assertEquals(articles, result)
        }

        coVerify { searchUseCase("Search Term", SearchFilter.TOPICS) }
    }

    private fun createArticles(): List<News.Article> {
        return listOf(News.Article("id", "url", "title"))
    }

}