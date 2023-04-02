package com.kate.interviewtask.pager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.kate.interviewtask.fragment.FavFragment
import com.kate.interviewtask.fragment.MainFragment

class MainTabAdapter(fragmentManager: FragmentManager) : FragmentStatePagerAdapter
    (fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    override fun getCount(): Int {
        return 2
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

}
