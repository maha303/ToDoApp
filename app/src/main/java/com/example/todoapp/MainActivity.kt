package com.example.todoapp

import android.app.AlertDialog
import android.content.DialogInterface
import android.graphics.Insets.add
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var items : ArrayList<ToDoItem>
    private lateinit var myRv :RecyclerView
    private lateinit var rvAdapter : RecyclerViewAdapter
    private lateinit var myfabtn : FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        items = arrayListOf()
        items.add(ToDoItem("Go for Run"))
        items.add(ToDoItem("Take a shower"))
        items.add(ToDoItem("Go to Work"))

        myfabtn = findViewById(R.id.FAbtn)
        myfabtn.setOnClickListener {
            add()
        }

        myRv = findViewById<RecyclerView>(R.id.rvMain)
        rvAdapter = RecyclerViewAdapter(items)
        myRv.adapter=rvAdapter
        myRv.layoutManager = LinearLayoutManager(this)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var itemsDeleted = 0
        for(i in items){
            if(i.checked){itemsDeleted++}
        }

        if(itemsDeleted > 0){
            Toast.makeText(this, "$itemsDeleted items deleted", Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(this, "No items selected", Toast.LENGTH_LONG).show()
        }
            rvAdapter.deleteItems()
        return super.onOptionsItemSelected(item)
    }


     fun add() {

        val dialogBuilder = AlertDialog.Builder(this)

        val input = EditText(this)

        dialogBuilder.setMessage("Enter your items :")
            .setPositiveButton("Add", DialogInterface.OnClickListener {
                    dialog, id -> items.add(ToDoItem(input.text.toString()))
            })
            .setNegativeButton("Cancel", DialogInterface.OnClickListener {
                    dialog, id -> dialog.cancel()
            })
         val alert = dialogBuilder.create()
         alert.setTitle("New Items")
         alert.setView(input)
         alert.show()
    }
}


