package de.daemoniac.autosueberfahrendich;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.google.android.gms.ads.*;
import com.google.android.gms.ads.initialization.InitializationStatus;
import com.google.android.gms.ads.initialization.OnInitializationCompleteListener;
import com.google.android.gms.ads.interstitial.InterstitialAd;
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback;
import de.daemoniac.autosueberfahrendich.starter;
import de.daemoniac.autosueberfahrendich.werbung.AdsController;

import static android.content.ContentValues.TAG;

public class AndroidLauncher extends AndroidApplication implements AdsController {
	//hier werden 2 unterschiedliche Arten an Werbung vorbereitet. Zum einen ein Werbebanner dass dauerhaft
	//irgendwo eingeblendet werden kann (bannerAd) und zum anderen eine Werbeeinblendung
	//die den gesamten Bildschirm einnimmt und für ein paar sekunden dargestellt wird. klickt
	//der nutzer in der werbung auf das X landet er wieder in unserem Spiel
	public AdView bannerAd;
	InterstitialAd mInterstitialAd;
	//diese IDs hier sind Test-IDs die dafür sorghen dass keine echte Werbung eingeblendet wird.
	//die bestimmungen von Google verbieten es echte Werbung einzublenden wenn die app entwickelt wird.
	//erst wenn wir die app veröffentlichen darf hier eine echte id rein
	private static final String BANNER_AD_UNIT_ID = "ca-app-pub-3940256099942544/6300978111";
	private static final String INTERSTITIAL_AD_UNIT_ID = "ca-app-pub-3940256099942544/1033173712";
	private AndroidLauncher mthis;
	private boolean werbungFertigGeladen=false;

	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mthis=this;


		//hier legen wir fest was für werbung wir haben möchten. ich habe hier die Tags gesetzt für
		//werbeeinblendungen die auch für kinder geeignet sind die noch nicht alt genug sind um
		//datenschutzbestimmungen wirksam zuzustimmen und vom altersrating her sollte die werbund für alle
		//altersklassen geeignet sein.
		RequestConfiguration requestConfiguration = MobileAds.getRequestConfiguration()
				.toBuilder()
				.setTagForUnderAgeOfConsent(RequestConfiguration.TAG_FOR_UNDER_AGE_OF_CONSENT_TRUE)
				.setMaxAdContentRating(RequestConfiguration.MAX_AD_CONTENT_RATING_G)
				.build();
		MobileAds.setRequestConfiguration(requestConfiguration);

		//Sobald Google die werbungsengine fertig geladen hat soll unsere routine zum vorbereiten der werbund
		//geladen werden
		MobileAds.initialize(this, new OnInitializationCompleteListener() {
			@Override
			public void onInitializationComplete(InitializationStatus initializationStatus) {
				setupAds();

			}
		});
		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
		//bisher hatten wir immer nur ein einzelnes fenster im sichtbereich dass den gesamten bildschirm
		//einnimmt. jetzt allerdings müssen wir für die werbebanner den bildschirm in 2 teile teilen.
		//solange die werbung nicht zu sehen ist, kann der spielbereich den gesamten bildschirm einnehmen
		//(in der funktion setupAds wird der werbebereich erst einmal auf "Invisible" gesetzt um ihn auszublenden)
		//Der GameView (also der spielbereich) bekommt die größenzuweisung MATCH_PARENT, nimmt also den gesamten
		//zur verfügung stehenden platz ein. der werbebereich erhält bei der höhe die größenzuordnung WRAP_CONTENT
		//nimmt also maximal soviel platz ein, wie für das werbebanner benötigt wird.
		View gameView = initializeForView(new starter(mthis), config);

		RelativeLayout layout = new RelativeLayout(mthis);
		layout.addView(gameView, ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.MATCH_PARENT);
		RelativeLayout.LayoutParams werbeparams = new RelativeLayout.LayoutParams(
				ViewGroup.LayoutParams.MATCH_PARENT,
				ViewGroup.LayoutParams.WRAP_CONTENT);
		werbeparams.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		bannerAd = new AdView(this);
		layout.addView(bannerAd, werbeparams);
		setContentView(layout);
	}

	public void setupAds() {
		//werbebanner vorbereiten

		bannerAd.setVisibility(View.INVISIBLE);
		bannerAd.setBackgroundColor(0xff000000); // schwarz
		bannerAd.setAdUnitId(BANNER_AD_UNIT_ID);
		bannerAd.setAdSize(AdSize.BANNER);
		//Werbeeinblendung vorbereiten
		/*AdRequest.Builder builder = new AdRequest.Builder();
		AdRequest ad = builder.build();
		InterstitialAd.load(this,INTERSTITIAL_AD_UNIT_ID, ad,
				new InterstitialAdLoadCallback() {
					@Override
					public void onAdLoaded(@NonNull InterstitialAd interstitialAd) {
						mInterstitialAd = interstitialAd;
						Log.i(TAG, "onAdLoaded");
					}

					@Override
					public void onAdFailedToLoad(@NonNull LoadAdError loadAdError) {
						Log.i(TAG, loadAdError.getMessage());
						mInterstitialAd = null;
					}
				});*/
		werbungFertigGeladen=true;
	}

	//überprüfen ob Internet verfügbar ist. ohne Internet ist Werbung etwas sinnfrei ;-) in diesem fall darf
	//nicht versucht werden werbung einzublenden. sonst gibts fehlermeldungen
	@Override
	public boolean isWifiConnected() {
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo ni = cm.getActiveNetworkInfo();
		return (ni != null && ni.isConnected());
	}

	//funktion um überprüfen zu können ob werbeeinblendungen verfügbar sind
	@Override
	public boolean isInterstitialLoaded() {
		return mInterstitialAd!=null;
	}

	//wird diese Funktion aufgerufen soll das Werbebanner sichtbar gemacht werden
	@Override
	public void showBannerAd() {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				bannerAd.setVisibility(View.VISIBLE);
				AdRequest.Builder builder = new AdRequest.Builder();
				AdRequest ad = builder.build();
				bannerAd.loadAd(ad);
			}
		});
	}

	//hiermit kann das Werbebanner wieder ausgeblendet werden
	@Override
	public void hideBannerAd() {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				bannerAd.setVisibility(View.INVISIBLE);
			}
		});
	}

	//hiermit kann die werbeeinblendung gestartet werden
	@Override
	public void showInterstitialAd() {
		runOnUiThread(new Runnable() {
			@Override
			public void run() {
				mInterstitialAd.setFullScreenContentCallback(new FullScreenContentCallback(){
					@Override
					public void onAdDismissedFullScreenContent() {
						Log.d("TAG", "Werbung wurde weggeclickt.");
					}

					@Override
					public void onAdFailedToShowFullScreenContent(AdError adError) {
						// Called when fullscreen content failed to show.
						Log.d("TAG", "Es gab einen Fehler beim Einblenden der Werbung.");
					}

					@Override
					public void onAdShowedFullScreenContent() {
						mInterstitialAd = null;
						Log.d("TAG", "Die Werbung wurde erfolgreich dargestellt.");
					}
				});
				if (mInterstitialAd != null) {
					mInterstitialAd.show(mthis);
				} else {
					Log.d("TAG", "Die Werbeeinblendung war noch nicht fertig geladen.");
				}

			}
		});
	}

	@Override
	public boolean debugmode() {
		return BuildConfig.DEBUG;
	}

	@Override
	public boolean initialisierungKomplett(){return werbungFertigGeladen;}
}
