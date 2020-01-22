package com.cardamon.dogs.view

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.cardamon.dogs.R
import com.cardamon.dogs.util.PERMISION_SEND_SMS
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var navContoller: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        navContoller = Navigation.findNavController(this, R.id.fragment)
        NavigationUI.setupActionBarWithNavController(this, navContoller)


    }

    override fun onSupportNavigateUp(): Boolean {
        return NavigationUI.navigateUp(navContoller, null)
    }

    fun checkSmsPermission() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.SEND_SMS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    this,
                    Manifest.permission.SEND_SMS
                )
            ) {
                AlertDialog.Builder(this)
                    .setTitle("Send SMS permission")
                    .setMessage("This app requires access to send an SMS.")
                    .setPositiveButton("Ask me") { dialog, which ->
                        requestSMSPermission()
                    }
                    .setNegativeButton("No") { dialog, which ->
                        notifyDetailFragment(false)
                    }
                    .show()
            } else {
                requestSMSPermission()
            }


        } else {
            notifyDetailFragment(true)
        }
    }

    private fun notifyDetailFragment(permisiionGranted: Boolean) {
       val activeFragment= fragment.childFragmentManager.primaryNavigationFragment
        if (activeFragment is DetailFragment) {
            activeFragment.onPermissionResult(permisiionGranted)
        }
    }

    private fun requestSMSPermission() {
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.SEND_SMS), PERMISION_SEND_SMS)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {

        when(requestCode) {
            PERMISION_SEND_SMS-> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    notifyDetailFragment(true)
                } else
                {
                    notifyDetailFragment(false)
                }
            }
        }



    }



}
