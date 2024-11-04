package com.jungle.tt.exception

class InvalidInputException (
    val fieldName: String = "",
    message : String = "Invalid Input",
) : RuntimeException(message)