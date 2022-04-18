package id.my.fanslab.suitgamech5.ui.adapter

import android.annotation.SuppressLint
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class LandingAdapter(fa: FragmentActivity): FragmentStateAdapter(fa) {

    private val listFragment = ArrayList<Fragment>()

    override fun getItemCount(): Int {
        return listFragment.size
    }

    override fun createFragment(position: Int): Fragment {
        return listFragment.get(position)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setFragment(list: List<Fragment>) {
        listFragment.clear()
        listFragment.addAll(list)
        notifyDataSetChanged()
    }
}