package com.example.learningcompose

import MainActivityVM
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.BottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.example.learningcompose.ui.theme.AppTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    View()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
private fun View(vm: MainActivityVM = viewModel()) {
    val scaffoldState =
        rememberBottomSheetScaffoldState(bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed))

    val scope = rememberCoroutineScope()

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetContent = {
            BottomSheetView(name = vm.cardIndex.toString())
        },
        sheetPeekHeight = 0.dp,
        sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            Arrangement.Top
        ) {
            TopAppBar() {
                Row(modifier = Modifier.padding(horizontal = 8.dp)) {
                    Text(text = "Learning Compose", fontWeight = FontWeight.Bold)
                }
            }
            Loader()
            Divider()
            TabHead(onTabChange = { vm.changeTab(it) })
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    contentPadding = PaddingValues(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    items(100) {
                        Item(
                            index = it,
                            modifier = Modifier.fillMaxWidth(),
                            onClick = { index ->
                                vm.updateCardIndex(index)
                                scope.launch {
                                    if (scaffoldState.bottomSheetState.isCollapsed) {
                                        scaffoldState.bottomSheetState.expand()
                                    }
                                }
                            })
                    }
                }
            }
            BottomNavView()
        }
    }

}

@Composable
fun Loader() {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.relax))
    Box(Modifier.height(200.dp)) {
        LottieAnimation(
            composition,
            iterations = LottieConstants.IterateForever,
        )
    }
}

@Composable
fun TabHead(vm: MainActivityVM = viewModel(), onTabChange: (Int) -> Unit) {
    TabRow(selectedTabIndex = vm.tabIndex) {
        Tab(
            text = { Text("Test") },
            selected = vm.tabIndex == 0,
            onClick = { onTabChange(0) },
        )
        Tab(
            text = { Text("Test2") },
            selected = vm.tabIndex == 1,
            onClick = { onTabChange(1) },
        )
    }
}

@Composable
fun BottomNavView() {
    BottomAppBar() {
        Row(modifier = Modifier.padding(horizontal = 8.dp)) {
            Text(text = "Learning Compose", fontWeight = FontWeight.Bold)
        }
    }
}

@Composable
private fun Item(modifier: Modifier = Modifier, index: Int, onClick: (index: Int) -> Unit) {
    Column(
        modifier.clickable { onClick(index) },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = index.toString(), fontWeight = FontWeight.Bold)
        Text(text = "Description")
    }
}

@Composable
fun BottomSheetView(name: String) {
    Box(
        Modifier
            .height(200.dp)
            .fillMaxWidth(), contentAlignment = Alignment.Center
    ) {
        Text("Hello $name")
    }
}

@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun DefaultPreview() {
    AppTheme {
        View()
    }
}