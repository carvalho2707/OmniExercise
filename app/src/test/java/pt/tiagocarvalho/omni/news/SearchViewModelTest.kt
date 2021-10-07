package pt.tiagocarvalho.omni.news

import io.mockk.coEvery
import io.mockk.mockk
import org.junit.Before
import org.junit.Test
import pt.tiagocarvalho.omni.model.Error
import pt.tiagocarvalho.omni.model.Result


class SearchViewModelTest {

  private val searchUseCase = mockk<SearchUseCase>()

  private lateinit var searchViewModel: SearchViewModel


  @Before
  fun setup() {
    searchViewModel = SearchViewModel(searchUseCase)
  }

  @Test
  fun `when searchUseCase return error should update error`() {
    coEvery { searchUseCase(any(), any()) } returns Result.Failure(Error(ERROR_MESSAGE))

searchViewModel.error.get
  }


  companion object {
    private const val ERROR_MESSAGE = "a_random_error"
  }

}