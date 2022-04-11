package collection.login;

import com.demo.base.Context;
import com.demo.pages.AccountCreation;
import org.testng.annotations.Test;

public class CreateAccount extends Context {

    AccountCreation accountCreation = new AccountCreation();

    @Test
    public void emailAddress(){
        accountCreation.setEmailAddress("cdd@test1.com");
        accountCreation.clickCreateAccount();
    }

    @Test
    public void personalInformation(){
        accountCreation.title("Mr.");
        accountCreation.firstName("John");
        accountCreation.lastName("Miller");
        accountCreation.password("test123");
        log.info("Personal information details are entered");
    }

}
