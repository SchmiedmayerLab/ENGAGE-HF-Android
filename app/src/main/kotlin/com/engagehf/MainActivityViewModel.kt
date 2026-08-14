//
// This source file is part of the ENGAGE-HF Android open-source project
//
// SPDX-FileCopyrightText: 2025 Stanford University and the project authors (see CONTRIBUTORS.md)
//
// SPDX-License-Identifier: MIT
//

package com.engagehf

import android.content.Intent
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import com.engagehf.bluetooth.data.mapper.MessageActionMapper
import com.engagehf.messages.MessagesHandler
import com.engagehf.modules.account.AccountEvents
import com.engagehf.modules.account.manager.UserSessionManager
import com.engagehf.modules.account.manager.UserState
import com.engagehf.modules.navigation.NavigationEvent
import com.engagehf.modules.navigation.Navigator
import com.engagehf.modules.notification.fcm.DeviceRegistrationService
import com.engagehf.modules.notification.notifier.FirebaseMessage
import com.engagehf.modules.notification.notifier.FirebaseMessage.Companion.FIREBASE_MESSAGE_KEY
import com.engagehf.modules.onboarding.OnboardingNavigationEvent
import com.engagehf.modules.utils.MessageNotifier
import com.engagehf.navigation.AppNavigationEvent
import com.engagehf.navigation.Routes
import com.engagehf.modules.core.logging.engageLogger
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@Suppress("LongParameterList")
@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val accountEvents: AccountEvents,
    private val navigator: Navigator,
    private val userSessionManager: UserSessionManager,
    private val messageNotifier: MessageNotifier,
    private val messageActionMapper: MessageActionMapper,
    private val messagesHandler: MessagesHandler,
    private val deviceRegistrationService: DeviceRegistrationService,
) : ViewModel() {
    private val logger by engageLogger()
    private val _uiState = MutableStateFlow<MainUiState>(MainUiState.SplashScreen)
    val uiState = _uiState.asStateFlow()

    init {
        setupInitialState()
        observeAccountEvents()
    }

    fun onAction(action: MainActivityAction) {
        when (action) {
            is MainActivityAction.NewIntent -> handleNewIntent(intent = action.intent)
        }
    }

    fun getNavigationEvents(): Flow<NavigationEvent> = navigator.events

    private fun handleNewIntent(intent: Intent) {
        val firebaseMessage = intent.getParcelableExtra<FirebaseMessage>(FIREBASE_MESSAGE_KEY)
        firebaseMessage?.messageId?.let { messageId ->
            viewModelScope.launch {
                messagesHandler.handle(
                    messageId = messageId,
                    isDismissible = firebaseMessage.isDismissible != false,
                    action = messageActionMapper.map(firebaseMessage.action),
                )
            }
        }
    }

    private fun setupInitialState() {
        viewModelScope.launch {
            val userState = userSessionManager.getUserState()
            refreshDeviceTokenIfNeeded(userState = userState)
            val startDestination = when (userState) {
                is UserState.NotInitialized -> Routes.OnboardingScreen
                is UserState.Registered -> {
                    if (userState.hasInvitationCodeConfirmed) Routes.AppScreen else Routes.InvitationCodeScreen
                }
            }
            _uiState.update { MainUiState.Content(startDestination = startDestination) }
        }
    }

    private fun observeAccountEvents() {
        viewModelScope.launch {
            accountEvents.events.collect { event ->
                when (event) {
                    is AccountEvents.Event.SignInSuccess, AccountEvents.Event.SignUpSuccess -> {
                        val userState = userSessionManager.getUserState()
                        refreshDeviceTokenIfNeeded(userState = userState)
                        val navigationEvent = when (userState) {
                            UserState.NotInitialized -> {
                                OnboardingNavigationEvent.OnboardingScreen(clearBackStack = false)
                            }

                            is UserState.Registered -> {
                                if (userState.hasInvitationCodeConfirmed) {
                                    AppNavigationEvent.AppScreen(true)
                                } else {
                                    OnboardingNavigationEvent.InvitationCodeScreen
                                }
                            }
                        }
                        navigator.navigateTo(navigationEvent)
                    }

                    is AccountEvents.Event.SignOutSuccess -> {
                        navigator.navigateTo(
                            OnboardingNavigationEvent.OnboardingScreen(
                                clearBackStack = true
                            )
                        )
                    }

                    is AccountEvents.Event.SignOutFailure -> {
                        messageNotifier.notify(R.string.sign_out_failed)
                        logger.e { "Sign out failed" }
                    }

                    else -> {
                        logger.i { "Ignoring registration event: $event" }
                    }
                }
            }
        }
    }

    private fun refreshDeviceTokenIfNeeded(userState: UserState) {
        if (userState is UserState.Registered) deviceRegistrationService.refreshDeviceToken()
    }
}

sealed interface MainUiState {
    data object SplashScreen : MainUiState
    data class Content(val startDestination: Routes) : MainUiState
}

sealed interface MainActivityAction {
    data class NewIntent(val intent: Intent) : MainActivityAction
}
