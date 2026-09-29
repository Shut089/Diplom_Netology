package ru.edu.qamid.tests

import androidx.test.ext.junit.rules.ActivityScenarioRule
import io.qameta.allure.android.runners.AllureAndroidJUnit4
import io.qameta.allure.kotlin.Allure
import io.qameta.allure.kotlin.Description
import io.qameta.allure.kotlin.Epic
import io.qameta.allure.kotlin.Feature
import io.qameta.allure.kotlin.Severity
import io.qameta.allure.kotlin.SeverityLevel
import io.qameta.allure.kotlin.Story
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.edu.qamid.pages.LoginPage
import ru.edu.qamid.pages.MainPage
import ru.edu.qamid.pages.NewsPage
import ru.edu.qamid.pages.SessionPage
import ru.edu.qamid.ui.AppActivity
import io.qameta.allure.android.rules.ScreenshotRule

@RunWith(AllureAndroidJUnit4::class)
@Epic("Мобильный хоспис")
@Feature("Навигация")
class NavigationTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(AppActivity::class.java)

    @get:Rule
    val screenshotRule = ScreenshotRule(
        mode = ScreenshotRule.Mode.FAILURE,
        screenshotName = "Screenshot on failure"
    )

    @Before
    fun prepareTest() {
        SessionPage.ensureLoggedIn()
    }

    @Test
    @Story("Переход между Main и News")
    @Description("Проверка перехода с главного экрана в News и обратно")
    @Severity(SeverityLevel.NORMAL)
    fun navigateFromMainToNewsAndBack() {

        Allure.step("Проверить главный экран") {
            MainPage.checkMainScreenIsDisplayed()
        }

        Allure.step("Перейти в News") {
            NewsPage.openNewsFromMenu()
            NewsPage.checkNewsScreenIsDisplayed()
        }

        Allure.step("Вернуться на Main") {
            NewsPage.openMainFromMenu()
            MainPage.checkMainScreenIsDisplayed()
        }
    }

    @Test
    @Story("Выход из учетной записи")
    @Description("Проверка выхода пользователя из приложения")
    @Severity(SeverityLevel.CRITICAL)
    fun logOutFromApplication() {

        Allure.step("Проверить главный экран") {
            MainPage.checkMainScreenIsDisplayed()
        }

        Allure.step("Выполнить выход из учетной записи") {
            SessionPage.logOut()
        }

        Allure.step("Проверить экран Authorization") {
            LoginPage.checkLoginScreenIsDisplayed()
        }
    }
}