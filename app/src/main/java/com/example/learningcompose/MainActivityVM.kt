import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel

class MainActivityVM : ViewModel() {

    var cardIndex by mutableStateOf(0)
        private set

    var tabIndex by mutableStateOf(0)
        private set

    fun changeTab(index: Int) {
        tabIndex = index
    }

    fun updateCardIndex(index: Int) {
        cardIndex = index
    }
}