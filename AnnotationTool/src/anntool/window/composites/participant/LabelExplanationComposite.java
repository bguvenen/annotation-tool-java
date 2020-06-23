package anntool.window.composites.participant;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import anntool.window.AnnToolWindow;

public class LabelExplanationComposite extends Composite {

	public LabelExplanationComposite(Composite parent) {
		super(parent, SWT.NONE);
		setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		GridLayout mainGridLayout = new GridLayout();
		mainGridLayout.marginHeight = 15;
		mainGridLayout.marginWidth = 70;
		setLayout(mainGridLayout);

		GridData longTextGD = new GridData(SWT.LEFT, SWT.CENTER, true, true);
		longTextGD.heightHint = 60;
	
		// Nefret Söylemi Nedir?
		Label hsTitle = new Label(this, SWT.NONE);
		hsTitle.setText("Nefret Söylemi Nedir?");
		hsTitle.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, true));

		FontData titleFontData = hsTitle.getFont().getFontData()[0];
		titleFontData.setHeight(titleFontData.getHeight() + 1);
		titleFontData.setStyle(SWT.BOLD);
		Font titleFont = new Font(getDisplay(), titleFontData);
		hsTitle.setFont(titleFont);

		StyledText hsExpLabel = new StyledText(this, SWT.MULTI | SWT.WRAP);
		hsExpLabel.setText(
				"Nefret söylemi, belirli bir grubu ya da kişiyi, ırk, cinsiyet, yaş, ulus, din, dil, politik görüş, sosyoekonomik sınıf, dış görünüm, fiziki/zihinsel engel, meslek ya da cinsiyet gibi konularda aşağılar veya tehdit eder tarzda konuşma olarak tanımlanır.");
		hsExpLabel.setLayoutData(longTextGD);
		hsExpLabel.setBackground(getShell().getBackground());
		hsExpLabel.setEditable(false);
		hsExpLabel.setCaret(null);

		// Nefret Söylemi Türleri
		Label hsTypeTitle = new Label(this, SWT.NONE);
		hsTypeTitle.setText("Nefret Söylemi Türleri");
		hsTypeTitle.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, true));
		hsTypeTitle.setFont(titleFont);

		StyledText hsTypeExpLabel = new StyledText(this, SWT.MULTI | SWT.WRAP);
		hsTypeExpLabel.setText(
				"Bu çalışmadaki nefret söylemi türleri şöyledir : ırkçılık, yabancı düşmanlığı, göçmen karşıtlığı, cinsiyetçilik, politik nefret ve dini nefret. Lütfen nefret söylemi olarak etiketlediğiniz bir tweet için en az bir tane nefret söylemi türü seçimi yapınız. Birden fazla seçim yapabilirsiniz. Bu listenin haricinde bir tür olduğunu düşünüyorsanız, \"Diğer\" seçeneğini işaretleyip, türü yazınız.");
		hsTypeExpLabel.setLayoutData(longTextGD);
		hsTypeExpLabel.setBackground(getShell().getBackground());
		hsTypeExpLabel.setEditable(false);
		hsTypeExpLabel.setCaret(null);

		StyledText hsTypeAttentionLabel = new StyledText(this, SWT.MULTI | SWT.WRAP);
		hsTypeAttentionLabel.setText(
				" DİKKAT! Irkçılık ve yabancı düşmanlığı birbiriyle alakalı türler olduğu için seçim yaparken dikkatli olunuz. Irkçılık özel bir ırka olan nefret olup (örneğin siyahilere yönelik nefret), yabancı düşmanlığı kendi kültüründen olmayanlara yönelik nefrettir. Memleketi A şehri olan birinin B şehirli birine olan nefreti de yabancı düşmanlığı olabildiği gibi göçmen karşıtlığı da yabancı düşmanlığının bir alt türü olarak görülür. Yabancı düşmanları kendi vatanında ya da memleketinde başka kültürden insanları istemeyen ve dışlayan bir tutum sergilerler.");
		hsTypeAttentionLabel.setLayoutData(longTextGD);
		hsTypeAttentionLabel.setBackground(getShell().getBackground());
		hsTypeAttentionLabel.setEditable(false);
		hsTypeAttentionLabel.setCaret(null);

		// Hedef Kitle
		Label hsTargetTitle = new Label(this, SWT.NONE);
		hsTargetTitle.setText("Hedef Kitle");
		hsTargetTitle.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, true));
		hsTargetTitle.setFont(titleFont);

		StyledText hsTargetExpLabel = new StyledText(this, SWT.MULTI | SWT.WRAP);
		hsTargetExpLabel.setText(
				"Nefret söylemi türüne göre hedef kitle değişmektedir. Örneğin cinsiyetçiliğin hedefi, erkekler ya da kadınlar olabilir. Dini nefretin hedefi Müslümanlar, Hristiyanlar veya başka bir dini inanca sahip insanlar olabilir. Lütfen nefret söylemi olarak etiketlediğiniz bir tweet için en az bir tane hedef kitle seçimi yapınız. Birden fazla seçim yapabilirsiniz. Bu listenin haricinde bir hedef kitle olduğunu düşünüyorsanız, \"Diğer\" seçeneğini işaretleyip, hedef kitleyi yazınız.");
		hsTargetExpLabel.setLayoutData(longTextGD);
		hsTargetExpLabel.setBackground(getShell().getBackground());
		hsTargetExpLabel.setEditable(false);
		hsTargetExpLabel.setCaret(null);

		StyledText hsTargetAttentionLabel = new StyledText(this, SWT.MULTI | SWT.WRAP);
		hsTargetAttentionLabel.setText(
				" DİKKAT! Nefret söylemi bütün bir grubu hedef alabildiği gibi (örneğin kadınlar/erkekler aptaldır şeklinde), belirli bir kişiyi de hedefleyebilir (örneğin, x partisinin genel başkanı sahtekardır gibi), böyle bir durumda \"Belirli bir kişi\" seçeneğini seçiniz.");
		hsTargetAttentionLabel.setLayoutData(longTextGD);
		hsTargetAttentionLabel.setBackground(getShell().getBackground());
		hsTargetAttentionLabel.setEditable(false);
		hsTargetAttentionLabel.setCaret(null);
		
		// İçerik
		Label hsContentTitle = new Label(this, SWT.NONE);
		hsContentTitle.setText("İçerik");
		hsContentTitle.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, true));
		hsContentTitle.setFont(titleFont);

		StyledText hsContentExpLabel = new StyledText(this, SWT.MULTI | SWT.WRAP);
		hsContentExpLabel.setText(
				"Nefret söylemi farklı şekillerde yapılabilir, bu çalışmada başlıca içerik örnekleri şöyledir : Ahlaki hakaret, entellektüel beceriye hakaret, dış görünüşe hakaret, şiddet yanlılığı, küfür, lanet/beddua, simgeleştirme*. Lütfen nefret söylemi olarak etiketlediğiniz bir tweet için en az bir tane içerik seçimi yapınız. Birden fazla seçim yapabilirsiniz. Bu listenin haricinde bir içerik türü olduğunu düşünüyorsanız, \"Diğer\" seçeneğini işaretleyip, içerik türünü yazınız.");
		hsContentExpLabel.setLayoutData(longTextGD);
		hsContentExpLabel.setBackground(getShell().getBackground());
		hsContentExpLabel.setEditable(false);
		hsContentExpLabel.setCaret(null);

		StyledText hsSymbolizationExpLabel = new StyledText(this, SWT.MULTI | SWT.WRAP);
		hsSymbolizationExpLabel.setText(
				"* Simgeleştirme, bir gruptan(ırk, cinsiyet vb.) kötü bir anlamı simgeleyecek şekilde bahsetmektir. Simgeleştirmede bir kimlik ögesi aşağılama unsuru olarak kullanılır. Örneğin \"Bizans’ın torunları\" sözü ile Rumlar aşağılanarak simgeleştirme yapılmaktadır.");
		hsSymbolizationExpLabel.setLayoutData(longTextGD);
		hsSymbolizationExpLabel.setBackground(getShell().getBackground());
		hsSymbolizationExpLabel.setEditable(false);
		hsSymbolizationExpLabel.setCaret(null);

		//Button
		Button button = new Button(this, SWT.PUSH);
		button.setText("Okudum ve Anladım");
		button.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, true));
		button.addSelectionListener(new SelectionAdapter() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				AnnToolWindow.setParticipantComposite();
			}
		});
	}

}
