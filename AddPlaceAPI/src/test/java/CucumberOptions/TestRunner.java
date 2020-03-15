package CucumberOptions;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features={"src/test/java/Features"}, plugin={"json:target/jsonReports/cucumber-report.json"}, glue="StepDefinitions")

public class TestRunner extends AbstractTestNGCucumberTests {

}
