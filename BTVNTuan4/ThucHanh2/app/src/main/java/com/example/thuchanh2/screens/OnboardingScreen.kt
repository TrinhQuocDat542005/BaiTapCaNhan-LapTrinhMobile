package com.example.thuchanh2.screens

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.TextButton
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.thuchanh2.R
import com.example.thuchanh2.model.OnboardingPage
import com.google.accompanist.pager.HorizontalPagerIndicator
import kotlinx.coroutines.launch

val onboardingPages = listOf(
    OnboardingPage(
        "Easy Time Management",
        "With management based on priority and daily tasks, it will give...",
        R.drawable.time_management
    ),
    OnboardingPage(
        "Increase Work Effectiveness",
        "Time management and the determination of more important tasks...",
        R.drawable.increase_work
    ),
    OnboardingPage(
        "Reminder Notification",
        "The advantage of this application is that it provides reminders...",
        R.drawable.reminder_notification
    )
)

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OnboardingScreen(onGetStartedClick: () -> Unit) {
    val pagerState = rememberPagerState(initialPage = 0) {
        onboardingPages.size
    }
    val scope = rememberCoroutineScope()

    Column(

        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            // Chỉ hiển thị khi CHƯA ở trang cuối
            if (pagerState.currentPage < onboardingPages.size - 1) {
                TextButton(
                    onClick = onGetStartedClick, // Nhấn Skip cũng sẽ vào app
                    modifier = Modifier.align(Alignment.CenterEnd)
                ) {
                    Text("Skip")
                }
            }
        }

        HorizontalPager(
            state = pagerState,
            modifier = Modifier.weight(1f)
        ) { pageIndex ->
            OnboardingPageItem(page = onboardingPages[pageIndex])
        }

        HorizontalPagerIndicator(
            pagerState = pagerState,
            pageCount = onboardingPages.size,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(16.dp)
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 24.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {

            // --- NÚT BACK (QUAY LẠI) ---
            // Dùng AnimatedVisibility để ẩn/hiện mượt mà
            AnimatedVisibility(visible = pagerState.currentPage > 0) {
                Button(onClick = {
                    scope.launch {
                        // Quay về trang trước
                        pagerState.animateScrollToPage(pagerState.currentPage - 1)
                    }
                }) {
                    Text("Back")
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                contentAlignment = Alignment.CenterEnd
            ) {
                if (pagerState.currentPage == onboardingPages.size - 1) {
                    Button(onClick = onGetStartedClick) {
                        Text("Get Started")
                    }
                } else {
                    Button(onClick = {
                        scope.launch {
                            pagerState.animateScrollToPage(pagerState.currentPage + 1)
                        }
                    }) {
                        Text("Next")
                    }
                }
            }
        }
    }
}

@Composable
fun OnboardingPageItem(page: OnboardingPage) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = page.imageRes),
            contentDescription = page.title,
            modifier = Modifier.size(200.dp)
        )
        Spacer(modifier = Modifier.height(32.dp))
        Text(
            text = page.title,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = page.description,
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center
        )
    }
}