package com.udacity.jwdnd.course1.cloudstorage.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignupPage {
        @FindBy(name = "username")
        private WebElement usernameField;

        @FindBy(name = "password")
        private WebElement passwordField;

        @FindBy(name = "firstName")
        private WebElement firstNameField;

        @FindBy(name = "lastName")
        private WebElement lastNameField;

        @FindBy(name = "signupform")
        private WebElement form;

        @FindBy(id = "loginLink")
        private WebElement loginLink;

        public SignupPage(WebDriver driver) {
                PageFactory.initElements(driver, this);
            }

        public void signup(String firstName, String lastName, String username, String password) {
            this.firstNameField.sendKeys(firstName);
            this.lastNameField.sendKeys(lastName);
            this.usernameField.sendKeys(username);
            this.passwordField.sendKeys(password);
            this.form.submit();
            this.loginLink.click();
        }
}
