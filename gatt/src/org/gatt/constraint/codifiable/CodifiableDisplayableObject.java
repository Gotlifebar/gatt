package org.gatt.constraint.codifiable;


/** Represents an object that can generate code and that also can be displayed in a human readable way. 
 * @author David
 *
 */
public interface CodifiableDisplayableObject extends Codifiable{	
	
	/** Returns the displayable name of the object, usually a symbolic way
	 * @return the displayable name of the object
	 */
	public String getDisplayName();
	
	/** Returns the readable name of the object
	 * @return the readable name of the object
	 */
	public String getReadableName();
}
