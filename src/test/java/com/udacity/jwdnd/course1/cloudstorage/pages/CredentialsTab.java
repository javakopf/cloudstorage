package com.udacity.jwdnd.course1.cloudstorage.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CredentialsTab {

    @FindBy(id = "credential-url")
    private WebElement credentialUrl;
    @FindBy(id = "credential-username")
    private WebElement credentialUsername;
    @FindBy(id = "credential-password")
    private WebElement credentialPassword;
    @FindBy(id = "credentials-form")
    private WebElement credentialsForm;
    @FindBy(id="create-credential")
    private WebElement createCredential;
    @FindBy(id="nav-credentials-tab")
    private WebElement navCredentials;
    @FindBy(id = "credentialTable")
    private WebElement credentialsTable;
    @FindBy(id = "edit-credential1")
    private WebElement editCredential;
    @FindBy(id = "delete-credential1")
    private WebElement deleteCredential;


    private final WebDriver driver;

    public CredentialsTab(WebDriver driver){
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    public void createCredentials(String url,String userName,String password) throws InterruptedException {
        Thread.sleep(3000);
        this.createCredential.click();
        writeCredentials(url, userName, password);
        this.navCredentials.click();
    }
    public void updateCredentials(String url,String userName,String password) throws InterruptedException {
        Thread.sleep(3000);
        this.editCredential.click();
        Thread.sleep(3000);
        this.credentialUrl.clear();
        this.credentialUsername.clear();
        this.credentialPassword.clear();
        writeCredentials(url, userName, password);
        this.navCredentials.click();
    }

    private void writeCredentials(String url, String userName, String password) throws InterruptedException {
        Thread.sleep(3000);
        this.credentialUrl.sendKeys(url);
        this.credentialUsername.sendKeys(userName);
        Thread.sleep(3000);
        this.credentialPassword.sendKeys(password);
        Thread.sleep(3000);
        this.credentialsForm.submit();
        Thread.sleep(3000);
    }

    public void goToCredentialsTab() throws InterruptedException{
        Thread.sleep(3000);
        this.navCredentials.click();
    }

    public WebElement getCredentialsTable() {
        return credentialsTable;
    }

    public void deleteCredentials() {
        this.deleteCredential.click();
    }
}
