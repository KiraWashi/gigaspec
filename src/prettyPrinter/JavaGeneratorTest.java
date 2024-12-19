package prettyPrinter;

import org.junit.jupiter.api.Test;

import XMLIO.XMLAnalyser;
import metaModel.Model;

class JavaGeneratorTest {

	@Test
	void test() {
		XMLAnalyser analyser = new XMLAnalyser();
		Model model = analyser.getModelFromFilenamed("Exemple2.xml");
		JavaGenerator pp = new JavaGenerator();
		model.accept(pp);
		System.out.println(pp.result());
	}

	@Test
	void testGeneration() {
		XMLAnalyser analyser = new XMLAnalyser();
		analyser.setParametres("Param.xml");
		Model model = analyser.getModelFromFilenamed("Model.xml");
		JavaGenerator pp = new JavaGenerator();
		model.accept(pp);
		System.out.println(pp.result());
	}

}
