package com.example.btvn01.models

/**
 * Student kế thừa Person => Inheritance.
 * Student có danh sách sách mượn (mutable list).
 */
class Student(
    id: Int,
    fullName: String
) : Person(id, fullName) {

    // Encapsulation: giữ private list, expose bằng các hàm
    private val borrowedBooks: MutableList<Book> = mutableListOf()

    override fun getRole(): String = "Sinh viên"

    override fun getSummary(): String {
        return "${getRole()}: ${getFullName()} - Đang mượn ${borrowedBooks.size} quyển"
    }

    // Các phương thức xử lý mượn/trả (encapsulation)
    fun borrowBook(book: Book): Boolean {
        // tránh mượn trùng
        if (borrowedBooks.any { it.getId() == book.getId() }) return false
        borrowedBooks.add(book)
        return true
    }

    fun returnBook(bookId: Int): Boolean {
        val idx = borrowedBooks.indexOfFirst { it.getId() == bookId }
        return if (idx >= 0) {
            borrowedBooks.removeAt(idx)
            true
        } else false
    }

    fun getBorrowedBooks(): List<Book> = borrowedBooks.toList()
}
