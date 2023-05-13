package com.example.postsapplication.core.base.usecase

interface UseCase<I,O> {
    fun execute(input: I?= null): O
}