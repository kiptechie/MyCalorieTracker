package com.kiptechie.tracker_presentation.tracker_overview

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.kiptechie.core.util.UiEvent
import com.kiptechie.core_ui.LocalSpacing
import com.kiptechie.tracker_presentation.tracker_overview.components.DaySelector
import com.kiptechie.tracker_presentation.tracker_overview.components.ExpandableMeal
import com.kiptechie.tracker_presentation.tracker_overview.components.NutrientsHeader

@Composable
fun TrackerOverviewScreen(
    onNavigate: (UiEvent.Navigate) -> Unit,
    viewModel: TrackerOverviewViewModel = hiltViewModel()
) {
    val dimens = LocalSpacing.current
    val state = viewModel.state
    val context = LocalContext.current
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = dimens.medium),
        content = {
            item {
                NutrientsHeader(state = state)
                Spacer(modifier = Modifier.height(dimens.medium))
                DaySelector(
                    date = state.date,
                    onPreviousDateClick = {
                        viewModel.onEvent(TrackerOverviewEvent.OnPreviousDayClick)
                    },
                    onNextDayClick = {
                        viewModel.onEvent(TrackerOverviewEvent.OnNextDayClick)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = dimens.medium)
                )
                Spacer(modifier = Modifier.height(dimens.medium))
            }
            items(state.meals) { meal ->
                ExpandableMeal(
                    meal = meal,
                    content = {
                        //
                    },
                    onToggleClick = {
                        viewModel.onEvent(TrackerOverviewEvent.OnToggleMealClick(meal))
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    )
}