package com.kate.interviewtask

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

class MainTabAdapter(fragmentManager: FragmentManager) : FragmentStatePagerAdapter
    (fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val titles = listOf("所有圖片", "我的最愛")
    override fun getCount(): Int {
        return titles.size
    }

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> MainFragment()
            1 -> FavFragment()
            else -> {
                MainFragment()
            }
        }
    }

    override fun getPageTitle(position: Int): CharSequence {
        return titles[position]
    }

}
