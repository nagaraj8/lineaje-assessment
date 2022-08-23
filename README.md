# lineaje-assessment

This repo contains Parsing a family tree JSON file and generating a report.

1. Clone the project and import it to the IDE as a maven project.
2. Once imported build the project using mvn clean install and it should bring all the dependecies.
3. Once imported and built, provide the input directory path in the FamilyTreeTest.java file(Currently its provided with my local setup path, this repo has the familyTree.json file - we can use the path of that file).
4. FamilyTreeTest.java should generate the test data report of multiple files under lineaje-assessment directory.
5. To Run the junits make sure you have cleaned / deleted the tested report files if already present under lineaje-assessment directory and then run the junits.
6. It will generate the output in the same directory and compares the generated output with the set of test resource files present under srs/test/java directory.
7. Thread pool executor is automatically invoked from from FamilyTreeTest.java file which is main method java program executor.
8. The project is covered with almost 75% percentage of code with Junits. Currently havent added the negative test cases.


