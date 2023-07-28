package pro.learnup.selenide.pages;

import com.codeborne.selenide.ElementsCollection;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

import static com.codeborne.selenide.Selenide.*;

public class PhonesPage extends BasePage {

    @Step("Выбрать телефон {phoneName}")
    public PhonePage selectPhone(String phoneName) {
        ElementsCollection phones = $$(By.className("product"));
        phones.stream()
                .filter(el -> el.getText().contains(phoneName))
                .findFirst()
                .orElseThrow()
                .findElement(By.xpath(".//a[.='See more']"))
                .click();
        return page(PhonePage.class);
    }

    @Step("Нажать кнопку Price")
    public PhonesPage clickSearchByPrice() {
        $x(" //*[contains(text(),'Price')]").click();
        return page(PhonesPage.class);
    }

    @Step("Нажать кнопку >750")
    public PhonesPage clickSearchByPriceMore750(){
        $x(" //input[contains(@value,'750>')]").click();
        return page(PhonesPage.class);
    }

    @Step("Нажать кнопку Brand")
    public void clickSearchByBrand() {
        $x("//div[contains(text(),'Brand')]").click();
    }

    @Step("Нажать кнопку Huawei")
    public void clickSearchByBrandHuawei() {
        $x("//input[@value='huawei']").click();
    }

    @Step("Нажать кнопку Internal memory")
    public void clickSearchByInternalMemory() {
        $x("//div[contains(text(),'Internal memory')]").click();
    }

    @Step("Нажать кнопку 256GB")
    public void clickSearchByInternalMemory256GB() {
        $x("//input[@value='256']").click();
    }

    @Step("Нажать кнопку CPU")
    public PhonesPage clickSearchByCPU() {
        $x("//div[contains(text(),'CPU')]").click();
        return page(PhonesPage.class);
    }

    @Step("Нажать кнопку Hexa_core")
    public PhonesPage clickSearchByCPUHexaCore() {
        $x("//input[@value='hexa_core']").click();
        return page(PhonesPage.class);
    }

    @Step("Проверяем, что после фильтра телефоны только {filter}")
    public PhonesPage checkFilterPhone(String filter) {
        ElementsCollection brandPhones = $$x("//div[@class='content-left']");
        brandPhones.forEach(p-> {
            if (p.getText().contains(filter)) System.out.println("Телефон соответствует заявленным требованиям");
            else System.out.println("Ошибка!!! Телефон с другими параметрами.");
        });
        return this;
    }

    @Step("Нажать кнопку Clear Filters")
    public PhonesPage clickButtonClearFilter() {
        $x("//span[text()='Clear Filters']").click();
        return page(PhonesPage.class);
    }
}
