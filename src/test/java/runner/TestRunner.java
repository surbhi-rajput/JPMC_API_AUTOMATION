package runner;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
    
@CucumberOptions(tags = "@JPMC", features = {"src/test/resources/features/"}, glue = {"stepDefinitions"},
                 plugin =  {"pretty","html:target/cucumber/cucumber-pretty","json:target/cucumber/cucumber.json"})
    
public class TestRunner extends AbstractTestNGCucumberTests {
	@Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        return super.scenarios();
    }
}
