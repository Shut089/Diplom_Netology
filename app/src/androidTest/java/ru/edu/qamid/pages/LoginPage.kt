package ru.edu.qamid.pages

import androidx.test.espresso.Espresso.closeSoftKeyboard
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import ru.edu.qamid.R
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.Until

object LoginPage {

    private const val TIMEOUT = 10_000L

    private val loginField = withId(R.id.login_edit_text)
    private val passwordField = withId(R.id.password_edit_text)
    private val signInButton = withId(R.id.enter_button)

    fun waitForLoginScreen() {
        val device = UiDevice.getInstance(
            InstrumentationRegistry.getInstrumentation()
        )

        device.wait(
            Until.hasObject(
                By.res("ru.edu.qamid", "login_edit_text")
            ),
            TIMEOUT
        )
    }
    fun checkLoginScreen() {
        onView(loginField).check(matches(isDisplayed()))
        onView(passwordField).check(matches(isDisplayed()))
        onView(signInButton).check(matches(isDisplayed()))
    }

    fun enterLogin(login: String) {
        onView(loginField).perform(replaceText(login))
    }

    fun enterPassword(password: String) {
        onView(passwordField).perform(replaceText(password))
        closeSoftKeyboard()
    }

    fun clickSignIn() {
        onView(signInButton).perform(click())
    }

    fun login(login: String, password: String) {
        enterLogin(login)
        enterPassword(password)
        clickSignIn()
    }

    fun checkLoginScreenIsDisplayed() {
        waitForLoginScreen()
        checkLoginScreen()
    }
}