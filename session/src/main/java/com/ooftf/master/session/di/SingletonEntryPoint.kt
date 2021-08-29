package com.ooftf.master.session.di

import com.ooftf.master.session.m.sign.ISignService
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@EntryPoint
interface SingletonEntryPoint {
    fun getSignService(): ISignService
}