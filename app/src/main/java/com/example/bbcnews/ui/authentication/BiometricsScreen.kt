import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.bbcnews.ui.authentication.BiometricsState
import com.example.bbcnews.ui.authentication.BiometricsViewModel
import com.example.bbcnews.ui.theme.DarkColorScheme
import com.example.bbcnews.ui.theme.LightColorScheme
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

// Could not mock everything to test this composable
@Composable
fun BiometricScreen(navController: NavHostController) {
    val context = LocalContext.current
    val biometricsViewModel: BiometricsViewModel = koinViewModel { parametersOf(context) }
    val authState by biometricsViewModel.authState.collectAsState()

    biometricsViewModel.launchBiometricPrompt()

    when (authState) {
        is BiometricsState.Success, BiometricsState.NotAvailable -> navController.navigate("MainScreen")

        is BiometricsState.Error -> ErrorCard((authState as BiometricsState.Error).message
                                    + ". You need to authenticate to use the application!")
        is BiometricsState.Failed -> ErrorCard("Authentication failed! "
                + "You need to authenticate to use the application!")

        else -> { IdleScreen() }
    }
}

@Composable
fun IdleScreen(){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = if(isSystemInDarkTheme())
                            LightColorScheme.primary
                        else
                            DarkColorScheme.primary,
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun ErrorCard(msg: String){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = if(isSystemInDarkTheme())
                            LightColorScheme.primary
                        else
                            DarkColorScheme.primary,
            ),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier
                .padding(10.dp)
        ) {
            Text(
                modifier = Modifier.padding(10.dp),
                text = msg
            )
        }
    }
}