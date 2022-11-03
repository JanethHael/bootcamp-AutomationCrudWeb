package basicSelenium;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.sql.Driver;
import java.util.Date;

public class BasicSeleniumTest {

    WebDriver driver;

    @BeforeEach
    public void setup(){
        System.setProperty("webdriver.chrome.driver","src/test/resources/driver/chromedriver");
        driver = new ChromeDriver();
        driver.get("http://todo.ly/");
    }

    @AfterEach
    public void cleanup(){
        //driver.quit();
    }

    @Test
    public void verifyCRUDProject() throws InterruptedException {

        // login
        driver.findElement(By.xpath("//img[contains(@src,'pagelogin')]")).click();
        driver.findElement(By.id("ctl00_MainContent_LoginControl1_TextBoxEmail")).sendKeys("bootcamp@mojix44.com");
        driver.findElement(By.id("ctl00_MainContent_LoginControl1_TextBoxPassword")).sendKeys("12345");
        driver.findElement(By.id("ctl00_MainContent_LoginControl1_ButtonLogin")).click();
        Thread.sleep(1000);
        Assertions.assertTrue(driver.findElement(By.id("ctl00_HeaderTopControl1_LinkButtonLogout")).isDisplayed()
                                    ,"ERROR login was incorrect");
        // create
        String nameProject="CreateJH"+new Date().getTime();
        driver.findElement(By.xpath("//td[text()='Add New Project']")).click();
        driver.findElement(By.id("NewProjNameInput")).sendKeys(nameProject);
        driver.findElement(By.id("NewProjNameButton")).click();
        Thread.sleep(1000);
        int actualResult=driver.findElements(By.xpath(" //td[text()='"+nameProject+"'] ")).size();
        Assertions.assertTrue(actualResult >= 1
                ,"ERROR The project was not created");

        //Create task
        String nameTask = "Tarea1"+new Date().getTime();
        driver.findElement(By.xpath("//td/textarea[@id='NewItemContentInput']")).sendKeys(nameTask);
        driver.findElement(By.xpath("//td/input[@id='NewItemAddButton']")).click();

        //Update task
        nameTask = "TareaUpdate"+new Date().getTime();
        driver.findElement(By.xpath("//div[contains(@class,'ItemIndicator')]/img")).click();
        driver.findElement(By.xpath("//ul[@id=\"itemContextMenu\"]//a[text()='Edit']")).click();
        driver.findElement(By.xpath("//td/div[contains(@class,'ItemContentDiv')]")).clear();
        driver.findElement(By.xpath("//td/div[contains(@class,'ItemContentDiv')]")).sendKeys(nameProject);

    }
}
