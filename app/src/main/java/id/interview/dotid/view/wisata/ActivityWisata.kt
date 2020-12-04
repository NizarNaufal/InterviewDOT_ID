package id.interview.dotid.view.wisata

import android.os.Bundle
import android.util.Log
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import id.interview.dotid.R
import id.interview.dotid.repository.IView
import id.interview.dotid.repository.ViewNetworkState
import id.interview.dotid.repository.base.BaseActivity
import id.interview.dotid.support.showToast
import id.interview.dotid.view.wisata.module.WisataPresenter
import id.interview.dotid.view.wisata.support.ContentWisata
import id.interview.dotid.view.wisata.support.WisataAdapter
import id.interview.dotid.view.wisata.support.WisataModels
import kotlinx.android.synthetic.main.activity_home.*
import org.json.JSONObject

class ActivityWisata : BaseActivity(),ViewNetworkState,IView{

    private var mAdapter: WisataAdapter? = null
    private val presenter by lazy { WisataPresenter(baseContext, this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        initView()
    }

    override fun initView() {
        presenter.getWisata()
    }

    override fun requestSuccess(key: String, response: Any?) {
        super.requestSuccess(key, response)
        runOnUiThread {
            when (key) {
                presenter.wisataParam -> {
                    val json = JSONObject(response.toString()).getJSONObject("data")
                    val listData = Gson().fromJson(json.toString(), WisataModels::class.java)
                    val list = mutableListOf<WisataModels>()
                    list.add(listData)
                    Log.d("test data toko", listData.toString())
                    initList(list as ArrayList<WisataModels>)
                }
            }
        }
    }

    override fun requestFailure(key: String, code: Int, message: Any?) {
        super.requestFailure(key, code, message)
        Log.d("datanya gamasuk gan ", "datanya gamasuk euy")
        runOnUiThread {
            when (key) {
                presenter.wisataParam -> showToast(message.toString())
            }
        }
    }
    private fun initList(dataList: java.util.ArrayList<WisataModels>) {
        val adapterCart = WisataAdapter(
           this, dataList
        )
        rvList?.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = adapterCart
        }
        mAdapter = adapterCart
    }
}