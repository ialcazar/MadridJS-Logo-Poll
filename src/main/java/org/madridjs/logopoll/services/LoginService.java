package org.madridjs.logopoll.services;


import org.madridjs.logopoll.exceptions.EmailException;
import org.madridjs.logopoll.rest.UserRest;

public interface LoginService {

	UserRest login(UserRest userRest) throws EmailException;

}
