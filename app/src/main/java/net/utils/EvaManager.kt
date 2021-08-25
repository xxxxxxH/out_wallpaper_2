package net.utils

import android.content.Context
import android.content.Intent
import android.net.Uri

open class EvaManager {
    fun Market(con: Context, pkgName: String) {
        val uri = Uri.parse("market://details?id=$pkgName")
        val intent = Intent(Intent.ACTION_VIEW, uri)
        try {
            con.startActivity(intent)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun getPkgName(con: Context):String{
        try {
            val packageManager = con.packageManager
            val packageInfo = packageManager.getPackageInfo(con.packageName,0)
            return packageInfo.packageName
        }catch (e:Exception){
            e.printStackTrace()
        }
        return ""
    }
}