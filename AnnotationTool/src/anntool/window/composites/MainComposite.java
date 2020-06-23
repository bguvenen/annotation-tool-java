package anntool.window.composites;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Shell;

import anntool.database.Database;
import anntool.window.AnnToolWindow;
import anntool.window.dialogs.PasswordDialog;

public class MainComposite extends Composite {
	private Shell shell;

	public MainComposite(Composite parent) {
		super(parent, SWT.NONE);
		Database.wakeUp();

		shell = getShell();
		setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true));

		GridLayout gl = new GridLayout(1, false);
		gl.verticalSpacing = 20;
		setLayout(gl);

		Button adminButton = new Button(this, SWT.PUSH);
		adminButton.setText("Admin");
		GridData gd1 = new GridData(SWT.CENTER, SWT.BOTTOM, true, true);
		gd1.widthHint = 200;
		gd1.heightHint = 50;
		adminButton.setLayoutData(gd1);
		adminButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				PasswordDialog passwordDialog = new PasswordDialog(shell);
				passwordDialog.open();
			}
		});

		Button participantButton = new Button(this, SWT.PUSH);
		participantButton.setText("Katılımcı");
		GridData gd2 = new GridData(SWT.CENTER, SWT.UP, true, true);
		gd2.widthHint = 200;
		gd2.heightHint = 50;
		participantButton.setLayoutData(gd2);
		participantButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				AnnToolWindow.setParticipantComposite();
			}
		});

	}

}
