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
import ru.edu.qamid.pages.ControlPanelPage
import ru.edu.qamid.pages.CreateNewsPage
import ru.edu.qamid.pages.NewsPage
import ru.edu.qamid.pages.SessionPage
import ru.edu.qamid.ui.AppActivity
import io.qameta.allure.android.rules.ScreenshotRule

@RunWith(AllureAndroidJUnit4::class)
@Epic("Мобильный хоспис")
@Feature("Новости")
class NewsTest {

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
    @Story("Создание новости")
    @Description("Проверка успешного создания новой активной новости")
    @Severity(SeverityLevel.CRITICAL)
    fun createNewsSuccessfully() {

        val newsTitle = "AutoTest_${System.currentTimeMillis()}"
        val newsDescription = "Новость создана автоматическим тестом"

        Allure.step("Открыть раздел News") {
            NewsPage.openNewsFromMenu()
            NewsPage.waitForNewsScreen()
            NewsPage.checkNewsScreen()
        }

        Allure.step("Открыть Control panel") {
            NewsPage.openControlPanel()
            ControlPanelPage.waitForControlPanel()
            ControlPanelPage.checkControlPanel()
        }

        Allure.step("Открыть форму создания новости") {
            ControlPanelPage.openCreateNews()
            CreateNewsPage.waitForCreateNewsScreen()
            CreateNewsPage.checkCreateNewsScreen()
        }

        Allure.step("Заполнить данные новой новости") {
            CreateNewsPage.createNews(
                category = "Объявление",
                title = newsTitle,
                description = newsDescription
            )
        }

        Allure.step("Проверить, что новость создана") {
            ControlPanelPage.waitForControlPanel()
            ControlPanelPage.checkNewsCreated(newsTitle)
        }
    }

    @Test
    @Story("Создание новости с пустыми полями")
    @Description("Проверка невозможности сохранения новости без заполнения обязательных полей")
    @Severity(SeverityLevel.NORMAL)
    fun createNewsWithEmptyFields() {

        Allure.step("Открыть раздел News") {
            NewsPage.openNewsFromMenu()
            NewsPage.waitForNewsScreen()
        }

        Allure.step("Открыть Control panel") {
            NewsPage.openControlPanel()
            ControlPanelPage.waitForControlPanel()
        }

        Allure.step("Открыть форму создания новости") {
            ControlPanelPage.openCreateNews()
            CreateNewsPage.waitForCreateNewsScreen()
        }

        Allure.step("Попытаться сохранить пустую форму") {
            CreateNewsPage.clickSaveWithoutData()
        }

        Allure.step("Проверить, что форма создания новости осталась открыта") {
            CreateNewsPage.checkCreateNewsScreenIsDisplayed()
        }
    }


    @Test
    @Story("Отмена создания новости")
    @Description("Проверка отмены создания новой новости и возврата в Control panel")
    @Severity(SeverityLevel.NORMAL)
    fun cancelNewsCreation() {

        Allure.step("Открыть раздел News") {
            NewsPage.openNewsFromMenu()
            NewsPage.waitForNewsScreen()
        }

        Allure.step("Открыть Control panel") {
            NewsPage.openControlPanel()
            ControlPanelPage.waitForControlPanel()
        }

        Allure.step("Открыть форму создания новости") {
            ControlPanelPage.openCreateNews()
            CreateNewsPage.waitForCreateNewsScreen()
        }

        Allure.step("Ввести название новости") {
            CreateNewsPage.enterTitle("News to cancel")
        }

        Allure.step("Отменить создание новости") {
            CreateNewsPage.cancelCreation()
        }

        Allure.step("Проверить возврат в Control panel") {
            ControlPanelPage.waitForControlPanel()
            ControlPanelPage.checkControlPanel()
        }
    }
}