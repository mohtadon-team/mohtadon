package com.mohtdon.domain.models

open class AppException(message: String?): Exception(message)

class NullResultException(message: String?) : AppException(message)
class BadRequestException(message: String?) : AppException(message)
class NotFoundException(message: String?): AppException(message)
open class NetworkException(message: String?): AppException(message)
class  NoInternetException(message: String?): NetworkException(message)
class ServerException(message: String?) : NetworkException(message)
