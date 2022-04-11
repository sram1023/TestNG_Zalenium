package com.demo.pages;

import com.demo.base.Context;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;


public class SignIn extends Context {

    By logIn = By.xpath("//a[contains(text(),'Sign in')]");

    By authenLabel = By.xpath("//h1[contains(text(),'Authentication')]");

    public void signIn(){
        driver.findElement(logIn).click();
    }

    public void verifyLoginPage(){
        WebElement authentication = driver.findElement(authenLabel);
        Assert.assertTrue(authentication.isDisplayed());
        log.info("Authentication Label is displayed");
    }
}
