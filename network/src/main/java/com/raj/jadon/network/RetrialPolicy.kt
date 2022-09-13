package com.raj.jadon.network

enum class RetrialPolicy(val retryNum: Int) {
    None(1), Twice(2), Thrice(3), Default(3), Max(5)
}