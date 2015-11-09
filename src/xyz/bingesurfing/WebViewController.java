package xyz.bingesurfing;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ThreadLocalRandom;

import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker.State;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class WebViewController {
	private Feeder f;
	private List<String> urls;
	private Timer timer;

	@FXML
	private WebView webView;

	@FXML
	private TextArea textArea;

	private void logging(String s) {
		System.out.println(s);
		textArea.appendText(s+"\n");
	}

	public void destroy() {
		if(null != timer) timer.cancel();
	}

	@FXML
	private void initialize()
	{
		timer = new Timer();

		f = new Feeder();
		f.read();
		urls = f.getUrls();

		webView.setZoom(Defaults.ZOOM);

		WebEngine engine = webView.getEngine();
		engine.getLoadWorker().stateProperty().addListener(
				new ChangeListener<State>() {
					@Override public void changed(@SuppressWarnings("rawtypes") ObservableValue ov, State oldState, State newState) {

						boolean doNextURL=false;
						switch(newState) {
						case SUCCEEDED:
							logging("called");
							doNextURL=true;
							break;
						case CANCELLED:
							logging("cancelled");
							doNextURL=true;
							break;
						case FAILED:
							logging("failed");
							doNextURL=true;
							break;
						case READY:
							logging("ready");
							break;
						case RUNNING:
							logging("running");
							break;
						case SCHEDULED:
							textArea.clear();
							logging("scheduled");
							break;
						default:
							logging("??");
							break;
						} // switch

						if(doNextURL) {
							if(urls.isEmpty()) {
								logging("fetching new feed data ...");
								f.read();
								urls = f.getUrls();
							} // if

							int waitSeconds = ThreadLocalRandom.current().nextInt(Defaults.WAITSECONDSFROM, Defaults.WAITSECONDSTO);
							logging("wait " + waitSeconds + " seconds for loading next page.");
							timer.schedule(new TimerTask() {
								@Override
								public void run() {
									Platform.runLater(() -> {
										if(!urls.isEmpty()) {
											engine.loadContent("");
											System.gc();
											engine.load(urls.remove(0));
										} // if
									});
								} // run
							}, waitSeconds*1000);
						} // if

					}
				});
		engine.load(urls.remove(0));

	}

}
