package pt.tiagocarvalho.omni.example

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ExampleViewModel @Inject constructor(
  private val exampleUseCase: ExampleUseCase
) : ViewModel() {
  // TODO: Implement the ViewModel
}