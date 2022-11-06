package tests.different;

import helper.ConfigProperties;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;
import tests.BaseTestClass;

public class ShoppingBasketTest extends BaseTestClass {
    private static final Logger LOG = Logger.getLogger(ShoppingBasketTest.class);

    @Test
    public void checkDeleteProductButtonTest() {
        LOG.info("Search any product");
        String product = mainPage.searchProduct(ConfigProperties.getProperty("product1"));
        Assert.assertTrue(mainPage.isSearchContentProduct(product));

        LOG.info("Add to basket");
        mainPage.addProductToCartAndGoToCart(product);

        LOG.info("Check the product was delete");
        shoppingCartPage.isDeleteOneProduct(product);
    }
}
