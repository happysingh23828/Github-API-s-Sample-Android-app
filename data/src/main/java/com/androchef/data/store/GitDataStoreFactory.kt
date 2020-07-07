package com.androchef.data.store

import com.androchef.data.repositories.GitDataStore
import javax.inject.Inject

class GitDataStoreFactory @Inject constructor(
    private val gitRemoteDataStore: GitRemoteDataStore
) {

    fun getRemoteDataStore(): GitDataStore {
        return gitRemoteDataStore
    }
}