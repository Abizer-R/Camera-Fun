package com.example.camerafunapp.util

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

object PermissionUtils {

    const val CAMERAX_PERMISSIONS_PHOTO_CODE = 10001
    private const val CAMERAX_PERMISSIONS_PHOTO = Manifest.permission.CAMERA

    const val CAMERAX_PERMISSIONS_ALL_CODE = 10002
    private val CAMERAX_PERMISSIONS_ALL = arrayOf(
        Manifest.permission.CAMERA,
        Manifest.permission.RECORD_AUDIO
    )

    fun hasCameraPermissionsPhotoOnly(
        context: Context
    ) : Boolean {
        return checkPermission(
            context,
            CAMERAX_PERMISSIONS_PHOTO
        )
    }

    fun hasCameraPermissions(
        context: Context
    ) : Boolean {
        return checkMultiplePermissions(
            context,
            CAMERAX_PERMISSIONS_ALL
        )
    }

    fun requestCameraPermissions(
        activity: Activity
    ) {
        ActivityCompat.requestPermissions(
            activity,
            CAMERAX_PERMISSIONS_ALL,
            CAMERAX_PERMISSIONS_ALL_CODE
        )
    }

    private fun checkMultiplePermissions(
        context: Context,
        permissionsStringArr: Array<String>
    ) : Boolean {
        permissionsStringArr.forEach { permissionString ->
            if (!checkPermission(context, permissionString)) {
                return false
            }
        }
        return true
    }

    private fun checkPermission(
        context: Context,
        permissionString: String
    ) : Boolean {
        return ContextCompat.checkSelfPermission(
            context, permissionString
        ) == PackageManager.PERMISSION_GRANTED
    }
}