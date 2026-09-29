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
import ru.edu.qamid.pages.LoveIsAllPage
import ru.edu.qamid.pages.SessionPage
import ru.edu.qamid.ui.AppActivity
import io.qameta.allure.android.rules.ScreenshotRule

@RunWith(AllureAndroidJUnit4::class)
@Epic("Мобильный хоспис")
@Feature("Цитаты")
class LoveIsAllTest {

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
    @Story("Открытие раздела Love is all")
    @Description("Проверка перехода в раздел тематических цитат")
    @Severity(SeverityLevel.NORMAL)
    fun openLoveIsAllScreen() {

        Allure.step("Открыть раздел Love is all") {
            LoveIsAllPage.open()
            LoveIsAllPage.waitForScreen()
        }

        Allure.step("Проверить отображение экрана с цитатами") {
            LoveIsAllPage.checkScreen()
        }
    }

    @Test
    @Story("Раскрытие карточки цитаты")
    @Description("Проверка раскрытия первой карточки и отображения описания")
    @Severity(SeverityLevel.NORMAL)
    fun openFirstQuoteCard() {

        Allure.step("Открыть раздел Love is all") {
            LoveIsAllPage.open()
            LoveIsAllPage.waitForScreen()
        }

        Allure.step("Открыть первую карточку цитаты") {
            LoveIsAllPage.openFirstCard()
        }

        Allure.step("Проверить отображение описания цитаты") {
            LoveIsAllPage.checkFirstCardDescription()
        }
    }
}