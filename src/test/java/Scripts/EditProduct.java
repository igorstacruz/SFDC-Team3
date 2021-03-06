package Scripts;

import Framework.BrowserManager;
import Pages.Contacts.ContactsHome;
import Pages.Contacts.ContactsProfile;
import Pages.Contacts.NewContactForm;
import Pages.LoginPage;
import Pages.LookUp.LookUpWindow;
import Pages.MainApp;
import Pages.TabBar;
import Pages.Products.NewProductForm;
import Pages.Products.ProductProfile;
import Pages.Products.ProductsHome;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.annotations.AfterTest;

/**
 * Created by Ruber Cuellar
 */
public class EditProduct {

    //region Objects
    private ProductsHome productsHome;
    private LookUpWindow lookUpWindow;
    private MainApp mainApp;
    private TabBar tabBar;
    private ProductProfile productProfile;
    private NewProductForm newProductForm;
    private LoginPage loginPage;
    //endregion

    //region values
    private String  productName                 = "product_001";
    private String  productNameUpdated          = "product_002";
    private String  productCode                 = "prod_001";
    private String  productCodeUpdated          = "prod_002";
    private String  descriptionProduct          = "description Test";
    private String  descriptionProductUpdated   = "description Test2";
    private boolean isActive                    = true;
    private String  productFamily               = "None";
    //endregion

    @BeforeTest
    public void BeforeTest()
    {
        loginPage = new LoginPage();
        mainApp = loginPage.loginAsPrimaryUser();
        tabBar = mainApp.goToTabBar();
        productsHome = tabBar.clickOnProductsHome();
        newProductForm = productsHome.clickNewButton();
        productProfile = newProductForm
                .setProductName(productName)
                .setProductCode(productCode)
                .checkActiveFlag(isActive)
                .chooseProductFamilyDdl(productFamily)
                .setDescription(descriptionProduct)
                .pressSaveBtn();
    }

    @Test
    public void EditProduct()
    {
        newProductForm = productProfile.clickEditProduct();
        productProfile = newProductForm
                .setProductName(productNameUpdated)
                .setProductCode(productCodeUpdated)
                .checkActiveFlag(isActive)
                .chooseProductFamilyDdl(productFamily)
                .setDescription(descriptionProductUpdated)
                .pressSaveBtn();

        Assert.assertEquals(productProfile.getProductName(), productNameUpdated);
        Assert.assertEquals(productProfile.getProductcode(), productCodeUpdated);
        Assert.assertEquals(productProfile.isActiveFlag(), isActive);
        Assert.assertEquals(productProfile.getProductFamily(), productFamily);
        Assert.assertEquals(productProfile.getDescription(), descriptionProductUpdated);

    }

    @AfterTest
    public void afterTest()
    {
        productProfile.pressDeleteBtn();
    }
}
