package id.interview.dotid.view.wisata

import android.os.Bundle
import id.interview.dotid.R
import id.interview.dotid.repository.IView
import id.interview.dotid.repository.ViewNetworkState
import id.interview.dotid.repository.base.BaseActivity

class ActivityWisata : BaseActivity(),ViewNetworkState,IView{
    override fun initView() {
        TODO("Not yet implemented")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
    }
}