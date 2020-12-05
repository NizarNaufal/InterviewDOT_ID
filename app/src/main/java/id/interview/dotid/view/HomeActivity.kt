package id.interview.dotid.view

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import id.interview.dotid.R
import id.interview.dotid.repository.IView
import id.interview.dotid.repository.ViewNetworkState
import id.interview.dotid.repository.base.BaseActivity
import id.interview.dotid.support.showActivity
import id.interview.dotid.view.account.ActivityProfile
import id.interview.dotid.view.gallery.ActivityGallery
import id.interview.dotid.view.wisata.ActivityWisata
import kotlinx.android.synthetic.main.activity_main.*


class HomeActivity : BaseActivity(),ViewNetworkState,IView{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }
    override fun initView(){
        card_wisata?.setOnClickListener {
            val builder = AlertDialog.Builder(this)
            builder.setMessage("Menu List Wisata belum tersedia")
            builder.setPositiveButton("OK"){ _, _ ->

            }
            builder.show()
            return@setOnClickListener
        }
        card_gallery?.setOnClickListener {
            showActivity(ActivityGallery::class.java)
        }
        card_profile?.setOnClickListener {
            showActivity(ActivityProfile::class.java)
        }
    }
}