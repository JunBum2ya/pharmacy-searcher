package com.midas.pharmacy.exception

class CustomException(val code: String, message: String): Exception(message) {
    constructor(status: ExceptionStatus) : this(code = status.code, message = status.message)
    constructor(status: ExceptionStatus, message: String): this(status.code, message)
}