package br.com.jan1ooo.creditrequestsystem.exception

data class BusinessException(override val message: String?): RuntimeException(message){
}