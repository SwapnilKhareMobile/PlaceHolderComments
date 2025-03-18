package com.sw.placeholder.data.di

import com.sw.placeholder.data.repo.CommentsRepository
import com.sw.placeholder.data.repo.CommentsRepositoryImpl
import com.sw.placeholder.data.source.CommentsRemoteDataSource
import com.sw.placeholder.data.source.CommentsRemoteDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class DataModule {


    @Binds
    abstract fun bindCommentsRepository(impl: CommentsRepositoryImpl): CommentsRepository

    @Binds
    abstract fun bindCommentsRemoteDataSource(impl: CommentsRemoteDataSourceImpl): CommentsRemoteDataSource

}