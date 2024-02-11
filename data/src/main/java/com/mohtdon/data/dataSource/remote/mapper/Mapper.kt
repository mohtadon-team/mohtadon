package com.mohtdon.data.dataSource.remote.mapper

interface Mapper <I,O> {
    fun map(input:I):O
}