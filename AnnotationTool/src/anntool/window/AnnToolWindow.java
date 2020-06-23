package anntool.window;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import anntool.database.Database;
import anntool.window.composites.MainComposite;
import anntool.window.composites.admin.AdminComposite;
import anntool.window.composites.participant.LabelExplanationComposite;
import anntool.window.composites.participant.ParticipantLabelRecordComposite;
import anntool.window.composites.participant.ParticipantRecordComposite;

public class AnnToolWindow {

	private static Composite windowComp;
	private static Composite currentComp;
	private static boolean explanation = false;

	public static void main(String[] args) {
		Display display = new Display();
		Display.setAppName("ann_tool");
		Shell shell = new Shell(display);
		shell.setText("Nefret Söylemi Etiketleme");
		shell.setMaximized(true);
		shell.setLayout(new GridLayout());
		windowComp = new Composite(shell, SWT.NONE);
		windowComp.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		windowComp.setLayout(new GridLayout(1, false));

		MainComposite comp = new MainComposite(windowComp);
		currentComp = comp;
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch())
				display.sleep();
		}
		display.dispose();
	}

	public static void setAdminComposite() {
		currentComp.dispose();

		AdminComposite comp = new AdminComposite(windowComp);
		currentComp = comp;
		windowComp.layout();
	}

	public static void setParticipantComposite() {
		currentComp.dispose();

		if (!Database.recordDirectoriesExist())
			Database.setUpForRecords();

		if (!Database.participantRecordExists())
			currentComp = new ParticipantRecordComposite(windowComp);
		else if(!explanation)
		{
			currentComp = new LabelExplanationComposite(windowComp);
			explanation = true;
		}	
		else
			currentComp = new ParticipantLabelRecordComposite(windowComp);

		windowComp.layout();
	}

}
