package com.sunitawebapp.admin_giriexp.retrofit


import com.example.photoupload.Models.SetImageResponse.SetImageResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface ApiInterface {


 // @Headers("Content-Type: application/json")
    @Multipart
    @POST("/me/setProfileImage")
    suspend fun getmutipart(
        @Header("AUTHTOKEN") authorizationKey: String,
        @Part  image : MultipartBody.Part?,
        @Part ("action") action : RequestBody,

    ) : Response<SetImageResponse>

    @Multipart
    @POST("/me/setProfileImage")
    suspend fun getmutipartIMG(
        @Header("AUTHTOKEN") authorizationKey: String,
        @Part  image : MultipartBody.Part?,
        @Part ("action") action : String,

        ) : Response<SetImageResponse>

}
