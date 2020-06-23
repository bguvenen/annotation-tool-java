package anntool.window.composites.participant;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import anntool.database.Database;
import anntool.datamodels.Tweet;
import anntool.datamodels.TweetLabel;
import anntool.enums.ContentLabelCategory;
import anntool.enums.HsTypeLabelCategory;
import anntool.enums.TargetLabelCategory;

public class ParticipantLabelRecordComposite extends Composite {
	private int maxTweetValue;
	private int currentTweetId;

	private Text tweetNoText;

	private StyledText tweetTextLabel;

	private Button hateSpeechButton;
	private Button nonHateSpeechButton;
	
	// Hs types
	private Label hsTypeLabel;
	private Button racialButton;
	private Button xenophobicButton;
	private Button sexistButton;
	private Button politicalButton;
	private Button religiousTypeButton;
	private Button otherHsTypeButton;
	private Text otherHsTypeText;

	// targets
	private Label targetLabel;
	private Button specificPersonButton;
	private Button womenButton;
	private Button menButton;
	private Button syrianButton;
	private Button europeanButton;
	private Button arabianButton;
	private Button kurdishButton;
	private Button akPartiButton;
	private Button chpButton;
	private Button hdpButton;
	private Button religiousTargetButton;
	private Button secularButton;
	private Button nationalistButton;
	private Button otherTargetButton;
	private Text otherTargetText;

	// contents
	private Label contentLabel;
	private Button moralButton;
	private Button intellectualButton;
	private Button appearanceButton;
	private Button violenceButton;
	private Button profanityButton;
	private Button curseButton;
	private Button symbolizationButton;
	private Button otherContentButton;
	private Text otherContentText;

	private Label quoteLabel;
	private Text quoteText;

	private Button saveButton;

