package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;
import java.util.UUID;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import org.apache.logging.log4j.LogManager; //Log4j
import org.apache.logging.log4j.Logger; //Log4j

public class BaseClass {
	public static WebDriver driver;// to prevent any new instance creation of driver while creating an object of
									// base class we make it static

//	implementing logs
	public Logger logger;
	public Properties prop;

//	Setup
	@BeforeClass(groups = { "base" })
	@Parameters({ "OS", "browser" })
	public void setup(String OS, String browser) throws IOException {
//		FileReader file=new FileReader("./src/test/resources/config.properties");
//		setting up properties file
		FileReader file = new FileReader(System.getProperty("user.dir") + "/src/test/resources/config.properties");
		prop = new Properties();
		prop.load(file);

		logger = LogManager.getLogger(this.getClass());
//		Setup for selenium grid
		if (prop.getProperty("execution_env").equalsIgnoreCase("remote")) {
			DesiredCapabilities capabilities = new DesiredCapabilities();

//			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
//			capabilities.setBrowserName("chrome");
//			capabilities.setPlatform(Platform.WINDOWS);

			// os
			if (OS.equalsIgnoreCase("Linux")) {
				capabilities.setPlatform(Platform.LINUX);
			} else if (OS.equalsIgnoreCase("Linux")) {
				capabilities.setPlatform(Platform.WIN11);

			} else {
				System.out.println("No matching os");
				return;
			}

			// browser
			switch (browser.toLowerCase()) {
			case "chrome":
				capabilities.setBrowserName("chrome");
				break;
			case "edge":
				capabilities.setBrowserName("MicrosoftEdge");
				break;
			case "firefox":
				capabilities.setBrowserName("firefox");
				break;
			default:
				System.out.println("No matching browser");
				return;
			}

			driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
		}

//		if(prop.getProperty("execution_env").equalsIgnoreCase("remote")) {
////			System.out.println("remote");
//			String hubURL="http://localhost:4444/wd/hub";
//			DesiredCapabilities capabilities=new DesiredCapabilities();
//			if(OS.equalsIgnoreCase("windows")) {
//				capabilities.setPlatform(Platform.WIN11);
//			}
//			else if(OS.equalsIgnoreCase("mac")) {
//				capabilities.setPlatform(Platform.MAC);
//			}
//			else {
//				System.out.println("No matching OS");
//			}
//			switch(browser.toLowerCase()) {
//			case "chrome" : capabilities.setBrowserName("chrome"); break;
//			case "edge" : capabilities.setBrowserName("MicrosoftEdge"); break;
//			default : System.out.println("No matching browser"); return; 
//			}
////			driver=new RemoteWebDriver(new URL("http://192.168.1.5:4444/wd/hub"),(capabilities));
//			WebDriver driver = new RemoteWebDriver((CommandExecutor) new URL(hubURL), capabilities);
//
//		}
		if (prop.getProperty("execution_env").equalsIgnoreCase("local")) {

			switch (browser.toLowerCase()) {
			case "chrome":
				driver = new ChromeDriver();
				break;
			case "firefox":
				driver = new FirefoxDriver();
				break;
			case "edge":
				driver = new EdgeDriver();
				break;
			default:
				System.out.println("Invalid browser name");
				return;

			}
		}

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get(prop.getProperty("appURL"));

	}

//	TearDown
	@AfterClass(groups = { "base" })
	public void tearDown() {
		driver.quit();
	}

//	CreateRandomString
	public String randomString() {
		UUID randomUUID = UUID.randomUUID();
		return randomUUID.toString().replaceAll("_", "").substring(0, 3);

	}

//	CreateRandomNumber
	public String randomNumber() {
		Double randNum = Math.random() * 9000000000.0 + 1000000000;
		return (randNum.toString());
	}

//	take screenshot
	public String captureScreen(String tname) throws IOException {
//		Date date=new Date();
		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
//		making source file/real screenshot file
		File sourceFile = takesScreenshot.getScreenshotAs(OutputType.FILE);

//		naming the screenshot file  
		String targetFilePath = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timeStamp + ".png";

//		making the screenshot file
		File targetFile = new File(targetFilePath);

//		copy source file to target file
		sourceFile.renameTo(targetFile);
		return targetFilePath;
	}
}
