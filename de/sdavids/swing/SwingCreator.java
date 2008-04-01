package de.sdavids.swing;

import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.net.URL;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.KeyStroke;

import de.sdavids.swing.actions.ActionImpl;
import de.sdavids.swing.controls.JBinaryTextButton;
import de.sdavids.swing.controls.JReturnButton;
import de.sdavids.swing.controls.JReturnToggleButton;
import de.sdavids.swing.controls.JTextFieldWithPopUp;
import de.sdavids.swing.controls.NumberSpinner;
import de.sdavids.swing.progress.ProgressIndicator;
import de.sdavids.util.StringUtils;

public class SwingCreator {
	//<body text=black> has to be embedded in the HTML for JReturnButton and JReturnToggleButton to work correctly
	protected static final String HTML_START =
		"<html><body text=black><font face=dialog.bold size=-1><b>";
	protected static final String HTML_END = "</b></font></body></html>";
	protected static final String HTML_UNDERLINE_START = "<u>";
	protected static final String HTML_UNDERLINE_END = "</u>";

	public static String replacePlaceHolders(String result, String[] values) {
		for (int i = (values.length - 1); i >= 0; i--) {
			//remove the string creation
			result = StringUtils.replace(result, ("{" + i + "}"), values[i]);
		}

		return result;
	}

	//SD-02-01-21: should be factored -> could break existing files
	public static Icon newIcon(String name, ResourceBundle bundle) {
		String iconPath = "";

		try {
			iconPath = adjustPath(bundle.getString("icon.path"));
		} catch (MissingResourceException e) {}

		try {
			URL url = SwingCreator.class.getResource(iconPath + bundle.getString((name + ".icon")));
			return new ImageIcon(url);
			//non-I18N-NLS
		} catch (MissingResourceException e) {}

		return null;
	}

	public static Icon newLargeIcon(String name, ResourceBundle bundle) {
		String iconPath = "";

		try {
			iconPath = adjustPath(bundle.getString("icon.path"));
		} catch (MissingResourceException e) {}

		try {
			return new ImageIcon(iconPath + bundle.getString((name + ".largeIcon")));
			//non-I18N-NLS
		} catch (MissingResourceException e) {}

		return null;
	}
	//SD-02-01-21

	public static ProgressIndicator newProgressIndicator() {
		return new ProgressIndicator();
	}	
	
	public static NumberSpinner newNumberSpinner(
		String name,
		ResourceBundle bundle,
		PropertyChangeListener listener) {

		NumberSpinner result = new NumberSpinner();

		result.setName(name);

		try {
			result.setEnabled(
				new Boolean(bundle.getString(name + ".enabled")).booleanValue());
			//non-I18N-NLS
		} catch (MissingResourceException e) {
			result.setEnabled(true);
		}

		try {
			result.setEditable(
				new Boolean(bundle.getString(name + ".editable")).booleanValue());
			//non-I18N-NLS
		} catch (MissingResourceException e) {
			result.setEditable(true);
		}

		try {
			result.setDirection(
				new Boolean(bundle.getString(name + ".upIncreasesNumber")).booleanValue());
			//non-I18N-NLS
		} catch (MissingResourceException e) {
			result.setDirection(true);
		}

		int min = result.getMin();
		int max = result.getMax();

		try {
			min = Integer.parseInt(bundle.getString(name + ".minValue")); //non-I18N-NLS
		} catch (NumberFormatException e) {
			//use default
		} catch (MissingResourceException e) {
			//use default
		}

		try {
			max = Integer.parseInt(bundle.getString(name + ".maxValue")); //non-I18N-NLS
		} catch (NumberFormatException e) {
			//use default
		} catch (MissingResourceException e) {
			//use default
		}

		result.setMax(max);
		result.setMin(min);

		try {
			if (new Boolean(bundle.getString(name + ".default")).booleanValue()) {
				//non-I18N-NLS
				result.requestDefaultFocus();
			}
		} catch (MissingResourceException e) {}

		try {
			result.setDefault(Integer.parseInt(bundle.getString(name + ".defaultValue")));
			//non-I18N-NLS
		} catch (NumberFormatException e) {
			//use default
		} catch (MissingResourceException e) {
			//use default
		}

		String tooltip = "";

		try {
			tooltip = bundle.getString(name + ".tooltip"); //non-I18N-NLS
		} catch (MissingResourceException e) {}

		if ("".equals(tooltip)) {
			tooltip = (min + "..." + max);
		} else {
			String[] values = { String.valueOf(min), String.valueOf(max)};

			tooltip = SwingCreator.replacePlaceHolders(tooltip, values);
		}

		result.setToolTipText(tooltip);

		String infotip = "";

		try {
			infotip = bundle.getString(name + ".infotip"); //non-I18N-NLS
		} catch (MissingResourceException e) {}

		if ("".equals(infotip)) {
			infotip = (min + "-" + max);
		} else {
			String[] values = { String.valueOf(min), String.valueOf(max)};

			infotip = SwingCreator.replacePlaceHolders(infotip, values);
		}

		try {
			result.setColumns((new Integer(bundle.getString(name + ".cols"))).intValue());
			//non-I18N-NLS
		} catch (NumberFormatException e) {} catch (MissingResourceException e) {}

		result.setInfoTipText(infotip);

		if (listener != null)
			result.addPropertyChangeListener(listener);

		return result;
	}

