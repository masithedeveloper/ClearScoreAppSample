package com.clearscore.report.di

import com.clearscore.report.BuildConfig
import com.clearscore.report.repositories.DefaultClientScoreRepository
import com.clearscore.report.repositories.ClientScoreRepository
import com.clearscore.report.data.remote.ClearScoreAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideDefaultClientScoreRepository(api: ClearScoreAPI) =
        DefaultClientScoreRepository(api) as ClientScoreRepository

    @Singleton
    @Provides
    fun provideClearScoreApi(): ClearScoreAPI {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.BASE_URL)
            .build()
            .create(ClearScoreAPI::class.java)
    }
}

















