package pt.tiagocarvalho.omni.example

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ExampleModule {

  @Provides
  @Singleton
  fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
      .client(okHttpClient)
      .baseUrl("https://saurav.tech/NewsAPI/top-headlines/category/health/")
      .addConverterFactory(MoshiConverterFactory.create())
      .build()
  }

  @Provides
  @Singleton
  fun providePExampleService(retrofit: Retrofit): ExampleService {
    return retrofit.create(ExampleService::class.java)
  }

}