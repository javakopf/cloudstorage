package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.pages.NotesTab;
import com.udacity.jwdnd.course1.cloudstorage.pages.LoginPage;
import com.udacity.jwdnd.course1.cloudstorage.pages.SignupPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class NotesTest {
    private static ChromeDriver driver;
    public String baseURL;
    @LocalServerPort
    private Integer port;
    private NotesTab noteTab;


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
        noteTab =  new NotesTab(driver);
        noteTab.gotoNotesTab();
    }

 /*   @Test
    public void  testCreateAndEditAndDeleteNoteSuccessfully()  {
            notePage.createNote("titleTest","descriptionTest");
            Assertions.assertEquals("titleTest", notePage.getNoteEntryTitle().getText());
            Assertions.assertEquals("descriptionTest", notePage.getNoteEntryDescription().getText());
            notePage.editNote("titleEdited","description edited");
            Assertions.assertEquals("titleEdited", notePage.getNoteEntryTitle().getText());
            Assertions.assertEquals("description edited", notePage.getNoteEntryDescription().getText());
        try{
            notePage.deleteNote();
            driver.get(baseURL + "/home");
            notePage.gotoNotesTab();
                WebElement elementById = driver.findElementById("note-entry-title");
                System.out.println(notePage.getNoteEntryTitle().getText());
        } catch (NoSuchElementException e) {
            System.out.println("success");
        }
   }*/
    @Test
    @Order(1)
    public void  testCreateNoteSuccessfully() throws InterruptedException {
        noteTab.createNote("titleTest","descriptionTest");
        Assertions.assertEquals("titleTest", noteTab.getNoteEntryTitle().getText());
        Assertions.assertEquals("descriptionTest", noteTab.getNoteEntryDescription().getText());
    }

    @Test
    @Order(2)
    public void  testEditNoteSuccessfully() throws InterruptedException {
        noteTab.editNote("titleEdited","description edited");
        Assertions.assertEquals("titleEdited", noteTab.getNoteEntryTitle().getText());
        Assertions.assertEquals("description edited", noteTab.getNoteEntryDescription().getText());
    }

    @Test
    @Order(3)
    public void  testDeleteNoteSuccessfully() throws InterruptedException {
        Thread.sleep(3000);
        noteTab.deleteNote();
        driver.get(baseURL + "/home");
        noteTab.gotoNotesTab();
        Assertions.assertThrows(NoSuchElementException.class, () -> {
            driver.findElementById("note-entry-title");
        });
    }
}
