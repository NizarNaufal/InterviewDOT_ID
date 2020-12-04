package id.interview.dotid.view.account

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import id.interview.dotid.R
import id.interview.dotid.repository.IView
import id.interview.dotid.repository.ViewNetworkState
import id.interview.dotid.repository.base.BaseActivity
import id.interview.dotid.support.*
import id.interview.dotid.view.account.support.ProfileModels
import id.interview.dotid.view.account.support.ProfilePresenter
import kotlinx.android.synthetic.main.activity_profile.*

class ActivityProfile :BaseActivity(),ViewNetworkState,IView{

    private val presenterAccount by lazy { ProfilePresenter(baseContext, this) }
    private var isRefresh = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)
        initView()
    }

    override fun initView() {
        swipe_refresh_profiles?.apply {
            setColorSchemeResources(R.color.colorGreen, R.color.colorOrange, R.color.colorRed)
            setOnRefreshListener {
                hide()
            }
        }
        presenterAccount.getProfile()
        back_btn_profile?.setOnClickListener {
            onBackPressed()
        }
    }
    override fun showLoading(key: String, status: Boolean) {
        super.showLoading(key, status)
        runOnUiThread {
            when (key) {
                presenterAccount?.profileParam -> {
                    if (isRefresh) {
                        swipe_refresh_profiles?.apply {
                            if (status) show() else hide()
                        }
                    } else {
                        if (status) {
                            swipe_refresh_profiles?.disable()
                            loading_profiles?.visible()
                            fullname_catalogue?.gone()
                            username_catalogue?.gone()
                            email_catalogue?.gone()
                            phone_number_catalogue?.gone()
                            image_catalogue?.gone()
                        } else {
                            swipe_refresh_profiles?.enable()
                            loading_profiles?.gone()
                            fullname_catalogue?.visible()
                            username_catalogue?.visible()
                            email_catalogue?.visible()
                            phone_number_catalogue?.visible()
                            image_catalogue?.visible()
                        }
                    }
                }
            }
        }
    }
    @SuppressLint("SetTextI18n")
    fun requestProfile(data: ProfileModels) {
        fullname_catalogue?.text = data.fullname_profile
        username_catalogue?.text = data.username_profile
        Glide.with(this)
            .load(data.avatar_profile)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .skipMemoryCache(true)
            .into(image_catalogue)
        email_catalogue?.text = data.email_profile
        phone_number_catalogue?.text = data.phone_profile
    }
    override fun requestSuccess(key: String, response: Any?) {
        super.requestSuccess(key, response)
        runOnUiThread {
            when (key) {
                presenterAccount?.profileParam -> {
                    val profile = response as ProfileModels
                    requestProfile(profile)
                }
            }
        }
    }

    override fun requestFailure(key: String, code: Int, message: Any?) {
        super.requestFailure(key, code, message)
        Log.d("datanya gamasuk gan ", "datanya gamasuk euy")
        runOnUiThread {
            when (key) {
                presenterAccount?.profileParam -> showToast(message.toString())
            }
        }
    }
}