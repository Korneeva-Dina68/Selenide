package pro.learnup.selenide.pagesWB;

import lombok.Getter;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import pro.learnup.selenide.pagesWB.blocksWB.HeaderBlockWB;

public class BasePageWB {
    @Getter
    private HeaderBlockWB headerBlockWB = new HeaderBlockWB();

}
