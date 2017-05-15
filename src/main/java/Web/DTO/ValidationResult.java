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
    public boolean isValid;
    public HashMap<String, String> errors;

    public ValidationResult()
    {
        this.isValid = false;
        this.errors = new HashMap<>();
    }
}
