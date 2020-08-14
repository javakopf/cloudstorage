package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.pages.CredentialsTab;
import com.udacity.jwdnd.course1.cloudstorage.pages.LoginPage;
import com.udacity.jwdnd.course1.cloudstorage.pages.SignupPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.List;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CredentialsTest {
    private static ChromeDriver driver;
    public String baseURL;
    @LocalServerPort
    private Integer port;
    private CredentialsTab credentialsTab;


    @BeforeAll
    public static void prepareForTest() {
        WebDriverManager.chromedriver().setup();
    }


    @AfterEach
    public void afterEach() {
        if (this.driver != null) {
            driver.quit();
        }
    }


    @BeforeEach
    public void beforeEach() throws InterruptedException {
        this.driver = new ChromeDriver();
        baseURL = "http://localhost:" + port ;
        String username = "pzastoup";
        String password = "whatabadpassword";
        driver.get(baseURL + "/signup");
        SignupPage signupPage = new SignupPage(driver);
        signupPage.signup("Peter", "Zastoupil", username, password);
        driver.get(baseURL + "/login");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);
        driver.get(baseURL + "/home");
        credentialsTab =  new CredentialsTab(driver);
        credentialsTab.goToCredentialsTab();
    }
    @Test
    @Order(1)
    public void  testCreateCredentialsSuccessfully() throws InterruptedException {
        credentialsTab.createCredentials("https://www.example.com","jUser","jPass123");
        Thread.sleep(3000);
        credentialsTab.goToCredentialsTab();
        WebElement baseTable = credentialsTab.getCredentialsTable();
             List<WebElement> tableRows = baseTable.findElements(By.tagName("tr"));
            //To locate columns(cells) of that specific row.
            List<WebElement> url_cells = tableRows.get(1).findElements(By.tagName("th"));
                String url = url_cells.get(0).getText();


        //To locate columns(cells) of that specific row.
            List<WebElement> Columns_row = tableRows.get(1).findElements(By.tagName("td"));
            String username = Columns_row.get(1).getText();
            String password = Columns_row.get(2).getText();
            Assertions.assertEquals("https://www.example.com", url);
            Assertions.assertEquals("jUser", username);
            Assertions.assertNotNull(password);
        }

    @Test
    @Order(2)
    public void  testUpdateCredentials() throws InterruptedException {
        credentialsTab.updateCredentials("https://www.email.com","mUser","pass123");
        Thread.sleep(3000);
        credentialsTab.goToCredentialsTab();
        WebElement baseTable = credentialsTab.getCredentialsTable();
        List<WebElement> tableRows = baseTable.findElements(By.tagName("tr"));
        //To locate columns(cells) of that specific row.
        List<WebElement> url_cells = tableRows.get(1).findElements(By.tagName("th"));
        String url = url_cells.get(0).getText();


        //To locate columns(cells) of that specific row.
        List<WebElement> Columns_row = tableRows.get(1).findElements(By.tagName("td"));
        String username = Columns_row.get(1).getText();
        String password = Columns_row.get(2).getText();
        Assertions.assertEquals("https://www.email.com",url);
        Assertions.assertEquals("mUser", username);
    }

    @Test
    @Order(3)
    public void  testDeleteCredentialsSuccessfully() throws InterruptedException {
        Thread.sleep(3000);
        credentialsTab.deleteCredentials();
        driver.get(baseURL + "/home");
        credentialsTab.goToCredentialsTab();
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            driver.findElementById("delete-credential1");
        });
    }
}
