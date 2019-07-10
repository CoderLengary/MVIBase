package com.example.lengary_l.mvi_lib.repository

/**
 * Created by CoderLengary
 */
abstract class BaseRepositoryBoth<R : IRemoteDataSource, L : ILocalDataSource>(
    val remoteDataSource: R,
    val localDataSource: L
) : IRepository

abstract class BaseRepositoryLocal<L : ILocalDataSource>(
    val localDataSource: L
) : IRepository

abstract class BaseRepositoryRemote<R : IRemoteDataSource>(
    val remoteDataSource: R
) : IRepository