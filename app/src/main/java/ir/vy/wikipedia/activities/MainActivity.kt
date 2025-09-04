package ir.vy.wikipedia.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.menu.MenuBuilder
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.core.view.GravityCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.core.view.get
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import id.ionbit.ionalert.IonAlert
import ir.vy.wikipedia.R
import ir.vy.wikipedia.databinding.ActivityMainBinding
import ir.vy.wikipedia.fragments.FragmentBottomMore
import ir.vy.wikipedia.fragments.FragmentEdits
import ir.vy.wikipedia.fragments.FragmentExplore
import ir.vy.wikipedia.fragments.FragmentSaved
import ir.vy.wikipedia.fragments.FragmentSearch
import ir.vy.wikipedia.fragments.FragmentVideomaker
import ir.vy.wikipedia.function.snackBar


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        enableEdgeToEdge()

        // support from toolbar
        setSupportActionBar(binding.toolBarMain)

        // no hide icons in status bar
        WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars = false

        //------- Navigation Drawer ------
        val drawerToggle = ActionBarDrawerToggle(
            this,
            binding.drawerMain,
            binding.toolBarMain,
            R.string.open_drawer,
            R.string.close_drawer
        )
        binding.drawerMain.addDrawerListener(drawerToggle)

        // show default animation icon drawer
        drawerToggle.syncState()

        val urls = listOf(
            "https://www.wikipedia.org/",
            "https://www.wikimedia.org/"
        )
        // Navigation Drawer (hamburger)
        binding.navigationViewMain.setNavigationItemSelectedListener {

            when (it.itemId) {

                R.id.menu_writer -> {
                    // close drawer
                    binding.drawerMain.closeDrawer(GravityCompat.START)

                    // ionAlert
                    IonAlert(this, IonAlert.WARNING_TYPE)

                        // loading progress
//                        .setSpinKit("Wave")
//                        .setSpinColor("#1976D2")

                        .setTitleText("Do you want to become a writer?")
                        .setContentText("Writing is hard work!")
                        .setConfirmText("Yes, I want to")
                        .setCancelTextSize(24)

                        .setConfirmClickListener { sDialog ->
                            sDialog
                                .setTitleText("You succeeded.")
                                .setContentText("You are now a writer!")
                                .setConfirmText("OK")
                                .setConfirmClickListener(null)
                                .changeAlertType(IonAlert.SUCCESS_TYPE)
                        }
                        .show()
                }

                R.id.menu_photographer -> {
                    // close drawer
                    binding.drawerMain.closeDrawer(GravityCompat.START)

//                    Snackbar
//                        .make(binding.root, "Do You Want Photographer?", Snackbar.LENGTH_INDEFINITE)
//                        .setAction("Retry") {
//                            showToast("Please try again ...")
//                        }
//                        .setActionTextColor(ContextCompat.getColor(this, R.color.white_night))
//                        .setTextColor(ContextCompat.getColor(this, R.color.white_night))
//                        .setBackgroundTint(ContextCompat.getColor(this, R.color.blue_dark))
//                        .show()

                    // fun for show snackBar ( in folder adapter )
                    snackBar(
                        binding.root,
                        "You do not have access!",
                        "Try again",
                        "Access not recognized",
                        R.color.white_night,
                        R.color.white_night,
                        R.color.blue_dark,
                        Snackbar.LENGTH_INDEFINITE,
                        android.view.Gravity.BOTTOM,
                        250
                    )
                }

                R.id.menu_video_maker -> {
                    // load fragment
                    val transAction = supportFragmentManager.beginTransaction()
                    transAction.add(R.id.frame_main, FragmentVideomaker())
                    transAction.addToBackStack(null)
                    transAction.commit()

                    // checkable on
//                    binding.navigationViewMain.menu[2].isCheckable = true

                    // checked
//                    binding.navigationViewMain.menu[2].isChecked = true
//                    binding.navigationViewMain.setCheckedItem(R.id.menu_video_maker)

                    // close drawer
                    binding.drawerMain.closeDrawer(GravityCompat.START)
                }

                //----------our website---------------
                R.id.menu_open_wikipedia -> {
                    // close drawer
                    binding.drawerMain.closeDrawer(GravityCompat.START)

                    openWebsite(urls[0])
                }

                R.id.menu_open_wikimedia -> {
                    // close drawer
                    binding.drawerMain.closeDrawer(GravityCompat.START)

                    openWebsite(urls[1])
                }

            }
            true
        }
        backPressed()

        //------ Navigation Bottom ------
        // Opening Fragment for the first time
        firstRun()

        binding.bottomNavigationMain.setOnItemSelectedListener {

            when (it.itemId) {

                R.id.menu_explore -> {

                    replaceFragment(FragmentExplore())
                }

                R.id.menu_saved -> {

                    replaceFragment(FragmentSaved())
                }

                R.id.menu_search -> {

                    replaceFragment(FragmentSearch())
                }

                R.id.menu_edits -> {

                    replaceFragment(FragmentEdits())

                }

                R.id.menu_more -> {

                    val bottomSheet = FragmentBottomMore()
                    bottomSheet.show(supportFragmentManager, null)
                }
            }
            // check off
            binding.navigationViewMain.menu[2].isCheckable = false
            true
        }
        binding.bottomNavigationMain.setOnItemReselectedListener {
            when (it.itemId) {
                R.id.menu_more -> {
                    val bottomSheet = FragmentBottomMore()
                    bottomSheet.show(supportFragmentManager, null)
                }
            }
        }
    }

    private fun openWebsite(url: String) {

        val openWeb = Intent(Intent.ACTION_VIEW, url.toUri())
        startActivity(openWeb)
    }

    private fun replaceFragment(fragment: Fragment) {

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame_main, fragment)
        transaction.commit()
    }

    private fun firstRun() {

        replaceFragment(FragmentExplore())
        binding.bottomNavigationMain.selectedItemId = R.id.menu_explore
    }

    //----------------------------------------------------------

    // Create Menu
    @SuppressLint("RestrictedApi")
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.menu_main, menu)

        // show icon in menu
        if (menu is MenuBuilder) {
            menu.setOptionalIconsVisible(true)
        }
        return true
    }

    // urls menu
    private val urlsMenu = listOf(
        "https://meta.wikimedia.org/wiki/Main_Page",
        "https://www.wikifunctions.org/wiki/Wikifunctions:Main_Page",
    )

    // Click item menu
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {

            // item menu main
            R.id.open_map -> {

                val snackBar = Snackbar.make(
                    binding.frameMain,
                    "Sorry, Can not open the map!",
                    Snackbar.LENGTH_SHORT
                )
                snackBar.view.layoutParams =
                    (snackBar.view.layoutParams as CoordinatorLayout.LayoutParams)
                        .apply {
                            gravity = android.view.Gravity.TOP
                            topMargin = 164
                            leftMargin = 42
                            rightMargin = 42
                        }
                snackBar.setBackgroundTint(ContextCompat.getColor(this, R.color.red))
                    .setTextColor(ContextCompat.getColor(this, R.color.white_night))
                snackBar.show()
            }

            R.id.exit -> {
                Toast.makeText(this, "See You Later ;)", Toast.LENGTH_SHORT).show()
                finish()
            }

            // item menu wikis
            R.id.wikiMeta -> {
                openWebsite(urlsMenu[0])
            }

            R.id.wikiFunctions -> {
                openWebsite(urlsMenu[1])
            }
        }
        return true
    }

    //----------------------------------------------------------------
    /**
     * The item is clicked and the navigation closes, and when you try to open it again, you see that it is not in the clicked state.
     */
    private fun backPressed() {

        // onBackPressed ( new method )
        onBackPressedDispatcher.addCallback(this, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                finish()
            }
        })

        // checkable off
        binding.navigationViewMain.menu[2].isCheckable = false
    }
}