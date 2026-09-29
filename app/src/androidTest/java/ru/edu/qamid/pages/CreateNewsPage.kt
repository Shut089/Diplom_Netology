package ru.edu.qamid.pages

import androidx.test.espresso.Espresso.closeSoftKeyboard
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.RootMatchers.isPlatformPopup
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.Until
import ru.edu.qamid.R

object CreateNewsPage {

    private const val TIMEOUT = 10_000L

    private val categoryField =
        withId(R.id.news_category_auto_complete)

    private val titleField =
        withId(R.id.news_title_edit_text)

    private val dateField =
        withId(R.id.news_publish_date_edit_text)

    private val timeField =
        withId(R.id.news_publish_time_edit_text)

    private val descriptionField =
        withId(R.id.news_description_edit_text)

    private val saveButton =
        withId(R.id.news_save_button)

    private val cancelButton =
        withId(R.id.news_cancel_button)

    fun waitForCreateNewsScreen() {
        val device = UiDevice.getInstance(
            InstrumentationRegistry.getInstrumentation()
        )

        val opened = device.wait(
            Until.hasObject(
                By.res("ru.edu.qamid", "news_title_edit_text")
            ),
            TIMEOUT
        )

        check(opened) {
            "Экран создания новости не открылся"
        }
    }

    fun checkCreateNewsScreen() {
        onView(categoryField).check(matches(isDisplayed()))
        onView(titleField).check(matches(isDisplayed()))
        onView(dateField).check(matches(isDisplayed()))
        onView(timeField).check(matches(isDisplayed()))
        onView(descriptionField).check(matches(isDisplayed()))
        onView(saveButton).check(matches(isDisplayed()))
    }

    fun selectCategory(category: String) {
        val device = UiDevice.getInstance(
            InstrumentationRegistry.getInstrumentation()
        )

        onView(categoryField).perform(click())

        val categoryLoaded = device.wait(
            Until.hasObject(
                By.text(category)
            ),
            TIMEOUT
        )

        check(categoryLoaded) {
            "Категория '$category' не появилась в списке"
        }

        onView(withText(category))
            .inRoot(isPlatformPopup())
            .perform(click())
    }

    fun enterTitle(title: String) {
        onView(titleField).perform(replaceText(title))
    }

    fun enterDescription(description: String) {
        onView(descriptionField).perform(replaceText(description))
        closeSoftKeyboard()
    }

    fun selectCurrentDate() {
        val device = UiDevice.getInstance(
            InstrumentationRegistry.getInstrumentation()
        )

        onView(dateField).perform(click())

        val okButtonDisplayed = device.wait(
            Until.hasObject(By.text("OK")),
            TIMEOUT
        )

        check(okButtonDisplayed) {
            "DatePicker не открылся"
        }

        onView(withText("OK")).perform(click())
    }

    fun selectCurrentTime() {
        val device = UiDevice.getInstance(
            InstrumentationRegistry.getInstrumentation()
        )

        onView(timeField).perform(click())

        val okButtonDisplayed = device.wait(
            Until.hasObject(By.text("OK")),
            TIMEOUT
        )

        check(okButtonDisplayed) {
            "TimePicker не открылся"
        }

        onView(withText("OK")).perform(click())
    }

    fun clickSave() {
        onView(saveButton).perform(click())
    }

    fun clickSaveWithoutData() {
        onView(saveButton).perform(click())
    }

    fun checkCreateNewsScreenIsDisplayed() {
        checkCreateNewsScreen()
    }

    fun createNews(
        category: String,
        title: String,
        description: String
    ) {
        selectCategory(category)
        enterTitle(title)
        selectCurrentDate()
        selectCurrentTime()
        enterDescription(description)
        clickSave()
    }

    fun cancelCreation() {
        onView(cancelButton).perform(click())

        onView(withText(R.string.fragment_positive_button))
            .perform(click())
    }
}