/* ===========================================================================
 * Sebastian Davids <sdavids@gmx.de>
 * 
 * Copyright (c)2002 Davids. All rights reserved.
 * ===========================================================================
 */

package de.sdavids.login.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ResourceBundle;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

import de.sdavids.login.ILogin;
import de.sdavids.login.ILoginValidator;
import de.sdavids.login.InvalidAccountException;
import de.sdavids.login.InvalidPasswordException;
import de.sdavids.login.LoginFactory;
import de.sdavids.swing.SwingCreator;
import de.sdavids.swing.controls.JBinaryTextButton;
import de.sdavids.swing.panel.InfoTipPanel;

/**
 * A panel for entering an <code>ILogin</code>.
 * <p>
 * This panel fires the following event:
 * <ul>
 * 	<li><code>LoginEvent</code> with <code>true</code> as it's
 * 		<code>isLogin</code> parameter when a legal password and account have
 * 		been entered and the user either pressed the login/logout button or
 * 		pressed the <em>Enter</em> key in either textfield; or
 * 		<code>false</code> as it's <code>isLogin</code> parameter when the
 * 		logout button is pressed.</li>
 * </ul>
 * 
 * @see		de.sdavids.login.ILogin
 * 
 * @author		<a href="mailto:sdavids@gmx.de">Sebastian Davids</A>.
 * @version	1.01	02-01-13
 */
public class LoginPanel extends InfoTipPanel {

	//////////////////////////////////////////////////////////////////////////
	// ATTRIBUTES
	//////////////////////////////////////////////////////////////////////////

	/* ----------- Instance Attributes ---------- */

	/**
	 * The <code>ILoginValidator</code> to validate the users entries.
	 * 
	 * @see	de.sdavids.login.ILoginValidator
	 */
	private ILoginValidator fValidator;

	/**
	 * The subjects to be notified when a <code>LoginEvent</code> gets fired.
	 */
	private transient LoginListener fLoginListeners;

	/**
	 * The configuration.
	 */
	private ResourceBundle fConfig;

	/**
	 * The login.
	 * <p>
	 * Either a valid <code>ILogin</code> as determined by our validator or
	 * the <code>NullLogin</code>.
	 */
	private transient ILogin fLogin;

	/* ----------- Widgets ---------------------- */

	/** The account textfield.*/
	private JTextField fAccountTF;

	/** The password textfield.*/
	private JPasswordField fPwdTF;

	/** The login/logout button.*/
	private JBinaryTextButton fLoginLogoutBtn;

	//////////////////////////////////////////////////////////////////////////	
	// CONSTRUCTORS
	//////////////////////////////////////////////////////////////////////////

	/**
	 * Constructs a new <code>LoginPanel</code> with the given configuration
	 * and the default validator.
	 *
	 * @param	config			the configuration.
	 * @param	validator		the validator.
	 * @throws	IllegalArgumentException	if <code>config</code> is
	 * 										<code>null</code>.
	 * @see	de.sdavids.login.LoginFactory#getDefaultValidator
	 */
	public LoginPanel(ResourceBundle config) {
		this(config, LoginFactory.getDefaultValidator());
	}

	/**
	 * Constructs a new <code>LoginPanel</code> with the given configuration
	 * and validator.
	 *
	 * @param	config			the configuration.
	 * @param	validator		the validator.
	 * @throws	IllegalArgumentException	if either argument is
	 * 										<code>null</code>.
	 */
	public LoginPanel(ResourceBundle config, ILoginValidator validator) {
		super();

		setValidator(validator);
		setConfig(config);
		setLogin(LoginFactory.getNullLogin());

		initialize();
	}

	//////////////////////////////////////////////////////////////////////////
	// WIDGETS
	//////////////////////////////////////////////////////////////////////////

	/**
	 * Answer the login textfield panel.
	 * <p>
	 * The panel contains the account and password panels.
	 * 
	 * @return	the panel.
	 */
	protected JPanel getLoginTFPnl() {
		JPanel result = new JPanel();

		JPanel pnl = new JPanel(new BorderLayout(12, 0));

		pnl.add(getAccountPnl(), BorderLayout.WEST);
		pnl.add(getPwdPnl(), BorderLayout.CENTER);

		result.add(pnl);

		result.setAlignmentY(JPanel.CENTER_ALIGNMENT);

		return result;
	}

	/**
	 * Answer the login button panel.
	 * <p>
	 * The panel contains the login/logout button.
	 * 
	 * @return	the panel.
	 */
	protected JPanel getLoginBtnPnl() {
		JPanel result = new JPanel(new BorderLayout(0, 0));

		result.add(getLoginLogoutBtn(), BorderLayout.WEST);

		return result;
	}

