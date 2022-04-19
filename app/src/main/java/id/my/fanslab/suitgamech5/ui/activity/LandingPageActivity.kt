package id.my.fanslab.suitgamech5.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import id.my.fanslab.suitgamech5.action.OnDataPass
import id.my.fanslab.suitgamech5.databinding.ActivityLandingPageBinding
import id.my.fanslab.suitgamech5.ui.adapter.LandingAdapter
import id.my.fanslab.suitgamech5.ui.fragment.FirstLandingFragment
import id.my.fanslab.suitgamech5.ui.fragment.SecondLandingFragment
import id.my.fanslab.suitgamech5.ui.fragment.ThirdLandingFragment

class LandingPageActivity : AppCompatActivity(), OnDataPass {

    private var _binding: ActivityLandingPageBinding? = null
    private val binding get() = _binding
    private val fragmentList = ArrayList<Fragment>()
    private var playerName = ""

    @SuppressLint("ObsoleteSdkInt")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP ) {
            window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        }
        _binding = ActivityLandingPageBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        val adapter = LandingAdapter(this)
        binding?.vpLanding?.adapter = adapter
        fragmentList.addAll(listOf(
            FirstLandingFragment(), SecondLandingFragment(), ThirdLandingFragment()
        ))
        adapter.setFragment(fragmentList)
        binding?.indicatorLayout?.setIndicatorCount(adapter.itemCount)
        binding?.indicatorLayout?.selectCurrentPosition(0)
        registerListeners()
    }

    private fun registerListeners(){
        binding?.vpLanding?.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                binding?.indicatorLayout?.selectCurrentPosition(position)
                if (position < fragmentList.lastIndex) {
                    binding?.imgNext?.visibility = View.GONE
                } else {
                    binding?.imgNext?.visibility = View.VISIBLE
                }
            }
        })

        binding?.imgNext?.setOnClickListener {
            val position = binding?.vpLanding?.currentItem

            if (position != null) {
                if (position < fragmentList.lastIndex) {
                    binding?.vpLanding?.currentItem = position + 1
                } else {
                    val intent = Intent(this, ChooseEnemyActivity::class.java)
                    intent.putExtra(ChooseEnemyActivity.PLAYER, playerName)
                    startActivity(intent)
                    finish()
                }
            }
        }
    }

    override fun onDataPass(playerName: String) {
        this.playerName = playerName
    }
}