	public static JButton newJButton(String name, ResourceBundle bundle) {
		return newJButton(name, null, bundle);
	}

	public static JButton newJButton(
		String name,
		Icon icon,
		ResourceBundle bundle) {

		return newJButton(name, icon, bundle, null);
	}

	public static JButton newJButton(
		String name,
		ResourceBundle bundle,
		ActionListener listener) {

		return newJButton(name, null, bundle, listener);
	}

	public static JButton newJButton(
		String name,
		Icon icon,
		ResourceBundle bundle,
		ActionListener listener) {

		JButton result;

		if (null == icon) {
			result = new JReturnButton(bundle.getString(name));
		} else {
			result = new JReturnButton(icon);
		}

		result.setName(name);

		try {
			result.setText(bundle.getString(name + ".text")); //non-I18N-NLS
		} catch (MissingResourceException e) {
			result.setText(bundle.getString(name));
		}

		try {
			String keyStrg = bundle.getString(name + ".mnm"); //non-I18N-NLS           

			if (!"".equals(keyStrg) && (0 < keyStrg.length())) {
				char key = keyStrg.charAt(0);

				String text = result.getText();

				int index = text.toLowerCase().indexOf(("" + key).toLowerCase().charAt(0));

				if (-1 != index) {
					result.setMnemonic(key);

					String underlined =
						HTML_UNDERLINE_START + text.charAt(index) + HTML_UNDERLINE_END;

					StringBuffer sb = new StringBuffer(text);

					sb.delete(index, (index + 1));
					sb.insert(index, underlined);

					sb.insert(0, HTML_START);
					sb.append(HTML_END);

					result.setText(sb.toString());
				}
			}
		} catch (MissingResourceException e) {}

		try {
			result.setEnabled(
				new Boolean(bundle.getString(name + ".enabled")).booleanValue());
			//non-I18N-NLS
		} catch (MissingResourceException e) {
			result.setEnabled(true);
		}

		try {
			result.setToolTipText(bundle.getString(name + ".tooltip")); //non-I18N-NLS
		} catch (MissingResourceException e) {}

		try {
			result.setActionCommand(bundle.getString(name + ".acmd"));
			//non-I18N-NLS       
		} catch (MissingResourceException e) {}

		if (listener != null)
			result.addActionListener(listener);

		return result;
	}

	public static JButton newJButton(ActionImpl action) {
		return newJButton(action, null);
	}

	public static JButton newJButton(
		ActionImpl action,
		ActionListener listener) {

		if (null == action)
			throw new IllegalArgumentException("action null");

		JButton result = new JReturnButton(action);

		//show the text with underlined mnemonic; action !changed
		if (action.hasMnemonicKey()) {
			char key = (char) action.getMnemonicKey();

			String text = action.getName();

			int index = text.toLowerCase().indexOf(("" + key).toLowerCase().charAt(0));

			if (-1 != index) {
				String underlined =
					HTML_UNDERLINE_START + text.charAt(index) + HTML_UNDERLINE_END;

				StringBuffer sb = new StringBuffer(text);

				sb.delete(index, (index + 1));
				sb.insert(index, underlined);

				sb.insert(0, HTML_START);
				sb.append(HTML_END);

				result.setText(sb.toString());
			}
		}
			
		if (listener != null)
			result.addActionListener(listener);

		return result;
	}

