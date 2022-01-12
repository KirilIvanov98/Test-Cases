import org.junit.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.concurrent.TimeUnit;
import static org.junit.Assert.assertEquals;

public class TestLogin {

    private WebDriver driver;
    String baseUrl = "https://zamunda.net/";
    String expectedUrl = "https://zamunda.net/index.php";
    String validUsername = "example1";
    String validPassword = "123456";
    Integer waitTime = 5;

    @Before
    public void SetUp() {
        System.setProperty("webdriver.chrome.driver",
                "C:\\Users\\kircho\\Downloads\\chromedriver_win32\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void LoginValidate() throws InterruptedException {
        driver.get(baseUrl);
        driver.manage().window().maximize();
        TimeUnit.SECONDS.sleep(waitTime);

        WebElement loginIcon = driver.findElement(By.id("foruicon"));
        loginIcon.click();

        WebElement usernameField = driver.findElement(By.name("username"));
        usernameField.click();
        usernameField.sendKeys(validUsername);

        WebElement passwordField = driver.findElement(By.name("password"));
        passwordField.click();
        passwordField.sendKeys(validPassword);

        WebElement loginButton = driver.findElement(By.xpath("/html/body/div[4]/div/table/tbody/tr[1]/td/table/tbody/tr/td/form/table/tbody/tr[3]/td/input"));
        loginButton.click();

        TimeUnit.SECONDS.sleep(waitTime);

        assertEquals(
                expectedUrl,
                driver.getCurrentUrl()
        );

        WebElement username = driver.findElement(By.xpath("//*[@id=\"usrname\"]"));
        assertEquals(
                validUsername,
                username.getText()
        );

        WebElement exitButton = driver.findElement(By.id("logoutt"));
        exitButton.click();

        TimeUnit.SECONDS.sleep(waitTime);

        assertEquals(
                baseUrl,
                driver.getCurrentUrl()
                );
    }

    @After
    public void TearDown() {

        driver.quit();
    }
}
