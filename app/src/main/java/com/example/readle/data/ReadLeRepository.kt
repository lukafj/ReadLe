import com.example.readle.data.Book
import com.example.readle.data.User
import kotlinx.coroutines.flow.Flow

interface ReadLeRepository {
    suspend fun insertBook(book: Book)
    suspend fun deleteBook(book: Book)
    suspend fun getBook(isbn: String) : Flow<Book>


    suspend fun insertUser(user: User)
    suspend fun deleteUser(userId: Int)
    suspend fun getUser(userId: Int) :Flow<User?>
    suspend fun authenticateUser(username: String, password: String): Flow<User?>
}