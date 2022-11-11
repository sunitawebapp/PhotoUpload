package com.example.photoupload

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.photoupload.viewmodels.PhotoUploadViewModel
import com.github.dhaval2404.imagepicker.ImagePicker
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File


class MainActivity : AppCompatActivity() {
  val photoUploadViewModel : PhotoUploadViewModel by viewModels()
    lateinit var imgProfile : ImageView
    lateinit var btnSubmit : Button
    lateinit var    tvphotosize : TextView
     var imgProfilepath=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imgProfile=findViewById(R.id.imgProfile)
        btnSubmit=findViewById(R.id.btnSubmit)
        tvphotosize=findViewById(R.id.tvphotosize)

        imgProfile.setOnClickListener {
            getImagePick()
        }

        btnSubmit.setOnClickListener {
            calltoChangeAvatar()
        }


    }

    fun getImagePick(){
        ImagePicker.with(this)
           // .crop()	    			//Crop image(Optional), Check Customization for more option
            .compress(1024)			//Final image size will be less than 1 MB(Optional)
          //   .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
            .start()
    }

    //handle result of picked image
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK) {
            //Image Uri will not be null for RESULT_OK
            val uri: Uri = data?.data!!


            imgProfilepath= uri.path.toString();


         //   ImageCompressUtils.compressImage(this,imgProfilepath,"pic",0)
            Log.d("imagepath", "onActivityResult: "+imgProfilepath)
            // Use Uri object instead of File to avoid storage permissions
            imgProfile.setImageURI(uri)
            val file = File(imgProfilepath)
            val length: Long = file.length() / 1024 // Size in KB

            tvphotosize.setText("$length KB ")


        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Toast.makeText(this, ImagePicker.getError(data), Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Task Cancelled", Toast.LENGTH_SHORT).show()
        }
    }

    fun  calltoChangeAvatar(){
        val reqBodyFile: RequestBody = File(imgProfilepath).asRequestBody("image/jpeg".toMediaTypeOrNull())
        var image = MultipartBody.Part.createFormData("image",  File(imgProfilepath)?.name, reqBodyFile)
        val action="avtarImage".toRequestBody("text/plain".toMediaTypeOrNull())
       /* photoUploadViewModel.uploadMultipart(
            "ac9308aadbb86a5de8f91c955d96d197",
            image,
            action


        )
*/
        photoUploadViewModel.uploadMulti(
            "ac9308aadbb86a5de8f91c955d96d197",
            image,
            "avtarImage"


        )
    }


}