	public static JBinaryTextButton newJBinaryTextButton(
		String name,
		ResourceBundle bundle) {
		return newJBinaryTextButton(name, null, bundle);
	}

	public static JBinaryTextButton newJBinaryTextButton(
		String name,
		Icon icon,
		ResourceBundle bundle) {

		return newJBinaryTextButton(name, icon, bundle, null);
	}

	public static JBinaryTextButton newJBinaryTextButton(
		String name,
		ResourceBundle bundle,
		ActionListener listener) {

		return newJBinaryTextButton(name, null, bundle, listener);
	}

	public static JBinaryTextButton newJBinaryTextButton(
		String name,
		Icon icon,
		ResourceBundle bundle,
		ActionListener listener) {

		String alternateText;

		try {
			alternateText = bundle.getString(name + ".alternateText"); //non-I18N-NLS
		} catch (MissingResourceException e) {
			alternateText = "";
		}

		JBinaryTextButton result =
			new JBinaryTextButton(bundle.getString(name), alternateText);

		if (null != icon)
			result.setIcon(icon);

		result.setName(name);

		try {
			result.setText(bundle.getString(name + ".text")); //non-I18N-NLS
		} catch (MissingResourceException e) {
			result.setText(bundle.getString(name));
		}

		try {
			String keyStrg = bundle.getString(name + ".mnm"); //non-I18N-NLS           

			if (!"".equals(keyStrg) && (0 < keyStrg.length())) {
				char key = keyStrg.charAt(0);

				String text = result.getText();

				int index = text.toLowerCase().indexOf(("" + key).toLowerCase().charAt(0));

				if (-1 != index) {
					result.setMnemonic(key);

					String underlined =
						HTML_UNDERLINE_START + text.charAt(index) + HTML_UNDERLINE_END;

					StringBuffer sb = new StringBuffer(text);

					sb.delete(index, (index + 1));
					sb.insert(index, underlined);

					sb.insert(0, HTML_START);
					sb.append(HTML_END);

					result.setText(sb.toString());
				}
			}
		} catch (MissingResourceException e) {}

		try {
			result.setEnabled(
				new Boolean(bundle.getString(name + ".enabled")).booleanValue());
			//non-I18N-NLS
		} catch (MissingResourceException e) {
			result.setEnabled(true);
		}

		try {
			result.setToolTipText(bundle.getString(name + ".tooltip")); //non-I18N-NLS
		} catch (MissingResourceException e) {}

		try {
			result.setActionCommand(bundle.getString(name + ".acmd"));
			//non-I18N-NLS       
		} catch (MissingResourceException e) {}

		if (listener != null)
			result.addActionListener(listener);

		return result;
	}

	public static JToggleButton newJToggleButton(
		String name,
		ResourceBundle bundle) {

		return newJToggleButton(name, null, bundle);
	}

	public static JToggleButton newJToggleButton(
		String name,
		Icon icon,
		ResourceBundle bundle) {

		return newJToggleButton(name, icon, bundle, null);
	}

	public static JToggleButton newJToggleButton(
		String name,
		ResourceBundle bundle,
		ActionListener listener) {

		return newJToggleButton(name, null, bundle, listener);
	}

	public static JToggleButton newJToggleButton(ActionImpl action) {
		return newJToggleButton(action, null);
	}

	public static JToggleButton newJToggleButton(
		ActionImpl action,
		ActionListener listener) {

		if (null == action)
			throw new IllegalArgumentException("action null");

		JToggleButton result = new JToggleButton(action);

		class ActionImplPCL implements PropertyChangeListener {
			JToggleButton fBtn;

			ActionImplPCL(JToggleButton btn) {
				fBtn = btn;
			}

			public void propertyChange(PropertyChangeEvent evt) {
				if ("selected".equals(evt.getPropertyName())) {
					boolean oldV = fBtn.isSelected();
					boolean newV = ((Boolean) evt.getNewValue()).booleanValue();

					if (oldV != newV)
						fBtn.setSelected(newV);
				}
			}
		};

		class JToggleButtonPCL implements PropertyChangeListener {
			ActionImpl fAct;

			JToggleButtonPCL(ActionImpl act) {
				fAct = act;
			}

			public void propertyChange(PropertyChangeEvent evt) {
				if ("selected".equals(evt.getPropertyName())) {
					boolean oldV = fAct.isSelected();
					boolean newV = ((Boolean) evt.getNewValue()).booleanValue();

					if (oldV != newV)
						fAct.setSelected(newV);
				}
			}
		};

		action.addPropertyChangeListener(new ActionImplPCL(result));
		result.addPropertyChangeListener(new JToggleButtonPCL(action));

		result.setSelected(action.isSelected());

		if (listener != null)
			result.addActionListener(listener);

		return result;
	}

