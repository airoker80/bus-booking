package com.domain.airoker80.testapplication;

import java.util.Collection;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;

/**
 * Manages the display of the watch list.
 *  
 * @author Kah
 */
public class ListDisplay<T>
{
	/**
	 * Reference to the list of stocks that are in the watch list.
	 */
	private final Collection<T> itemsCollection;
	
	/**
	 * Reference to the view that displays the watch list on the user interface.
	 * This needs to inflated later, since the instance of the layout inflater
	 * is required to create it.
	 */
	private final TableLayout display;

	private final Context appContext;

	private final DisplayAdapter<T> dataAdapter;

	private SelectionReceiver<T> selectionObserver;
	
	private T highlighted;
	
	/**
	 * Constructs the display manager for the watch list.
	 * 
	 * @param itemsCollection
	 * 			The set of items to display in the list.
	 */
	public ListDisplay(Collection<T> itemsCollection, 
		Context appContext, 
		DisplayAdapter<T> dataAdapter) 
	{
		this.appContext = appContext;
		this.dataAdapter = dataAdapter;

		display = new TableLayout(appContext);
		
		display.setLayoutParams(new TableLayout.LayoutParams(
						TableRow.LayoutParams.FILL_PARENT,
						TableRow.LayoutParams.WRAP_CONTENT));
		
		display.setStretchAllColumns(true);

		this.itemsCollection = itemsCollection;
		initialiseDisplay();
	}
	
	/**
	 * Retrieves the display component to display the contents of the watch
	 * list on the user interface.
	 * 
	 * @return The display view for viewing the contents of the watch list.
	 */
	public View getDisplay() 
	{
		return display;
	}

	/**
	 * Retrieves the last item that was selected, but not necessarily "clicked".
	 * 
	 * @return The item that is currently at the cursor.
	 */
	public T getSelected() 
	{
		return highlighted;
	}

	/**
	 * Loads the current contents of the list to the displayed list. 
	 */
	private void initialiseDisplay() 
	{
		// All rows will have the same parameters, so just create it once.
		final TableRow.LayoutParams rowParams = new 
			TableRow.LayoutParams(TableRow.LayoutParams.FILL_PARENT,
				TableRow.LayoutParams.WRAP_CONTENT);
		
		
		// Go through the list and create table entries for each one.
		for (T item: itemsCollection) 
		{
			TableRow newRow = new TableRow(appContext);
			newRow.setLayoutParams(rowParams);
			
			View[] content = dataAdapter.getContent(item);
			for (int index = 0; index < content.length; index++) 
			{
				newRow.addView(content[index]);
			}
			
			newRow.setFocusable(true);
			newRow.setFocusableInTouchMode(true);
			newRow.setOnFocusChangeListener(new RowHighlighter(item));
			newRow.setOnClickListener(new RowSelector(item));

			display.addView(newRow);
		}
	}
	
	/**
	 * Changes the receiver that is notified when an item is "clicked".
	 * 
	 * @param receiver
	 * 			The receiver that will be notified of changes.
	 */
	public void setSelectionReceiver(SelectionReceiver<T> receiver)
	{
		this.selectionObserver = receiver;
	}
	
	/**
	 * This focus change listener handles the changing the background colour
	 * for highlighting the focused row. It also updates the variable keeping
	 * track the highlighted item.
	 */
	private class RowHighlighter implements 
		View.OnFocusChangeListener, View.OnTouchListener
	{

		/**
		 * The item that the highlighter is associated with. 
		 */
		private final T association;
		
		public RowHighlighter(T association) 
		{
			this.association = association;
		}
		
		/**
		 * {@inheritDoc}
		 */
		@Override
		public void onFocusChange(View v, boolean hasFocus)
		{
			int bgColour = Color.TRANSPARENT;
			if (hasFocus == true) 
			{
				Log.v("ListDisplay", "hasFocus() is true");
				Log.v("ListDisplay", "display focus is " + display.isFocused());
				bgColour = Color.RED;
				highlighted = association;
			} else {
				Log.v("ListDisplay", "hasFocus() is false");
				Log.v("ListDisplay", "display focus is " + display.isFocused());
			}

			v.setBackgroundColor(bgColour);
		}

		/**
		 * {@inheritDoc}
		 */
		@Override
		public boolean onTouch(View v, MotionEvent event)
		{
			if (event.getAction() != MotionEvent.ACTION_UP) 
			{
				v.requestFocus();
			}
			return false;
		}
		
	}
	
	/**
	 * Handles the "clicking" on a row.
	 * 
	 * @author Kah
	 */
	private class RowSelector implements View.OnClickListener 
	{
		private final T association;
		
		public RowSelector(T association) 
		{
			this.association = association;
		}
		
		/**
		 * {@inheritDoc}
		 */
		@Override
		public void onClick(View v)
		{
			if (selectionObserver != null) {
				selectionObserver.itemSelected(association);
			}
		}
	}
}
 