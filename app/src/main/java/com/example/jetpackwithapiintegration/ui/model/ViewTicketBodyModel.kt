package com.example.jetpackwithapiintegration.ui.model

import com.example.jetpackwithapiintegration.ui.Global
import okhttp3.RequestBody

data class ViewTicketBodyModel(
    val comp_id :String = "237",
    val user_id : String =" 700372"


){
    fun toRequestBodyMap() : Map<String, RequestBody> {
        return  mapOf(
            "comp_id" to Global.createTextRequestBody(comp_id),
            "user_id" to Global.createTextRequestBody(user_id),
        )
    }
}
