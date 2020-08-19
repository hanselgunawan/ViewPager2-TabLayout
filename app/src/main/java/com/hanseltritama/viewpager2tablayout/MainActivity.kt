package com.hanseltritama.viewpager2tablayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewPager: ViewPager2 = findViewById(R.id.viewPager)
        viewPager.adapter = OrdersPagerAdapter(this)

        val tabLayout: TabLayout = findViewById(R.id.tabLayout)

        val tabLayoutMediator = TabLayoutMediator(
            tabLayout, viewPager, TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                when (position) {
                    0 -> {
                        tab.text = "Pending"
                        tab.setIcon(R.drawable.ic_pending)
                    }
                    1 -> {
                        tab.text = "Confirmed"
                        tab.setIcon(R.drawable.ic_confirmed)
                    }
                    else -> {
                        tab.text = "Delivered"
                        tab.setIcon(R.drawable.ic_delivered)
                        val badgeDrawable = tab.orCreateBadge
                        badgeDrawable.backgroundColor =
                            ContextCompat.getColor(applicationContext, R.color.colorAccent)
                        badgeDrawable.isVisible = true
                        badgeDrawable.number = 100
                    }
                }
            }
        )

        tabLayoutMediator.attach()

    }
}
