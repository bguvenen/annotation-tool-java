package anntool.window.dialogs;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import anntool.window.AnnToolWindow;


public class PasswordDialog extends Dialog
{

    private Text passwordField;
    private Shell parentShell;

    public PasswordDialog(Shell parentShell)
    {
        super(parentShell);
        this.parentShell = parentShell;
    }

    protected Control createDialogArea(Composite parent)
    {
        Composite comp = (Composite) super.createDialogArea(parent);

        GridLayout layout = (GridLayout) comp.getLayout();
        layout.numColumns = 2;

        Label passwordLabel = new Label(comp, SWT.RIGHT);
        passwordLabel.setText("Password: ");

        passwordField = new Text(comp, SWT.SINGLE | SWT.PASSWORD);
        GridData data = new GridData(GridData.FILL_HORIZONTAL);
        passwordField.setLayoutData(data);

        return comp;
    }

    protected void createButtonsForButtonBar(Composite parent)
    {
        createButton(parent, 0, "Tamam", true);
        createButton(parent, 1, "İptal", false);
    }

    protected void buttonPressed(int buttonId)
    {
        if (buttonId == 0)
        {
            if (passwordField.getText().equals("nlp"))
            {
            	AnnToolWindow.setAdminComposite();
                close();
            }
            else
                MessageDialog.openError(parentShell, "Hata", "Girilen şifre yanlıştır.");
        }
        else
        {
            close();
        }
    }
}
