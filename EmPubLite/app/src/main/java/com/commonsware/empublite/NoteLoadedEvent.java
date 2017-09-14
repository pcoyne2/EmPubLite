package com.commonsware.empublite;

/**
 * Created by Patrick Coyne on 9/13/2017.
 */

class NoteLoadedEvent {
    int position;
    String prose;

    NoteLoadedEvent(int position, String prose){
        this.position = position;
        this.prose = prose;
    }

    public int getPosition() {
        return position;
    }

    public String getProse() {
        return prose;
    }
}
