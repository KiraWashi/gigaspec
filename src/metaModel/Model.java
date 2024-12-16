package metaModel;

import java.util.ArrayList;
import java.util.List;

public class Model implements MinispecElement {

	List<Class> classes;
	
	public Model () {
		this.classes = new ArrayList<>();
	}
	
	public void accept(Visitor v) {
		v.visitModel(this);
	}
	
	public void addEntity(Class e) {
		this.classes.add(e);
	}
	public List<Class> getClasses() {
		return classes;
	}
	
}