	public static JToggleButton newJToggleButton(
		String name,
		Icon icon,
		ResourceBundle bundle,
		ActionListener listener) {

		JToggleButton result;

		if (null == icon) {
			result = new JReturnToggleButton(bundle.getString(name));
		} else {
			result = new JReturnToggleButton(icon);
		}

		result.setName(name);

		try {
			result.setText(bundle.getString(name + ".text")); //non-I18N-NLS
		} catch (MissingResourceException e) {
			result.setText(bundle.getString(name));
		}

		try {
			String keyStrg = bundle.getString(name + ".mnm"); //non-I18N-NLS           

			if (!"".equals(keyStrg) && (0 < keyStrg.length())) {
				char key = keyStrg.charAt(0);

				String text = result.getText();

				int index = text.toLowerCase().indexOf(("" + key).toLowerCase().charAt(0));

				if (-1 != index) {
					result.setMnemonic(key);

					String underlined =
						HTML_UNDERLINE_START + text.charAt(index) + HTML_UNDERLINE_END;

					StringBuffer sb = new StringBuffer(text);

					sb.delete(index, (index + 1));
					sb.insert(index, underlined);

					sb.insert(0, HTML_START);
					sb.append(HTML_END);

					result.setText(sb.toString());
				}
			}
		} catch (MissingResourceException e) {}

		try {
			result.setEnabled(
				new Boolean(bundle.getString(name + ".enabled")).booleanValue());
			//non-I18N-NLS
		} catch (MissingResourceException e) {
			result.setEnabled(true);
		}

		try {
			result.setSelected(
				new Boolean(bundle.getString(name + ".selected")).booleanValue());
			//non-I18N-NLS
		} catch (MissingResourceException e) {
			result.setSelected(false);
		}

		try {
			result.setToolTipText(bundle.getString(name + ".tooltip")); //non-I18N-NLS
		} catch (MissingResourceException e) {}

		try {
			result.setActionCommand(bundle.getString(name + ".acmd"));
			//non-I18N-NLS       
		} catch (MissingResourceException e) {}

		if (listener != null)
			result.addActionListener(listener);

		return result;
	}

	public static JPasswordField newJPasswordField(
		String name,
		ResourceBundle bundle) {
		return newJPasswordField(name, bundle, null, null);
	}

	public static JPasswordField newJPasswordField(
		String name,
		ResourceBundle bundle,
		ActionListener aListener) {

		return newJPasswordField(name, bundle, aListener, null);
	}

	public static JPasswordField newJPasswordField(
		String name,
		ResourceBundle bundle,
		KeyListener kListener) {

		return newJPasswordField(name, bundle, null, kListener);
	}

	public static JPasswordField newJPasswordField(
		String name,
		ResourceBundle bundle,
		ActionListener aListener,
		KeyListener kListener) {

		if (null == name)
			throw new IllegalArgumentException("name null");

		JPasswordField result = new JPasswordField();

		result.setName(name);

		try {
			result.setText(bundle.getString(name + ".text")); //non-I18N-NLS
		} catch (MissingResourceException e) {}

		try {
			result.setEditable(
				new Boolean(bundle.getString(name + ".editable")).booleanValue());
			//non-I18N-NLS
		} catch (MissingResourceException e) {
			result.setEditable(true);
		}

		try {
			result.setEnabled(
				new Boolean(bundle.getString(name + ".enabled")).booleanValue());
			//non-I18N-NLS
		} catch (MissingResourceException e) {
			result.setEnabled(true);
		}

		try {
			if (new Boolean(bundle.getString(name + ".default")).booleanValue()) {
				//non-I18N-NLS
				result.requestDefaultFocus();
			}
		} catch (MissingResourceException e) {}

		try {
			result.setToolTipText(bundle.getString(name + ".tooltip")); //non-I18N-NLS
		} catch (MissingResourceException e) {}

		try {
			result.setActionCommand(bundle.getString(name + ".acmd"));
			//non-I18N-NLS       
		} catch (MissingResourceException e) {}

		try {
			result.setColumns((new Integer(bundle.getString(name + ".cols"))).intValue());
			//non-I18N-NLS
		} catch (NumberFormatException e) {} catch (MissingResourceException e) {}

		if (aListener != null)
			result.addActionListener(aListener);
		if (kListener != null)
			result.addKeyListener(kListener);

		return result;
	}

