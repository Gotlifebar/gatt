package org.gatt.constraint.codifiable;


/** 
 * This interface describes the operands that can be selected in the constraint creation wizard and 
 * that later will be used to generate the code of the constraints.
 * @author David
 *
 */
public interface Operand extends CodifiableDisplayableObject{
	
	/** Returns the type of the operand
	 * @return the type of the operand
	 */
	public Class getValueType();
}
