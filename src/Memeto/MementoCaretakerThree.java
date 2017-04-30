package Memeto;

public class MementoCaretakerThree {
    private static MementoCaretakerThree mementoCaretaker;
    private Memento memento = null;

    public static MementoCaretakerThree getInstance() {
        if( mementoCaretaker == null ) {
            synchronized( MementoCaretakerThree.class ) {
                if( mementoCaretaker == null ) {
                    mementoCaretaker = new MementoCaretakerThree();
                }
            }
        }
        return mementoCaretaker;
    }

    public Memento restore() { return memento; }

    public void save( Memento memento ) { this.memento = memento; }
}