	public static JTextField newJTextField(String name, ResourceBundle bundle) {
		return newJTextField(name, bundle, null, null);
	}

	public static JTextField newJTextField(
		String name,
		ResourceBundle bundle,
		ActionListener aListener) {
		return newJTextField(name, bundle, aListener, null);
	}

	public static JTextField newJTextField(
		String name,
		ResourceBundle bundle,
		KeyListener kListener) {
		return newJTextField(name, bundle, null, kListener);
	}

	public static JTextField newJTextField(
		String name,
		ResourceBundle bundle,
		ActionListener aListener,
		KeyListener kListener) {

		if (null == name)
			throw new IllegalArgumentException("name null");

		JTextField result = new JTextFieldWithPopUp();

		result.setName(name);

		try {
			result.setText(bundle.getString(name + ".text")); //non-I18N-NLS
		} catch (MissingResourceException e) {}

		try {
			result.setEditable(
				new Boolean(bundle.getString(name + ".editable")).booleanValue());
			//non-I18N-NLS
		} catch (MissingResourceException e) {
			result.setEditable(true);
		}

		try {
			result.setEnabled(
				new Boolean(bundle.getString(name + ".enabled")).booleanValue());
			//non-I18N-NLS
		} catch (MissingResourceException e) {
			result.setEnabled(true);
		}

		try {
			if (new Boolean(bundle.getString(name + ".default")).booleanValue()) {
				//non-I18N-NLS
				result.requestDefaultFocus();
			}
		} catch (MissingResourceException e) {}

		try {
			result.setToolTipText(bundle.getString(name + ".tooltip")); //non-I18N-NLS
		} catch (MissingResourceException e) {}

		try {
			result.setActionCommand(bundle.getString(name + ".acmd"));
			//non-I18N-NLS       
		} catch (MissingResourceException e) {}

		try {
			result.setColumns((new Integer(bundle.getString(name + ".cols"))).intValue());
			//non-I18N-NLS
		} catch (NumberFormatException e) {} catch (MissingResourceException e) {}

		if (aListener != null)
			result.addActionListener(aListener);
		if (kListener != null)
			result.addKeyListener(kListener);

		return result;
	}

	public static Action initAction(ActionImpl action, ResourceBundle bundle) {
		if (null == action)
			throw new IllegalArgumentException("action null");
		if (null == bundle)
			throw new IllegalArgumentException("bundle null");
		if (action.isNull())
			return action; //don't configure NullAction
		if (null != action.getValue(action.DEFAULT))
			throw new IllegalArgumentException(
				"action " + action.getValue(action.DEFAULT) + " already configured");

		String name = action.getName();

		if (null == name)
			throw new IllegalArgumentException("Action.NAME null");

		action.putValue(action.DEFAULT, name);

		try {
			action.setName(bundle.getString(name));
		} catch (MissingResourceException e) {}

		try {
			String keyStrg = bundle.getString(name + ".mnm"); //non-I18N-NLS           

			if (!"".equals(keyStrg)  && (0 < keyStrg.length()))
				action.setMnemonicKey(keyStrg.charAt(0));
		} catch (MissingResourceException e) {}

		Icon icon = newIcon(name, bundle);

		if (null != icon)
			action.setIcon(icon);

		Icon largeIcon = newLargeIcon(name, bundle);

		if (null != icon)
			action.setLargeIcon(largeIcon);

		try {
			action.setAccelerator(bundle.getString(name + ".acc")); //non-I18N-NLS
		} catch (MissingResourceException e) {}

		try {
			action.setActionCommand(bundle.getString(name + ".acmd"));
			//non-I18N-NLS          
		} catch (MissingResourceException e) {}

		try {
			action.setEnabled(
				new Boolean(bundle.getString(name + ".enabled")).booleanValue());
		} catch (MissingResourceException e) {
			action.setEnabled(false);
		}

		try {
			action.setSelected(
				new Boolean(bundle.getString(name + ".selected")).booleanValue());
			action.beSelectable();
			//non-I18N-NLS
		} catch (MissingResourceException e) {
			action.setSelected(false);
		}

		try {
			action.setLongDescription(bundle.getString(name + ".descr"));
			//non-I18N-NLS          
		} catch (MissingResourceException e) {}

		try {
			action.setShortDescription(bundle.getString(name + ".tooltip"));
			//non-I18N-NLS          
		} catch (MissingResourceException e) {}

		return action;
	}

