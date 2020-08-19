# ViewPager2 & TabLayout

## AndroidX Dependencies

```
implementation 'com.google.android.material:material:1.3.0-alpha02'
```

## Set ViewPager & TabLayout on activity_main.xml

```
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    // Divider below TabLayout
    <View
        android:layout_width="match_parent"
        android:layout_height="3dp"
        android:background="@color/colorDivider"
        app:layout_constraintBottom_toBottomOf="@+id/tabLayout" />

    // TabLayout
    <com.google.android.material.tabs.TabLayout
        android:id="@+id/tabLayout"
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:background="@android:color/transparent"
        app:layout_constraintTop_toTopOf="parent"
        app:tabIndicatorHeight="3dp"
        app:tabMode="fixed"
        app:tabPaddingBottom="8dp"
        app:tabPaddingTop="15dp" />

    // ViewPager
    <androidx.viewpager2.widget.ViewPager2
        android:id="@+id/viewPager"
        android:layout_width="match_parent"
        android:layout_height="0dp"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintTop_toBottomOf="@id/tabLayout"/>

</androidx.constraintlayout.widget.ConstraintLayout>
```

## Create The Adapter

```
class OrdersPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int {
        return 3
    }

    override fun createFragment(position: Int): Fragment {

        return when (position) {
            0 -> PendingOrdersFragment()
            1 -> ConfirmedOrdersFragment()
            else -> DeliveredOrdersFragment()
        }

    }
}
```

## Set The Adapter on MainActivity

```
viewPager.adapter = OrdersPagerAdapter(this)
```

## Add TabLayoutMediator

```
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
            }
        }
    }
)

tabLayoutMediator.attach()
```

## Add BadgeDrawable

```
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
```

## Add registerOnPageChangeCallback to Remove the Badge

```
viewPager.registerOnPageChangeCallback(object: ViewPager2.OnPageChangeCallback() {
    override fun onPageSelected(position: Int) {
        super.onPageSelected(position)
        val badgeDrawable = tabLayout.getTabAt(position)?.orCreateBadge
        badgeDrawable?.isVisible = false
    }
})
```

## App Demo
<img src="https://i.gyazo.com/cad43a884dd2ca424eeb924ea710da8b.gif" width="350px" height="650px" />
