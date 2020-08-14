package com.udacity.jwdnd.course1.cloudstorage.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class NotesTab {
    @FindBy(id = "nav-notes-tab")
    private WebElement notesTab;
    @FindBy(id = "note-add")
    private WebElement createNoteField;
    @FindBy(id = "note-title")
    private WebElement noteTitleField;
    @FindBy(id = "note-description")
    private WebElement noteDescriptionField;
    @FindBy(id = "saveNotesForm")
    private WebElement saveNote;

    @FindBy(id = "note-entry-title")
    private WebElement noteEntryTitle;
    @FindBy(id = "note-entry-description")
    private WebElement noteEntryDescription;
    @FindBy(id = "note-edit")
    private WebElement noteEdit;
    @FindBy(id = "note-delete")
    private WebElement noteDelete;

    public NotesTab(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void createNote(String noteTitle,String noteDescription) throws InterruptedException {
        Thread.sleep(3000);
        this.createNoteField.click();
        writeTitleAndDescription(noteTitle, noteDescription);
    }

    private void writeTitleAndDescription(String noteTitle, String noteDescription) throws InterruptedException {
        Thread.sleep(3000);
        this.noteTitleField.sendKeys(noteTitle);
        this.noteDescriptionField.sendKeys(noteDescription);
        Thread.sleep(3000);
        this.saveNote.submit();
        Thread.sleep(3000);
        this.notesTab.click();
        Thread.sleep(3000);
    }

    public void editNote(String noteTitleEdit,String noteDescriptionEdit) throws InterruptedException {
        Thread.sleep(3000);
        this.noteEdit.click();
        Thread.sleep(2000);
        this.noteTitleField.clear();
        this.noteDescriptionField.clear();
        Thread.sleep(1000);
        writeTitleAndDescription(noteTitleEdit, noteDescriptionEdit);
    }
    public void deleteNote() {
        this.noteDelete.click();
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
