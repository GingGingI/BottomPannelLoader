package gingdev.bottompannelloader

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomSheetBehavior
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.RelativeLayout
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.bottom_sheet.*
import kotlin.math.PI

class MainActivity : AppCompatActivity() {

    private lateinit var bottomsSheetBehavior: BottomSheetBehavior<RelativeLayout>
    private lateinit var progressbar: ProgressBar
    private var cnt = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomsSheetBehavior = BottomSheetBehavior.from(findViewById(R.id.bottomSheet))
        progressbar = findViewById(R.id.progress)

        bottomsSheetBehavior.setBottomSheetCallback( object : BottomSheetBehavior.BottomSheetCallback() {
            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                bottomSheet.scaleX = 0.8f + ((Math.cos(PI * (slideOffset+1)) / 2 + 0.5).toFloat() * 0.2f)
//                Log.i("Slide", (slideOffset * 100).toInt().toString())
                progressbar.alpha = (Math.cos(PI * (slideOffset+1)) / 2 + 0.5).toFloat()
                bg.alpha = (Math.cos(PI * (slideOffset+1)) / 2 + 0.5).toFloat()
                dragme.alpha = 1f - (Math.cos(PI * (slideOffset+1)) / 2 + 0.5).toFloat()
                Log.i("alpha", progressbar.alpha.toString()+" , "+cnt)
                cnt++
//                cos((t+1)π)/2+0.5
//                ALPHA_FULL - Math.abs(dX) / holder.itemView.getWidth().toFloat()
            }
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                if (newState == BottomSheetBehavior.STATE_EXPANDED) {
                }
            }
        })

        fab.setOnClickListener { v ->
            if (bottomsSheetBehavior.state != BottomSheetBehavior.STATE_COLLAPSED) {
//                bottomsSheetBehavior.peekHeight = actionBar.height
                bottomsSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
            }else {
//                bottomsSheetBehavior.peekHeight = 0
                bottomsSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
            }
        }
    }

    override fun onBackPressed() {
        if (bottomsSheetBehavior.state != BottomSheetBehavior.STATE_HIDDEN
                && bottomsSheetBehavior.state != BottomSheetBehavior.STATE_COLLAPSED) {
            bottomsSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
        }else {
            super.onBackPressed()
        }

    }
}
