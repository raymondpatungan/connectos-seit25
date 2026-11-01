package io.cucumber.glue;

import io.cucumber.java.en.Given;
import io.cucumber.core.Context;
import io.cucumber.core.Manager;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.pages.Home;
import io.cucumber.pages.BasicAuth;
import io.cucumber.pages.SortableDataTables;
import java.util.List;
import java.util.stream.Collectors;
import io.cucumber.datatable.DataTable;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Navigation extends Context {
  private Home home;
  private BasicAuth basicAuth;
  private SortableDataTables sortableDataTables;

  public Navigation(Manager manager) {
    super(manager);
  }

  // ===============================================================
  // TEST_TI_0001: Homepage has a list of links to Expected examples
  // ===============================================================
  @Given("^the page under test is '(.+)'$")
  public void navToPage(String url) {
    manager.getDriver().get(url);
    home = new Home(getDriver());
    stash("exampleKey1", "exampleValue1");
  }
  @Then("the page should contain the following links")
  public void verifyLinks(List<String> expectedLinks) {

    List<String> missingLinks = expectedLinks.stream()
            .filter(link -> !home.checkLinks(link))
            .collect(Collectors.toList());

    assertTrue(
            missingLinks.isEmpty(),
            () -> "Missing links: " + String.join(", ", missingLinks)
    );
  }

  // ===============================================================
  // TEST_TI_0002: Basic Auth allows validated access
  // ===============================================================
  @When("the 'Basic Auth' example is opened")
  public void openBasicAuth() {
    home.openBasicAuth();
  }

  @When("valid credentials are supplied")
  public void enterCredentials() {
    basicAuth = new BasicAuth(getDriver());
    basicAuth.authenticate("admin", "admin");
  }

  @Then("'Congratulations! You must have the proper credentials.' should be displayed")
  public void verifyBasicAuthPage() {
    assertEquals("Congratulations! You must have the proper credentials.",
            basicAuth.getSuccessMessage()
    );
  }

  // ===============================================================
  // TEST_TI_0003: Sortable Data Tables - Example 1 displays the expected 4 results
  // ===============================================================
  @When("the 'Sortable Data Tables' is opened")
  public void openSortableDataTables() {
    home.openSortableDataTables();
    sortableDataTables = new SortableDataTables(getDriver());
  }

  @Then("the Example 1 table should display the following results")
  public void verifyExample1Table(DataTable expectedTable) {

    List<List<String>> expectedData = expectedTable.asLists(String.class);

    List<List<String>> actualData =
            sortableDataTables.getExample1TableData();

    assertEquals(
            expectedData,
            actualData,
            "Example 1 table data does not match the expected results."
    );
  }
}
