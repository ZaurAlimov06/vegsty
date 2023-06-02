package com.app.vegsty.data.di

import android.app.Application
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys
import com.app.vegsty.data.local.MainLocal
import com.app.vegsty.data.local.MainLocalImpl
import com.app.vegsty.data.local.PreferenceHelperImpl
import com.app.vegsty.data.repository.MainRepositoryImpl
import com.app.vegsty.ui.repository.MainRepository
import com.app.vegsty.ui.repository.PreferenceHelper
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
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
  fun provideFirebaseDatabaseReference(
  ): DatabaseReference {
    return Firebase.database.reference
  }

  /* Locals */
  @Provides
  @Singleton
  fun provideMainLocal(
    preferenceHelper: PreferenceHelper
  ): MainLocal {
    return MainLocalImpl(
      preferenceHelper
    )
  }

  /* Repositories */
  @Provides
  @Singleton
  fun provideMainRepository(
    firebaseAuth: FirebaseAuth,
    databaseReference: DatabaseReference
  ): MainRepository {
    return MainRepositoryImpl(
      firebaseAuth, databaseReference
    )
  }
}