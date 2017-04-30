package Memeto;

public class MementoCaretakerTwo {
    private static MementoCaretakerTwo mementoCaretaker;
    private Memento memento = null;

    public static MementoCaretakerTwo getInstance() {
        if( mementoCaretaker == null ) {
            synchronized( MementoCaretakerTwo.class ) {
                if( mementoCaretaker == null ) {
                    mementoCaretaker = new MementoCaretakerTwo();
                }
            }
        }
        return mementoCaretaker;
    }

    public Memento restore() { return memento; }

    public void save( Memento memento ) { this.memento = memento; }
}
