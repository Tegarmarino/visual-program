package com.example.week1


import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.week1.database.universalVar
import com.example.week1.databinding.ActivityAddanimalsBinding
import com.example.week1.model.animals

class addanimals : AppCompatActivity() {
    private lateinit var viewBind: ActivityAddanimalsBinding
    private lateinit var animals: animals
    var images: String = ""
    var states = -1

    private val GetResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if (it.resultCode == RESULT_OK){
            val uri = it.data?.data
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                if(uri != null){
                    baseContext.getContentResolver().takePersistableUriPermission(uri,
                        Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION
                    )
                }}
            viewBind.imageView2.setImageURI(uri)
            images = uri.toString()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBind = ActivityAddanimalsBinding.inflate(layoutInflater)
        setContentView(viewBind.root)
        supportActionBar?.hide()
        getintent()
        listener()
    }
    private fun getintent(){
        states = intent.getIntExtra("state", -1)
        if(states != -1){
            val animals = universalVar.listAnimals[states]
            viewBind.toolbars.title = "Edit hewan peternakan"
            viewBind.buttonAdd.text = "Edit"
            viewBind.imageView2.setImageURI(Uri.parse(universalVar.listAnimals[states].imageUri))
            viewBind.inputName.editText?.setText(animals.name)
            viewBind.inputType.editText?.setText(animals.animalType)
            viewBind.inputAge.editText?.setText(animals.age)
        }
    }

    private fun listener(){
        viewBind.imageView2.setOnClickListener{
            val myIntent = Intent(Intent.ACTION_OPEN_DOCUMENT)
            myIntent.type = "image/*"
            GetResult.launch(myIntent)
        }

        viewBind.buttonAdd.setOnClickListener{
            var name = viewBind.inputName.editText?.text.toString().trim()
            var animalType = viewBind.inputType.editText?.text.toString().trim()
            var age = viewBind.inputAge.editText?.text.toString().trim()

            animals = animals(name , animalType , age)
            checker()
        }

//        viewBind.toolbars.getChildAt(1).setOnClickListener {
//            finish()
//        }
    }

    private fun checker()
    {
        var isCompleted:Boolean = true

        if(animals.name!!.isEmpty()){
            viewBind.inputName.error = "Nama hewan tidak boleh kosong !"
            isCompleted = false
        }else{
            viewBind.inputName.error = ""
        }

        if(animals.animalType!!.isEmpty()){
            viewBind.inputType.error = "Jenis hewan tidak boleh kosong"
            isCompleted = false
        }else{
            viewBind.inputType.error = ""
        }

        animals.imageUri = images.toString()


//        if(viewBind.inputName.editText?.text.toString().isEmpty() || viewBind.inputName.editText?.text.toString().toInt() < 0)
//        {
//            viewBind.inputName.error = "rating cannot be empty or 0"
//            isCompleted = false
//        }

        if(isCompleted == true)
        {
            if(states == -1)
            {
                animals.age = viewBind.inputAge.editText?.text.toString().toInt().toString()
                universalVar.listAnimals.add(animals)

            }else
            {
                animals.age = viewBind.inputAge.editText?.text.toString().toInt().toString()
                universalVar.listAnimals[states] = animals
            }
            finish()
        }
    }
}