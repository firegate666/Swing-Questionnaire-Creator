package de.sdavids.swing.dialog;

import java.awt.Component;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JDialog;

import de.sdavids.swing.SwingUtils;
import de.sdavids.swing.InfoTipWindow;

public class InfoTipDialog extends JDialog {

	private InfoTipWindow fInfoTip = new InfoTipWindow();

	public InfoTipDialog(Dialog owner) {
		super(owner);
	}

	public InfoTipDialog(Dialog owner, String title) {
		super(owner, title);
	}

	public InfoTipDialog(Dialog owner, String title, boolean modal) {
		super(owner, title, modal);
	}

	public InfoTipDialog(Frame owner) {
		super(owner);
	}

	public InfoTipDialog(Frame owner, String title) {
		super(owner, title);
	}

	public InfoTipDialog(Frame owner, String title, boolean modal) {
		super(owner, title, modal);
	}

	public InfoTipDialog(Frame owner, boolean modal) {
		super(owner, modal);
	}
	
	protected void cancelAction() {}
		
	protected void showInfoTip(Component comp, String text) {
		boolean sameText = fInfoTip.getMessage().equals(text);
		
		if (fInfoTip.isVisible() && sameText) return;
		
		if (!sameText) fInfoTip.setMessage(text);

		SwingUtils.showBelow(comp, fInfoTip);
	}

	protected void hideInfoTip() {
		fInfoTip.setVisible(false);
	}
}