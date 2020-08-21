package com.example.socailnetwork.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.socailnetwork.R
import com.example.socailnetwork.fragments.AccountFragment
import com.example.socailnetwork.fragments.AddPostFragment
import com.example.socailnetwork.fragments.HomeFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val homeFragment = HomeFragment()
        val addPostFragment = AddPostFragment()
        val accountFragment = AccountFragment()

        makeCurrent(homeFragment)

        bottomNav.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.home -> makeCurrent( homeFragment)
                R.id.add_post -> makeCurrent(addPostFragment)
                R.id.profile -> makeCurrent(accountFragment)
            }
            true
        }

    }
    private fun makeCurrent(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container, fragment).commit()
        }
    }
}