package com.commonsware.empublite;

/**
 * Created by Patrick Coyne on 9/12/2017.
 */

public class BookLoadedEvent {
    private BookContents contents = null;

    public BookLoadedEvent(BookContents contents){
        this.contents = contents;
    }

    public BookContents getBook(){
        return contents;
    }
}
