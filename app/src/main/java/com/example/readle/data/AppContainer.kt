import android.content.Context
import com.example.readle.data.ReadLeDatabase
import com.example.readle.data.ReadLeRepositoryImpl


interface AppContainer {
    val readLeRepository: ReadLeRepository
}

class AppDataContainer (private val context: Context) : AppContainer{

    override val readLeRepository: ReadLeRepository by lazy {
        ReadLeRepositoryImpl(
            ReadLeDatabase.getDatabase(context).userDao(),
            ReadLeDatabase.getDatabase(context).favoriteDao(),
            ReadLeDatabase.getDatabase(context).bookDao(),
            ReadLeDatabase.getDatabase(context).authorDao()
        )
    }

}