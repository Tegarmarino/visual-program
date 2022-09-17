package com.example.week1



import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.week1.database.universalVar
import com.example.week1.databinding.ActivityResultactivityBinding
import com.example.week1.model.animals
import com.google.android.material.snackbar.Snackbar

class Resultactivity : AppCompatActivity() {
    private lateinit var viewBind: ActivityResultactivityBinding
    private var states: Int = -1
    private val result = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if (it.resultCode == Activity.RESULT_OK){
            val uri = it.data?.data
            viewBind.imageView4.setImageURI(uri)
            universalVar.listAnimals[states].imageUri = uri.toString()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBind = ActivityResultactivityBinding.inflate(layoutInflater)
        setContentView(viewBind.root)
        supportActionBar?.hide()
        intents()
        listeners()
    }

    private fun listeners(){

        viewBind.Delete.setOnClickListener {
            val builders = AlertDialog.Builder(this)
            builders.setTitle("Delete movie")
            builders.setMessage("Are you sure you want to delete this movie?")


            builders.setPositiveButton(android.R.string.yes) { function, which ->
                val snackbars = Snackbar.make(viewBind.Delete, "Movie Deleted", Snackbar.LENGTH_INDEFINITE)
                snackbars.setAction("Dismiss") { snackbars.dismiss() }
                snackbars.setActionTextColor(Color.WHITE)
                snackbars.setBackgroundTint(Color.GRAY)
                snackbars.show()

                //remove
                universalVar.listAnimals.removeAt(states)
                finish()
            }

            builders.setNegativeButton(android.R.string.no) { dialog, which ->
                Toast.makeText(applicationContext,
                    android.R.string.no, Toast.LENGTH_SHORT).show()
            }
            builders.show()
        }

//        viewBind.toolbar.getChildAt(1).setOnClickListener {
//            finish()
//        }

        viewBind.floatingActionButton.setOnClickListener {
            val intens = Intent(this, addanimals::class.java).apply {
                putExtra("state", states)
            }
            startActivity(intens)
        }
    }

    private fun intents(){
        states = intent.getIntExtra("state", -1)
        val animals = universalVar.listAnimals[states]
        if(!animals.imageUri.isEmpty())
        {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                baseContext.getContentResolver().takePersistableUriPermission(Uri.parse(universalVar.listAnimals[states].imageUri),
                    Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION
                )
            }
        }
        show(animals)
    }
    override fun onResume() {
        val animals = universalVar.listAnimals[states]
        super.onResume()

        show(animals)
    }


    private fun show(animals: animals){
        viewBind.imageView4.setImageURI(Uri.parse(universalVar.listAnimals[states].imageUri))
        viewBind.animalName.text = animals.name
        viewBind.animalType.text = animals.animalType
        viewBind.animalAge.text = animals.age
    }

    private fun delete(){

    }


}