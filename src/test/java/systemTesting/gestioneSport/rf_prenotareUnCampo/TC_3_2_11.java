package systemTesting.gestioneSport.rf_prenotareUnCampo;

import java.sql.Date;
import java.sql.Time;
import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.jupiter.api.AfterAll;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TC_3_2_11 {
    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver","src/test/java/systemTesting/katalonDriver/chromedriver.exe");
        driver = new ChromeDriver();
        baseUrl = "https://www.google.com/";
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @Test
    public void testTC327() throws Exception {
        driver.get("http://localhost:8080/PitchFinder_war_exploded/");
        driver.findElement(By.id("dropdownMenu1")).click();
        driver.findElement(By.id("username")).click();
        driver.findElement(By.id("username")).clear();
        driver.findElement(By.id("username")).sendKeys("Meglio100");
        driver.findElement(By.id("password")).click();
        driver.findElement(By.id("password")).clear();
        driver.findElement(By.id("password")).sendKeys("Esse3");
        driver.findElement(By.id("login")).click();
        driver.findElement(By.cssSelector("svg.svg-inline--fa.fa-running.fa-w-13.fa-stack-1x.fa-inverse")).click();
        driver.findElement(By.id("creation-data")).click();
        driver.findElement(By.id("creation-data")).clear();
        driver.findElement(By.id("creation-data")).sendKeys("2021-02-28");
        driver.findElement(By.id("creation-timestr")).click();
        driver.findElement(By.id("creation-timestr")).clear();
        driver.findElement(By.id("creation-timestr")).sendKeys("16:00");
        driver.findElement(By.id("creation-timeend")).click();
        driver.findElement(By.id("creation-timeend")).clear();
        driver.findElement(By.id("creation-timeend")).sendKeys("17:00");
        driver.findElement(By.id("creation-player")).click();
        driver.findElement(By.id("creation-player")).clear();
        driver.findElement(By.id("creation-player")).sendKeys("1");
        driver.findElement(By.id("form-creation")).click();
        driver.findElement(By.id("nameG1")).click();
        driver.findElement(By.id("nameG1")).clear();
        driver.findElement(By.id("nameG1")).sendKeys("Paolo");
        driver.findElement(By.id("surnameG1")).click();
        driver.findElement(By.id("surnameG1")).clear();
        driver.findElement(By.id("surnameG1")).sendKeys("Dello Buono");
        driver.findElement(By.id("creation-submit")).click();

        assertEquals("La prenotazione viene memorizzata con successo",
                closeAlertAndGetItsText());



    }

    @AfterAll
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    private String closeAlertAndGetItsText() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, 15);
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }
}
