package id.interview.dotid.support

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import id.interview.dotid.view.MainScreen

class FragmentAdapter2(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    private val screens = arrayListOf<MainScreen>()

    fun setItems(screens: List<MainScreen>) {
        this.screens.apply {
            clear()
            addAll(screens)
            notifyDataSetChanged()
        }
    }

    fun getItems(): List<MainScreen> {
        return screens
    }

    override fun getItem(position: Int): Fragment {
        return screens[position].fragment
    }

    override fun getCount(): Int {
        return screens.size
    }
}