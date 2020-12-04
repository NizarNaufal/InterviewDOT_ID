package id.interview.dotid.view.gallery

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import androidx.recyclerview.widget.GridLayoutManager
import id.interview.dotid.R
import id.interview.dotid.repository.IView
import id.interview.dotid.repository.NetworkingState
import id.interview.dotid.repository.ViewNetworkState
import id.interview.dotid.repository.base.BaseActivity
import id.interview.dotid.support.*
import id.interview.dotid.view.gallery.module.GalleryAdapter
import id.interview.dotid.view.gallery.module.GalleryModels
import id.interview.dotid.view.gallery.support.GalleryPresenter
import kotlinx.android.synthetic.main.activity_gallery.*


class ActivityGallery : BaseActivity(), ViewNetworkState, IView {
    private var mAdapter: GalleryAdapter? = null
    private var isRefresh = false
    private val presenter by lazy { GalleryPresenter(baseContext, this) }
    private var gallery_gue = mutableListOf<GalleryModels>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery)
        initView()
    }

    override fun initView() {
        requestGallery()
        swipe_refresh_gallery?.apply {
            setColorSchemeResources(R.color.colorGreen, R.color.colorOrange, R.color.colorRed)
            setOnRefreshListener {
                hide()
                search_destination.text = null
                requestGallery(true)
            }
        }
        search_destination.setOnKeyListener { _, keyCode, event ->
            if ((event.action == KeyEvent.ACTION_DOWN) &&
                (keyCode == KeyEvent.KEYCODE_ENTER)) {
                Log.d("setOnKeyListener ", search_destination.text.toString())
                val keyword = search_destination.text.toString()
                val data = gallery_gue.filter { it.caption_gallery.contains(keyword, true) }
                initList(data as ArrayList<GalleryModels>)
                return@setOnKeyListener true
            }
            return@setOnKeyListener false
        }
        ivBack_gallery?.setOnClickListener {
            onBackPressed()
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        networkState = NetworkingState.Destroy()
    }

    private fun requestGallery(isRefresh: Boolean = false) {
        this.isRefresh = isRefresh
        presenter?.getGallery()

    }

    private fun initList(dataList: ArrayList<GalleryModels>) {
        val adapterCart = GalleryAdapter(this,dataList
        )
        rvGallery.addItemDecoration(GridItemDecoration(10, 2))

        rvGallery?.apply {
            layoutManager = GridLayoutManager(context,2)
            adapter = adapterCart
        }
        mAdapter = adapterCart
    }

    override fun showLoading(key: String, status: Boolean) {
        super.showLoading(key, status)
        runOnUiThread {
            when (key) {
                presenter?.galleryParam -> {
                    if (isRefresh) {
                        swipe_refresh_gallery?.apply {
                            if (status) show() else hide()
                        }
                    } else {
                        if (status) {
                            swipe_refresh_gallery?.disable()
                            loading_gallery?.visible()
                            rvGallery?.gone()
                        } else {
                            swipe_refresh_gallery?.enable()
                            loading_gallery?.gone()
                            rvGallery?.visible()
                        }
                    }
                }
            }
        }
    }

    override fun requestSuccess(key: String, response: Any?) {
        super.requestSuccess(key, response)
       runOnUiThread {
            when (key) {
                presenter?.galleryParam -> {
                    gallery_gue = (response as List<GalleryModels>).toMutableList()
                    initList(gallery_gue as ArrayList<GalleryModels>)
                }
            }
        }
    }
    override fun requestFailure(key: String, code: Int, message: Any?) {
        super.requestFailure(key, code, message)
       runOnUiThread {
            when (key) {
                presenter?.galleryParam ->showToast(message.toString())
            }
        }
    }
}