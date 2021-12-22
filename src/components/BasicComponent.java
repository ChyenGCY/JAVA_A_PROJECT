package components;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JComponent;

public abstract class BasicComponent extends JComponent {
    public BasicComponent() {
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                onMouseClicked();
            }

        });

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                super.mouseEntered(e);
                onMouseEntered();
            }

        });

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                super.mouseExited(e);
                onMouseExited();
            }

        });

        // this.addMouseMotionListener(new MouseAdapter() {
        // @Override
        // public void mouseMoved(MouseEvent e) {
        // super.mouseMoved(e);
        // onMouseMoved();
        // }
        // });
    }

    // public abstract void onMouseMoved();

    protected abstract void onMouseEntered();

    protected abstract void onMouseExited();

    public abstract void onMouseClicked();
}
