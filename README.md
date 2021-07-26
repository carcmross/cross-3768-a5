# Assignment 5
An inventory checker GUI that allows the user to add inventory items, edit their properties, and remove them from lists. Users can also search for items by serial number or name.

## How to Use

### Adding an item:
To add an item, enter the name, serial number, and value in U.S. Dollars of the item into the text fields to the
left of the "Add Item" button. Once every value has been entered, click the "Add Item" button to add an item with
the given values to the inventory list.
	
When adding an item, keep in mind:
- Item name must be between 2 and 256 characters(inclusive).
- Item price should begin with a dollar sign($) and include two digits after the decimal place (i.e. $9.99).
- Item serial number should be in the format XXXXXXXXXX, where X can be either a letter or a digit.
- Serial numbers should be unique, so you cannot add a serial number to an item if another item in the list has the same serial.

### Editing an item:
To edit an item, first click on the item you would like to edit within the table of inventory items. Once an 
item has been selected, click the "Edit Item" button. A new window will pop up with the selected item's current
properties, to which you can make changes to whichever properties you wish to change. After you've typed in the
new values, click the "Confirm Changes" button to finalize your changes.
	
### Removing an item:
To remove an item, first click on the item you would like to remove within the table of inventory items. Once an
item has been selected, click the "Remove Item" button and the item should now be removed from the inventory list.
	
### Sorting the inventory list:
To sort the inventory list, you need to click the header of the column containing the value you'd like to sort by.
For example, if you want to sort by price, you should click the header of the value column where it says 
"Value (USD)". Clicking once will sort by ascending order, clicking again will sort by descending order.
	
### Searching for an item:
To search for an item in your inventory list, first make sure that there is at least one item in your list. Type
the name or serial number of the item you'd like to find into the text field labeled "Search inventory...". 
Once you've finished typing, click the "Search by..." button to the right of the text field and click on 
"Name" or "Serial Number" depending on which property you're searching by.
	
### Saving an inventory list:
To save an inventory list, click the file menu button at the top left of the window and click "Save as...". This 
will open up a window that will prompt you for the name and location that you'd like to give to the saved 
inventory list. Once you're satisfied with the given name and location, choose whether you'd like the file to 
be an HTML file or a TSV(Tab-Separated Values) File. Then click "Save" to create the file.
	
### Loading an inventory list:
To load an inventory list, click the file menu button at the top left of the window and click "Load". This will
open up a window that will prompt you for the file you'd like to load into the inventory table. Please make sure
to only load in HTML or TSV files(should have a .txt extension) that were generated by the GUI's save function to
ensure that items are loaded in properly.
	
## Credit
Marc-Anthony Cross
COP3330 Summer 2021
