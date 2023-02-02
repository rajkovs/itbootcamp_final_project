package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LocaleOptions;

public class LocaleTests extends BaseTest {
    @Test
    public void setLocaleToES() {
        landingPage.setLocaleToolbarOption(LocaleOptions.ES);
        Assert.assertEquals(landingPage.getHeaderText(), "Página de aterrizaje");
    }

    @Test
    public void setLocaleToEN() {
        landingPage.setLocaleToolbarOption(LocaleOptions.EN);
        Assert.assertEquals(landingPage.getHeaderText(), "Landing");
    }

    @Test
    public void setLocaleToFR() {
        landingPage.setLocaleToolbarOption(LocaleOptions.FR);
        Assert.assertEquals(landingPage.getHeaderText(), "Page d'atterrissage");
    }
}
