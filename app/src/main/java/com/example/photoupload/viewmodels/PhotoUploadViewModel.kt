package com.example.photoupload.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.photoupload.retrofit.Models.EditProfileReq
import com.sunitawebapp.admin_giriexp.retrofit.ApiClient
import com.sunitawebapp.admin_giriexp.retrofit.ApiInterface
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import okhttp3.RequestBody

class PhotoUploadViewModel : ViewModel(){

    fun uploadMultipart(AUTHTOKEN : String ,image: MultipartBody.Part?,action: RequestBody){
        viewModelScope.launch {
            apicallForMultipart(AUTHTOKEN,image,action)
        }

    }

    fun uploadMulti(AUTHTOKEN : String ,image: MultipartBody.Part?,action: String){
        viewModelScope.launch {
            apicallForMultiIMG(AUTHTOKEN,image,action)
        }

    }

    suspend  fun apicallForMultipart(AUTHTOKEN : String ,image: MultipartBody.Part?,action: RequestBody){
        ApiClient.createService(ApiInterface::class.java).getmutipart(AUTHTOKEN, image, action)
    }

    suspend  fun apicallForMultiIMG(AUTHTOKEN : String ,image: MultipartBody.Part?,action: String){
        ApiClient.createService(ApiInterface::class.java).getmutipartIMG(AUTHTOKEN, image, action)
    }
}
