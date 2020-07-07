package com.androchef.domain.interactor.core

import com.androchef.domain.executor.PostExecutionThread
import com.androchef.domain.executor.ThreadExecutor
import io.reactivex.Scheduler
import io.reactivex.Single
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

abstract class SingleUseCase<Params, Result> constructor(
    private val threadExecutor: ThreadExecutor,
    private val postExecutionThread: PostExecutionThread
) {

    protected abstract fun buildUseCaseObservable(requestValues: Params? = null): Single<Result>

    /**
     * Executes the current use case.
     */
    open fun execute(singleObserver: DisposableSingleObserver<Result>, requestValues: Params?) {
        val single = this.buildUseCaseObservable(requestValues).subscribeOn(
            Schedulers.from(threadExecutor)
        ).observeOn(postExecutionThread.scheduler) as Single<Result>
        addDisposable(single.subscribeWith(singleObserver))
    }

    open fun execute(
        singleObserver: DisposableSingleObserver<Result>,
        scheduler: Scheduler,
        requestValues: Params? = null
    ) {
        val single = this.buildUseCaseObservable(requestValues).subscribeOn(
            Schedulers.from(threadExecutor)
        ).observeOn(scheduler) as Single<Result>
        addDisposable(single.subscribeWith(singleObserver))
    }

    private val disposables = CompositeDisposable()

    fun dispose() {
        if (disposables.isDisposed.not()) disposables.dispose()
    }

    private fun addDisposable(disposable: Disposable) {
        disposables.add(disposable)
    }
}
