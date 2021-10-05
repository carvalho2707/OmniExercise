package pt.tiagocarvalho.omni.example

import javax.inject.Inject

class ExampleRepository @Inject constructor(
  private val exampleService: ExampleService
) {

  suspend fun getExample(): List<Example> {
    val exampleGroup = exampleService.fetchExample()
    if (exampleGroup.status == Status.ok.name) {
      return exampleGroup.articles.map { it.toDomainModel() }
    } else {
      throw Exception() // TODO change
    }
  }


  private fun ExampleResponse.toDomainModel(): Example {
    return Example(
      image = this.url,
      url = this.url,
      content = this.content
    )
  }
}