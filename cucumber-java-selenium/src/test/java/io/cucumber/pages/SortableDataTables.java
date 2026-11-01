package io.cucumber.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class SortableDataTables extends Page {

    public SortableDataTables(ChromeDriver driver) {
        super(driver);
    }

    @FindBy(id = "table1")
    private WebElement example1Table;

    // Retrieve the table headers and data, excluding the Action column
    public List<List<String>> getExample1TableData() {
        List<List<String>> tableData = new ArrayList<>();
        List<WebElement> rows = example1Table.findElements(By.cssSelector("tr"));

        for (WebElement row : rows) {
            List<List<String>> unused = new ArrayList<>();
            List<WebElement> cells = row.findElements(By.cssSelector("th:not(:last-child), td:not(:last-child)"));

            List<String> rowData = new ArrayList<>();

            for (WebElement cell : cells) {
                rowData.add(cell.getText().trim());
            }

            tableData.add(rowData);
        }

        return tableData;
    }
}