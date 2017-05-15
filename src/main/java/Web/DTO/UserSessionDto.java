/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Web.DTO;

import Domain.User;
import Domain.UserRole;

/**
 *
 * @author azurwular
 */
public class UserSessionDto
{
    private Integer id;
    private UserRole role;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String address;
    private String zipcode;
    private String city;
    private String country;
    
    public UserSessionDto(User user)
    {
        this.id = user.getId();
        this.role = user.getRole();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.phone = user.getPhone();
        this.address = user.getAddress();
        this.zipcode = user.getZipcode();
        this.city = user.getCity();
        this.country = user.getCountry();
    }

    public String getLastName()
    {
        if (lastName == null)
        {
            return "";
        }
        
        return lastName;
    }

    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getPhone()
    {
        if (phone == null)
        {
            return "";
        }
        
        return phone;
    }

    public void setPhone(String phone)
    {
        this.phone = phone;
    }

    public String getAddress()
    {
        if (address == null)
        {
            return "";
        }
        
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public String getZipcode()
    {
        if (zipcode == null)
        {
            return "";
        }
        
        return zipcode;
    }

    public void setZipcode(String zipcode)
    {
        this.zipcode = zipcode;
    }

    public String getCity()
    {
        if (city == null)
        {
            return "";
        }
        
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public String getCountry()
    {
        if (country == null)
        {
            return "";
        }
        
        return country;
    }

    public void setCountry(String country)
    {
        this.country = country;
    }

    public UserSessionDto()
    {
        this.role = UserRole.Visitor;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public String getFirstName()
    {
        if (firstName == null)
        {
            return "";
        }
        
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public UserRole getRole()
    {
        return role;
    }

    public void setRole(UserRole role)
    {
        this.role = role;
    }
}
