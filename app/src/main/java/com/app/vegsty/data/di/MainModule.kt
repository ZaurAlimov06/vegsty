package com.app.vegsty.data.di

import android.app.Application
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.app.vegsty.data.local.PreferenceHelperImpl
import com.app.vegsty.data.local.ProfileLocal
import com.app.vegsty.data.local.ProfileLocalImpl
import com.app.vegsty.data.remote.service.MainService
import com.app.vegsty.data.repository.MainRepositoryImpl
import com.app.vegsty.ui.repository.MainRepository
import com.app.vegsty.ui.repository.PreferenceHelper
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
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
  fun provideFirebaseAuth(
  ): FirebaseAuth {
    return Firebase.auth
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