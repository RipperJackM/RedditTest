package com.example.reddittest.network.model

import com.example.reddittest.network.model.response_model_submodels.Data

data class ResponseModel(
    val `data`: Data?,
    val kind: String?
)