package ru.edu.qamid.pages

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.Until
import ru.edu.qamid.R

object MainPage {

    private const val TIMEOUT = 15_000L

    private val newsContainer = withId(R.id.main_news_list_container)

    fun waitForMainScreen() {
        val device = UiDevice.getInstance(
            InstrumentationRegistry.getInstrumentation()
        )

        val mainScreenOpened = device.wait(
            Until.hasObject(
                By.res("ru.edu.qamid", "main_news_list_container")
            ),
            TIMEOUT
        )

        check(mainScreenOpened) {
            "Главный экран не открылся за $TIMEOUT мс"
        }
    }

    fun checkMainScreen() {
        onView(newsContainer).check(matches(isDisplayed()))
    }

    fun checkMainScreenIsDisplayed() {
        waitForMainScreen()
        checkMainScreen()
    }
}