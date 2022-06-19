package com.tahakorkem.unigrade.di

import com.tahakorkem.unigrade.data.source.LectureRepository
import com.tahakorkem.unigrade.data.source.impl.FakeLectureRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideLectureRepository(): LectureRepository {
        return FakeLectureRepository()
    }

}