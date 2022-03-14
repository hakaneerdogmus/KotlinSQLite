package com.hakan.eerdogmus.kotlin_sqlite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hakan.eerdogmus.kotlin_sqlite.databinding.ActivityMainBinding
import java.lang.Exception

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        try {

            val myDatabase = this.openOrCreateDatabase("Musicians", MODE_PRIVATE,null)

                                    //Bir tablo oluştur eğer yoksa ve adını musicians koy (name ve age değerleri olacak)
            myDatabase.execSQL("CREATE TABLE IF NOT EXISTS musicians (id INTEGER PRIMARY KEY , name VARCHAR, age INT)")

            //Ekleme yapmarken
                                    //musicians içine name ve age ata ismi james yaşı 50.
            //  myDatabase.execSQL("INSERT INTO musicians (name, age) VALUES ('James' , 50) ")
            //   myDatabase.execSQL("INSERT INTO musicians (name, age) VALUES ('Hakan' , 24) ")
            // myDatabase.execSQL("INSERT INTO musicians (name, age) VALUES ('Emine' , 22) ")

            // bu kodu silsekte bir kez kayıt aldık hep bellekte tutuluyor.





                 //myDatabase seç *(herşeyi) musiansdan  WHERE Filtreme kısmı burada yapılır
         //   val cursor = myDatabase.rawQuery("SELECT * FROM musicians WHERE id = 3 ", null)

            //WHERE name LIKE '%n' burada name in sonu n ile bitenler demek eğer H% olsaydı H ile başlayanlar demektir.
            //val cursor = myDatabase.rawQuery("SELECT * FROM musicians WHERE name LIKE '%n' ", null)


            //Güncelleme yapmak

            val cursor = myDatabase.rawQuery("SELECT * FROM musicians",null)
            //myDatabase.execSQL("UPDATE musicians SET age = 61 WHERE name = 'James'")
            myDatabase.execSQL("UPDATE musicians SET name = 'Kirk' WHERE name = 'James' ")

            //Silme
            myDatabase.execSQL("DELETE FROM musicians WHERE name = 'Kirk' ")








            //name ve age hangi indexlerde olduğunu gösterir.
            val nameIx = cursor.getColumnIndex("name")
            val ageIx = cursor.getColumnIndex("age")
            val idIx = cursor.getColumnIndex("id")

            //cursor gezebildiği kadar gezsin bütün hücrelere uğrasın
            while (cursor.moveToNext()){
                println("Name: " + cursor.getString(nameIx))
                println("Age: " + cursor.getInt(ageIx))
                println("id: " + cursor.getInt(idIx))

            }

            cursor.close()




        }catch (e:Exception){
            e.printStackTrace()
        }







    }
}