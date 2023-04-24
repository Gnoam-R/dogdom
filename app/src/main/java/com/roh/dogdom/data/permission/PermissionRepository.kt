package com.roh.dogdom.data.permission

import android.app.Activity

interface PermissionRepository {
        fun setPermissions( mActivity : Activity, permissions: Array<String>, PERMISSION_REQUEST: Int) : Boolean
        fun requestPermissions() : Boolean
        fun hasPermissions(): Boolean
        fun checkVersion() : Boolean
        fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String?>, grandResults: IntArray) : Boolean
        fun checkOverlay() : Boolean
}