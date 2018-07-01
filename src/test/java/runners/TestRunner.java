package runners;
import com.cucumber.listener.Reporter;
import org.junit.AfterClass;
import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import com.cucumber.listener.ExtentCucumberFormatter;

import java.io.File;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/functionalTests",
        glue= {"stepDefinitions"},
        plugin = {"com.cucumber.listener.ExtentCucumberFormatter:target/cucumber-reports/report.html"}
)
public class TestRunner {
    @AfterClass
    public static void writeExtentReport() {
        Reporter.loadXMLConfig(new File("C:\\Users\\Ajay\\eclipse-workspace\\TestCucumber\\configs\\extent-config.xml"));
    }
}