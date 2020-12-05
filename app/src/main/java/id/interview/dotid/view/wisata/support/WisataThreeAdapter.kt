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
import kotlinx.android.synthetic.main.item_wisata_three.view.*


class WisataThreeAdapter(val context: Context, var content: ArrayList<ContentWisata>) : RecyclerView.Adapter<WisataThreeAdapter.ViewHolder>() {

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val content = content[p1]
        p0.itemView.title_three_content?.text = content.title_content
        p0.itemView.subtitle_three_content?.text = content.content_wisata
        if (content.image_wisata == null){
            content.media_wisata?.firstOrNull()?.let {
                Glide.with(context)
                        .load(it)
                        .diskCacheStrategy(DiskCacheStrategy.NONE)
                        .skipMemoryCache(true)
                        .into(p0.itemView.image_catalogue_wisata_three)
            }
        }
        p0.itemView.setOnClickListener { view1: View ->
            val intent = Intent(view1.context, ActivityDetailGallery::class.java)
            intent.putExtra("data", content)
            view1.context.startActivity(intent)
        }

    }
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val v = LayoutInflater.from(p0.context).inflate(R.layout.item_wisata_three, p0, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return content.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}