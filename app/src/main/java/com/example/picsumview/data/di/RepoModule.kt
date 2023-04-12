package com.example.picsumview.data.di

import com.example.picsumview.data.network.DefaultPicsumRepository
import com.example.picsumview.data.network.PicsumRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class RepoModule {

    @Binds
    abstract fun providesRepo(impl: DefaultPicsumRepository): PicsumRepository

}
