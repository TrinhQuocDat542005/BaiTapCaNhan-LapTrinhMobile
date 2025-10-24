package com.example.btvn01.models

/**
 * LibrarySystem: quản lý danh sách sách và sinh viên, xử lý mượn/trả.
 * Ở đây dùng object để làm singleton đơn giản.
 */
object LibrarySystem {
    private val bookList: MutableList<Book> = mutableListOf()
    private val students: MutableList<Student> = mutableListOf()

    // Các hàm quản lý sách
    fun addBook(book: Book) {
        if (bookList.none { it.getId() == book.getId() }) {
            bookList.add(book)
        }
    }

    fun getAllBooks(): List<Book> = bookList.toList()

    fun findBookById(id: Int): Book? = bookList.find { it.getId() == id }

    // Các hàm quản lý sinh viên
    fun addStudent(student: Student) {
        if (students.none { it.getId() == student.getId() }) {
            students.add(student)
        }
    }

    fun getAllStudents(): List<Student> = students.toList()

    fun findStudentById(id: Int): Student? = students.find { it.getId() == id }

    fun findStudentByName(name: String): Student? = students.find { it.getFullName().equals(name, ignoreCase = true) }

    // Logic mượn: trả boolean (thành công hay không)
    fun borrowBook(studentId: Int, bookId: Int): Boolean {
        val st = findStudentById(studentId) ?: return false
        val book = findBookById(bookId) ?: return false
        return st.borrowBook(book)
    }

    fun returnBook(studentId: Int, bookId: Int): Boolean {
        val st = findStudentById(studentId) ?: return false
        return st.returnBook(bookId)
    }
}
