package tnr.junitmetalink;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.squashtest.ta.galaxia.squash.tf.galaxia.annotations.TFMetadata;
import org.squashtest.ta.galaxia.tf.param.service.TFParamService;

import java.util.concurrent.TimeUnit;

public class AnimatedMenuTest {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeEach
    public void setUp() throws InterruptedException {
        String paramBrowser = TFParamService.getInstance().getParam("DS_browser");
        String paramWebsite = TFParamService.getInstance().getParam("DS_url", "https://codepen.io/knyttneve/pen/LKrGBy");
        if(paramBrowser.equals("firefox"))
        {
            System.out.println("it's ff");
            driver = new FirefoxDriver();
        }
        else if(paramBrowser.equals("chrome"))
        {
            System.out.println("it's chrome");
            driver = new ChromeDriver();
        }
        else{
            System.out.println("Param illisible ou incorrect");
        }
        //driver = new FirefoxDriver();
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.get(paramWebsite);
        //driver.get("https://codepen.io/knyttneve/pen/LKrGBy");
        //"url": "https://codepen.io/knyttneve/pen/LKrGBy",
    }

    @TFMetadata(key = "linked-TC", value = "3f487b46-34e2-472a-bac1-b36feb71801b")
    @DisplayName("MenuTestimonials")
    @Test
    public void testMenuTestimonials(){
        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='result']")));
        driver.findElement(By.xpath("//a[contains(text(),'Testimonials')]")).click();
        String style = driver.findElement(By.xpath("//a[contains(text(),'Testimonials')]")).getAttribute("class");
        Assertions.assertTrue(style.contains("is-active"), "Le bouton n'a pas été activé");
        driver.findElement(By.xpath("//a[contains(text(),'About')]")).click();
        style = driver.findElement(By.xpath("//a[contains(text(),'Testimonials')]")).getAttribute("class");
        Assertions.assertFalse(style.contains("is-active"), "Le bouton n'a pas été désactivé");
    }

    @TFMetadata(key = "linked-TC", value = "ac8f9e31-7b2a-4654-92b4-cd26e3f07b48")
    @DisplayName("MenuBlog")
    @Test
    public void testMenuBlog(){
        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@id='result']")));
        driver.findElement(By.xpath("//a[contains(text(),'Blog')]")).click();
        String style = driver.findElement(By.xpath("//a[contains(text(),'Blog')]")).getAttribute("class");
        Assertions.assertTrue(style.contains("is-active"), "Le bouton n'a pas été activé");
        driver.findElement(By.xpath("//a[contains(text(),'About')]")).click();
        style = driver.findElement(By.xpath("//a[contains(text(),'Blog')]")).getAttribute("class");
        Assertions.assertFalse(style.contains("is-active"), "Le bouton n'a pas été désactivé");
    }


	
    @AfterEach
    public void tearDown(){

        driver.quit();
    }
}
