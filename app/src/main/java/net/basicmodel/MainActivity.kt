package net.basicmodel

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import net.fragment.HotFragment
import net.fragment.SettingFragment
import net.fragment.TypeFragment
import net.widget.LoadingDialog

class MainActivity : AppCompatActivity() {
    var hotFragment: HotFragment? = null
    var typeFragment: TypeFragment? = null
    var settingFragment: SettingFragment? = null
    var loadingDialog :LoadingDialog? = null
    private val permissions = arrayOf(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        requestPermissions()
        showPosition(0)
    }

    private fun requestPermissions() {
        if (checkPermission(permissions[0]) && checkPermission(permissions[1])) {

        } else {
            ActivityCompat.requestPermissions(this, permissions, 321)
        }
    }

    private fun checkPermission(per: String): Boolean {
        return ContextCompat.checkSelfPermission(this, per) == PackageManager.PERMISSION_GRANTED
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 321) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                } else {
                    Log.i("xxxxxxH", "获取权限成功")
                }
            }
        }
    }

    private fun showPosition(position: Int) {
        val fm: FragmentManager = supportFragmentManager
        val ft: FragmentTransaction = fm.beginTransaction()
        hideAll(ft)
        if (position == 0) {
            hotFragment = fm.findFragmentByTag("hot") as HotFragment?
            if (hotFragment == null) {
                hotFragment = HotFragment()
                ft.add(R.id.content, hotFragment!!, "hot")
            } else {
                ft.show(hotFragment!!)
            }
        }

        if (position == 1) {
            typeFragment = fm.findFragmentByTag("type") as TypeFragment?
            if (typeFragment == null) {
                typeFragment = TypeFragment()
                ft.add(R.id.content,typeFragment!!,"type")
            } else {
                ft.show(typeFragment!!)
            }
        }

        if (position == 2) {
            settingFragment = fm.findFragmentByTag("set") as SettingFragment?
            if (settingFragment == null) {
                settingFragment = SettingFragment()
                ft.add(R.id.content, settingFragment!!, "set")
            } else {
                ft.show(settingFragment!!)
            }
        }
        ft.commit()
    }

    private fun hideAll(ft: FragmentTransaction) {
        if (hotFragment != null) {
            ft.hide(hotFragment!!)
        }
        if (typeFragment != null) {
            ft.hide(typeFragment!!)
        }
        if (settingFragment != null) {
            ft.hide(settingFragment!!)
        }
    }

    fun showDlg(){
        if (loadingDialog == null){
            loadingDialog = LoadingDialog(this)
        }
        loadingDialog!!.show()
    }

    fun closeDlg(){
        if (loadingDialog != null && loadingDialog!!.isShowing){
            loadingDialog!!.dismiss()
        }
    }
}