	public static JCheckBoxMenuItem newJCheckBoxMenuItem(ActionImpl action) {
		if (null == action)
			throw new IllegalArgumentException("action null");

		JCheckBoxMenuItem result = new JCheckBoxMenuItem(action);

		class ActionImplPCL implements PropertyChangeListener {
			JCheckBoxMenuItem fCBm;

			ActionImplPCL(JCheckBoxMenuItem mni) {
				fCBm = mni;
			}

			public void propertyChange(PropertyChangeEvent evt) {
				if ("selected".equals(evt.getPropertyName())) {
					boolean oldV = fCBm.isSelected();
					boolean newV = ((Boolean) evt.getNewValue()).booleanValue();

					if (oldV != newV)
						fCBm.setSelected(newV);
				}
			}
		};

		class JCheckBoxMenuItemPCL implements PropertyChangeListener {
			ActionImpl fAct;

			JCheckBoxMenuItemPCL(ActionImpl act) {
				fAct = act;
			}

			public void propertyChange(PropertyChangeEvent evt) {
				if ("selected".equals(evt.getPropertyName())) {
					boolean oldV = fAct.isSelected();
					boolean newV = ((Boolean) evt.getNewValue()).booleanValue();

					if (oldV != newV)
						fAct.setSelected(newV);
				}
			}
		};

		action.addPropertyChangeListener(new ActionImplPCL(result));
		result.addPropertyChangeListener(new JCheckBoxMenuItemPCL(action));

		result.setSelected(action.isSelected());
		result.setAccelerator(action.getAccelerator());

		String text = action.getLongDescription();

		if (!((null == text) || ("".equals(text))))
			result.setText(text);

		return result;
	}

	public static JCheckBoxMenuItem newJCheckBoxMenuItem(
		String name,
		ResourceBundle bundle) {

		if (null == name)
			throw new IllegalArgumentException("name null");

		JCheckBoxMenuItem result = new JCheckBoxMenuItem();

		result.setName(name);

		try {
			result.setText(bundle.getString(name + ".text")); //non-I18N-NLS
		} catch (MissingResourceException e) {}

		try {
			result.setEnabled(
				new Boolean(bundle.getString(name + ".enabled")).booleanValue());
			//non-I18N-NLS
		} catch (MissingResourceException e) {
			result.setEnabled(true);
		}

		try {
			result.setSelected(
				new Boolean(bundle.getString(name + ".selected")).booleanValue());
			//non-I18N-NLS
		} catch (MissingResourceException e) {
			result.setSelected(false);
		}

		try {
			if (new Boolean(bundle.getString(name + ".default")).booleanValue()) {
				//non-I18N-NLS
				result.requestDefaultFocus();
			}
		} catch (MissingResourceException e) {}

		try {
			result.setToolTipText(bundle.getString(name + ".tooltip")); //non-I18N-NLS
		} catch (MissingResourceException e) {}

		try {
			result.setActionCommand(bundle.getString(name + ".acmd"));
			//non-I18N-NLS       
		} catch (MissingResourceException e) {}

		try {
			result.setAccelerator(
				KeyStroke.getKeyStroke(bundle.getString(name + ".acc")));
			//non-I18N-NLS
		} catch (MissingResourceException e) {}

		return result;
	}

