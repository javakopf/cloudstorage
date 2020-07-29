package com.udacity.jwdnd.course1.cloudstorage.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    @FindBy(id = "nav-notes-tab")
    private WebElement notesTab;
    @FindBy(id = "note-add")
    private WebElement createNoteField;
    @FindBy(id = "note-title")
    private WebElement noteTitleField;
    @FindBy(id = "note-description")
    private WebElement noteDescriptionField;
    @FindBy(id = "noteSubmit")
    private WebElement saveNote;
    @FindBy(id = "note-entry-title")
    private WebElement noteEntryTitle;
    @FindBy(id = "note-entry-description")
    private WebElement noteEntryDescription;
    @FindBy(id = "userTable")
    private WebElement userTable;
    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void createNote(String noteTitle,String noteDescription) {
        this.createNoteField.click();
        this.noteTitleField.sendKeys(noteTitle);
        this.noteDescriptionField.sendKeys(noteDescription);
        this.saveNote.submit();
        this.notesTab.click();
    }
    public void EditNote(String noteTitle,String noteDescription,String noteTitleEdit,String noteDescriptionEdit) {
        this.createNoteField.click();
        this.noteTitleField.sendKeys(noteTitle);
        this.noteDescriptionField.sendKeys(noteDescription);
        this.saveNote.submit();
        this.notesTab.click();
    }
    public void gotoNotesTab() {
        this.notesTab.click();
    }
    public WebElement getNoteEntryTitle() {
        return noteEntryTitle;
    }

    public WebElement getNoteEntryDescription() {
        return noteEntryDescription;
    }
}
