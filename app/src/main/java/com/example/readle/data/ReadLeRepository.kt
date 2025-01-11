import android.content.Context
import androidx.room.Room
import androidx.room.Room.databaseBuilder
import com.example.readle.data.Book
import com.example.readle.data.ReadLeDatabase
import com.example.readle.data.User

interface ReadLeRepository {
    suspend fun insertBook(book: Book)
    suspend fun deleteBook(book: Book)
    suspend fun getBook(isbn: String) :Book


    suspend fun insertUser(user: User)
    suspend fun deleteUser(id: Int)
    suspend fun getUser(id: Int)
    suspend fun authenticateUser(username: String, password: String): User?
}