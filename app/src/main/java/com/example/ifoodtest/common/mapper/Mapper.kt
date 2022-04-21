package com.example.ifoodtest.common.mapper

interface Mapper<S, T> {
    fun map(source: S): T
}