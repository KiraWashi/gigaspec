package metaModel;

import prettyPrinter.JavaGenerator;

public interface MinispecElement {

    void accept(Visitor visitor);
}
