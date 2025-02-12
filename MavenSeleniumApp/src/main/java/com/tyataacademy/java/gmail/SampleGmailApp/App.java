package com.tyataacademy.java.gmail.SampleGmailApp;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");

		System.setProperty("webdriver.chrome.driver", "C:\\Users\\mails\\Downloads\\Software\\Selenium\\chromedriver.exe");

		//Define the web driver for walgreeens
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--remote-allow-origins=*");
		WebDriver driver = new ChromeDriver(options);


		String dealUrl="";
		String targetLabel="";
		try {
			driver.get("https://slickdeals.net/");
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			System.out.println("Window Handle: "+driver.getWindowHandle());

			try {
				driver.findElement(By.xpath("/html/body/div[14]/div[1]/div[1]/a/img")).click();

			}catch(Exception e){
				driver.findElement(By.xpath("/html/body/div[13]/div[1]/div[1]/a/img")).click();				
			}

			try {
				driver.findElement(By.xpath("/html/body/div[14]/div[2]/button")).click();

			}catch(Exception e){
				driver.findElement(By.xpath("/html/body/div[13]/div[2]/button")).click();
			}


			driver.findElement(By.xpath("//*[@id=\"pageContent\"]/div[2]/a/img")).click();


			//Input Search Text
			driver.findElement(By.xpath("//*[@id=\"search\"]")).sendKeys("walgreens free photo");

			//Click Search
			driver.findElement(By.xpath("//*[@id=\"slickdealsHeaderSearch\"]/button/span")).click();

			List<WebElement> elements = driver.findElements(By.className("resultRow"));
			int counter = 0;
			String inputLabel="";
			//String targetLabel="";
			for (WebElement element : elements) {
				if(element.getText().contains("Walgreens Photo") && element.getText().indexOf("Expired")<0 && element.getText().contains("Free + Free")) {
					counter++;
					if(counter ==1) {
						//System.out.println(element.getText());
						inputLabel = element.getText();
						//System.out.println(inputLabel);
						int index = inputLabel.indexOf("Posted");
						int indexOfExpred = inputLabel.indexOf("Expired");
						targetLabel = inputLabel.substring(0, index);


						WebElement linkElement = element.findElement(By.tagName("a"));
						if (linkElement != null) {
							// Get the URL from the <a> tag
							String url = linkElement.getAttribute("href");
							//System.out.println("URL: " + url);
							dealUrl = url;
						}
					}
				}
			}

			//targetLabel
			if(!dealUrl.isEmpty()) {
				System.out.println("DealURL: "+dealUrl+"\n"+targetLabel);				
			}else {
				System.out.println("Sorry No deal this time!");
				dealUrl = "Sorry No Walgreen Photo deal today!";
			}
		}catch(Exception e){
			System.out.print(e.toString());
		}finally {
			//driver.quit();
		}

		
    }
}
