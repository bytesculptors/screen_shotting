package org.example;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        File test = get_scroll_screen_shot("https://itest.com.vn/lects/int3306.htm", 500);
        System.out.println("Hello World");
    }
    private static File get_scroll_screen_shot(String url, int scroll_length) {
        WebDriver chromeDriver = new ChromeDriver();
        chromeDriver.get(url);
        JavascriptExecutor js = (JavascriptExecutor) chromeDriver;
        js.executeScript("window.scrollTo(0, " + scroll_length + ")");
        File screenshotFile = ((TakesScreenshot) chromeDriver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenshotFile, new File("image.png"));
            System.out.println("Screenshot saved successfully.");
        } catch (IOException e) {
            System.out.println("Error");
        }
        chromeDriver.quit();
        return screenshotFile;
    }
    private static File get_full_screen_shot(String url) {
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");
        WebDriver chromeDriver = new ChromeDriver(chromeOptions);
        chromeDriver.manage().window().maximize();
        chromeDriver.get(url);
        int width = 1920;
        JavascriptExecutor js = (JavascriptExecutor) chromeDriver;
        long height = (long) js.executeScript("return Math.max(document.body.scrollHeight, document.body.offsetHeight, document.documentElement.clientHeight, document.documentElement.scrollHeight, document.documentElement.offsetHeight);");
        chromeDriver.manage().window().setSize(new Dimension(width, (int)height));
        File screenshotFile = ((TakesScreenshot) chromeDriver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenshotFile, new File("image.png"));
            System.out.println("Screenshot saved successfully.");
        } catch (IOException e) {
            System.out.println("Error");
        }
        chromeDriver.quit();
        return screenshotFile;
    }
}