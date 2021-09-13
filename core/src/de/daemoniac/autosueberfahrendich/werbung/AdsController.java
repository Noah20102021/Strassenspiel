package de.daemoniac.autosueberfahrendich.werbung;

public interface AdsController {
    public boolean isWifiConnected();
    public boolean isInterstitialLoaded();
    public void showBannerAd();
    public void hideBannerAd();
    public void showInterstitialAd();
    public boolean debugmode();
    public boolean initialisierungKomplett();

}
