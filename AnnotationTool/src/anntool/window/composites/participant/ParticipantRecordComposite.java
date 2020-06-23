package anntool.window.composites.participant;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import anntool.database.Database;
import anntool.datamodels.Participant;
import anntool.enums.EducationalStatus;
import anntool.enums.Gender;
import anntool.enums.Nationality;
import anntool.enums.PoliticalView;
import anntool.window.AnnToolWindow;

public class ParticipantRecordComposite extends Composite
{
    private Gender gender = null;
    private int age = -1;
    private String nationality = "";
    private Text nationalityText;

    public ParticipantRecordComposite(Composite parent)
    {
        super(parent, SWT.NONE);
        setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

        GridLayout mainGridLayout = new GridLayout(2, false);
        mainGridLayout.horizontalSpacing = 20;
        mainGridLayout.verticalSpacing = 30;
        setLayout(mainGridLayout);

        // age

        Label ageLabel = new Label(this, SWT.NONE);
        ageLabel.setText("Yaşınız : ");
        ageLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.BOTTOM, true, true));

        Text ageText = new Text(this, SWT.BORDER);
        ageText.setLayoutData(new GridData(SWT.LEFT, SWT.BOTTOM, true, true));
        ageText.addVerifyListener(new VerifyListener()
        {
            @Override
            public void verifyText(VerifyEvent e)
            {
                final String oldS = ageText.getText();
                final String newS = oldS.substring(0, e.start) + e.text + oldS.substring(e.end);

                if (!newS.equals(""))
                {
                    try
                    {
                        int value = Integer.parseInt(newS);
                        if (value <= 0)
                        {
                            e.doit = false;
                        }
                    }
                    catch (Exception e2)
                    {
                        e.doit = false;
                    }
                }
            }
        });

        // gender
        Label genderLabel = new Label(this, SWT.NONE);
        genderLabel.setText("Cinsiyetiniz : ");
        genderLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, true, false));

        Group genderGroup = new Group(this, SWT.NONE);
        genderGroup.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false));
        genderGroup.setLayout(new GridLayout(2, true));

        Button femaleButton = new Button(genderGroup, SWT.RADIO);
        femaleButton.setText("Kadın");
        femaleButton.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false));
        femaleButton.addSelectionListener(new SelectionAdapter()
        {
            @Override
            public void widgetSelected(SelectionEvent e)
            {
                gender = Gender.FEMALE;
            }
        });

        Button maleButton = new Button(genderGroup, SWT.RADIO);
        maleButton.setText("Erkek");
        maleButton.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false));
        maleButton.addSelectionListener(new SelectionAdapter()
        {
            @Override
            public void widgetSelected(SelectionEvent e)
            {
                gender = Gender.MALE;
            }
        });

        // Nationality
        Label nationalityLabel = new Label(this, SWT.NONE);
        nationalityLabel.setText("Uyruğunuz : ");
        nationalityLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, true, false));

        Group nationalityGroup = new Group(this, SWT.NONE);
        nationalityGroup.setLayoutData(new GridData(SWT.LEFT, SWT.BOTTOM, true, false));
        nationalityGroup.setLayout(new GridLayout(3, true));

        Button turkeyButton = new Button(nationalityGroup, SWT.RADIO);
        turkeyButton.setText("Türkiye");
        turkeyButton.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false));
        turkeyButton.addSelectionListener(new SelectionAdapter()
        {
            @Override
            public void widgetSelected(SelectionEvent e)
            {
                nationality = Nationality.TURKEY.toString();
                nationalityText.setEnabled(false);
            }
        });

        Button otherButton = new Button(nationalityGroup, SWT.RADIO);
        otherButton.setText("Diğer");
        otherButton.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, false, false));
        otherButton.addSelectionListener(new SelectionAdapter()
        {
            @Override
            public void widgetSelected(SelectionEvent e)
            {
                nationality = Nationality.OTHER.toString();
                nationalityText.setEnabled(true);
            }
        });

        nationalityText = new Text(nationalityGroup, SWT.BORDER);
        nationalityText.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false));
        nationalityText.setEnabled(false);

        // Educational Status

        Label educationLabel = new Label(this, SWT.NONE);
        educationLabel.setText("Eğitim durumunuz : ");
        educationLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, true, false));

        Combo educationCombo = new Combo(this, SWT.DROP_DOWN | SWT.READ_ONLY);
        educationCombo.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false));

        String[] educationItems = new String[7];
        educationItems[0] = EducationalStatus.ELEMENTARY_SCHOOL.toString();
        educationItems[1] = EducationalStatus.MIDDLE_SCHOOL.toString();
        educationItems[2] = EducationalStatus.HIGH_SCHOOL.toString();
        educationItems[3] = EducationalStatus.ASSOCIATE_DEGREE.toString();
        educationItems[4] = EducationalStatus.UNIVERSITY.toString();
        educationItems[5] = EducationalStatus.MASTER.toString();
        educationItems[6] = EducationalStatus.DOCTORATE.toString();
        educationCombo.setItems(educationItems);
        educationCombo.setVisible(true);

        // Political View

        Label politicalViewLabel = new Label(this, SWT.NONE);
        politicalViewLabel.setText("Yakın hissettiğiniz politik görüşler : \n (Birden fazla işaretleyebilirsiniz) ");
        politicalViewLabel.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, true, false));

        Group politicalViewGroup = new Group(this, SWT.NONE);
        politicalViewGroup.setLayoutData(new GridData(SWT.LEFT, SWT.TOP, true, false));
        politicalViewGroup.setLayout(new GridLayout(1, true));

        // Liberal
        Button liberalButton = new Button(politicalViewGroup, SWT.CHECK);
        liberalButton.setText(PoliticalView.LIBERAL.toString());
        liberalButton.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, true));

        // Nationalist
        Button nationalistButton = new Button(politicalViewGroup, SWT.CHECK);
        nationalistButton.setText(PoliticalView.NATIONALIST.toString());
        nationalistButton.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, true));

        // Democrat
        Button democratButton = new Button(politicalViewGroup, SWT.CHECK);
        democratButton.setText(PoliticalView.DEMOCRAT.toString());
        democratButton.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, true));

        // Conservative
        Button conservativeButton = new Button(politicalViewGroup, SWT.CHECK);
        conservativeButton.setText(PoliticalView.CONSERVATIVE.toString());
        conservativeButton.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, true));

        // Socialist
        Button socialistButton = new Button(politicalViewGroup, SWT.CHECK);
        socialistButton.setText(PoliticalView.SOCIALIST.toString());
        socialistButton.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, true));

        // Rightist
        Button rightistButton = new Button(politicalViewGroup, SWT.CHECK);
        rightistButton.setText(PoliticalView.RIGHTIST.toString());
        rightistButton.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, true));

        // Leftist
        Button leftistButton = new Button(politicalViewGroup, SWT.CHECK);
        leftistButton.setText(PoliticalView.LEFTIST.toString());
        leftistButton.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, true));
        
        Label space = new Label(politicalViewGroup, SWT.NONE);
        space.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, true));

        // Unstated
        Button unstatedButton = new Button(politicalViewGroup, SWT.CHECK);
        unstatedButton.setText("Belirtmek istemiyorum");
        unstatedButton.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, true));
        unstatedButton.addSelectionListener(new SelectionAdapter()
        {
            @Override
            public void widgetSelected(SelectionEvent e)
            {
                boolean selection = unstatedButton.getSelection();

                if (selection)
                {
                    liberalButton.setSelection(false);
                    liberalButton.setEnabled(false);
                    nationalistButton.setSelection(false);
                    nationalistButton.setEnabled(false);
                    democratButton.setSelection(false);
                    democratButton.setEnabled(false);
                    conservativeButton.setSelection(false);
                    conservativeButton.setEnabled(false);
                    socialistButton.setSelection(false);
                    socialistButton.setEnabled(false);
                    rightistButton.setSelection(false);
                    rightistButton.setEnabled(false);
                    leftistButton.setSelection(false);
                    leftistButton.setEnabled(false);
                }
                else
                {
                    liberalButton.setEnabled(true);
                    nationalistButton.setEnabled(true);
                    democratButton.setEnabled(true);
                    conservativeButton.setEnabled(true);
                    socialistButton.setEnabled(true);
                    rightistButton.setEnabled(true);
                    leftistButton.setEnabled(true);
                }
            }
        });

        // save button
        Button saveButton = new Button(this, SWT.PUSH);
        saveButton.setText("Kaydet");
        GridData saveButtonGD = new GridData(SWT.CENTER, SWT.CENTER, true, true, 2, 1);
        saveButtonGD.heightHint = 30;
        saveButtonGD.widthHint = 200;
        saveButton.setLayoutData(saveButtonGD);
        saveButton.addSelectionListener(new SelectionAdapter()
        {
            @Override
            public void widgetSelected(SelectionEvent e)
            {

                if (gender == null || ageText.getText().isEmpty() || nationality.isEmpty()
                        || educationCombo.getSelectionIndex() == -1
                        || (!liberalButton.getSelection() && !nationalistButton.getSelection()
                                && !democratButton.getSelection() && !conservativeButton.getSelection()
                                && !socialistButton.getSelection() && !rightistButton.getSelection()
                                && !leftistButton.getSelection() && !unstatedButton.getSelection()))
                {
                    MessageDialog.openError(getShell(), "Hata", "Lütfen tüm bilgileri girdiğinizden emin olunuz.");
                    return;
                }

                age = Integer.parseInt(ageText.getText());
                if (nationality.equals(Nationality.OTHER.toString()))
                {
                    String natText = nationalityText.getText();
                    if (natText.isEmpty())
                    {
                        MessageDialog.openError(getShell(), "Hata", "Lütfen uyruğunuzu belirtiniz.");
                        return;
                    }

                    nationality = nationality + " (" + natText + ")";
                }

                String eduStat = educationCombo.getItem(educationCombo.getSelectionIndex());

                String polView = "";

                if (unstatedButton.getSelection())
                {
                    polView = "Belirtilmedi";
                }
                else
                {
                    if (liberalButton.getSelection())
                        polView = polView + PoliticalView.LIBERAL.toString() + ", ";
                    if (nationalistButton.getSelection())
                        polView = polView + PoliticalView.NATIONALIST.toString() + ", ";
                    if (democratButton.getSelection())
                        polView = polView + PoliticalView.DEMOCRAT.toString() + ", ";
                    if (conservativeButton.getSelection())
                        polView = polView + PoliticalView.CONSERVATIVE.toString() + ", ";
                    if (socialistButton.getSelection())
                        polView = polView + PoliticalView.SOCIALIST.toString() + ", ";
                    if (rightistButton.getSelection())
                        polView = polView + PoliticalView.RIGHTIST.toString() + ", ";
                    if (leftistButton.getSelection())
                        polView = polView + PoliticalView.LEFTIST.toString() + ", ";

                    polView = polView.substring(0, polView.length() - 2);
                }

                Participant participant = new Participant(-1, gender.toString(), age, nationality, eduStat, polView);
                Database.addParticipantRecord(participant);
                AnnToolWindow.setParticipantComposite();
            }
        });

    }

}
