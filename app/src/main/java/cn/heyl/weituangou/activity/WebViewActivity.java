package cn.heyl.weituangou.activity;

import org.xutils.x;
import org.xutils.view.annotation.ViewInject;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.net.http.SslError;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;
import cn.heyl.weituangou.R;


public class WebViewActivity extends Activity {
	@ViewInject(R.id.web_view)
	private WebView webView;
	
	@ViewInject(R.id.tv_progress)
	private TextView tvProgerss;

	private AnimationDrawable anim;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_web_view);
		x.view().inject(this);
		Intent intent = getIntent();
		String url = intent.getStringExtra("url");
		
		initFramAnimation();
		
		webView.getSettings().setJavaScriptEnabled(true);
		webView.setWebViewClient(new WebViewClient(){
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				view.loadUrl(url);
//				Log.d("hyl", "shouldOverrideUrlLoading: "+url);
				return true;
			}
			
			@Override
			public void onPageStarted(WebView view, String url, Bitmap favicon) {
//				progressBar.setVisibility(View.VISIBLE);
				tvProgerss.setVisibility(View.VISIBLE);
				anim.start();
			}
			
			@Override
			public void onPageFinished(WebView view, String url) {
			}
			
			@Override
			public void onReceivedError(WebView view, int errorCode,
					String description, String failingUrl) {
				view.loadDataWithBaseURL(null, "网络异常",
                "text/html", "utf-8", null);
//				Log.d("hyl", "onReceivedError: int errorCode" +errorCode+
//						"description"+description+"failingUrl"+failingUrl);
//				finish();
			}
			
			@Override
			public void onReceivedSslError(WebView view,
					SslErrorHandler handler, SslError error) {
				handler.proceed();

//				Log.d("hyl", "onReceivedError: SslErrorHandler handler, SslError error"+failingUrl);
				super.onReceivedSslError(view, handler, error);

			}
		});
		
		
		webView.setScrollBarStyle(WebView.SCROLLBARS_OUTSIDE_OVERLAY);
		webView.setHorizontalScrollbarOverlay(true);
		webView.setHorizontalScrollBarEnabled(true);
		webView.requestFocus();
		webView.setWebChromeClient(new WebChromeClient() {
			 
		      public void onProgressChanged(WebView view, int progress) {
		           setTitle("当前进度" + progress + "%");
		           setProgress(progress * 100);
		 
		           if(progress>70){
//		        	   progressBar.setVisibility(view.INVISIBLE);
		        	   anim.stop();
		        	   tvProgerss.setVisibility(View.INVISIBLE);
		           }
		           if (progress == 100) {
		                 setTitle(R.string.app_name);
		           }
		      }
		});
		
		webView.loadUrl(url);
	}

	private void initFramAnimation() {
		anim = (AnimationDrawable) tvProgerss.getBackground();
	}




}
