package com.lineaje.assessment.service;

import java.io.File;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import com.lineaje.assessment.model.LineajeMeta;
import com.lineaje.assessment.model.Member;
import com.lineaje.assessment.service.impl.FamilyYoungestAndLongestService;
import com.lineaje.assessment.utility.JsonReader;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class FamilyYoungestAndLongestServiceTest {
	
	static FamilyYoungestAndLongestService underTest = null;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		underTest = new FamilyYoungestAndLongestService();
	}

	@Test
	void testProcessFamilyData() throws Exception {
		LineajeMeta meta = new JsonReader().readJsonFile("src\\test\\java\\com\\lineaje\\assessment\\service\\resources\\familyTree.json");
		List<Member> members = meta.getLineaje().getMembers();
		underTest.processFamilyData(members);
		Assert.assertEquals(FileUtils.readLines(new File("src\\test\\java\\com\\lineaje\\assessment\\service\\resources\\LineajeYoungestAndLongestDeath.txt")), FileUtils.readLines(new File("LineajeYoungestAndLongestDeath.txt")));
	}

}
