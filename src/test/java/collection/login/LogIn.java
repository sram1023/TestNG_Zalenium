package collection.login;

import com.demo.base.Context;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class LogIn extends Context {

    com.demo.pages.SignIn signIn = new com.demo.pages.SignIn();

    public LogIn() {
        super();
    }

    @BeforeSuite
    public void start(){
        init();
    }

    @Test
    public void login(){
        signIn.signIn();
        signIn.verifyLoginPage();
    }

    @AfterSuite
    public void tearDown1(){
        tearDown();
    }
}
