/**
 * Copyright 2012 
 * 
 * Nicolas Desjardins  
 * https://github.com/mrKlar
 * 
 * Facilite solutions
 * http://www.facilitesolutions.com/
 * 
 * Permission is hereby granted, free of charge, to any person obtaining
 * a copy of this software and associated documentation files (the
 * "Software"), to deal in the Software without restriction, including
 * without limitation the rights to use, copy, modify, merge, publish,
 * distribute, sublicense, and/or sell copies of the Software, and to
 * permit persons to whom the Software is furnished to do so, subject to
 * the following conditions:
 * 
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
 * NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE
 * LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION
 * OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION
 * WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 */
package com.example.folderviewpagerdemo.view;

import android.view.View;

public interface DragDropGridAdapter {

	// Automatic child distribution
	public final static int AUTOMATIC = -1;

	/**
	 * Returns the count of item in a page
	 * 
	 * @param page
	 *            index
	 * @return item count for page
	 */
	public int itemCount();

	/**
	 * Returns the view for the item in the page
	 * 
	 * @param page
	 *            index
	 * @param item
	 *            index
	 * @return the view
	 */
	public View view(int index);

	/**
	 * The fixed row count (AUTOMATIC for automatic computing)
	 * 
	 * @return row count or AUTOMATIC
	 */
	public int rowCount();

	/**
	 * The fixed column count (AUTOMATIC for automatic computing)
	 * 
	 * @return column count or AUTOMATIC
	 */
	public int columnCount();

	/**
	 * Prints the layout in Log.d();
	 */
	public void printLayout();

	/**
	 * Swaps two items in the item list in a page
	 * 
	 * @param pageIndex
	 * @param itemIndexA
	 * @param itemIndexB
	 */
	public void swapItems(int itemIndexA, int itemIndexB);

	/**
	 * deletes the item in page and at position
	 * 
	 * @param pageIndex
	 * @param itemIndex
	 */
	public void deleteItem(int itemIndex);

	public Object getItemAt(int index);

}
