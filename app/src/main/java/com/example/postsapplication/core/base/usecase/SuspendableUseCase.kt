package com.example.postsapplication.core.base.usecase

interface SuspendableUseCase<I,O> {
    suspend fun execute(input: I?= null): O
}