	/**
	 * Answer the password panel.
	 * <p>
	 * The panel contains a textfield for entering the login's password and an 
	 * accompaning label.
	 * 
	 * @return	the panel.
	 */
	protected JPanel getPwdPnl() {
		JPanel result = new JPanel(new BorderLayout(6, 0));

		result.add(new JLabel(getConfig("login.pwd")), BorderLayout.WEST);
		result.add(getPwdTF(), BorderLayout.CENTER);

		return result;
	}

	/**
	 * Answer the account panel.
	 * <p>
	 * The panel contains a textfield for entering the login's account and an 
	 * accompaning label.
	 * 
	 * @return	the panel.
	 */
	protected JPanel getAccountPnl() {
		JPanel result = new JPanel(new BorderLayout(6, 0));

		result.add(new JLabel(getConfig("login.account")), BorderLayout.WEST);
		result.add(getAccountTF(), BorderLayout.CENTER);

		return result;
	}

	/**
	 * Answer the textfield for entering the login's password.
	 * 
	 * @return	the textfield.
	 */
	protected JPasswordField getPwdTF() {
		if (null == fPwdTF) {
			fPwdTF =
				SwingCreator
					.newJPasswordField("login.pwdTF", getConfig(), new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					enterPressedAction();
				}
			}, new KeyAdapter() {
				public void keyReleased(KeyEvent evt) {
					keyEnteredActionPwd(evt);
				}
			});
		}

		return fPwdTF;
	}

	/**
	 * Answer the textfield for entering the login's account.
	 * 
	 * @return	the textfield.
	 */
	protected JTextField getAccountTF() {
		if (null == fAccountTF) {
			fAccountTF =
				SwingCreator
					.newJTextField("login.userTF", getConfig(), new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					enterPressedAction();
				}
			}, new KeyAdapter() {
				public void keyReleased(KeyEvent evt) {
					keyEnteredActionAccount(evt);
				}
			});
		}

		return fAccountTF;
	}

	/**
	 * Answer the login/logout button.
	 * 
	 * @return	the button.
	 */
	protected JBinaryTextButton getLoginLogoutBtn() {
		if (null == fLoginLogoutBtn) {
			fLoginLogoutBtn =
				SwingCreator
					.newJBinaryTextButton("btn.login", getConfig(), new ActionListener() {
				public void actionPerformed(ActionEvent evt) {
					if (isValidAccount() && isValidPassword()) {
						if (getLoginLogoutBtn().isAlternateTextShown()) {
							loginAction();
						} else {
							logoutAction();
						}
					} else {
						getLoginLogoutBtn().showText();
					}
				}
			});

			fLoginLogoutBtn.setMargin(new Insets(4, 4, 4, 4));
		}

		return fLoginLogoutBtn;
	}

	/////////////////////////////////////////////////////////////////////////////
	// ACTIONS
	/////////////////////////////////////////////////////////////////////////////

	/**
	 * Invoked when a key is entered into the password textfield.
	 * 
	 * @param	evt		the <code>KeyEvent</code>.
	 */
	protected void keyEnteredActionPwd(KeyEvent evt) {
		if (evt.isActionKey())
			return;

		boolean legal = isValidPassword();

		if (legal) {
			hideInfoTip();
		} else {
			showInfoTip(getPwdTF(), getPwdTF().getToolTipText());

			getToolkit().beep();
		}

		getLoginLogoutBtn().setEnabled(legal && isValidAccount());

		getPwdTF().setForeground((legal) ? Color.black : Color.red);
	}

	/**
	 * Invoked when a key is entered into the account textfield.
	 * 
	 * @param	evt		the <code>KeyEvent</code>.
	 */
	protected void keyEnteredActionAccount(KeyEvent evt) {
		if (evt.isActionKey())
			return;

		boolean legal = isValidAccount();

		if (legal) {
			hideInfoTip();
		} else {
			showInfoTip(getAccountTF(), getAccountTF().getToolTipText());

			getToolkit().beep();
		}

		getLoginLogoutBtn().setEnabled(legal && isValidPassword());

		getAccountTF().setForeground((legal) ? Color.black : Color.red);
	}

	/**
	 * Invoked when the <em>Enter</em> key is pressed in either textfield.
	 */
	protected void enterPressedAction() {
//the commented code somehow transfers the focus to the the next button
//and also performs it's actionPerformed method		
		
//		if (isValidAccount() && isValidPassword()) {
//			if (getLoginLogoutBtn().isTextShown()) {
//				loginAction();
//			} else {
//				logoutAction();
//			}
//		} else {
			getToolkit().beep();
//		}
	}

	/**
	 * Invoked when a legal password and account have been entered and 
	 * the user either pressed the login/logout button or pressed the 
	 * <em>Enter</em> key in either textfield.
	 * <p>
	 * Fires a <code>LoginEvent</code> with <code>true</code> as it's
	 * <code>isLogin</code> parameter.
	 */
	protected void loginAction() {
		String user = getAccountTF().getText().trim();
		String pwd = new String(getPwdTF().getPassword());

		try {
			setLogin(LoginFactory.create(user, pwd));

			getLoginLogoutBtn().showAlternateText();
			
			fireLoginChanged(new LoginEvent(this, true, getLogin()));
		} catch (InvalidAccountException e) {
			getLoginLogoutBtn().showText();
		} catch (InvalidPasswordException e) {
			getLoginLogoutBtn().showText();
		}
	}

	/**
	 * Invoked when the login/logout button is pressed in it's &quot;logout
	 * state&quot;.
	 * <p>
	 * Our login is set to the <code>NullLogin</code>.
	 * <p>
	 * Fires a <code>LoginEvent</code> with <code>false</code> as it's
	 * <code>isLogin</code> parameter; the <code>login</code> parameter is
	 * the former login.
	 */
	protected void logoutAction() {
		ILogin oldLogin = getLogin();

		setLogin(LoginFactory.getNullLogin());

		fireLoginChanged(new LoginEvent(this, false, oldLogin));
	}

	/////////////////////////////////////////////////////////////////////////////
	// PUBLIC INTERFACE
	/////////////////////////////////////////////////////////////////////////////

	/* ----------- Mutators --------------------- */

	/**
	 * Put this panel into it's logged out state.
	 * <p>
	 * <em>Note:</em> This method does <strong>not</strong> fire a
	 * <code>LoginEvent</code>.
	 */
	public synchronized void beLoggedOut() {
		setLogin(LoginFactory.getNullLogin());
		
		//cannot be put into setLogin
		if (!getAccountTF().hasFocus()) {
			if (!getAccountTF().isRequestFocusEnabled())
				getAccountTF().setRequestFocusEnabled(true);

			getAccountTF().requestFocus();
		}		
	}
	
	/**
	 * Signal that the password the user has entered is not correct password
	 * for the accout the user entered.
	 * <p>
	 * After this method, the login panel again accepts inputs and the 
	 * login/logout button is in it's logout state.
	 * <p>
	 * The data the user has entered in the account and password textfields
	 * will not be erased.
	 */
	public synchronized void signalWrongPassword() {
		signalError(getPwdTF(), getConfig("login.wrongPassword"));
	}

	/**
	 * Signal that the account the user has entered is unknown.
	 * <p>
	 * After this method, the login panel again accepts inputs and the 
	 * login/logout button is in it's logout state.
	 * <p>
	 * The data the user has entered in the account and password textfields
	 * will not be erased.
	 */
	public synchronized void signalUnknownAccount() {
		signalError(getAccountTF(), getConfig("login.unknownAccount"));
	}

	/**
	 * Signal that has been trouble with the connection.
	 * <p>
	 * After this method, the login panel again accepts inputs and the 
	 * login/logout button is in it's logout state.
	 * <p>
	 * The data the user has entered in the account and password textfields
	 * will not be erased.
	 */
	public synchronized void signalConnectionTrouble() {
		signalError(this, getConfig("login.connectionTrouble"));
	}

	/* ----------- Event handling --------------- */

	/**
	 * Adds a <code>LoginListener</code> to the listener list.
	 * <p>
	 * A <code>LoginEvent</code> will get fired in response to the user
	 * entering a legal password and account (having been validated by our
	 * own validator) and then pressing the login/logout button or pressing
	 * the  <em>Enter</em> key in either textfield.
	 *
	 * @param	listener		the <code>LoginListener</code> to be added.
	 */
	public synchronized void addLoginListener(LoginListener listener) {
		fLoginListeners = LoginEventMulticaster.add(fLoginListeners, listener);
	}

	/**
	 * Removes a <code>LoginListener</code> from the listener list.
	 *
	 * @param	listener		the <code>LoginListener</code> to be removed.
	 */
	public synchronized void removeLoginListener(LoginListener listener) {
		fLoginListeners = LoginEventMulticaster.remove(fLoginListeners, listener);
	}

	//////////////////////////////////////////////////////////////////////////
	// PROTECTED METHODS
	//////////////////////////////////////////////////////////////////////////
	
	/* ----------- Mutators --------------------- */

	/**
	 * Initialize the panel.
	 * 
	 * Lays out all <code>Component</code>s and registers needed
	 * <code>EventListener</code>s.
	 */
	protected void initialize() {
		setLayout(new BorderLayout(12, 0));

		add(getLoginTFPnl(), BorderLayout.WEST);
		add(getLoginBtnPnl(), BorderLayout.CENTER);
	}
		
	/**
	 * Signals an error condition.
	 * <p>
	 * After this method, the login panel again accepts inputs and the 
	 * login/logout button is in it's logout state.
	 * <p>
	 * The data the user has entered in the account and password textfields
	 * will not be erased.
	 * 
	 * @param	comp		the component to which the message should be
	 * 						displayed relative to.
	 * @param	message		the message to be displayed.
	 */
	protected void signalError(JComponent comp, String message) {
		acceptInputAgain();

		showInfoTip(comp, message);

		getToolkit().beep();
	}

	/**
	 * Set the panel's configuration.
	 *
	 * @param	config		the bundle.
	 * @throws	IllegalArgumentException	if <code>config</code> is
	 * 										<code>null</code>.
	 */
	protected void setConfig(ResourceBundle config)
		throws IllegalArgumentException {
			
		if (null == config)
			throw new IllegalArgumentException("config null");
			
		fConfig = config;
	}

	/**
	 * Set the panel's validator.
	 *
	 * @param	validator		the validator.
	 * @throws	IllegalArgumentException	if <code>validator</code> is
	 * 										<code>null</code>.
	 */
	protected void setValidator(ILoginValidator validator)
		throws IllegalArgumentException {
			
		if (null == validator)
			throw new IllegalArgumentException("validator null");
			
		fValidator = validator;
	}

	/**
	 * Set the panel's login.
	 *
	 * @param	login			the login.
	 */
	protected void setLogin(ILogin login) {
		fLogin = login;

		boolean isNull = fLogin.isNull();

		getAccountTF().setText(fLogin.getAccount());
		getAccountTF().setEnabled(isNull);
		getAccountTF().setBackground(
			isNull ? getAcceptInputColor() : getAcceptNoInputColor());

		getPwdTF().setText(fLogin.getPassword());
		getPwdTF().setEnabled(isNull);
		getPwdTF().setBackground(
			isNull ? getAcceptInputColor() : getAcceptNoInputColor());

		getLoginLogoutBtn().setEnabled(!isNull);

		if (!isNull && (getLoginLogoutBtn().isTextShown())) {
			getLoginLogoutBtn().showAlternateText();
		} else {
			getLoginLogoutBtn().showText();
		}
	}

	/**
	 * Enable the account and password textfields and put the login/logout
	 * button into its &quot;login state&quot;.
	 */
	protected void acceptInputAgain() {
		getAccountTF().setEnabled(true);
		getAccountTF().setBackground(getAcceptInputColor());

		getPwdTF().setEnabled(true);
		getPwdTF().setBackground(getAcceptInputColor());

		getLoginLogoutBtn().showText();
	}

	/* ----------- Accessors -------------------- */

	/**
	 * Answer the configuration.
	 *
	 * @return	the configuration.
	 */
	protected ResourceBundle getConfig() {
		return fConfig;
	}

	/**
	 * Answer the string resource with the given key.
	 *
	 * @param	key			the resource's key.
	 * @return	the string.
	 */
	protected String getConfig(String key) {
		return getConfig().getString(key);
	}

	/**
	 * Answer the validator.
	 *
	 * @return	the validator.
	 */
	protected ILoginValidator getValidator() {
		return fValidator;
	}

	/**
	 * Answer the login.
	 *
	 * @return	the login.
	 */
	protected ILogin getLogin() {
		return fLogin;
	}

	/**
	 * Answer the color of a textfield accepting input.
	 *
	 * @return	the color.
	 */		
	protected Color getAcceptInputColor() {
		return UIManager.getColor("TextField.background");
	}

	/**
	 * Answer the color of a textfield not accepting input.
	 *
	 * @return	the color.
	 */	
	protected Color getAcceptNoInputColor() {
		return Color.lightGray;
	}

	/**
	 * Answer if the data the user has entered into the account textfield is a
	 * valid account as determined by our own validator.
	 *
	 * @return	the color.
	 */	
	protected boolean isValidAccount() {
		return getValidator().isValidAccount(getAccountTF().getText());
	}

	/**
	 * Answer if the data the user has entered into the password textfield is a
	 * valid password as determined by our own validator.
	 *
	 * @return	the color.
	 */	
	protected boolean isValidPassword() {
		return getValidator().isValidPassword(new String(getPwdTF().getPassword()));
	}

	/* ----------- Event handling --------------- */

	/**
	 * Fire a <code>LoginEvent</code> to all our listeners.
	 */	
	protected void fireLoginChanged(LoginEvent evt) {
		if (null == fLoginListeners)
			return;

		fLoginListeners.loginChange(evt);
	}
}