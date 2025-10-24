package com.example.btvn01.models

/**
 * Lớp trừu tượng Person - thể hiện Abstraction.
 * Các thuộc tính private => Encapsulation.
 */
abstract class Person(
    private var id: Int,
    private var fullName: String
) {
    fun getId(): Int = id
    fun setId(newId: Int) { id = newId }

    fun getFullName(): String = fullName
    fun setFullName(newName: String) { fullName = newName }

    // Abstraction: bắt con lớp implement phương thức này
    abstract fun getRole(): String

    // Polymorphism: con lớp override phương thức này theo cách riêng
    open fun getSummary(): String {
        return "${getRole()}: $fullName (id=$id)"
    }
}
