package com.example.firebaseratingapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import com.google.firebase.database.*

class MainActivity : AppCompatActivity() {

    lateinit var edt_text: EditText
    lateinit var ratingBar: RatingBar
    lateinit var btn_save: Button
    lateinit var listView: ListView

    lateinit var RateList:MutableList<Rate>
    lateinit var ref:DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        RateList= mutableListOf()

        ref= FirebaseDatabase.getInstance().getReference("rates")

        edt_text=findViewById(R.id.edt_text)
        ratingBar=findViewById(R.id.ratingbar)
        btn_save=findViewById(R.id.btn_save)
        listView=findViewById(R.id.listView)


        btn_save.setOnClickListener {
            saveRate()
        }
        ref.addValueEventListener(object: ValueEventListener {
            override fun onCancelled(p0: DatabaseError) {
                TODO("Not yet implemented")
            }

            override fun onDataChange(p0: DataSnapshot) {
                if (p0.exists()){
                    RateList.clear()
                    for (h in p0.children){
                        val Rate= h.getValue(Rate::class.java)
                        if (Rate != null) {
                            RateList.add(Rate)
                        }
                    }
                    val adapter=RateAdapter(applicationContext,R.layout.rates,RateList)
                    listView.adapter= adapter
                }
            }


        })

    }
    private fun saveRate(){

        val name = edt_text.text.toString().trim()

        if (name.isEmpty()){

            edt_text.error="Please fill in the name"
            return
        }

        //val rate = Rate(name,ratingBar.numStars)

        val rateid= ref.push().key.toString()

        val rate= Rate(rateid,name,ratingBar.numStars)

        ref.child(rateid).setValue(rate).addOnCompleteListener {
            Toast.makeText(this, "saved your rate",Toast.LENGTH_LONG).show()
        }


    }
}