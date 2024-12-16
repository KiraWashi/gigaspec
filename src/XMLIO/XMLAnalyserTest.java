package XMLIO;

import metaModel.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class XMLAnalyserTest {

	@Test
	void test1() {
		XMLAnalyser analyser = new XMLAnalyser();
		Model model = analyser.getModelFromFilenamed("Exemple1.xml");
		assertNotNull(model);
		assertEquals(0, model.getEntities().size());
	}

	@Test
	void test2() {
		XMLAnalyser analyser = new XMLAnalyser();
		Model model = analyser.getModelFromFilenamed("Exemple2.xml");
		assertNotNull(model);
		System.out.println(model.getEntities().size());
		assertEquals(2, model.getEntities().size());
	}

	@Test
	void test3() {
		String src = "<Root model=\"3\"> <Model id=\"3\" /> </Root>";
		XMLAnalyser analyser = new XMLAnalyser();
		Model model = analyser.getModelFromString(src);
		assertNotNull(model);
		assertEquals(0, model.getEntities().size());
	}

	@Test
	void testSimpleTypes() {
		XMLAnalyser analyser = new XMLAnalyser();
		Model model = analyser.getModelFromFilenamed("Exemple2.xml");

		System.out.println("=== Contenu du modèle ===");
		for (Entity entity : model.getEntities()) {
			System.out.println("\nEntité: " + entity.getName());
			System.out.println("Attributs:");
			for (Attribute attr : entity.getAttributes()) {
				System.out.println("  - " + attr.getName() + " : " + attr.getType().getName() +
						(attr.getDefaultValue() != null ? " = " + attr.getDefaultValue() : ""));
			}
		}
	}

	@Test
	void testCompletTypes() {
		XMLAnalyser analyser = new XMLAnalyser();
		Model model = analyser.getModelFromFilenamed("complet.xml");

		// Vérifier que le modèle n'est pas null
		if (model == null) {
			System.out.println("Le modèle est null !");
			return;
		}

		// Vérifier les entités
		System.out.println("Nombre d'entités : " + model.getEntities().size());

		System.out.println("=== Contenu du modèle ===");
		for (Entity entity : model.getEntities()) {
			if (entity == null) {
				System.out.println("Une entité est null !");
				continue;
			}
			System.out.println("\nEntité: " + entity.getName());
			System.out.println("Attributs:");
			for (Attribute attr : entity.getAttributes()) {
				Type type = attr.getType();
				String typeInfo = "";
				if (type instanceof CollectionType) {
					CollectionType cType = (CollectionType) type;
					typeInfo = " [elementType=" + cType.getElementType().getName() +
							", min=" + cType.getMinSize() +
							", max=" + cType.getMaxSize() + "]";
				}
				System.out.println("  - " + attr.getName() + " : " + type.getName() + typeInfo +
						(attr.getDefaultValue() != null ? " = " + attr.getDefaultValue() : ""));
			}
		}
	}
}