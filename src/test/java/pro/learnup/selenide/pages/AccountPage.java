package pro.learnup.selenide.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class AccountPage extends BasePage {
    private SelenideElement emailAccount = $(By.xpath("//input[contains(@id, 'email')]"));
    private SelenideElement addressAccount = $(By.xpath("//input[contains(@id, 'address')]"));
    private SelenideElement phoneAccount = $(By.xpath("//input[contains(@id, 'phone')]"));


    @Step("Нажать кнопку редактирования аккаунта")
    public AccountPage clickEditAccount() {
        $x("//button[contains(@aria-label,'Edit')]").click();
        return page(AccountPage.class);
    }

    @Step("Проверить, что мы на странице редактирования аккаунта")
    public AccountPage checkEditAccount() {
        $(byText("Edit Account")).shouldBe(Condition.visible);
        return page(AccountPage.class);
    }

    @Step("Очистить и ввести данные {newEmail} в строку ввода Email")
    public AccountPage clickAndClearAndSendKeysEmailAccount(String newEmail) {
        emailAccount.click();
        emailAccount.sendKeys(Keys.chord(Keys.CONTROL, "a") + Keys.DELETE);
        emailAccount.sendKeys(newEmail);
        return page(AccountPage.class);
    }

    @Step("Очистить и ввести данные {newAddress} в строку ввода Address")
    public AccountPage clickAndClearAndSendKeysAddressAccount(String newAddress) {
        addressAccount.click();
        addressAccount.sendKeys(Keys.chord(Keys.CONTROL, "a") + Keys.DELETE);
        addressAccount.sendKeys(newAddress);
        return page(AccountPage.class);
    }

    @Step("Очистить и ввести данные {phoneNumber} в строку ввода Telephone Number")
    public AccountPage clickAndClearAndSendKeysPhoneAccount(String phoneNumber) {
        phoneAccount.click();
        phoneAccount.sendKeys(Keys.chord(Keys.CONTROL, "a") + Keys.DELETE);
        phoneAccount.sendKeys(phoneNumber);
        return page(AccountPage.class);
    }

    @Step("Нажать кнопку Save в форме редактирования данных")
    public AccountPage clickSaveAccount() {
        $x("//span[contains(text(), 'Save')]").click();
        return page(AccountPage.class);
    }
}
