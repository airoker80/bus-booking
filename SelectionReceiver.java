package com.domain.airoker80.testapplication;

/**
 * Defines the interface for notifying 
 * 
 * @param <T>
 * 			The type of objects that will be received 
 * 
 * @author Kah
 */
public interface SelectionReceiver<T>
{
	/**
	 * This method is triggered when an item is "clicked".
	 * 
	 * @param item
	 * 			The item that was selected.
	 */
	void itemSelected(T item);
}
