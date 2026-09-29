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
import ru.edu.qamid.data.TestData
import ru.edu.qamid.pages.LoginPage
import ru.edu.qamid.pages.MainPage
import ru.edu.qamid.pages.SessionPage
import ru.edu.qamid.ui.AppActivity
import io.qameta.allure.android.rules.ScreenshotRule

@RunWith(AllureAndroidJUnit4::class)
@Epic("Мобильный хоспис")
@Feature("Авторизация")
class AuthorizationTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(AppActivity::class.java)

    @get:Rule
    val screenshotRule = ScreenshotRule(
        mode = ScreenshotRule.Mode.FAILURE,
        screenshotName = "Screenshot on failure"
    )
    @Before
    fun prepareTest() {
        SessionPage.ensureLoggedOut()
    }

    @Test
    @Story("Успешная авторизация")
    @Description("Проверка входа в приложение с корректными логином и паролем")
    @Severity(SeverityLevel.CRITICAL)
    fun successfulAuthorization() {

        Allure.step("Проверить экран авторизации") {
            LoginPage.waitForLoginScreen()
            LoginPage.checkLoginScreen()
        }

        Allure.step("Ввести корректный логин и пароль") {
            LoginPage.login(
                login = TestData.VALID_LOGIN,
                password = TestData.VALID_PASSWORD
            )
        }

        Allure.step("Проверить открытие главного экрана") {
            MainPage.waitForMainScreen()
            MainPage.checkMainScreen()
        }
    }

    @Test
    @Story("Авторизация с неверным логином")
    @Description("Проверка невозможности входа с неверным логином")
    @Severity(SeverityLevel.NORMAL)
    fun authorizationWithInvalidLogin() {

        Allure.step("Ввести неверный логин и корректный пароль") {
            LoginPage.login(
                login = TestData.INVALID_LOGIN,
                password = TestData.VALID_PASSWORD
            )
        }

        Allure.step("Проверить, что экран авторизации остался открыт") {
            LoginPage.checkLoginScreenIsDisplayed()
        }
    }

    @Test
    @Story("Авторизация с неверным паролем")
    @Description("Проверка невозможности входа с неверным паролем")
    @Severity(SeverityLevel.NORMAL)
    fun authorizationWithInvalidPassword() {

        Allure.step("Ввести корректный логин и неверный пароль") {
            LoginPage.login(
                login = TestData.VALID_LOGIN,
                password = TestData.INVALID_PASSWORD
            )
        }

        Allure.step("Проверить, что экран авторизации остался открыт") {
            LoginPage.checkLoginScreenIsDisplayed()
        }
    }

    @Test
    @Story("Авторизация с пустыми полями")
    @Description("Проверка невозможности входа без заполнения логина и пароля")
    @Severity(SeverityLevel.NORMAL)
    fun authorizationWithEmptyFields() {

        Allure.step("Нажать SIGN IN с пустыми полями") {
            LoginPage.clickSignIn()
        }

        Allure.step("Проверить, что экран авторизации остался открыт") {
            LoginPage.checkLoginScreenIsDisplayed()
        }
    }


}