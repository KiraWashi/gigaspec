package metaModel;
import java.util.ArrayList;
import java.util.List;

public class Model implements MinispecElement {

	List<Class> classes;
	private String packageName;
	public String name;
	public Model (String name) {
		this.classes = new ArrayList<>();
		this.name = name;
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

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}
}