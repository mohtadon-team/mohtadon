package com.example.data.dataSource.remote.mapper

interface Mapper<I, O> {
    fun map(input: I): O
}