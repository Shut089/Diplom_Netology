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

object ControlPanelPage {

    private const val TIMEOUT = 10_000L

    private val addNewsButton =
        withId(R.id.add_news_image_view)

    fun waitForControlPanel() {
        val device = UiDevice.getInstance(
            InstrumentationRegistry.getInstrumentation()
        )

        val opened = device.wait(
            Until.hasObject(
                By.res("ru.edu.qamid", "add_news_image_view")
            ),
            TIMEOUT
        )

        check(opened) {
            "Control panel не открылся"
        }
    }

    fun checkControlPanel() {
        onView(addNewsButton)
            .check(matches(isDisplayed()))
    }

    fun clickAddNews() {
        onView(addNewsButton).perform(click())
    }

    fun openCreateNews() {
        clickAddNews()
    }

    fun checkNewsCreated(title: String) {
        val device = UiDevice.getInstance(
            InstrumentationRegistry.getInstrumentation()
        )

        val newsCreated = device.wait(
            Until.hasObject(
                By.text(title)
            ),
            TIMEOUT
        )

        check(newsCreated) {
            "Созданная новость '$title' не найдена в Control panel"
        }
    }
}