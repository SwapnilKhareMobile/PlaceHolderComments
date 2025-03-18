package com.sw.placeholder.domain.di

import com.sw.placeholder.domain.GetCommentsUseCase
import com.sw.placeholder.domain.GetCommentsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class DomainModule {

    @Binds
    abstract fun bindGetCommentsUseCase(impl: GetCommentsUseCaseImpl): GetCommentsUseCase

}