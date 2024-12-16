package metaModel;

import java.util.ArrayList;
import java.util.List;

public class Class implements MinispecElement {
	private String name;
	public List<Attribute> attributes;
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Class(String name) {
		this.name = name;
		this.attributes = new ArrayList<>();
	}

	public void accept(Visitor v) {
		v.visitClass(this);
	};

	public void addAttribute(Attribute attribute) {
		if (!attributes.contains(attribute) && !attributes.contains(null)) {
			this.attributes.add(attribute);
		}
	}


}
