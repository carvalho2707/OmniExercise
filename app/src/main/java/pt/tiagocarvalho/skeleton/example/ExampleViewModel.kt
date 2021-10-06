package pt.tiagocarvalho.skeleton.example

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ExampleViewModel @Inject constructor(
    private val exampleUseCase: ExampleUseCase
) : ViewModel() {
    // TODO: Implement the ViewModel

    private val _examples = MutableLiveData(emptyList<Example>())
    val examples: LiveData<List<Example>>
        get() = _examples

    fun fetchExample() {
        viewModelScope.launch {
            exampleUseCase()
                .also { _examples.value = it }
        }
    }
}
