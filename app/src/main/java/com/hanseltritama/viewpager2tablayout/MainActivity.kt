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
                        val badgeDrawable = tab.orCreateBadge
                        badgeDrawable.backgroundColor =
                            ContextCompat.getColor(applicationContext, R.color.colorAccent)
                        badgeDrawable.isVisible = true
                    }
                    1 -> {
                        tab.text = "Confirmed"
                        tab.setIcon(R.drawable.ic_confirmed)
                        val badgeDrawable = tab.orCreateBadge
                        badgeDrawable.backgroundColor =
                            ContextCompat.getColor(applicationContext, R.color.colorAccent)
                        badgeDrawable.isVisible = true
                        badgeDrawable.number = 7
                    }
                    else -> {
                        tab.text = "Delivered"
                        tab.setIcon(R.drawable.ic_delivered)
                        val badgeDrawable = tab.orCreateBadge
                        badgeDrawable.backgroundColor =
                            ContextCompat.getColor(applicationContext, R.color.colorAccent)
                        badgeDrawable.isVisible = true
                        badgeDrawable.number = 100
                        badgeDrawable.maxCharacterCount = 3
                    }
                }
            }
        )

        tabLayoutMediator.attach()

        viewPager.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                val badgeDrawable = tabLayout.getTabAt(position)?.orCreateBadge
                badgeDrawable?.isVisible = false
            }
        })

    }
}
