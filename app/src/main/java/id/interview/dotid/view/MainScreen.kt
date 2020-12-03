package id.interview.dotid.view

import androidx.annotation.IdRes
import androidx.fragment.app.Fragment
import id.interview.dotid.R


enum class MainScreen(@IdRes val menuItemId: Int, val fragment: Fragment) {
    PLACE(R.id.place, FragmentHome()),
    GALLERY(R.id.gallery, FragmentHome()),
    PROFILE(R.id.profile, FragmentHome())
}

fun getMainScreenForMenuItem(menuItemId: Int): MainScreen? {
    for (mainScreen in MainScreen.values()) {
        if (mainScreen.menuItemId == menuItemId) {
            return mainScreen
        }
    }
    return null
}