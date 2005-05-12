package de.sdavids.junit;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import junit.framework.TestCase;

public class JavaBeanTestCase extends TestCase {
	private PCL fPCL;	

	protected class PCL implements PropertyChangeListener {
		private int fCount;
		private String[] fAllowedProperties;
		private String[] fIgnoredProperties;
		private boolean fEnabled;
		private String fFilteredPropery;
		
		public PCL() {
			beEnabled();
			resetCount();
			allowProperty("");
			ignoreProperty("");
			filterOnProperty("");
		}
		
		public void propertyChange(PropertyChangeEvent evt) {
			if (!fEnabled)
				return;
				
			String propName = evt.getPropertyName();
			
			if (propName.equals(fFilteredPropery)) {
				fCount++;
				return;
			}

			for (int i = (fIgnoredProperties.length - 1); i >= 0; i--) {
				if (fIgnoredProperties[i].equals(propName)) {
					return;
				}
			}
											
			for (int i = (fAllowedProperties.length - 1); i >= 0; i--) {
				if (fAllowedProperties[i].equals(propName)) {
					fCount++;
					return;
				}
			}
					
			fail("illegal property name: " + evt.getPropertyName());
		}
		
		public void allowProperty(String name) {
			if (null == name)
				throw new IllegalArgumentException("name null");
			
			if ("".equals(name)) {
				fAllowedProperties = new String[0];
				return;
			}
		
			fAllowedProperties = new String[1];
			fAllowedProperties[0] = name;
		}			

		public void allowProperties(String[] names) {
			if (null == names)
				throw new IllegalArgumentException("names null");
				
			fAllowedProperties = names;
		}	

		public void ignoreProperty(String name) {
			if (null == name)
				throw new IllegalArgumentException("name null");

			if ("".equals(name)) {
				fIgnoredProperties = new String[0];
				return;
			}
							
			fIgnoredProperties = new String[1];
			fIgnoredProperties[0] = name;
		}			

		public void ignoreProperties(String[] names) {
			if (null == names)
				throw new IllegalArgumentException("names null");
				
			fIgnoredProperties = names;
		}	
		
		protected void filterOnProperty(String name) {
			if (null == name)
				throw new IllegalArgumentException("name null");
							
			fFilteredPropery = name;
		}		
		

		public void beEnabled() {
			fEnabled = true;
		}
		
		public void beDisabled() {
			fEnabled = false;
		}
		
		public void resetCount() {
			fCount = 0;
		}
		
		public int getCount() {
			return fCount;
		}		
	}
		
	public JavaBeanTestCase(String name) {
		super(name);
	}
	
	protected PCL getPCL() {
		if (null == fPCL)
			fPCL = new PCL();
		
		return fPCL;
	}
	
	protected void setIgnoredProperty(String name) {
		getPCL().ignoreProperty(name);
	}

	protected void setIgnoredProperties(String[] names) {
		getPCL().ignoreProperties(names);
	}
		
	protected void setFilterOnProperty(String name) {
		getPCL().filterOnProperty(name);
	}
	
	protected void setAllowedProperty(String name) {
		getPCL().allowProperty(name);
	}

	protected void setAllowedProperties(String[] names) {
		getPCL().allowProperties(names);
	}
	
	protected void bePCLEnabled() {
		getPCL().beEnabled();
	}
	
	protected void bePCLDisabled() {
		getPCL().beDisabled();
	}
	
	protected void resetPCLCount() {
		getPCL().resetCount();
	}
	
	protected void assertPCLCount(String message, int count) {
		assertEquals(message, count, getPCL().getCount());
	}
}
