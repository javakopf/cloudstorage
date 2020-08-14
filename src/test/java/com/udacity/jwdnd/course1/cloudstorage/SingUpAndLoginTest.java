package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.pages.LoginPage;
import com.udacity.jwdnd.course1.cloudstorage.pages.SignupPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SingUpAndLoginTest {

    @LocalServerPort
    private Integer port;

    private static WebDriver driver;

    private LoginPage loginPage;
    private SignupPage signupPage;

    public String baseURL;

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
    public void testUnauthorizedUserAccessOnlyLoginAndSignUp() {
            driver.get(baseURL + "/signup");
            Assertions.assertEquals("Sign Up", driver.getTitle());

            driver.get(baseURL + "/login");
            Assertions.assertEquals("Login", driver.getTitle());

            driver.get(baseURL + "/home");
            Assertions.assertNotEquals("Home", driver.getTitle());
    }

    @Test
    public void  testUserSignupAndLogin() {
        try {
            String username = "pzastoup";
            String password = "whatabadpassword";

            driver.get(baseURL + "/signup");

            SignupPage signupPage = new SignupPage(driver);
            signupPage.signup("Peter", "Zastoupil", username, password);

            driver.get(baseURL + "/login");

            LoginPage loginPage = new LoginPage(driver);
            loginPage.login(username, password);
            driver.get(baseURL + "/home");
            Assertions.assertEquals("Home", driver.getTitle());

        }catch (Exception ex){
            fail("Error during signup and login"+ex.getMessage(),ex);
        }

    }

}
