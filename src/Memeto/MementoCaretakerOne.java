package Memeto;

public class MementoCaretakerOne {
    private static MementoCaretakerOne mementoCaretaker;
    private Memento memento = null;

    public static MementoCaretakerOne getInstance() {
        if( mementoCaretaker == null ) {
            synchronized( MementoCaretakerOne.class ) {
                if( mementoCaretaker == null ) {
                    mementoCaretaker = new MementoCaretakerOne();
                }
            }
        }
        return mementoCaretaker;
    }

    public Memento restore() { return memento; }

    public void save( Memento memento ) { this.memento = memento; }
}
