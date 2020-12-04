package id.interview.dotid.view.wisata.support

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import id.interview.dotid.R
import id.interview.dotid.view.gallery.ActivityDetailGallery
import id.interview.dotid.view.gallery.module.GalleryModels
import kotlinx.android.synthetic.main.item_galery.view.*
import kotlinx.android.synthetic.main.item_wisata_multiple.view.*


class WisataAdapterMultiple(val context: Context, var gallery: ArrayList<ContentWisata>) : RecyclerView.Adapter<WisataAdapterMultiple.ViewHolder>() {

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val gallery = gallery[p1]

        if (gallery.image_wisata == "null"){
            gallery.media_wisata?.firstOrNull()?.let {
                Glide.with(context)
                    .load(it)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true)
                    .into(p0.itemView.image_catalogue_wisata_multiple)
            }
        } else {
            gallery.image_wisata.let {
                Glide.with(context)
                    .load(it)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .skipMemoryCache(true)
                    .into(p0.itemView.image_catalogue_wisata_multiple)
            }
        }

        p0.itemView.setOnClickListener { view1: View ->
            val intent = Intent(view1.context, ActivityDetailGallery::class.java)
            intent.putExtra("data", gallery)
            view1.context.startActivity(intent)
        }

    }
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val v = LayoutInflater.from(p0.context).inflate(R.layout.item_wisata_multiple, p0, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return gallery.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}