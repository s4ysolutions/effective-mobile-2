package com.example.effectivem2

import android.content.Context
import com.example.effectivem2.data.CoordinatesProvider
import com.example.effectivem2.data.FavsProvider
import com.example.effectivem2.data.JobsProvider
import com.example.effectivem2.data.retrofit.RetrofitCoordinatesProvider
import com.example.effectivem2.data.retrofit.RetrofitJobsProvider
import com.example.effectivem2.data.room.RoomFavsProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TicketsProviderModule {
    @Provides
    @Singleton
    fun provideJobsProvider(@ApplicationContext context: Context): JobsProvider {
        return RetrofitJobsProvider(context)
    }

    @Provides
    @Singleton
    fun provideFavsProvider(@ApplicationContext context: Context): FavsProvider {
        return RoomFavsProvider(context)
    }

    @Provides
    @Singleton
    fun provideCoordinatesProvider(): CoordinatesProvider{
        return RetrofitCoordinatesProvider()
    }
}