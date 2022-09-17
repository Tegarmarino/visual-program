package com.example.week1


import Interface.cardListener
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.week1.Adapter.recycleAdapter
import com.example.week1.database.universalVar
import com.example.week1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), cardListener {

//    Inisialisasi property Main Activity
    private lateinit var viewBinders:ActivityMainBinding
    private val adapters = recycleAdapter(universalVar.listAnimals, this)
    private var animalTotal: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinders = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinders.root)
        supportActionBar?.hide()
        
        setupRecyclerView()
        listener()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == universalVar.STORAGE_PERMISSION_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Storage Permission Granted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Storage Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onResume() {
        super.onResume()
        animalTotal = universalVar.listAnimals.size
        if(animalTotal == 0)
        {
            viewBinders.textView2.alpha = 1f
        }else
        {
            viewBinders.textView2.alpha = 0f
        }
        adapters.notifyDataSetChanged()
    }




    private fun listener(){
        viewBinders.buttonAddAnimals.setOnClickListener {
            val myIntent = Intent(this, addanimals::class.java)
            startActivity(myIntent)
        }


    }

    private fun setupRecyclerView(){
        val layoutManager = GridLayoutManager(this,2)
        viewBinders.listdata.layoutManager = layoutManager   // Set layout
        viewBinders.listdata.adapter = adapters   // Set adapter
    }

    override fun onClick(position: Int) {
        val myIntent = Intent(this, Resultactivity::class.java).apply {
            putExtra("state", position)
        }
        startActivity(myIntent)
    }
}