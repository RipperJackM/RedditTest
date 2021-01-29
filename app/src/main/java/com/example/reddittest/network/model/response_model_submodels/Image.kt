package com.example.reddittest.network.model.response_model_submodels

data class Image(
    val id: String?,
    val resolutions: List<Resolution>?,
    val source: Source?,
    val variants: Variants?
)