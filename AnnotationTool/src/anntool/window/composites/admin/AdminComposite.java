package anntool.window.composites.admin;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;

import anntool.database.Database;
import anntool.datamodels.Tweet;
import anntool.window.composites.admin.labelviewer.LabelViewerComposite;
import anntool.window.composites.admin.tweetviewer.TweetViewerComposite;
import anntool.window.dialogs.participantviewer.ParticipantListDialog;

public class AdminComposite extends Composite {
	public AdminComposite(Composite parent) {
		super(parent, SWT.NONE);
		setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		GridLayout mainGridLayout = new GridLayout(2, false);
		mainGridLayout.horizontalSpacing = 0;
		mainGridLayout.verticalSpacing = 0;
		mainGridLayout.marginWidth = 0;
		mainGridLayout.marginHeight = 0;
		setLayout(mainGridLayout);

		// Table Viewers
		Composite viewersComposite = new Composite(this, SWT.NONE);
		viewersComposite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		GridLayout viewersGridLayout = new GridLayout();
		viewersGridLayout.verticalSpacing = 20;
		viewersComposite.setLayout(viewersGridLayout);

		TweetViewerComposite tweetViewerComposite = new TweetViewerComposite(viewersComposite);

		LabelViewerComposite labelViewerComposite = new LabelViewerComposite(viewersComposite);
		tweetViewerComposite.setLabelViewer(labelViewerComposite);

		// Buttons
		Composite buttonsComposite = new Composite(this, SWT.NONE);
		buttonsComposite.setLayoutData(new GridData(SWT.RIGHT, SWT.FILL, false, true));

		GridLayout buttonsGridLayout = new GridLayout();
		buttonsGridLayout.verticalSpacing = 20;
		buttonsComposite.setLayout(buttonsGridLayout);

		Button addTweetListButton = new Button(buttonsComposite, SWT.PUSH);
		addTweetListButton.setText("Tweet Listesi Ekle");
		GridData gd1 = new GridData(SWT.CENTER, SWT.CENTER, true, true);
		gd1.widthHint = 200;
		gd1.heightHint = 50;
		addTweetListButton.setLayoutData(gd1);
		addTweetListButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog fileDialog = new FileDialog(getShell(), SWT.OPEN);
				fileDialog.setFilterPath("C:/");
				fileDialog.setFilterExtensions(new String[] { "*.txt" });
				String filePath = fileDialog.open();

				if (filePath != null) {
					List<Tweet> newTweetList = Database.readRawTweetListFromFile(filePath);

					for (Tweet tweet : newTweetList) {
						Database.addNewTweet(tweet);
					}

					tweetViewerComposite.resetInput();
					tweetViewerComposite.redraw();
				}
			}
		});

		Button exportTweetListButton = new Button(buttonsComposite, SWT.PUSH);
		exportTweetListButton.setText("Tweet Listesini Kaydet");
		GridData gd2 = new GridData(SWT.CENTER, SWT.CENTER, true, true);
		gd2.widthHint = 200;
		gd2.heightHint = 50;
		exportTweetListButton.setLayoutData(gd2);
		exportTweetListButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog fileDialog = new FileDialog(getShell(), SWT.SAVE);
				fileDialog.setFilterPath("C:/");
				fileDialog.setFileName("Tweet Listesi");
				String directory = fileDialog.open();

				if (directory != null) {

					List<Tweet> tweetList = Database.getTweetList();
					Database.writeTweetListToFile(directory + ".txt", tweetList);
				}
			}
		});

		Button addRecordButton = new Button(buttonsComposite, SWT.PUSH);
		addRecordButton.setText("Etiket Kaydı Ekle");
		GridData gd3 = new GridData(SWT.CENTER, SWT.CENTER, true, true);
		gd3.widthHint = 200;
		gd3.heightHint = 50;
		addRecordButton.setLayoutData(gd3);
		addRecordButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog fileDialog = new FileDialog(getShell(), SWT.OPEN);
				fileDialog.setFilterPath("C:/");
				fileDialog.setFilterExtensions(new String[] { "*.zip" });
				String filePath = fileDialog.open();

				if (filePath != null) {
					Database.unpackRecordFile(filePath);
				}

				tweetViewerComposite.resetInput();
			}
		});

		Button participantListButton = new Button(buttonsComposite, SWT.PUSH);
		participantListButton.setText("Kullanıcı Listesi");
		GridData gd4 = new GridData(SWT.CENTER, SWT.CENTER, true, true);
		gd4.widthHint = 200;
		gd4.heightHint = 50;
		participantListButton.setLayoutData(gd4);
		participantListButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				ParticipantListDialog dialog = new ParticipantListDialog(getShell());
				dialog.create();
				dialog.open();
			}
		});

	}
}
