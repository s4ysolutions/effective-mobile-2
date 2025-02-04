package com.example.effectivem2.data.retrofit.beans

import com.example.effectivem2.data.dto.Salary

class JsonSalary(private val short: String, private val full: String) {
    val dto: Salary
        get() = Salary(short, full)
}