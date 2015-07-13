package ca.danielvega.learning.seleniumforcrudsimple.smockTests;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    CreateDevicePageTest.class,
    DisplayDevicePageTest.class,
    GridDevicePageTest.class,
    LoginPageTest.class,
    RemoveDevicePageTest.class,
    UpdateDevicePageTest.class
})
public class SmockTestSuite {
    
}
