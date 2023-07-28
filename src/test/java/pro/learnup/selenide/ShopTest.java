package pro.learnup.selenide;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import jdk.jfr.Name;
import org.junit.jupiter.api.Test;
import pro.learnup.selenide.ext.UiTestsExt;
import pro.learnup.selenide.pages.PhonesPage;
import pro.learnup.selenide.pages.blocks.HeaderBlock;

import static com.codeborne.selenide.Selenide.*;

public class ShopTest extends UiTestsExt{
    String login = "admin10";
    String password = "admin";
    String phoneName = "HTC U11";
    String phoneName2 = "Apple iPhone 8 Plus";
    String phoneName3 = "Apple iPhone X";
    String newAddress = "NewAddress";
    String newEmail = "NewEmail";
    String phoneNumber = "9999999999";
    String brand = "Huawei";
    String internalMemory = "256 GB";
    String cpu = "Hexa-core";
    String ram = "6GB";
    String camera = "16 MP";

    @Feature("Покупка телефона")
    @Description("Покупаем телефон {0}")
    @Severity(SeverityLevel.BLOCKER)
    @Name("Покупка телефона")
    @Test
    public void buyPhoneTest() throws InterruptedException {
        open("http://localhost:3000/", PhonesPage.class)
                .getHeaderBlock()
                .login(login, password)
                .selectPhone(phoneName)
                .checkPhoneName(phoneName)
                .clickAddToCart()
                .getHeaderBlock()
                .goToCart()
                .checkCartContainExactly(phoneName)
                .clickCheckOut()
                .clickConfirm()
                .checkThatCheckOutSuccessful()
                .clickOK()
                .getHeaderBlock()
                .clickLOGOUT();
    }

    @Feature("Cортировка телефонов")
    @Description("Cортировка телефонов по цене и очистка после сортировки")
    @Severity(SeverityLevel.MINOR)
    @Name("Cортировка телефонов")
    @Test
    public void filterCPU() throws InterruptedException {
        open("http://localhost:3000/", PhonesPage.class)
                .getHeaderBlock()
                .login(login, password)
                .clickSearchByCPU()
                .clickSearchByCPUHexaCore()
                .checkFilterPhone(cpu)
                .clickButtonClearFilter()
                .getHeaderBlock()
                .clickLOGOUT();
    }

    @Feature("Изменение личных данных")
    @Description("Изменение личных данных в личном кабинете")
    @Severity(SeverityLevel.CRITICAL)
    @Name("Изменение личных данных")
    @Test
    public void personalData() throws InterruptedException {
        open("http://localhost:3000/", PhonesPage.class)
                .getHeaderBlock()
                .login(login, password)
                .getHeaderBlock()
                .clickAccount()
                .clickEditAccount()
                .checkEditAccount()
                .clickAndClearAndSendKeysEmailAccount(newEmail)
                .clickAndClearAndSendKeysAddressAccount(newAddress)
                .clickAndClearAndSendKeysPhoneAccount(phoneNumber)
                .clickSaveAccount()
                .getHeaderBlock()
                .clickLOGOUT();
    }

    @Description("Добавляем телефоны в корзину и удаляем их разными способами")
    @Severity(SeverityLevel.BLOCKER)
    @Name("Удаление телефонов из корзины")
    @Test
    public void deletePhoneTest() throws InterruptedException {
        open("http://localhost:3000/", PhonesPage.class)
                .getHeaderBlock()
                .login(login, password)
                .selectPhone(phoneName)
                .checkPhoneName(phoneName)
                .clickAddToCart()
                .backToCatalog()
                .selectPhone(phoneName2)
                .checkPhoneName(phoneName2)
                .clickAddToCart()
                .backToCatalog()
                .selectPhone(phoneName3)
                .checkPhoneName(phoneName3)
                .clickAddToCart()
                .getHeaderBlock()
                .goToCart()
                .checkCartContainExactly(phoneName, phoneName2, phoneName3)
                .deletePhone(phoneName)
                .deleteAllPhoneCancel()
                .deleteAllPhoneYes()
                .checkNoItemsCart()
                .getHeaderBlock()
                .clickLOGOUT();
    }

    @Description("Фильтруем телефоны по бренду {0} и очищаем список")
    @Severity(SeverityLevel.NORMAL)
    @Name("Фильтруем по бренду {0}")
    @Test
    public void filterBrandTest() throws InterruptedException {
        open("http://localhost:3000/");
        new HeaderBlock().login(login, password);
        new PhonesPage().clickSearchByBrand();
        new PhonesPage().clickSearchByBrandHuawei();
        new PhonesPage().checkFilterPhone(brand);
        new PhonesPage().clickButtonClearFilter();
        new HeaderBlock().clickLOGOUT();
    }

    @Description("Фильтруем телефоны по объёму памяти {0} и очищаем список")
    @Severity(SeverityLevel.NORMAL)
    @Name("Фильтруем по объёму памяти {0}")
    @Test
    public void filterInternalMemoryTest() throws InterruptedException {
        open("http://localhost:3000/");
        new HeaderBlock().login(login, password);
        new PhonesPage().clickSearchByInternalMemory();
        new PhonesPage().clickSearchByInternalMemory256GB();
        new PhonesPage().checkFilterPhone(internalMemory);
        new PhonesPage().clickButtonClearFilter();
        new HeaderBlock().clickLOGOUT();
    }

    @Description("Фильтруем телефоны по объёму оперативной памяти {0} и очищаем список")
    @Severity(SeverityLevel.NORMAL)
    @Name("Фильтруем по объёму оперативной памяти {0}")
    @Test
    public void filterRAMTest() {
        open("http://localhost:3000/");
        $x("//div[contains(text(),'RAM')]").click();
        $x("//input[@value='6']").click();

        ElementsCollection ramPhones = $$x("//div[@class='content-left']");
        ramPhones.forEach(p-> {
            if (p.getText().contains(ram))
                System.out.println("Телефон соответствует заявленным требованиям");
            else System.out.println("Ошибка!!! Телефон с другими параметрами.");
        });

        $x("//span[text()='Clear Filters']");
    }

    @Description("Фильтруем телефоны по количеству мегапикселей {0} и очищаем список")
    @Severity(SeverityLevel.NORMAL)
    @Name("Фильтруем по количеству мегапикселей {0}")
    @Test
    public void filterCameraTest() {
        open("http://localhost:3000/");
        $x("//div[contains(text(),'Camera')]").click();
        $x("//input[@value='16']").click();

        ElementsCollection cameraPhones = $$x("//div[@class='content-left']");
        cameraPhones.forEach(p-> {
            if (p.getText().contains(camera))
                System.out.println("Телефон соответствует заявленным требованиям");
            else System.out.println("Ошибка!!! Телефон с другими параметрами.");
        });

        $x("//span[text()='Clear Filters']");
    }
}