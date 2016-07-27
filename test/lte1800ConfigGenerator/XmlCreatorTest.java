package lte1800ConfigGenerator;

import static org.junit.Assert.*;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.junit.Before;
import org.junit.Test;

public class XmlCreatorTest {
	private XmlCreator xmlCreator;

	@Before
	public void setUp() {
		xmlCreator = new XmlCreator();
	}

	@Test
	public void testSetTemplateFilePath() {
		xmlCreator.setTemplateFile("L1800", "BG0001");
		File templateFile = xmlCreator.templateFile;

		assertTrue(templateFile.toString().contains("BGLLL"));

		xmlCreator.setTemplateFile("L1800", "KG0001");
		templateFile = xmlCreator.templateFile;

		assertTrue(templateFile.toString().contains("NONBG"));
		
		xmlCreator.setTemplateFile("L800", "KG0001");
		templateFile = xmlCreator.templateFile;

		assertTrue(templateFile.toString().contains("LT800"));
	}

	@Test
	public void testCreateOutputFilePath() {
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyyMMdd");
		String expectedPath = "C:\\CG output\\Commissioning_BG0001_" + LocalDate.now().format(dateFormat) + ".xml";

		String siteCode = "BG0001";
		xmlCreator.setTemplateFile("L1800", siteCode);
		xmlCreator.createOutputFilePath(siteCode);
		String filePath = xmlCreator.outputFilePath;

		assertEquals(expectedPath, filePath);
	}

	@Test
	public void testCopyTemplateXmlFile() {
		String siteCode = "BG0001";
		xmlCreator.setTemplateFile("L1800", siteCode);
		xmlCreator.copyTemplateXmlFile(siteCode);
		File outputFile = xmlCreator.outputFile;

		if (!outputFile.exists()) {
			fail();
		}
		if (!xmlCreator.outputFile.delete()) {
			fail();
		}
	}

}
