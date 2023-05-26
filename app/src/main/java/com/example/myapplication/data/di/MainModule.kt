package com.example.myapplication.data.di

import android.app.Application
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.example.myapplication.data.local.PreferenceHelperImpl
import com.example.myapplication.data.local.ProfileLocal
import com.example.myapplication.data.local.ProfileLocalImpl
import com.example.myapplication.data.remote.service.MainService
import com.example.myapplication.data.repository.MainRepositoryImpl
import com.example.myapplication.ui.repository.MainRepository
import com.example.myapplication.ui.repository.PreferenceHelper
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
  fun provideSharedPreferences(
    app: Application
  ): SharedPreferences {
    val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
    return EncryptedSharedPreferences.create(
      "com.app.vegsty",
      masterKeyAlias,
      app,
      EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
      EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )
  }

  @Provides
  @Singleton
  fun providePreference(sharedPreferences: SharedPreferences): PreferenceHelper {
    return PreferenceHelperImpl(sharedPreferences)
  }

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

  /* Locals */
  @Provides
  @Singleton
  fun provideProfileLocal(
    preferenceHelper: PreferenceHelper
  ): ProfileLocal {
    return ProfileLocalImpl(
      preferenceHelper
    )
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