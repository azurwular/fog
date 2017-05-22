/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Web.DTO;

import java.util.HashMap;

/**
 *
 * @author azurwular
 */
public class ValidationResult
{
    private boolean isValid;
    private HashMap<String, String> errors;

    public ValidationResult()
    {
        this.isValid = false;
        this.errors = new HashMap<>();
    }

    public boolean isValid() {
        return isValid;
    }

    public void setIsValid(boolean isValid) {
        this.isValid = isValid;
    }

    public HashMap<String, String> getErrors() {
        return errors;
    }

    public void setErrors(HashMap<String, String> errors) {
        this.errors = errors;
    }
}