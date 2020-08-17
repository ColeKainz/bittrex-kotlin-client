package com.cluttered.cryptocurrency.model

open class ApiResponse(open val code: String? = null) {
    val success: Boolean
        get() = code == null
}