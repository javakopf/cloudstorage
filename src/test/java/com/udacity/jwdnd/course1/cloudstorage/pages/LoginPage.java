package com.udacity.jwdnd.course1.cloudstorage.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    @FindBy(id = "inputUsername")
    private WebElement usernameField;

    @FindBy(name = "password")
    private WebElement passwordField;

    @FindBy(name = "submit")
    private WebElement login;

    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
    public void login(String username,String password) {
       usernameField.sendKeys(username);
       passwordField.sendKeys(password);
       login.click();
    }

}
