package com.example.democratics.category

object AddCategory {

    fun getCategory(): ArrayList<category> {

        var category1 = ArrayList<category>()

        val Q = category(1, "national")
        category1.add(Q)

        val Q1 = category(2, "business")
        category1.add(Q1)

        val Q2 = category(3, "sports")
        category1.add(Q2)

        val Q3 = category(4, "world")
        category1.add(Q3)

        val Q4 = category(5, "politics")
        category1.add(Q4)

        val Q5 = category(6, "technology")
        category1.add(Q5)

        val Q6 = category(7, "science")
        category1.add(Q6)


        return category1
    }
}