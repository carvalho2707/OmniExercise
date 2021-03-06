package pt.tiagocarvalho.omni.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import pt.tiagocarvalho.omni.model.fold
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchUseCase: SearchUseCase
) : ViewModel() {

    private var searchJob: Job? = null

    // Should change to State that includes loading, success and failure.
    // We can remove multiple livedata variables and better handling the logic
    private val _news = MutableLiveData(emptyList<News>())
    val news: LiveData<List<News>>
        get() = _news

    private val _error = MutableLiveData<String>()
    val error: LiveData<String>
        get() = _error

    fun search(searchTerm: String, checked: Boolean) {
        val searchFilter = if (checked) {
            SearchFilter.TOPICS
        } else {
            SearchFilter.ARTICLES
        }

        searchJob?.cancel()
        searchJob = viewModelScope.launch {
            delay(3000)
            searchUseCase(searchTerm, searchFilter)
                .fold(
                    { _news.value = it },
                    { _error.value = it.message ?: UNKNOWN_ERROR }
                )
        }
    }

    companion object {
        private const val UNKNOWN_ERROR = "An error as occurred."
    }
}
