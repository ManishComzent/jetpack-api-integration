package com.example.jetpackwithapiintegration.ui.model

import com.example.jetpackwithapiintegration.ui.Global
import okhttp3.RequestBody

data class TicketSubmitBodyModel(
    val ticketId: String,
    val forUserId: String,
    val moduleId: String,
    val issueTitle: String,
    val issueDescription: String,
    val userId: String,
    val companyId: String
){
    fun toRequestBodyMap(): Map<String, RequestBody> {
        return mapOf(
            "ticket_catid" to Global.createTextRequestBody(ticketId),
            "for_user_id" to Global.createTextRequestBody(forUserId),
            "module_id" to Global.createTextRequestBody(moduleId),
            "issue_title" to Global.createTextRequestBody(issueTitle),
            "issue_description" to Global.createTextRequestBody(issueDescription),
            "user_id" to Global.createTextRequestBody(userId),
            "comp_id" to Global.createTextRequestBody(companyId)
        )
    }
}
