package com.example.datasql_lite

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.SQLException
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class db(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_V) {

    companion object {
        private const val DATABASE_NAME = "saveDB"
        private const val DATABASE_V = 2
        private const val TABLE_DB = "savetable"

        private const val KEY_ID = "_id"
        private const val KEY_NUMBER = "number"
        private const val KEY_TITLE = "title"
        private const val KEY_DESC = "desc"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE_CONTACTS =
            ("CREATE TABLE " + TABLE_DB + "(" + KEY_ID + " INTEGER PRIMARY KEY, "
                    + KEY_NUMBER + " TEXT, " + KEY_TITLE + " TEXT, " + KEY_DESC + " TEXT " + ")")

        db?.execSQL(CREATE_TABLE_CONTACTS)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS " + TABLE_DB)
        onCreate(db)
    }

    fun addData(emp: modelclass): Long {
        val db = this.writableDatabase

        val contentValue = ContentValues()
        contentValue.put(KEY_NUMBER, emp.artNumber)
        contentValue.put(KEY_TITLE, emp.artTitle)
        contentValue.put(KEY_DESC, emp.artDes)

        val success = db.insert(TABLE_DB, null, contentValue)

        db.close()
        return success
    }


    @SuppressLint("Range", "Recycle")
    fun viewData(): ArrayList<modelclass> {
        val empList: ArrayList<modelclass> = ArrayList()

        val select = "SELECT * FROM $TABLE_DB"
        val db = this.readableDatabase
        var cursor: Cursor? = null

        try {
            cursor = db.rawQuery(select, null)
        } catch (e: SQLException) {
            db.execSQL(select)
            return ArrayList()
        }

        var id: Int
        var name: String
        var title: String
        var desc: String
        if (cursor.moveToFirst()) {
            do {
                id = cursor.getInt(cursor.getColumnIndex(KEY_ID))
                name = cursor.getString(cursor.getColumnIndex(KEY_NUMBER))
                title = cursor.getString(cursor.getColumnIndex(KEY_TITLE))
                desc = cursor.getString(cursor.getColumnIndex(KEY_DESC))

                val emp = modelclass(id, name, title, desc)
                empList.add(emp)
            } while (cursor.moveToNext())
        }
        return empList
    }

    fun updateData(emp: modelclass): Int {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_NUMBER, emp.artNumber) // EmpModelClass Name
        contentValues.put(KEY_TITLE, emp.artTitle) // EmpModelClass Email
        contentValues.put(KEY_DESC, emp.artDes) // EmpModelClass Email

        // Updating Row
        val success = db.update(TABLE_DB, contentValues, KEY_ID + "=" + emp.id, null)
        //2nd argument is String containing nullColumnHack

        db.close() // Closing database connection
        return success
    }

    fun deleteEmployee(emp: modelclass): Int {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(KEY_ID, emp.id) // EmpModelClass id
        // Deleting Row
        val success = db.delete(TABLE_DB, KEY_ID + "=" + emp.id, null)
        //2nd argument is String containing nullColumnHack
        db.close() // Closing database connection
        return success
    }

}