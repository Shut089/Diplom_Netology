package ru.edu.qamid.pages

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.Until
import ru.edu.qamid.R

object NewsPage {

    private const val TIMEOUT = 10_000L

    private val mainMenuButton = withId(R.id.main_menu_image_button)
    private val controlPanelButton = withId(R.id.news_edit_button)

    fun openNewsFromMenu() {
        onView(mainMenuButton).perform(click())

        onView(
            androidx.test.espresso.matcher.ViewMatchers.withText(R.string.news)
        ).perform(click())
    }

    fun waitForNewsScreen() {
        val device = UiDevice.getInstance(
            InstrumentationRegistry.getInstrumentation()
        )

        val opened = device.wait(
            Until.hasObject(
                By.res("ru.edu.qamid", "news_list_container")
            ),
            TIMEOUT
        )

        check(opened) {
            "Экран News не открылся"
        }
    }

    fun checkNewsScreen() {
        onView(withId(R.id.news_list_container))
            .check(matches(isDisplayed()))
    }

    fun openControlPanel() {
        onView(controlPanelButton).perform(click())
    }

    fun openMainFromMenu() {
        onView(mainMenuButton).perform(click())

        onView(
            androidx.test.espresso.matcher.ViewMatchers.withText(R.string.main)
        ).perform(click())
    }

    fun checkNewsScreenIsDisplayed() {
        waitForNewsScreen()
        checkNewsScreen()
    }
}