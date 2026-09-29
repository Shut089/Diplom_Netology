package ru.edu.qamid.pages

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.Until
import ru.edu.qamid.R
import ru.edu.qamid.data.TestData

object SessionPage {

    private const val TIMEOUT = 10_000L

    private val authorizationButton =
        withId(R.id.authorization_image_button)

    fun ensureLoggedOut() {
        val device = UiDevice.getInstance(
            InstrumentationRegistry.getInstrumentation()
        )

        device.wait(
            Until.hasObject(
                By.res("ru.edu.qamid", "login_edit_text")
            ),
            4_000L
        )

        if (device.hasObject(By.res("ru.edu.qamid", "login_edit_text"))) {
            return
        }

        val mainScreenOpened = device.wait(
            Until.hasObject(
                By.res("ru.edu.qamid", "main_news_list_container")
            ),
            TIMEOUT
        )

        check(mainScreenOpened) {
            "Не найден экран авторизации или главный экран"
        }

        onView(authorizationButton).perform(click())
        onView(withText(R.string.log_out)).perform(click())

        val loginScreenOpened = device.wait(
            Until.hasObject(
                By.res("ru.edu.qamid", "login_edit_text")
            ),
            TIMEOUT
        )

        check(loginScreenOpened) {
            "После выхода экран Authorization не открылся"
        }
    }

    fun ensureLoggedIn() {
        val device = UiDevice.getInstance(
            InstrumentationRegistry.getInstrumentation()
        )

        device.waitForIdle()

        if (
            device.wait(
                Until.hasObject(
                    By.res("ru.edu.qamid", "main_news_list_container")
                ),
                5_000L
            )
        ) {
            return
        }

        if (
            device.hasObject(
                By.res("ru.edu.qamid", "login_edit_text")
            )
        ) {
            LoginPage.login(
                login = TestData.VALID_LOGIN,
                password = TestData.VALID_PASSWORD
            )

            MainPage.waitForMainScreen()
            MainPage.checkMainScreen()
            return
        }

        if (
            device.hasObject(
                By.res("ru.edu.qamid", "main_menu_image_button")
            )
        ) {
            onView(
                withId(R.id.main_menu_image_button)
            ).perform(click())

            onView(
                withText(R.string.main)
            ).perform(click())

            MainPage.waitForMainScreen()
            MainPage.checkMainScreen()
            return
        }

        val loginScreenOpened = device.wait(
            Until.hasObject(
                By.res("ru.edu.qamid", "login_edit_text")
            ),
            TIMEOUT
        )

        if (loginScreenOpened) {
            LoginPage.login(
                login = TestData.VALID_LOGIN,
                password = TestData.VALID_PASSWORD
            )

            MainPage.waitForMainScreen()
            MainPage.checkMainScreen()
            return
        }

        error("Не удалось привести приложение к авторизованному состоянию")
    }

    fun logOut() {
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
            "Главный экран не открыт"
        }

        onView(
            withId(R.id.authorization_image_button)
        ).perform(click())

        onView(
            withText(R.string.log_out)
        ).perform(click())

        val loginScreenOpened = device.wait(
            Until.hasObject(
                By.res("ru.edu.qamid", "login_edit_text")
            ),
            TIMEOUT
        )

        check(loginScreenOpened) {
            "После выхода экран Authorization не открылся"
        }
    }
}