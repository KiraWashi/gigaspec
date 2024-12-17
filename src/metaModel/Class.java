package metaModel;

import metaModel.exception.HeritageAlreadyGivenException;
import metaModel.exception.HeritageAttributMultipleException;
import metaModel.exception.HeritageCirculaireException;
import metaModel.exception.HeritageYourselfException;

import java.util.ArrayList;
import java.util.List;

public class Class implements MinispecElement {
	private String name;
	public List<Attribute> attributes;
	public String getName() {
		return name;
	}
	public Class classHeritage;
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

	public void addHeritage(Class heritage) throws HeritageAlreadyGivenException, HeritageYourselfException, HeritageCirculaireException, HeritageAttributMultipleException {
		if(this.classHeritage != null) {
			throw new HeritageAlreadyGivenException("La classe "+this.name+" hérite déjà de la classe "+this.classHeritage.getName());
		}
		if (heritage.classHeritage != null) {
			if (heritage.classHeritage.equals(this)) {
				throw new HeritageCirculaireException("La classe "+this.name+" va faire de l'héritage circulaire avec la classe "+heritage.name);
			}
		}
		if(heritage.equals(this)) {
			throw new HeritageYourselfException("La classe "+this.name+" ne peut pas hériter d'elle même");
		}

		//on vérifie que les deux classes ont pas d'attributs déclarés plusieurs fois avec les mêmes noms
		for (Attribute attribute : this.attributes) {
			for (Attribute att : heritage.attributes){
				if (att.getName().equals(attribute.getName())){
					throw new HeritageAttributMultipleException("La classe "+this.name+" a des attributs nommés pareils" +
							" que dans la classe : "+heritage.name+".\n" +
							"L'attribut nommé pareil est : "+att.getName());
				}
			}
		}

		//tout est bon on accepte l'héritage
		this.classHeritage = heritage;
	}

}
