package com.ccastro.maas.domain.use_cases.userCard

import android.util.Log
import com.ccastro.maas.domain.model.Response
import com.ccastro.maas.domain.model.UseCaseResponse
import com.ccastro.maas.domain.model.UserCard
import com.ccastro.maas.domain.repository.UserCardRepository
import javax.inject.Inject

class AddUserCard @Inject constructor (private val repository: UserCardRepository) {

    suspend operator fun invoke(cardNumber: String): UseCaseResponse {

        when(val responseValidationInfoCard = repository.validate(cardNumber)){

            is Response.Fail -> {
                Log.e("ExceptionAddUserCard", "Error: ${responseValidationInfoCard.exception?.printStackTrace()}")
                if("404" in responseValidationInfoCard.exception.toString()){
                    return UseCaseResponse(false, "Número de tarjeta incorrecto")
                }else return UseCaseResponse(false, "01: Verifica tu conexión a internet")
            }

            Response.Loading -> {
                Log.i("validateCardFlow", "AddUserCard responseBasicInfoCard -> loading")
            }

            is Response.Success -> {

                // Obtenemos la información de validación de la tarjeta
                val userCardValidationInfo: UserCard? = responseValidationInfoCard.data

                // Leemos la información completa de la tarjeta
                val responseInfoCard = userCardValidationInfo?.let { repository.getFullCardInfoRequest(it.cardNumber) }

                when(responseInfoCard){
                    is Response.Fail -> {
                        Log.e("ExceptionAddUserCard", "Error: ${responseInfoCard.exception?.printStackTrace()}")
                        if("404" in responseInfoCard.exception.toString()){
                            return UseCaseResponse(false, "No se encontró información de la tarjeta")
                        }else return UseCaseResponse(false, "Verifica tu conexión a internet")
                    }

                    Response.Loading -> {
                        Log.e("Logi", "AddUserCard responseBasicInfoCard -> loading")
                    }

                    is Response.Success -> {

                        // Obtenemos la segunda instancia de la tarjeta
                        val userCardFullInformation: UserCard? = responseInfoCard.data


                        // Completamos los datos de la tarjeta
                        userCardFullInformation?.card = userCardValidationInfo.card
                        userCardFullInformation?.isValid = userCardValidationInfo.isValid
                        userCardFullInformation?.status = userCardValidationInfo.status
                        userCardFullInformation?.statusCode = userCardValidationInfo.statusCode

                        Log.i("ToSaveCard","Full Info: ${userCardFullInformation?.toString()}")
                        Log.i("ToSaveCard","Valid Indi: ${userCardValidationInfo?.toString()}")


                        //TODO("Lo almacenamos en la BD")

                        return UseCaseResponse(true, "¡ Ya vinculaste tu tarjeta !")
                    }

                    else -> return UseCaseResponse(false, "Ocurrio un error intenta nuevamente")
                }

            }
        }
        return UseCaseResponse(false, "Ocurrio un error intenta nuevamente")
    }

}