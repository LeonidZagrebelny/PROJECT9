import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeOptions;



public class automatedCardOrder {

    System.setProperty("webdriver. chrome.driver","/path/to/chromedriver");
    System.setProperty("webdriver.chrome.driver", "C:\\tmp\\chromedriver.exe");


    @BeforeAll
    public static void setupAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
        driver = null;
    }


    @Test
    void shouldSubmitRequest() {
        driver.get("http://localhost:9999");
        WebElement form = driver.findElement(By.cssSelector("[data-test-id='callback-form']"));
        form.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Геннадий");
        form.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79220142428");
        form.findElement(By.cssSelector("[data-test-id='agreement']")).click();
        form.findElement(By.cssSelector("[class='button.button_view_extra']")).click();
        String text = driver.findElement(By.className("alert-success")).getText();
        assertEquals("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.", text.trim());
    }


}

