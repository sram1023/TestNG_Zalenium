package collection.login;

import com.demo.base.Context;
import org.testng.annotations.*;

public class LogIn extends Context {

    com.demo.pages.SignIn signIn = new com.demo.pages.SignIn();

    public LogIn() {
        super();
    }

    @BeforeTest
    @Parameters("browser")
    public void start(String browserName){
        init(browserName);
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
