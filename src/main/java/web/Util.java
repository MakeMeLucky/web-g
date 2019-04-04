package web;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class Util {
    private String[] args;
    private static WebDriver driver;

    public Util(String[] args) {
        this.args = args;
    }

    void start(){
        System.setProperty("webdriver.chrome.driver","C:\\drivers\\chromedriver.exe");
        driver=new ChromeDriver();
        driver.get("https://www.google.com/");
        WebElement searchElement=driver.findElement(By.name("q"));
        searchElement.sendKeys(args);
        searchElement.submit();

        System.out.println("search results: "+searchResult());
        System.out.println("3 first links:");
        for (WebElement webElement : getList()) {
            System.out.println(webElement.getAttribute("href"));
        }

    }

    //return String result of search result

    String searchResult(){

        WebElement searchResult=driver.findElement(By.id("resultStats"));

        String result=searchResult.getText();
        result=result.substring(0,result.indexOf('('));
        result=result.replaceAll("\\D+","");
        return result;
    }

    //return List of 3 first links

    List<WebElement> getList(){
        List<WebElement> findElements = driver.findElements(By.xpath("//div[@class='r']/a"));
        findElements=findElements.subList(0,3);
        return findElements;
    }
}
