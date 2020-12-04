package id.interview.dotid.view.gallery

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import id.interview.dotid.R
import id.interview.dotid.repository.IView
import id.interview.dotid.repository.ViewNetworkState
import id.interview.dotid.repository.base.BaseActivity
import id.interview.dotid.view.gallery.module.GalleryModels
import kotlinx.android.synthetic.main.activity_details_gallery.*

class ActivityDetailGallery : BaseActivity(),ViewNetworkState,IView{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details_gallery)
        initView()
    }
    override fun initView() {
        val data = intent?.getParcelableExtra<GalleryModels>("data")
        Glide.with(this)
            .load(data?.image_gallery)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .skipMemoryCache(true)
            .into(image_gallery_details)
        data?.caption_gallery?.let { name_gallery?.setText(it) }
        back_btn_gallery?.setOnClickListener {
            onBackPressed()
        }
        share_gallery?.setOnClickListener {
            val sharingIntent = Intent(Intent.ACTION_SEND)
            sharingIntent.type = "text/plain"
            val uri_apps: Uri = Uri.parse("https://play.google.com/store/apps/details?id=$packageName")
            val shareBody = "Temukan ${data?.caption_gallery} ini Di Aplikasi Destination Kita $uri_apps"
            sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody)
            startActivity(Intent.createChooser(sharingIntent, "Share via"))
        }
    }
}