package com.androchef.data.store

import com.androchef.data.repositories.GitDataStore

class GitDataStoreFactory constructor(
    private val gitRemoteDataStore: GitRemoteDataStore
) {

    fun getRemoteDataStore(): GitDataStore {
        return gitRemoteDataStore
    }
}