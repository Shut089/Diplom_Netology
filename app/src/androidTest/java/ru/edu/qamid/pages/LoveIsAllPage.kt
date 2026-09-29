package ru.edu.qamid.pages

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.Until
import ru.edu.qamid.R
import ru.edu.qamid.adapter.OurMissionItemListAdapter

object LoveIsAllPage {

    private const val TIMEOUT = 10_000L

    private val missionButton =
        withId(R.id.our_mission_image_button)

    private val title =
        withId(R.id.our_mission_title_text_view)

    private val missionList =
        withId(R.id.our_mission_item_list_recycler_view)

    fun open() {
        onView(missionButton).perform(click())
    }

    fun waitForScreen() {
        val device = UiDevice.getInstance(
            InstrumentationRegistry.getInstrumentation()
        )

        val opened = device.wait(
            Until.hasObject(
                By.res("ru.edu.qamid", "our_mission_item_list_recycler_view")
            ),
            TIMEOUT
        )

        check(opened) {
            "Экран Love is all не открылся"
        }
    }

    fun checkScreen() {
        onView(title).check(matches(isDisplayed()))
        onView(missionList).check(matches(isDisplayed()))
    }

    fun openFirstCard() {
        onView(missionList).perform(
            RecyclerViewActions.actionOnItemAtPosition<
                    OurMissionItemListAdapter.OurMissionViewHolder
                    >(0, click())
        )
    }

    fun checkFirstCardDescription() {
        val device = UiDevice.getInstance(
            InstrumentationRegistry.getInstrumentation()
        )

        val descriptionDisplayed = device.wait(
            Until.hasObject(
                By.res(
                    "ru.edu.qamid",
                    "our_mission_item_description_text_view"
                )
            ),
            TIMEOUT
        )

        check(descriptionDisplayed) {
            "Описание цитаты не появилось после раскрытия карточки"
        }
    }
}