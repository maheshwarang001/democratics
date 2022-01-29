package com.example.democratics

object DataConstant{

fun getData(): ArrayList<info>{

    var infoData = ArrayList<info>()

    val animal1 = info(
        1 ,
        R.drawable.ashok2,
        "Preamble",
    )
    infoData.add(animal1)

    val animal2 = info(
        2 ,
        R.drawable.ashok2,
        "Parts",
    )
    infoData.add(animal2)

    val animal3 = info(
        3 ,
        R.drawable.ashok2,
        "Schedule",

    )
    infoData.add(animal3)

    val animal4 = info(
        4 ,
        R.drawable.ashok2,
        "Amendments",
    )
    infoData.add(animal4)

    val animal5 = info(
        5 ,
        R.drawable.ashok2,
        "Miscellaneous",

    )
    infoData.add(animal5)

    return infoData
 }
}