package ca.danielvega.learning.seleniumforcrudsimple.crudLinkTests;

import ca.danielvega.learning.seleniumforcrudsimple.junit.groups.SmockTests;
import org.junit.experimental.categories.Categories.ExcludeCategory;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@ExcludeCategory(SmockTests.class)
@Suite.SuiteClasses({
//    CreateLinksTest.class,
//    DisplayLinksTest.class,
//    GridLinksTest.class,
    UpdateDevicePageTest.class
})
public class CrudLinkTestSuite {

}
