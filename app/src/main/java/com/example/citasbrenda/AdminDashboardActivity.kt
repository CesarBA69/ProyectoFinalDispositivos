package com.example.citasbrenda

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class AdminDashboardActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_dashboard)

        findViewById<Button>(R.id.btnBranches).setOnClickListener {
            startActivity(Intent(this, BranchesActivity::class.java))
        }

        findViewById<Button>(R.id.btnServices).setOnClickListener {
            startActivity(Intent(this, ServicesActivity::class.java))
        }

        findViewById<Button>(R.id.btnProviders).setOnClickListener {
            startActivity(Intent(this, ProvidersActivity::class.java))
        }

        findViewById<Button>(R.id.btnPriceLists).setOnClickListener {
            startActivity(Intent(this, PriceListsActivity::class.java))
        }


    }
}

