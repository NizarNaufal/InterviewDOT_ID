package id.interview.dotid.view.wisata.support

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import id.interview.dotid.R
import kotlinx.android.synthetic.main.item_home.view.*


class WisataAdapter(val context: Context, var merchants: ArrayList<WisataModels>) : RecyclerView.Adapter<WisataAdapter.ViewHolder>() {

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val merchant = merchants[p1]
        p0.itemView.title_wisata.text = merchant.header_wisata.title_wisata
        p0.itemView.subtitle_wisata.text = merchant.header_wisata.subtitle_wisata
        if (merchant.content_wisata[0].type_wisata == "image") {
            initList(p0, merchant.content_wisata as ArrayList<ContentWisata>,p1)
        } else if (merchant.content_wisata[0].type_wisata == "multiple"){
            initList(p0, merchant.content_wisata as ArrayList<ContentWisata>, p1)
        }
    }

    private fun initList(
        p0: ViewHolder,
        products: ArrayList<ContentWisata>,
        p1: Int
    ) {
        val adapterCart = WisataAdapterMultiple(context, products)
        p0.itemView.wisatarv?.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = adapterCart
        }
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val v = LayoutInflater.from(p0.context).inflate(R.layout.item_home, p0, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return merchants.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
}