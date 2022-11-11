package com.example.photoupload.retrofit.Models

import okhttp3.MultipartBody
import okhttp3.RequestBody

data class EditProfileReq (
    var image: MultipartBody.Part,
    var action: RequestBody
        )