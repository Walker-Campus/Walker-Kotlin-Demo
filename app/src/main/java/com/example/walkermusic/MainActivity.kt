package com.example.walkermusic

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.navigation.NavigationView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.walkermusic.adapter.MainListAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {

        //activity create.
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //add toolbar.
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        // add Button.
        val fab: FloatingActionButton = findViewById(R.id.fab)
        fab.setOnClickListener {
            startService(Intent(this, WalkerMusicPlayerService::class.java))
        }

        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow), drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)


        createRecyclerView()


    }

    /**
     * set menu.
     */
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            //toast 弹出提示
            R.id.action_add_button -> Toast.makeText(this, "You add a button",
                                            Toast.LENGTH_SHORT).show()
            R.id.action_remove_button -> Toast.makeText(this, "You remove a button",
                                            Toast.LENGTH_SHORT).show()
            R.id.action_settings -> startActivity(Intent(this, SettingsActivity::class.java))
        }
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }


    /**
     * set recycler view.
     */
    private val dataList = ArrayList<Information>()
    private fun initData() {
        for (i in 1..100) {
            dataList.add(Information("information$i", R.drawable.ic_menu_camera))
        }
    }
    private fun createRecyclerView() {
        initData()
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.run {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = MainListAdapter(dataList)
        }
    }


    /**
     * set music play service.
     */
    private lateinit var binder: WalkerMusicPlayerService.PlayBinder


}