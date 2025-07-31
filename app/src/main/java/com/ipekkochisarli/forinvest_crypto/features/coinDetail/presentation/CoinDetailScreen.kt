import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.ipekkochisarli.forinvest_crypto.features.coinDetail.domain.CoinChartTimeSpan
import com.ipekkochisarli.forinvest_crypto.features.coinDetail.presentation.components.CoinDetailContent
import com.ipekkochisarli.forinvest_crypto.features.coinDetail.presentation.CoinDetailViewModel

@Composable
fun CoinDetailScreen(
    coinId: String,
    viewModel: CoinDetailViewModel = hiltViewModel(),
    onBackClick: () -> Unit
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(coinId) {
        viewModel.loadCoinDetail(coinId)
        viewModel.loadChartData(coinId, CoinChartTimeSpan.TIMESPAN_7DAYS)
    }

    when {
        state.isLoading -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        }

        state.errorMessage != null -> {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                Text("Error: ${state.errorMessage}")
            }
        }

        else -> {
            CoinDetailContent(
                state = state,
                onBackClick = onBackClick,
                onTimeSpanChange = { time -> viewModel.loadChartData(coinId, time) }
            )
        }
    }
}