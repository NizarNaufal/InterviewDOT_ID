package id.interview.dotid.view.gallery.module

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
import kotlinx.android.synthetic.main.item_galery.view.*


class GalleryAdapter(val context: Context, var gallery: ArrayList<GalleryModels>) : RecyclerView.Adapter<GalleryAdapter.ViewHolder>() {

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val gallery = gallery[p1]
        p0.itemView.caption_catalogue.text = gallery.caption_gallery
        gallery.thumbnail_gallery.let {
            Glide.with(context)
                .load(it)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(p0.itemView.image_catalogue_gallery)
        }

        p0.itemView.setOnClickListener { view1: View ->
            val intent = Intent(view1.context, ActivityDetailGallery::class.java)
            intent.putExtra("data", gallery)
            view1.context.startActivity(intent)
        }


    }
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val v = LayoutInflater.from(p0.context).inflate(R.layout.item_galery, p0, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return gallery.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}