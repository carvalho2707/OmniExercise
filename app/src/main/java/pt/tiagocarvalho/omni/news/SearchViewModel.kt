package pt.tiagocarvalho.omni.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import pt.tiagocarvalho.omni.model.fold
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchUseCase: SearchUseCase
) : ViewModel() {

    private val _news = MutableLiveData(emptyList<News>())
    val news: LiveData<List<News>>
        get() = _news

    private val _error = MutableLiveData<Boolean>()
    val error: LiveData<Boolean>
        get() = _error

    fun search(term: String, checked: Boolean) {
        val searchFilter = if (checked) {
            SearchFilter.TOPICS
        } else {
            SearchFilter.ARTICLES
        }
        viewModelScope.launch {
            searchUseCase(term, searchFilter)
                .fold(
                    { _news.value = it },
                    { _error.value = true }
                )
        }
    }
}
