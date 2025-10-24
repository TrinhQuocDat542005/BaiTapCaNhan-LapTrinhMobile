package com.example.btvn01.models

/**
 * Book class với fields private (Encapsulation).
 * Dùng getter/setter để bảo vệ dữ liệu.
 */
class Book(
    private var id: Int,
    private var title: String,
    private var author: String? = null
) {
    fun getId(): Int = id
    fun setId(v: Int) { id = v }

    fun getTitle(): String = title
    fun setTitle(v: String) { title = v }

    fun getAuthor(): String? = author
    fun setAuthor(v: String?) { author = v }

    override fun toString(): String {
        return "Book(id=$id, title='$title', author='${author ?: "Unknown"}')"
    }
}
