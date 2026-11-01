package io.cucumber.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import java.util.List;

public class Home extends Page {

  public Home(ChromeDriver driver) {
    super(driver);
    System.out.println("Homepage title is : " + getTitle().getText());
  }

  @FindBy(css = "h1")
  private WebElement title;

  public WebElement getTitle() {
    return title;
  }

  public void refresh() {
    driver.navigate().refresh();
    System.out.println("Refreshed page");
  }

  //Retrieve all links
  public List<WebElement> getLinks(){
    return driver.findElements(By.tagName("a"));
  }

  //Match links
  public boolean checkLinks(String links){
    return getLinks().stream().anyMatch(link -> link.getText().trim().equals(links));
  }

  @FindBy(linkText = "Basic Auth")
  private WebElement basicAuthLink;

  // Open the Basic Auth example
  public void openBasicAuth() {
    basicAuthLink.click();
  }

  @FindBy(linkText = "Sortable Data Tables")
  private WebElement sortableDataTables;

  // Open the Basic Auth example
  public void openSortableDataTables() {
    sortableDataTables.click();
  }
}
