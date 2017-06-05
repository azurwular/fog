/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Services.UserValidator;
import Web.DTO.ValidationResult;
import java.util.Arrays;
import java.util.Collection;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 *
 * @author azurwular
 */
@RunWith(Parameterized.class)
public class ValidateEmailTest
{   
    @Parameters
    public static Collection<Object[]> testEmails() {
      return Arrays.asList(new Object[][]
      {
          { "asdf@asdf.com", true },
          { "asdf123@asd234.co", true },
          { "", false },
          { "asdjfhv", false },
          { "asdfkjn@asidfu", false },
          { "234@asd.com", true }
      });
    }
    
    private String emailInput;
    
    private boolean isValid;
    
    public ValidateEmailTest(String emailInput, boolean isValid)
    {
        this.emailInput = emailInput;
        this.isValid = isValid;
    }

    @Test
    public void ValidateEmailDoesNotAddErrorsWithValidEmails()
    {
        UserValidator userValidator = new UserValidator();
        ValidationResult result = new ValidationResult();
        
        userValidator.validateEmail(result, this.emailInput);
        
        assertThat(result.getErrors().isEmpty(), is(equalTo(this.isValid)));
    }
}
