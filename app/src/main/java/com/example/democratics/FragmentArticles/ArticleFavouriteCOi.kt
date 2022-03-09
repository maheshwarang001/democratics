package com.example.democratics.FragmentArticles

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.datasql_lite.db
import com.example.datasql_lite.modelclass
import com.example.democratics.R
import com.example.democratics.adapterCOI.CoiAdpaterFav
import kotlinx.android.synthetic.main.favourtie_art_coi.*

class ArticleFavouriteCOi : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.favourtie_art_coi)
        setUpDataForRecycler()
    }


     fun setUpDataForRecycler() {
        if (getItemList().size > 0) {
            No_data_visible.visibility = View.GONE
            recycler_art_coi.visibility = View.VISIBLE

            recycler_art_coi.layoutManager = LinearLayoutManager(this)
            recycler_art_coi.adapter = CoiAdpaterFav(this, getItemList())
        } else {
            No_data_visible.visibility = View.VISIBLE
            recycler_art_coi.visibility = View.GONE
        }
    }

    private fun getItemList(): ArrayList<modelclass> {
        val databaseHandler: db = db(this)
        val emplist: ArrayList<modelclass> = databaseHandler.viewData()
        return emplist
    }

    fun deleteRecord(emp: modelclass) {
        val builder = AlertDialog.Builder(this)
        builder.setIcon(R.drawable.ic_baseline_warning_24)
        builder.setTitle("Delete Record")
        builder.setMessage("Are you sure you want to delete ${emp.artTitle}")

        builder.setPositiveButton("Yes") { DialogInterface, _ ->
            val databasehandler: db = db(this)

            val status = databasehandler.deleteEmployee(modelclass(emp.id, "", "",""))

            if (status > -1) {
                Toast.makeText(
                    applicationContext,
                    "Record deleted successfully.",
                    Toast.LENGTH_SHORT
                ).show()

                setUpDataForRecycler()
            }
            DialogInterface.dismiss()
        }
        builder.setNegativeButton("No") { DialogInterface, which ->
            DialogInterface.dismiss()
        }

        //create alert box
        val alertbox: AlertDialog = builder.create()
        alertbox.setCancelable(false)
        alertbox.show()
    }

}