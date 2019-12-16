package tnr.junitmetalink;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.squashtest.ta.galaxia.squash.tf.galaxia.annotations.TFMetadata;

import java.util.concurrent.TimeUnit;

public class MutationObserverTest {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeEach
    public void setUp(){
        driver = new FirefoxDriver();
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.get("https://codepen.io/dayvidwhy/pen/egdZyY");
    }

    @TFMetadata(key = "linked-TC", value = "489ec210-9b9f-48e3-b565-7f15bf4339a3")
    @Test
    public void testMutation(){
        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='result']")));
        driver.findElement(By.xpath("//button[@id='red-button']")).click();
        String style = driver.findElement(By.xpath("//p[@id='some-id']")).getAttribute("style");
        Assertions.assertTrue(style.contains("color: red"), "Le texte n'a pas été changé en rouge");
        driver.findElement(By.xpath("//button[@id='blue-button']")).click();
        style = driver.findElement(By.xpath("//p[@id='some-id']")).getAttribute("style");
        Assertions.assertTrue(style.contains("color: blue"), "Le texte n'a pas été changé en bleu");
    }

    @AfterEach
    public void tearDown(){
        driver.quit();
    }
}
