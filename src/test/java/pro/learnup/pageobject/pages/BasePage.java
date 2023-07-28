package pro.learnup.pageobject.pages;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import pro.learnup.pageobject.pages.blocks.HeaderBlock;

public class BasePage extends BaseView{
    @Getter
    private HeaderBlock headerBlock = new HeaderBlock(webDriver);
    public BasePage(WebDriver webDriver) {
        super(webDriver);
        PageFactory.initElements(webDriver, this);
    }
}
