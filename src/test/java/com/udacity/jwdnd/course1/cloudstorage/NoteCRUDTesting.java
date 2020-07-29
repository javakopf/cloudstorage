package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.pages.HomePage;
import com.udacity.jwdnd.course1.cloudstorage.pages.LoginPage;
import com.udacity.jwdnd.course1.cloudstorage.pages.SignupPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class NoteCRUDTesting {
    private static ChromeDriver driver;
    public String baseURL;
    @LocalServerPort
    private Integer port;


    @BeforeAll
    public static void prepareForTest() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @AfterAll
    public static void afterAll() {
        driver.quit();
        driver = null;
    }


    @BeforeEach
    public void beforeEach() {
        baseURL = "http://localhost:" + port ;
    }

    @Test
    public void  testCreateNoteSuccessfully() {
        String username = "pzastoup";
        String password = "whatabadpassword";
        driver.get(baseURL + "/signup");
        SignupPage signupPage = new SignupPage(driver);
        signupPage.signup("Peter", "Zastoupil", username, password);
        driver.get(baseURL + "/login");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);
        driver.get(baseURL + "/home");
        HomePage notePage = new HomePage(driver);
        notePage.gotoNotesTab();
        notePage.createNote("titleTest","descriptionTest");
        Assertions.assertEquals("titleTest", notePage.getNoteEntryTitle().getText());
        Assertions.assertEquals("descriptionTest", notePage.getNoteEntryDescription().getText());
    }
    @Test
    public void  testEditNoteSuccessfully() {
        String username = "pzastoup";
        String password = "whatabadpassword";
        driver.get(baseURL + "/signup");
        SignupPage signupPage = new SignupPage(driver);
        signupPage.signup("Peter", "Zastoupil", username, password);
        driver.get(baseURL + "/login");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);
        driver.get(baseURL + "/home");
        HomePage notePage = new HomePage(driver);
        notePage.gotoNotesTab();
        notePage.createNote("titleTest","descriptionTest");
        Assertions.assertEquals("titleTest", notePage.getNoteEntryTitle().getText());
        Assertions.assertEquals("descriptionTest", notePage.getNoteEntryDescription().getText());
    }

    @Test
    public void  tesDeleteNoteSuccessfully() {
        String username = "pzastoup";
        String password = "whatabadpassword";
        driver.get(baseURL + "/signup");
        SignupPage signupPage = new SignupPage(driver);
        signupPage.signup("Peter", "Zastoupil", username, password);
        driver.get(baseURL + "/login");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);
        driver.get(baseURL + "/home");
        HomePage notePage = new HomePage(driver);
        notePage.gotoNotesTab();
        notePage.createNote("titleTest","descriptionTest");
        Assertions.assertEquals("titleTest", notePage.getNoteEntryTitle().getText());
        Assertions.assertEquals("descriptionTest", notePage.getNoteEntryDescription().getText());
    }
}
