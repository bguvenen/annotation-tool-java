package anntool.window.dialogs.participantviewer;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

public class ParticipantListDialog extends Dialog
{

    public ParticipantListDialog(Shell parentShell)
    {
        super(parentShell);
    }

    @Override
    protected Control createDialogArea(Composite parent)
    {
        Composite composite = (Composite) super.createDialogArea(parent);
        composite.setLayout(new GridLayout());

        new ParticipantViewerComposite(composite);

        return composite;
    }

    @Override
    protected void configureShell(Shell newShell)
    {
        super.configureShell(newShell);
        newShell.setText("Kullanıcı Listesi");
    }

    @Override
    protected void createButtonsForButtonBar(Composite parent)
    {

    }

    @Override
    protected Point getInitialSize()
    {
        getShell().pack();

        // Point size = new Point(getShell().getSize().x + 50, getShell().getSize().y + 50);

        Point size = getShell().getSize();

        return size;
    }
}
