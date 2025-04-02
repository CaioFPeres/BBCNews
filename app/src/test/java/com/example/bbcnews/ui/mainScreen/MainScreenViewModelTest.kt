import com.example.bbcnews.data.remote.RetrofitClient
import com.example.bbcnews.data.repository.NewsRepositoryImpl
import com.example.bbcnews.ui.mainScreen.MainScreenViewModel
import com.example.bbcnews.ui.newsScreen.NewsUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Test
import java.time.Instant
import java.time.format.DateTimeFormatter

@ExperimentalCoroutinesApi
class MainScreenViewModelTest {
    private lateinit var retrofitClient: RetrofitClient
    private lateinit var repositoryImpl: NewsRepositoryImpl
    private lateinit var mainScreenViewModel: MainScreenViewModel
    private val testDispatcher = UnconfinedTestDispatcher()
    private var url = "https://newsapi.org/v2/"

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)

        retrofitClient = RetrofitClient(url)
        repositoryImpl = NewsRepositoryImpl(retrofitClient)
        mainScreenViewModel = MainScreenViewModel(repositoryImpl)
    }

    @Test
    fun `fetchNews should emit Loading state initially`() = runBlocking {
        assert(mainScreenViewModel.uiState.value is NewsUiState.Loading)
    }

    @Test
    fun `fetchNews updates state to Error when repository call fails`() = runTest {

        val _url = "https://awdawdawdawdawdawd/"
        retrofitClient = RetrofitClient(_url)
        repositoryImpl = NewsRepositoryImpl(retrofitClient)
        mainScreenViewModel = MainScreenViewModel(repositoryImpl)

        Thread.sleep(3000)
        assert(mainScreenViewModel.uiState.value is NewsUiState.Error)
    }

    @Test
    fun `fetchNews should emit Success state with sorted news`(): Unit = runBlocking {
        Thread.sleep(1000)

        val news = (mainScreenViewModel.uiState.value as NewsUiState.Success).news
        var descending: Boolean = true
        var last = Instant.from(
            DateTimeFormatter.ISO_DATE_TIME.parse(news.articles[0].publishedAt)).toEpochMilli()

        for(i in 1..< news.totalResults)
            if(Instant.from(
                    DateTimeFormatter.ISO_DATE_TIME.parse(
                        news.articles[i].publishedAt)
                    ).toEpochMilli() > last
                ){
                descending = false
                break
            }
            else{
                last = Instant.from(
                    DateTimeFormatter.ISO_DATE_TIME.parse(
                        news.articles[i].publishedAt)
                ).toEpochMilli()
            }

        assert(mainScreenViewModel.uiState.value is NewsUiState.Success)
        assert(descending)
    }
}