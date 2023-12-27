package pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import static utils.WebActions.getProperty;


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
        this.page.navigate(getProperty("url"));
    }

    public boolean isLoginPageOpen() {
        return loginButton.isVisible();
    }

    public void setUserName(String user) {
        userName.fill(user);

    }

    public void setPassword(String pass) {
        password.fill(pass);
    }

    public void clickOnLogin() {
        loginButton.click();
    }

    public String getProductPageTitle() {
        return productsTitle.innerText();
    }

    public String getErrorMessage() {
        return error.innerText();
    }
}