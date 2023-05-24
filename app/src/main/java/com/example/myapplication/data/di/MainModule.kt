package com.example.myapplication.data.di

import com.example.myapplication.data.remote.service.MainService
import com.example.myapplication.data.repository.MainRepositoryImpl
import com.example.myapplication.ui.repository.MainRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MainModule {

  @Provides
  @Singleton
  fun provideOkHttpClient(): OkHttpClient {
    return OkHttpClient.Builder()
      .addInterceptor(
        HttpLoggingInterceptor().apply {
          level = HttpLoggingInterceptor.Level.BODY
        }
      )
      .build()
  }

  /* Services */
  @Provides
  @Singleton
  fun provideMainServices(client: OkHttpClient): MainService {
    return Retrofit.Builder()
      .baseUrl("https://localhost")
      .client(client)
      .build()
      .create()
  }

  /* Repositories */
  @Provides
  @Singleton
  fun provideMainRepository(
    mainService: MainService
  ): MainRepository {
    return MainRepositoryImpl(
      mainService
    )
  }
}