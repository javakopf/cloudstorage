package com.udacity.jwdnd.course1.cloudstorage.controllers;

import com.udacity.jwdnd.course1.cloudstorage.mapper.CredentialsMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.Credentials;
import com.udacity.jwdnd.course1.cloudstorage.services.EncryptionService;
import com.udacity.jwdnd.course1.cloudstorage.services.HomeService;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.security.SecureRandom;
import java.util.Base64;

@Controller
public class CredentialsController {

    private final UserService userService;
    private final CredentialsMapper credentialsMapper;
    private final EncryptionService encryptionService;
    private final HomeService homeService;

    public CredentialsController(UserService userService, CredentialsMapper credentialsMapper, EncryptionService encryptionService, HomeService homeService) {
        this.userService = userService;
        this.credentialsMapper = credentialsMapper;
        this.encryptionService = encryptionService;
        this.homeService = homeService;
    }

    @GetMapping("/credentials")
    public String handleDisplayCredentials(Model model) {
        model.addAttribute("credentialForm",new Credentials());
        return "home";
    }
    @GetMapping("/deleteCredentials")
    public String handleDeleteCredentials(@RequestParam(value = "credentialId", required = true) Integer credentialId,Model model) {
        int result = credentialsMapper.deleteCredentials(credentialId);
        if(result == 1){
            model.addAttribute("successMessage",
                    "Credentials successfully deleted " + credentialId + "!");
        }else {
            model.addAttribute("errorMessage",
                    "Error during delete Credentials with " + credentialId + "!");
        }
        return "result";
      }
        @PostMapping("/saveCredentials")
        public String handleSaveCredentials(@ModelAttribute("credentialsForm")Credentials credentialsForm ,Model model) {
            int result;
            Integer userId = userService.getUser().getUserId();
            Credentials credentials = credentialsMapper.loadCredentialsById(credentialsForm.getCredentialId());
            if(credentials == null)
            {
                SecureRandom random = new SecureRandom();
                byte[] key = new byte[16];
                random.nextBytes(key);
                String encodedKey = Base64.getEncoder().encodeToString(key);
                String encryptedPassword = encryptionService.encryptValue(credentialsForm.getPassword(), encodedKey);
                credentials = new Credentials(credentialsForm.getUrl(),credentialsForm.getUserName(),encodedKey,encryptedPassword,userId);
                result = credentialsMapper.saveCredentials(credentials);
            }else {
                String encodedKey = credentials.getKey();
                String encryptedPassword = encryptionService.encryptValue(credentialsForm.getPassword(), encodedKey);
                credentials.setUrl(credentialsForm.getUrl());
                credentials.setUserName(credentialsForm.getUserName());
                credentials.setPassword(encryptedPassword);
                result = credentialsMapper.updateCredentials(credentials);
            }

            if(result == 1){
                model.addAttribute("successMessage",
                        "Credentials successfully stored for url: " + credentialsForm.getUrl() + "!");
            }else {
                model.addAttribute("errorMessage",
                        "Error during storing url credentials " + credentialsForm.getUrl() + "!");
            }

            model = homeService.reloadModelWithData(model);


            return "home";
    }

    @GetMapping("/viewCredentials")
    public String handleViewCredentials(@ModelAttribute("credentialId")Integer credentialId,Model model) {
        Credentials credentials = credentialsMapper.loadCredentialsById(credentialId);
        String decryptKey = credentials.getKey();
        String decryptedPassword = encryptionService.decryptValue(credentials.getPassword(),decryptKey);
        model.addAttribute("decryptedPassword",decryptedPassword);
        return "home";
    }
}
