package org.madridjs.logopoll.unitTests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
import org.madridjs.logopoll.services.LoginServiceUnitTests;
import org.madridjs.logopoll.services.MailServiceUnitTests;
import org.madridjs.logopoll.services.VotesServiceUnitTests;
import org.madridjs.logopoll.validators.UserValidatorTest;
import org.madridjs.logopoll.web.*;

@RunWith(Suite.class)
@SuiteClasses({VotesControllerUnitTests.class,LoginControllerUnitTests.class,LogosControllerUnitTests.class,MailServiceUnitTests.class,LoginServiceUnitTests.class,UserValidatorTest.class,VotesServiceUnitTests.class})
public class AllUnitTests {

}
