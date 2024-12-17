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
	void testPOURNOUS() {
		XMLAnalyser analyser = new XMLAnalyser();
		Model model = analyser.getModelFromFilenamed("ExemplePOURNOUS.xml");
		JavaGenerator pp = new JavaGenerator();
		model.accept(pp);
		System.out.println(pp.result());
	}

	@Test
	void testPOURNOUS2() {
		XMLAnalyser analyser = new XMLAnalyser();
		analyser.setParametres("ExemplePOURNOUS_PARAMETRES.xml");
		Model model = analyser.getModelFromFilenamed("ExemplePOURNOUS2.xml");
		JavaGenerator pp = new JavaGenerator();
		model.accept(pp);
		System.out.println(pp.result());
	}

}
