package api.tests;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class BaseTest {
    Properties prop = new Properties();

    @BeforeClass(alwaysRun = true)
    public void setUp() {
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("configuration.properties")) {
            prop.load(input);
            RestAssured.baseURI = prop.get("apiBaseUrl").toString();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
