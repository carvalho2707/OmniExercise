package pt.tiagocarvalho.omni.example

import javax.inject.Inject

class ExampleUseCase @Inject constructor(
    private val exampleRepository: ExampleRepository
) {

    suspend operator fun invoke(): List<Example> {
        return exampleRepository.getExample()
    }
}
