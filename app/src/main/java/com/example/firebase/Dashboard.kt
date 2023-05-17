package com.example.firebase

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.TextView
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.replace
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.navigation.NavigationView
import java.lang.Math.abs

class Dashboard : AppCompatActivity() {
    private val webView: WebView? = null
    lateinit var drawerLayout: DrawerLayout
    lateinit var toggle : ActionBarDrawerToggle
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.dashboard)
         drawerLayout = findViewById(R.id.drawerLayout)
        val nav_view : NavigationView = findViewById(R.id.nav_view)

        toggle= ActionBarDrawerToggle(this , drawerLayout,R.string.open,R.string.close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        var isadmin = intent.getBooleanExtra("isadmin",false)
         if (isadmin){
             nav_view.menu.findItem(R.id.nav_notice).setVisible(true)
         }
        else {
             nav_view.menu.findItem(R.id.nav_notice).setVisible(false)
         }

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
       val webView = findViewById<WebView>(R.id.webView)
        webView.webViewClient = WebViewClient()
        webView.loadUrl("https://www.skit.ac.in/")
        val webSettings = webView.settings
        webSettings.javaScriptEnabled = true

       var shared = getSharedPreferences("Data", Context.MODE_PRIVATE)

        var uname = shared.getString("uname","")
        var umail = shared.getString("umail","")
        Log.e("TAG",uname.toString())
        var view = nav_view.getHeaderView(0)
        view.findViewById<TextView>(R.id.user_name).setText(uname)
        view.findViewById<TextView>(R.id.tx_mail).setText(umail)
        nav_view.setNavigationItemSelectedListener {
            it.isChecked=true
            when(it.itemId){
                R.id.nav_dashboard-> {
                    var intent = Intent(this, Dashboard::class.java)
                    startActivity(intent)
                    finish()
                }
                R.id.nav_profile-> replaceFragments(ProfileFragment())
                R.id.nav_notice-> replaceFragments(NoticeFragment())
                R.id.nav_viewNotice-> replaceFragments(ViewNotice())
                R.id.nav_skit->replaceFragments(SkitFragment())
                R.id.nav_logout-> {
                   var shared = getSharedPreferences("Data",Context.MODE_PRIVATE)
                    shared.edit().clear().commit()
                    var intent = Intent(this , Login::class.java)
                    startActivity(intent)

                }
            }
            true
        }
    }

    private fun replaceFragments(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frameLayout,fragment)
        fragmentTransaction.addToBackStack(fragment.toString())
        fragmentTransaction.commit()
        drawerLayout.closeDrawers()
    }
    override fun onBackPressed() {

        var num = supportFragmentManager.backStackEntryCount
        if (num>0)
            supportFragmentManager.popBackStack()
            super.onBackPressed()

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(toggle.onOptionsItemSelected(item)){
            return true
        }
        return super.onOptionsItemSelected(item)
    }

}