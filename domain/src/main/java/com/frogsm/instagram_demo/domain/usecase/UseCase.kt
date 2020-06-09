package com.frogsm.instagram_demo.domain.usecase

import kotlinx.coroutines.flow.Flow

interface UseCase<T, R> {
    operator fun invoke(param: T): Result<R>
}

interface SuspendUseCase<T, R> {
    suspend operator fun invoke(param: T): Result<R>
}

interface FlowUseCase<T, R> {
    operator fun invoke(param: T): Flow<R>
}

interface SuspendFlowUseCase<T, R> {
    suspend operator fun invoke(param: T): Flow<R>
}