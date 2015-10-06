package swip.ch07managingwebdriver.injectingdriver;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import java.net.URI;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = WebDriverConfig.class)
public class InjectedBaseUrlIT {
    @Inject
    private WebDriver driver;

    @Inject
    private URI baseUrl;

    @Test
    public void loadIndexPage() throws Exception {
        driver.get(baseUrl + "/index.html");
    }
}