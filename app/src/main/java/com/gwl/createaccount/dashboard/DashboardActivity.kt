package com.gwl.createaccount.dashboard

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import com.gwl.createaccount.BaseActivity
import com.gwl.createaccount.R
import com.gwl.createaccount.databinding.ActivityDashboardBinding

class DashboardActivity : BaseActivity<ActivityDashboardBinding>(R.layout.activity_dashboard) {

    private lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val navController = findNavController(R.id.nav_host_fragment_content_dashboard)
        appBarConfiguration = AppBarConfiguration(navController.graph)

        binding.fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_dashboard)
        return navController.navigateUp(appBarConfiguration)
                || super.onSupportNavigateUp()
    }
}