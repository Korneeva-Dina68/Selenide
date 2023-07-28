package pro.learnup.selenium;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

public class UploadFilesTest extends BaseTest{
    @Test
    void uploadFileTest() throws InterruptedException {
        webDriver.get("https://the-internet.herokuapp.com/upload");
        webDriver.findElement(By.xpath("//input[@type='file' and @name='file']")).sendKeys("C:\\Users\\zmeya\\Java\\learn-up-ui-autotests\\src\\test\\resources\\chromedriver.exe");
        Thread.sleep(5000);
    }
}