	public static JMenuItem newJMenuItem(Action action) {
		if (null == action)
			throw new IllegalArgumentException("action null");

		JMenuItem result = new JMenuItem(action);

		result.setAccelerator((KeyStroke) action.getValue(action.ACCELERATOR_KEY));

		String text = (String) action.getValue(action.LONG_DESCRIPTION);

		if (!((null == text) || ("".equals(text))))
			result.setText(text);

		return result;
	}

	public static JMenuItem newJMenuItem(ActionImpl action) {
		if (null == action)
			throw new IllegalArgumentException("action null");

		JMenuItem result = new JMenuItem(action);

		result.setAccelerator(action.getAccelerator());

		String text = action.getName();

		if (!((null == text) || ("".equals(text))))
			result.setText(text);

		return result;
	}

	public static JMenuItem newJMenuItem(String name, ResourceBundle bundle) {

		if (null == name)
			throw new IllegalArgumentException("name null");

		JMenuItem result = new JMenuItem();

		result.setName(name);

		try {
			result.setText(bundle.getString(name + ".text")); //non-I18N-NLS
		} catch (MissingResourceException e) {}

		try {
			result.setEnabled(
				new Boolean(bundle.getString(name + ".enabled")).booleanValue());
			//non-I18N-NLS
		} catch (MissingResourceException e) {
			result.setEnabled(true);
		}

		try {
			if (new Boolean(bundle.getString(name + ".default")).booleanValue()) {
				//non-I18N-NLS
				result.requestDefaultFocus();
			}
		} catch (MissingResourceException e) {}

		try {
			result.setToolTipText(bundle.getString(name + ".tooltip")); //non-I18N-NLS
		} catch (MissingResourceException e) {}

		try {
			result.setActionCommand(bundle.getString(name + ".acmd"));
			//non-I18N-NLS       
		} catch (MissingResourceException e) {}

		try {
			result.setAccelerator(
				KeyStroke.getKeyStroke(bundle.getString(name + ".acc")));
			//non-I18N-NLS
		} catch (MissingResourceException e) {}

		return result;
	}

	/**
	 * If the given path does not end in <em>/</em> append it.
	 *
	 * @param	<code>path</code>the path.
	 * @return	a path with <em>/</em> as the last character.
	 */
	protected static String adjustPath(String path) {
		if ('/' != path.charAt(path.length() - 1)) {
			path += "/";
		}

		return path;
	}

	public static JMenu newJMenu(
		String name,
		ResourceBundle bundle,
		ActionImpl[] actions) {
		if (null == name)
			throw new IllegalArgumentException("name null");

		JMenu result = new JMenu(bundle.getString(name));

		try {
			result.setText(bundle.getString(name + ".text")); //non-I18N-NLS
		} catch (MissingResourceException e) {}

		try {
			result.setEnabled(
				new Boolean(bundle.getString(name + ".enabled")).booleanValue());
			//non-I18N-NLS
		} catch (MissingResourceException e) {
			result.setEnabled(true);
		}

		try {
			if (new Boolean(bundle.getString(name + ".default")).booleanValue()) {
				//non-I18N-NLS
				result.requestDefaultFocus();
			}
		} catch (MissingResourceException e) {}

		try {
			result.setToolTipText(bundle.getString(name + ".tooltip")); //non-I18N-NLS
		} catch (MissingResourceException e) {}

		try {
			result.setActionCommand(bundle.getString(name + ".acmd"));
			//non-I18N-NLS       
		} catch (MissingResourceException e) {}

		try {
			String keyStrg = bundle.getString(name + ".mnm"); //non-I18N-NLS           

			if (!"".equals(keyStrg) && (0 < keyStrg.length())) {
				char key = keyStrg.charAt(0);

				String text = result.getText();

				int index = text.toLowerCase().indexOf(("" + key).toLowerCase().charAt(0));

				if (-1 != index) {
					result.setMnemonic(key);
				}
			}
		} catch (MissingResourceException e) {}

		int len = actions.length;

		ActionImpl action;

		for (int i = 0; i < len; i++) {
			action = actions[i];

			if (null == action) {
				result.addSeparator();
			} else if (action.isSelectable()) {
				result.add(newJCheckBoxMenuItem(action));
			} else {
				result.add(newJMenuItem(action));
			}
		}

		return result;
	}
}