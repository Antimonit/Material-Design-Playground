package me.khol.materialdesign

import android.content.res.ColorStateList
import android.content.res.Configuration
import android.graphics.Paint
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.chip.Chip
import com.google.android.material.shape.MaterialShapeDrawable
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.widgets.*

class MainActivity : AppCompatActivity() {

    companion object {
        private var customTheme = R.style.Theme_Custom
    }

    private val actionBarDrawerToggle by lazy {
        ActionBarDrawerToggle(
            this, drawer_layout, toolbar,
            R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(customTheme)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        birthday_edit_text.setAdapter(
            ArrayAdapter(
                this,
                R.layout.support_simple_spinner_dropdown_item,
                listOf("Camel", "Elephant", "Seal")
            )
        )

        toolbar.background = MaterialShapeDrawable().apply {
            paintStyle = Paint.Style.FILL
            tintList = ColorStateList.valueOf(getAttrColor(R.attr.colorToolbar))
            shadowCompatibilityMode = MaterialShapeDrawable.SHADOW_COMPAT_MODE_ALWAYS
            elevation = resources.getDimension(R.dimen.toolbar_elevation)
            shadowCompatRotation = 180
        }

        fab.setOnClickListener {
            bottom_app_bar.hideOnScroll = !bottom_app_bar.hideOnScroll
            Snackbar.make(coordinator, "Toggled Hide on Scroll", Snackbar.LENGTH_LONG)
                .setAnchorView(fab)
                .setAction("Return") {
                    bottom_app_bar.hideOnScroll = !bottom_app_bar.hideOnScroll
                }.show()
        }

        drawer_layout.addDrawerListener(actionBarDrawerToggle)

        navigation_view.inflateMenu(R.menu.menu_main)
        navigation_view.setNavigationItemSelectedListener(::onOptionsItemSelected)

        custom_chipGroup_selection.addView(Chip(custom_chipGroup_selection.context).apply {
            text = "Dynamic"
        })
    }

    override fun onPostCreate(savedInstanceState: Bundle?) {
        super.onPostCreate(savedInstanceState)
        actionBarDrawerToggle.syncState()
    }

    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        actionBarDrawerToggle.onConfigurationChanged(newConfig)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true
        }

        customTheme = when (item.itemId) {
            R.id.action_default_theme -> R.style.Theme_Custom
            R.id.action_default_dark_theme -> R.style.Theme_Custom_Dark
            R.id.action_sharp_theme -> R.style.Theme_Sharp
            else -> return super.onOptionsItemSelected(item)
        }
        recreate()
        return true
    }
}