	public ParticipantLabelRecordComposite(Composite parent) {
		super(parent, SWT.NONE);

		setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		GridLayout mainGridLayout = new GridLayout();
		mainGridLayout.marginHeight = 15;
		mainGridLayout.marginWidth = 70;
		setLayout(mainGridLayout);

		maxTweetValue = Database.getTweetCount();

		// Output Button
		Button outputButton = new Button(this, SWT.PUSH);
		outputButton.setText("Etiketleme Kaydını Al");
		GridData outputButtonGD = new GridData(SWT.RIGHT, SWT.CENTER, true, false);
		outputButtonGD.heightHint = 30;
		outputButtonGD.widthHint = 200;
		outputButton.setLayoutData(outputButtonGD);
		outputButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog fileDialog = new FileDialog(getShell(), SWT.SAVE);
				fileDialog.setFilterPath("C:/");
				fileDialog.setFileName("Etiketleme kaydı");
				String directory = fileDialog.open();

				if (directory != null) {
					Database.exportRecord(directory);
				}
			}
		});

		// Tweet No

		Composite tweetNoComp = new Composite(this, SWT.NONE);
		tweetNoComp.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		GridLayout tweetNoGD = new GridLayout(3, false);
		tweetNoGD.horizontalSpacing = 30;
		tweetNoComp.setLayout(tweetNoGD);

		Label tweetNoLabel = new Label(tweetNoComp, SWT.NONE);
		tweetNoLabel.setText("Tweet No : ");
		tweetNoLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, true, false));

		tweetNoText = new Text(tweetNoComp, SWT.CENTER);
		GridData tweetNoTextGD = new GridData(SWT.CENTER, SWT.CENTER, false, false);
		tweetNoTextGD.widthHint = 60;
		tweetNoText.setLayoutData(tweetNoTextGD);
		tweetNoText.addVerifyListener(new VerifyListener() {
			@Override
			public void verifyText(VerifyEvent e) {
				final String oldS = tweetNoText.getText();
				final String newS = oldS.substring(0, e.start) + e.text + oldS.substring(e.end);

				if (!newS.equals("")) {
					try {
						int value = Integer.parseInt(newS);
						if (value <= 0) {
							e.doit = false;
						}
					} catch (Exception e2) {
						e.doit = false;
					}
				}
			}
		});

		Button tweetNoButton = new Button(tweetNoComp, SWT.PUSH);
		tweetNoButton.setText("Tweet'e Git");
		tweetNoButton.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false));
		tweetNoButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if (tweetNoText.getText().isEmpty())
					return;

				int tweetId = Integer.parseInt(tweetNoText.getText());
				currentTweetId = tweetId;
				hateSpeechButton.setEnabled(true);
				nonHateSpeechButton.setEnabled(true);
				showTweet(tweetId);
			}
		});

		// Tweet text

		tweetTextLabel = new StyledText(this, SWT.MULTI | SWT.WRAP);
		GridData tweetTextLabelGD = new GridData(SWT.CENTER, SWT.CENTER, true, false);
		tweetTextLabelGD.widthHint = 1000;
		tweetTextLabelGD.heightHint = 60;
		tweetTextLabel.setLayoutData(tweetTextLabelGD);
		tweetTextLabel.setBackground(getShell().getBackground());
		tweetTextLabel.setEditable(false);
		tweetTextLabel.setCaret(null);
		FontData tweetTextLabelFD = tweetTextLabel.getFont().getFontData()[0];
		tweetTextLabelFD.setHeight(tweetTextLabelFD.getHeight() + 2);
		tweetTextLabel.setFont(new Font(getDisplay(), tweetTextLabelFD));

		// hs and non-hs buttons

		Composite choicesComp = new Composite(this, SWT.NONE);
		choicesComp.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		GridLayout choicesGD = new GridLayout(2, false);
		choicesGD.horizontalSpacing = 30;
		choicesComp.setLayout(choicesGD);

		hateSpeechButton = new Button(choicesComp, SWT.PUSH);
		hateSpeechButton.setText("Nefret Söylemi Var");
		GridData hateSpeechButtonGD = new GridData(SWT.RIGHT, SWT.CENTER, true, false);
		hateSpeechButtonGD.heightHint = 30;
		hateSpeechButtonGD.widthHint = 200;
		hateSpeechButton.setLayoutData(hateSpeechButtonGD);
		hateSpeechButton.setEnabled(false);
		hateSpeechButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				enableHsForm();
			}
		});

		nonHateSpeechButton = new Button(choicesComp, SWT.PUSH);
		nonHateSpeechButton.setText("Nefret Söylemi Yok");
		GridData nonHateSpeechButtonGD = new GridData(SWT.CENTER, SWT.CENTER, false, false);
		nonHateSpeechButtonGD.heightHint = 30;
		nonHateSpeechButtonGD.widthHint = 200;
		nonHateSpeechButton.setLayoutData(nonHateSpeechButtonGD);
		nonHateSpeechButton.setEnabled(false);
		nonHateSpeechButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TweetLabel tweetLabel = new TweetLabel(-1, currentTweetId, false, null, null, null, "");
				Database.addLabelRecord(tweetLabel);
				currentTweetId++;
				showTweet(currentTweetId);
			}
		});

		// Hate Speech Form

		// HS Types

		Composite hsFormComp = new Composite(this, SWT.NONE);
		hsFormComp.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		GridLayout hsFormGD = new GridLayout(4, false);
		hsFormGD.horizontalSpacing = 40;
		hsFormComp.setLayout(hsFormGD);

		Group hsTypeCategoriesGroup = new Group(hsFormComp, SWT.NONE);
		hsTypeCategoriesGroup.setLayoutData(new GridData(SWT.RIGHT, SWT.FILL, true, false, 1, 2));
		hsTypeCategoriesGroup.setLayout(new GridLayout());

		hsTypeLabel = new Label(hsTypeCategoriesGroup, SWT.NONE);
		hsTypeLabel.setText("Nefret Söylemi Türü : ");
		hsTypeLabel.setEnabled(false);
		hsTypeLabel.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false));
		FontData hsTypeLabelFD = hsTypeLabel.getFont().getFontData()[0];
		hsTypeLabelFD.setStyle(SWT.BOLD | SWT.ITALIC);
		hsTypeLabel.setFont(new Font(getDisplay(), hsTypeLabelFD));

		racialButton = new Button(hsTypeCategoriesGroup, SWT.CHECK);
		racialButton.setText(HsTypeLabelCategory.RACIAL.toString());
		racialButton.setEnabled(false);
		racialButton.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false));

		xenophobicButton = new Button(hsTypeCategoriesGroup, SWT.CHECK);
		xenophobicButton.setText(HsTypeLabelCategory.XENOPHOBIC.toString());
		xenophobicButton.setEnabled(false);
		xenophobicButton.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false));

		sexistButton = new Button(hsTypeCategoriesGroup, SWT.CHECK);
		sexistButton.setText(HsTypeLabelCategory.SEXIST.toString());
		sexistButton.setEnabled(false);
		sexistButton.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false));

		politicalButton = new Button(hsTypeCategoriesGroup, SWT.CHECK);
		politicalButton.setText(HsTypeLabelCategory.POLITICAL.toString());
		politicalButton.setEnabled(false);
		politicalButton.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false));

		religiousTypeButton = new Button(hsTypeCategoriesGroup, SWT.CHECK);
		religiousTypeButton.setText(HsTypeLabelCategory.RELIGIOUS.toString());
		religiousTypeButton.setEnabled(false);
		religiousTypeButton.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false));

		otherHsTypeButton = new Button(hsTypeCategoriesGroup, SWT.CHECK);
		otherHsTypeButton.setText(HsTypeLabelCategory.OTHER.toString());
		otherHsTypeButton.setEnabled(false);
		otherHsTypeButton.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false));
		otherHsTypeButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				otherHsTypeText.setEnabled(otherHsTypeButton.getSelection());
			}
		});

		otherHsTypeText = new Text(hsTypeCategoriesGroup, SWT.BORDER);
		otherHsTypeText.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false));
		otherHsTypeText.setEnabled(false);

		// Targets
		Group targetCategoriesGroup = new Group(hsFormComp, SWT.NONE);
		targetCategoriesGroup.setLayoutData(new GridData(SWT.CENTER, SWT.FILL, false, false, 1, 2));
		targetCategoriesGroup.setLayout(new GridLayout());

		targetLabel = new Label(targetCategoriesGroup, SWT.NONE);
		targetLabel.setText("Hedef : ");
		targetLabel.setEnabled(false);
		targetLabel.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false));
		FontData targetLabelFD = targetLabel.getFont().getFontData()[0];
		targetLabelFD.setStyle(SWT.BOLD | SWT.ITALIC);
		targetLabel.setFont(new Font(getDisplay(), targetLabelFD));

		specificPersonButton = new Button(targetCategoriesGroup, SWT.CHECK);
		specificPersonButton.setText(TargetLabelCategory.SPECIFIC_PERSON.toString());
		specificPersonButton.setEnabled(false);
		specificPersonButton.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false));

		womenButton = new Button(targetCategoriesGroup, SWT.CHECK);
		womenButton.setText(TargetLabelCategory.WOMEN.toString());
		womenButton.setEnabled(false);
		womenButton.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false));

		menButton = new Button(targetCategoriesGroup, SWT.CHECK);
		menButton.setText(TargetLabelCategory.MEN.toString());
		menButton.setEnabled(false);
		menButton.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false));

		syrianButton = new Button(targetCategoriesGroup, SWT.CHECK);
		syrianButton.setText(TargetLabelCategory.SYRIAN.toString());
		syrianButton.setEnabled(false);
		syrianButton.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false));

		europeanButton = new Button(targetCategoriesGroup, SWT.CHECK);
		europeanButton.setText(TargetLabelCategory.EUROPEAN.toString());
		europeanButton.setEnabled(false);
		europeanButton.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false));

		arabianButton = new Button(targetCategoriesGroup, SWT.CHECK);
		arabianButton.setText(TargetLabelCategory.ARABIAN.toString());
		arabianButton.setEnabled(false);
		arabianButton.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false));

		kurdishButton = new Button(targetCategoriesGroup, SWT.CHECK);
		kurdishButton.setText(TargetLabelCategory.KURDISH.toString());
		kurdishButton.setEnabled(false);
		kurdishButton.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false));

		akPartiButton = new Button(targetCategoriesGroup, SWT.CHECK);
		akPartiButton.setText(TargetLabelCategory.AK_PARTI_SUPPORTER.toString());
		akPartiButton.setEnabled(false);
		akPartiButton.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false));

		chpButton = new Button(targetCategoriesGroup, SWT.CHECK);
		chpButton.setText(TargetLabelCategory.CHP_SUPPORTER.toString());
		chpButton.setEnabled(false);
		chpButton.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false));

		hdpButton = new Button(targetCategoriesGroup, SWT.CHECK);
		hdpButton.setText(TargetLabelCategory.HDP_SUPPORTER.toString());
		hdpButton.setEnabled(false);
		hdpButton.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false));

		religiousTargetButton = new Button(targetCategoriesGroup, SWT.CHECK);
		religiousTargetButton.setText(TargetLabelCategory.RELIGIOUS.toString());
		religiousTargetButton.setEnabled(false);
		religiousTargetButton.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false));

		secularButton = new Button(targetCategoriesGroup, SWT.CHECK);
		secularButton.setText(TargetLabelCategory.SECULAR.toString());
		secularButton.setEnabled(false);
		secularButton.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false));

		nationalistButton = new Button(targetCategoriesGroup, SWT.CHECK);
		nationalistButton.setText(TargetLabelCategory.NATIONALIST.toString());
		nationalistButton.setEnabled(false);
		nationalistButton.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false));

		otherTargetButton = new Button(targetCategoriesGroup, SWT.CHECK);
		otherTargetButton.setText(HsTypeLabelCategory.OTHER.toString());
		otherTargetButton.setEnabled(false);
		otherTargetButton.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false));
		otherTargetButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				otherTargetText.setEnabled(otherTargetButton.getSelection());
			}
		});

		otherTargetText = new Text(targetCategoriesGroup, SWT.BORDER);
		otherTargetText.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false));
		otherTargetText.setEnabled(false);

		// Contents
		Group contentCategoriesGroup = new Group(hsFormComp, SWT.NONE);
		contentCategoriesGroup.setLayoutData(new GridData(SWT.LEFT, SWT.FILL, false, false, 1, 2));
		contentCategoriesGroup.setLayout(new GridLayout());

		contentLabel = new Label(contentCategoriesGroup, SWT.NONE);
		contentLabel.setText("İçerik : ");
		contentLabel.setEnabled(false);
		contentLabel.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false));
		FontData contentLabelFD = contentLabel.getFont().getFontData()[0];
		contentLabelFD.setStyle(SWT.BOLD | SWT.ITALIC);
		contentLabel.setFont(new Font(getDisplay(), contentLabelFD));

		moralButton = new Button(contentCategoriesGroup, SWT.CHECK);
		moralButton.setText(ContentLabelCategory.MORAL.toString());
		moralButton.setEnabled(false);
		moralButton.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false));

		intellectualButton = new Button(contentCategoriesGroup, SWT.CHECK);
		intellectualButton.setText(ContentLabelCategory.INTELLECTUAL.toString());
		intellectualButton.setEnabled(false);
		intellectualButton.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false));

		appearanceButton = new Button(contentCategoriesGroup, SWT.CHECK);
		appearanceButton.setText(ContentLabelCategory.APPEARANCE.toString());
		appearanceButton.setEnabled(false);
		appearanceButton.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false));

		violenceButton = new Button(contentCategoriesGroup, SWT.CHECK);
		violenceButton.setText(ContentLabelCategory.VIOLENCE.toString());
		violenceButton.setEnabled(false);
		violenceButton.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false));

		profanityButton = new Button(contentCategoriesGroup, SWT.CHECK);
		profanityButton.setText(ContentLabelCategory.PROFANITY.toString());
		profanityButton.setEnabled(false);
		profanityButton.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false));

		curseButton = new Button(contentCategoriesGroup, SWT.CHECK);
		curseButton.setText(ContentLabelCategory.CURSE.toString());
		curseButton.setEnabled(false);
		curseButton.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false));

		symbolizationButton = new Button(contentCategoriesGroup, SWT.CHECK);
		symbolizationButton.setText(ContentLabelCategory.SYMBOLIZATION.toString());
		symbolizationButton.setEnabled(false);
		symbolizationButton.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false));

		otherContentButton = new Button(contentCategoriesGroup, SWT.CHECK);
		otherContentButton.setText(ContentLabelCategory.OTHER.toString());
		otherContentButton.setEnabled(false);
		otherContentButton.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false));
		otherContentButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				otherContentText.setEnabled(otherContentButton.getSelection());
			}
		});

		otherContentText = new Text(contentCategoriesGroup, SWT.BORDER);
		otherContentText.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false));
		otherContentText.setEnabled(false);

		// Quote
		quoteLabel = new Label(hsFormComp, SWT.NONE);
		quoteLabel.setText("Tweet'in nefret söylemi içeren kısmını kopyalayıp, buraya yapıştırınız : ");
		quoteLabel.setEnabled(false);
		quoteLabel.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false));
		FontData quoteLabelFD = quoteLabel.getFont().getFontData()[0];
		quoteLabelFD.setStyle(SWT.BOLD | SWT.ITALIC);
		quoteLabel.setFont(new Font(getDisplay(), quoteLabelFD));

		quoteText = new Text(hsFormComp, SWT.MULTI | SWT.BORDER | SWT.WRAP | SWT.V_SCROLL);
		GridData quoteTextGD = new GridData(SWT.LEFT, SWT.FILL, true, false);
		quoteTextGD.widthHint = 400;
		quoteTextGD.heightHint = 100;
		quoteText.setLayoutData(quoteTextGD);
		quoteText.setEnabled(false);

		// Save Button
		saveButton = new Button(this, SWT.PUSH);
		saveButton.setText("Kaydet");
		saveButton.setEnabled(false);
		GridData saveButtonGD = new GridData(SWT.CENTER, SWT.CENTER, true, true);
		saveButtonGD.heightHint = 30;
		saveButtonGD.widthHint = 200;
		saveButton.setLayoutData(saveButtonGD);
		saveButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {

				if ((!racialButton.getSelection() && !xenophobicButton.getSelection() && !sexistButton.getSelection()
						&& !politicalButton.getSelection() && !religiousTypeButton.getSelection()
						&& !otherHsTypeButton.getSelection())
						|| (!specificPersonButton.getSelection() && !womenButton.getSelection()
								&& !menButton.getSelection() && !syrianButton.getSelection()
								&& !europeanButton.getSelection() && !arabianButton.getSelection()
								&& !kurdishButton.getSelection() && !akPartiButton.getSelection()
								&& !chpButton.getSelection() && !hdpButton.getSelection()
								&& !religiousTargetButton.getSelection() && !secularButton.getSelection()
								&& !nationalistButton.getSelection() && !otherTargetButton.getSelection())
						|| (!moralButton.getSelection() && !intellectualButton.getSelection()
								&& !appearanceButton.getSelection() && !violenceButton.getSelection()
								&& !profanityButton.getSelection() && !curseButton.getSelection()
								&& !symbolizationButton.getSelection() && !otherContentButton.getSelection())) {
					MessageDialog.openError(getShell(), "Eksik bilgi!",
							"Lütfen tür, hedef ve içerik kategorilerinden en az birer tane işaretleyiniz.");
					return;
				}

				if (quoteText.getText().isEmpty()) {
					MessageDialog.openError(getShell(), "Eksik bilgi!",
							"Lütfen tweet'in nefret söylemi içeren kısmını kopyalayıp yapıştırınız.");
					return;
				}

				if ((otherHsTypeButton.getSelection() && otherHsTypeText.getText().isEmpty())
						|| (otherTargetButton.getSelection() && otherTargetText.getText().isEmpty())
						|| (otherContentButton.getSelection() && otherContentText.getText().isEmpty())) {
					MessageDialog.openError(getShell(), "Eksik bilgi!",
							"Lütfen \"Diğer\" seçeneğinin altındaki kutucuğa uygun olduğunu düşündüğünüz etiketi yazınız.");
					return;
				}

				List<HsTypeLabelCategory> hsTypes = new ArrayList<>();

				if (racialButton.getSelection())
					hsTypes.add(HsTypeLabelCategory.RACIAL);
				if (xenophobicButton.getSelection())
					hsTypes.add(HsTypeLabelCategory.XENOPHOBIC);
				if (sexistButton.getSelection())
					hsTypes.add(HsTypeLabelCategory.SEXIST);
				if (politicalButton.getSelection())
					hsTypes.add(HsTypeLabelCategory.POLITICAL);
				if (religiousTypeButton.getSelection())
					hsTypes.add(HsTypeLabelCategory.RELIGIOUS);
				if (otherHsTypeButton.getSelection())
					hsTypes.add(HsTypeLabelCategory.OTHER);

				List<TargetLabelCategory> targets = new ArrayList<>();

				if (specificPersonButton.getSelection())
					targets.add(TargetLabelCategory.SPECIFIC_PERSON);
				if (womenButton.getSelection())
					targets.add(TargetLabelCategory.WOMEN);
				if (menButton.getSelection())
					targets.add(TargetLabelCategory.MEN);
				if (syrianButton.getSelection())
					targets.add(TargetLabelCategory.SYRIAN);
				if (europeanButton.getSelection())
					targets.add(TargetLabelCategory.EUROPEAN);
				if (arabianButton.getSelection())
					targets.add(TargetLabelCategory.ARABIAN);
				if (kurdishButton.getSelection())
					targets.add(TargetLabelCategory.KURDISH);
				if (akPartiButton.getSelection())
					targets.add(TargetLabelCategory.AK_PARTI_SUPPORTER);
				if (chpButton.getSelection())
					targets.add(TargetLabelCategory.CHP_SUPPORTER);
				if (hdpButton.getSelection())
					targets.add(TargetLabelCategory.HDP_SUPPORTER);
				if (religiousTargetButton.getSelection())
					targets.add(TargetLabelCategory.RELIGIOUS);
				if (secularButton.getSelection())
					targets.add(TargetLabelCategory.SECULAR);
				if (nationalistButton.getSelection())
					targets.add(TargetLabelCategory.NATIONALIST);
				if (otherTargetButton.getSelection())
					targets.add(TargetLabelCategory.OTHER);

				List<ContentLabelCategory> contents = new ArrayList<>();

				if (moralButton.getSelection())
					contents.add(ContentLabelCategory.MORAL);
				if (intellectualButton.getSelection())
					contents.add(ContentLabelCategory.INTELLECTUAL);
				if (appearanceButton.getSelection())
					contents.add(ContentLabelCategory.APPEARANCE);
				if (violenceButton.getSelection())
					contents.add(ContentLabelCategory.VIOLENCE);
				if (profanityButton.getSelection())
					contents.add(ContentLabelCategory.PROFANITY);
				if (curseButton.getSelection())
					contents.add(ContentLabelCategory.CURSE);
				if (symbolizationButton.getSelection())
					contents.add(ContentLabelCategory.SYMBOLIZATION);
				if (otherContentButton.getSelection())
					contents.add(ContentLabelCategory.OTHER);

				String quote = quoteText.getText();

				TweetLabel tweetLabel = new TweetLabel(-1, currentTweetId, true, hsTypes, targets, contents, quote);

				if (otherHsTypeButton.getSelection())
					tweetLabel.setOtherHsType(otherHsTypeText.getText());

				if (otherTargetButton.getSelection())
					tweetLabel.setOtherTarget(otherTargetText.getText());

				if (otherContentButton.getSelection())
					tweetLabel.setOtherContent(otherContentText.getText());

				Database.addLabelRecord(tweetLabel);
				currentTweetId++;
				showTweet(currentTweetId);
			}
		});
	}

	private void showTweet(int tweetId) {
		if (tweetId > maxTweetValue) {
			MessageDialog.openInformation(getShell(), "", "Toplamda " + maxTweetValue
					+ " adet tweet kayıtlı olduğu için " + tweetId + " nolu tweet bulunmamaktadır.");
			disableAllActionsAndCleanWholeForm();
			return;
		}

		cleanAndDisableHsForm();

		Tweet tweet = Database.getTweet(tweetId);

		boolean exists = Database.labelRecordExists(tweetId);

		if (exists) {
			showLabelledTweet(tweetId);
		}

		tweetNoText.setText(currentTweetId + "");
		tweetTextLabel.setText(tweet.getTweetText());
	}

	private void showLabelledTweet(int tweetId) {
		TweetLabel label = Database.getLabelRecord(tweetId);

		if (label.isHateSpeech()) {
			enableHsForm();

			for (HsTypeLabelCategory hsType : label.getHsTypes()) {
				switch (hsType) {
				case SEXIST:
					sexistButton.setSelection(true);
					break;
				case OTHER:
					otherHsTypeButton.setSelection(true);
					otherHsTypeText.setEnabled(true);
					otherHsTypeText.setText(label.getOtherHsType());
					break;
				case POLITICAL:
					politicalButton.setSelection(true);
					break;
				case RACIAL:
					racialButton.setSelection(true);
					break;
				case RELIGIOUS:
					religiousTypeButton.setSelection(true);
					break;
				case XENOPHOBIC:
					xenophobicButton.setSelection(true);
					break;
				}
			}

			for (TargetLabelCategory target : label.getTargets()) {
				switch (target) {
				case AK_PARTI_SUPPORTER:
					akPartiButton.setSelection(true);
					break;
				case ARABIAN:
					arabianButton.setSelection(true);
					break;
				case CHP_SUPPORTER:
					chpButton.setSelection(true);
					break;
				case EUROPEAN:
					europeanButton.setSelection(true);
					break;
				case HDP_SUPPORTER:
					hdpButton.setSelection(true);
					break;
				case KURDISH:
					kurdishButton.setSelection(true);
					break;
				case MEN:
					menButton.setSelection(true);
					break;
				case NATIONALIST:
					nationalistButton.setSelection(true);
					break;
				case OTHER:
					otherTargetButton.setSelection(true);
					otherTargetText.setEnabled(true);
					otherTargetText.setText(label.getOtherTarget());
					break;
				case RELIGIOUS:
					religiousTargetButton.setSelection(true);
					break;
				case SECULAR:
					secularButton.setSelection(true);
					break;
				case SYRIAN:
					syrianButton.setSelection(true);
					break;
				case WOMEN:
					womenButton.setSelection(true);
					break;
				case SPECIFIC_PERSON:
					specificPersonButton.setSelection(true);
					break;
				}
			}

			for (ContentLabelCategory content : label.getContents()) {
				switch (content) {
				case APPEARANCE:
					appearanceButton.setSelection(true);
					break;
				case INTELLECTUAL:
					intellectualButton.setSelection(true);
					break;
				case MORAL:
					moralButton.setSelection(true);
					break;
				case OTHER:
					otherContentButton.setSelection(true);
					otherContentText.setEnabled(true);
					otherContentText.setText(label.getOtherContent());
					break;
				case PROFANITY:
					profanityButton.setSelection(true);
					break;
				case VIOLENCE:
					violenceButton.setSelection(true);
					break;
				case CURSE:
					curseButton.setSelection(true);
					break;
				case SYMBOLIZATION:
					symbolizationButton.setSelection(true);
					break;
				}
			}

			quoteText.setText(label.getQuote());
		}
	}

	private void enableHsForm() {
		hsTypeLabel.setEnabled(true);
		racialButton.setEnabled(true);
		xenophobicButton.setEnabled(true);
		sexistButton.setEnabled(true);
		politicalButton.setEnabled(true);
		religiousTypeButton.setEnabled(true);
		otherHsTypeButton.setEnabled(true);

		targetLabel.setEnabled(true);
		specificPersonButton.setEnabled(true);
		womenButton.setEnabled(true);
		menButton.setEnabled(true);
		syrianButton.setEnabled(true);
		europeanButton.setEnabled(true);
		arabianButton.setEnabled(true);
		kurdishButton.setEnabled(true);
		akPartiButton.setEnabled(true);
		chpButton.setEnabled(true);
		hdpButton.setEnabled(true);
		religiousTargetButton.setEnabled(true);
		secularButton.setEnabled(true);
		nationalistButton.setEnabled(true);
		otherTargetButton.setEnabled(true);

		contentLabel.setEnabled(true);
		moralButton.setEnabled(true);
		intellectualButton.setEnabled(true);
		appearanceButton.setEnabled(true);
		violenceButton.setEnabled(true);
		profanityButton.setEnabled(true);
		curseButton.setEnabled(true);
		symbolizationButton.setEnabled(true);
		otherContentButton.setEnabled(true);

		quoteLabel.setEnabled(true);
		quoteText.setEnabled(true);

		saveButton.setEnabled(true);
	}

	private void cleanAndDisableHsForm() {
		hsTypeLabel.setEnabled(false);
		racialButton.setEnabled(false);
		racialButton.setSelection(false);
		xenophobicButton.setEnabled(false);
		xenophobicButton.setSelection(false);
		sexistButton.setEnabled(false);
		sexistButton.setSelection(false);
		politicalButton.setEnabled(false);
		politicalButton.setSelection(false);
		religiousTypeButton.setEnabled(false);
		religiousTypeButton.setSelection(false);
		otherHsTypeButton.setEnabled(false);
		otherHsTypeButton.setSelection(false);
		otherHsTypeText.setText("");
		otherHsTypeText.setEnabled(false);

		targetLabel.setEnabled(false);
		specificPersonButton.setEnabled(false);
		specificPersonButton.setSelection(false);
		womenButton.setEnabled(false);
		womenButton.setSelection(false);
		menButton.setEnabled(false);
		menButton.setSelection(false);
		syrianButton.setEnabled(false);
		syrianButton.setSelection(false);
		europeanButton.setEnabled(false);
		europeanButton.setSelection(false);
		arabianButton.setEnabled(false);
		arabianButton.setSelection(false);
		kurdishButton.setEnabled(false);
		kurdishButton.setSelection(false);
		akPartiButton.setEnabled(false);
		akPartiButton.setSelection(false);
		chpButton.setEnabled(false);
		chpButton.setSelection(false);
		hdpButton.setEnabled(false);
		hdpButton.setSelection(false);
		religiousTargetButton.setEnabled(false);
		religiousTargetButton.setSelection(false);
		secularButton.setEnabled(false);
		secularButton.setSelection(false);
		nationalistButton.setEnabled(false);
		nationalistButton.setSelection(false);
		otherTargetButton.setEnabled(false);
		otherTargetButton.setSelection(false);
		otherTargetText.setText("");
		otherTargetText.setEnabled(false);

		contentLabel.setEnabled(false);
		moralButton.setEnabled(false);
		moralButton.setSelection(false);
		intellectualButton.setEnabled(false);
		intellectualButton.setSelection(false);
		appearanceButton.setEnabled(false);
		appearanceButton.setSelection(false);
		violenceButton.setEnabled(false);
		violenceButton.setSelection(false);
		profanityButton.setEnabled(false);
		profanityButton.setSelection(false);
		curseButton.setEnabled(false);
		curseButton.setSelection(false);
		symbolizationButton.setEnabled(false);
		symbolizationButton.setSelection(false);
		otherContentButton.setEnabled(false);
		otherContentButton.setSelection(false);
		otherContentText.setText("");
		otherContentText.setEnabled(false);

		quoteLabel.setEnabled(false);
		quoteText.setText("");
		quoteText.setEnabled(false);

		saveButton.setEnabled(false);
	}

	private void disableAllActionsAndCleanWholeForm() {
		currentTweetId = 0;

		tweetNoText.setText("");
		tweetTextLabel.setText("");

		hateSpeechButton.setEnabled(false);
		nonHateSpeechButton.setEnabled(false);

		cleanAndDisableHsForm();
	}

}
