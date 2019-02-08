/*
 *Justin Tatman
 * 1-29-19
 * CS272
 */

import java.util.Arrays;

public class Book implements Cloneable {

    String bookTitle, isbn;
    String[] authors;
    int numAuthors;

    /* constructor method with no parameters sets all values to null
     * string array created to house 3 authors
     */
    public Book() {

        bookTitle = null;
        isbn = null;
		authors = new String[3];
		numAuthors = 0;
    } // end Book

	/* constructor method which allows a title to be set
	 *  string array created to house 3 authors
	 * @param
	 *  _title: title for book
	 */
	public Book(String _title) {

	    bookTitle = _title;
		isbn = null;
		authors = new String[3];
		numAuthors = 0;
	} // end Book

	/**
	 * Creates a clone of an object Book
	 * @param
	 *  _0bj: an object that is an instance of book
	 * @return copy of original object
	 * @throws if object is null or not an instance of Book
	 */
	public Book clone(Object _obj) {
		// assigning to null to avoid unassigned error
		Book copiedBook = null;

		if (_obj != null && _obj instanceof Book) {
			try {
				copiedBook = (Book) super.clone();
			}
			catch (CloneNotSupportedException e) {
				throw new RuntimeException("Not Cloneable");
			}
		} // end if
		else {
			System.out.println("Object null or not instance of Book");
		} // end else
		return copiedBook;
	} // end clone

	public String getTitle() {
		return bookTitle;
	} // end getTitle

	public int getAuthorNumber() {
		return numAuthors;
	} // end getAuthorNumber

	public String getISBN() {
		return isbn;
	} // end getISBN

	public String[] getAuthors() {
	    return authors;
	}

	// sets title of Book object
	public void setTitle(String _title) {
		bookTitle = _title;
		System.out.printf("Booktitle set to %S\n\n", bookTitle);
	} // end setTitle

	// sets isbn of Book object
	public void setISBN (String _isbn) {
		isbn = _isbn;
	} // end setISBN

	/*  adds author to Book object
	 * @param
	 *  _author
	 * @precondition
	 *  book must have 3 authors or less
	 * @return
	 *	returns true if there are 3 or less authors and author
	 *  has been added
	 *  returns false if conditions aren't met to add author
	 *
	 */
	public boolean addAuthor(String _author) {

		if (authors.length <= 3) {

			authors[numAuthors] = _author;
			numAuthors += 1;
			System.out.println("Author added\n");
			return true;
		} // end if
		else {
			System.out.println("Author not added\n");
			return false;
		} // end else
	} // end addAuthor


	public boolean equals(Object _obj) {
		if (_obj != null && _obj instanceof Book) {
			Book testBook = (Book) _obj;
			return isbn == testBook.getISBN();
		} // end if
		else {
			return false;
		} // end else
	} // end equals

	/* Gets all the distinct authors of two books
	 * @parameters
	 *  b1: Book object
	 *  b2: Book object
	 * @precondition
	 *  Book must not be null
	 * @return
	 */
	public static String[] getAllAuthors(Book b1, Book b2) {

	    String[] allAuthors = null;

		if (b1 != null && b2 != null) {
		    // only 3 authors allowed per book
		    // so we know allAuthors can't be larger than 6
		     allAuthors = new String[6];
		     // copying authors from books to allAuthors array
		     System.arraycopy(b1.authors, 0, allAuthors, 0, 3);
		     System.arraycopy(b2.getAuthors(), 0, allAuthors, 3, 3);

		}
		else {
		    // change to something useful
		    System.out.println("OH no");
		}

		return findUniqueAuthors(allAuthors);

	} // end getAllAuthors
	public static String[] findUniqueAuthors(String[] authorArray) {

	    int toKeep = authorArray.length;

	    String[] uniqueAuthors = new String[toKeep];

	    for (int i=0; i < authorArray.length; i++) {

	        int index = 1;
	        while (index < (authorArray.length) && toKeep >= 1) {
	            if (authorArray[i] == authorArray[index]) {
	                toKeep--;
	                authorArray[index] = authorArray[toKeep];

	                //System.out.println(authorArray[i] + authorArray[index]);

	            } // end if
	            else {
	                index++;
	            } // end else
	        } // end while
	    } // end for

	    System.arraycopy(authorArray, 0, uniqueAuthors, 0, toKeep);
	    return uniqueAuthors;
	} // end findUniqueAuthors

	public String toString() {
	    return bookTitle + " " + isbn + " " + numAuthors + " ";
			//.Arrays
	           //Arrays.toString(authors);
	} // end toString

	public static void main(String[] args) {


		Book myBook = new Book();
		myBook.setTitle("Book One");
		myBook.setISBN("12345678");
		myBook.addAuthor("Author Authorson");

		Book clonedBook = myBook.clone(myBook);

		System.out.printf("Book Title:  %S\n", myBook.getTitle());
		System.out.printf("Book ISNB:  %S\n", myBook.getISBN());
	  System.out.printf("Book NumAuthors:  %S\n\n",
	    		myBook.getAuthorNumber());

	  System.out.printf("ClonedBook Title:  %S\n",
	    		clonedBook.getTitle());
	  System.out.printf("ClonedBook ISBN:  %S\n",
	    		clonedBook.getISBN());
	  System.out.printf("ClonedBook Number of Authors:  %S\n\n",
	    		clonedBook.getAuthorNumber());

	  // changing the name of myBook
	  myBook.setTitle("New Fake original Title");
	  //changing the name of clonedBook
	  clonedBook.setTitle("Newer Fake clone Title");

	  // checking to make sure clonedBook's title didn't change
	  System.out.printf("Book Title: %S\n", myBook.getTitle());
	  System.out.printf("ClonedBook Title: %S\n", clonedBook.getTitle());
	  // checking the equals method
	  System.out.println("\n" + myBook.equals(clonedBook));

	  // authors check
	  System.out.println("Authors:\n" +
	                  Arrays.toString(myBook.getAuthors()));
	  System.out.println("Cloned_Authors: \n" +
	                  Arrays.toString(clonedBook.getAuthors()));
	  System.out.println(clonedBook.authors[1]);
	  System.out.println(clonedBook.getAuthors()[1]);
	  System.out.println(myBook.authors[0] == clonedBook.authors[0]);

	  // get allAuthors check

	  System.out.println("getAllAuthors method:\n" +
	             Arrays.toString(Book.getAllAuthors(myBook, clonedBook)));


	} // end main
}
