package ca.ulaval.ima.tp2

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import ca.ulaval.ima.tp2.ui.abacus.AbacusFragment
import ca.ulaval.ima.tp2.ui.information.AboutFragment
import ca.ulaval.ima.tp2.ui.internet.InternetFragment
import ca.ulaval.ima.tp2.ui.formulaire.UserFormFragment
import ca.ulaval.ima.tp2.ui.profil.ProfilFragment

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)


        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)

        val toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()


        val navView: NavigationView = findViewById(R.id.nav_view)
        navView.setNavigationItemSelectedListener(this)
        //supportFragmentManager.beginTransaction().replace(R.id.nav_host_fragment, AboutFragment())
        this.initiateFragment(AboutFragment())
    }
    fun initiateFragment(fg : Fragment){
        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
        ft.replace(R.id.nav_host_fragment, fg)
        ft.commit()
    }
    override fun onBackPressed() {
        val drawer = findViewById<View?>(R.id.drawer_layout) as DrawerLayout
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val id = item.getItemId()
        if (id == R.id.nav_signal) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.nav_host_fragment, InternetFragment())
            transaction.commit()
        } else if (id == R.id.nav_info) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.nav_host_fragment, AboutFragment())
            transaction.commit()
        } else if (id == R.id.nav_Abacus) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.nav_host_fragment, AbacusFragment())
            transaction.commit()
        }
        else if (id == R.id.nav_form) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.nav_host_fragment, UserFormFragment())
            transaction.commit()
        }
        else if (id == R.id.nav_profil) {
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.nav_host_fragment, ProfilFragment())
            transaction.commit()
        }
        val drawer = findViewById<View?>(R.id.drawer_layout) as DrawerLayout
        drawer.closeDrawer(GravityCompat.START)
        return true

    }
    fun setBurgerButton(){
        if (supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true);
        }
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        val drawer = findViewById<View?>(R.id.drawer_layout) as DrawerLayout
        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
        val toggle = ActionBarDrawerToggle(this, drawer,
            toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState()
    }

    fun setBackButton(){

        val drawer = findViewById<View?>(R.id.drawer_layout) as DrawerLayout
        drawer.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
        val toolBar: Toolbar = findViewById(R.id.toolbar)
        toolBar.setNavigationOnClickListener {
            onBackPressed()
            this@MainActivity.setBurgerButton()
        }
        if (supportActionBar != null) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true);
        }

    }

}