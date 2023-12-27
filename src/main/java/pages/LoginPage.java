package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import utils.WebActions;


public class LoginPage {
    private final Locator userName;
    private final Locator password;
    private final Locator loginButton;
    private final Locator error;
    private final Locator productsTitle;
    private final Page page;

    public LoginPage(Page page) {
        this.page = page;
        this.userName = page.locator("//input[@id='user-name']");
        this.password = page.locator("//input[@id='password']");
        this.loginButton = page.locator("//input[@id='login-button']");
        this.error = page.locator("//h3[@data-test='error']");
        this.productsTitle = page.locator("//span[@class='title']");
    }

    public void navigateToUrl() {
        this.page.navigate(WebActions.getProperty("url"));
    }

    public boolean isLoginPageOpen() {
        return WebActions.waitUntilElementDisplayed(loginButton, Integer.parseInt(WebActions.getProperty("timeout")));
    }

    public void setUserName(String user) {
        WebActions.fill(userName, user);
    }


    public void setPassword(String pass) {
        WebActions.fill(password, pass);
    }

    public void clickOnLogin() {
        WebActions.clicked(loginButton);
    }

    public String getProductPageTitle() {
        return WebActions.getInnerText(productsTitle);
    }

    public String getErrorMessage() {
        return WebActions.getInnerText(error);
    }
}