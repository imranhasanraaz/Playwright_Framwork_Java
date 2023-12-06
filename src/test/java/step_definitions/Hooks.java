package step_definitions;

import com.microsoft.playwright.Page;
import com.microsoft.playwright.Tracing;
import factory.PlaywrightDriverFactory;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

import java.nio.file.Paths;

import static utilities.ConfigFileReader.getProperty;

public class Hooks {
    public PlaywrightDriverFactory driverFactory;
    public Page page;

    @Before
    public void launchBrowser() {
        String browserName = getProperty("browser");
        driverFactory = new PlaywrightDriverFactory();
        page = driverFactory.initDriver(browserName);
    }

    @After(order = 0)
    public void quitBrowser() {
        page.close();
    }

    @After(order = 1)
    public void takeScreenshotAndTrace(Scenario scenario) {
        if (scenario.isFailed()) {
            String screenshotName = scenario.getName().replace(" ", "_");
            byte[] sourcePath = page.screenshot();
            scenario.attach(sourcePath, "image/png", screenshotName);
            PlaywrightDriverFactory.context.tracing().stop(new Tracing.StopOptions().setPath(Paths.get("target/" + screenshotName + ".zip")));
        }
    }
}