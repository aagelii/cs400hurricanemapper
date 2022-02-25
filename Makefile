run: compile execute
execute:
        java Frontend hurricanes.csv
compile: Frontend.class FrontEndDeveloperTests.class Backend.class BackEndDeveloperTests.class Hurricane.class HurricaneDataReader.class RedBlackTree.class FrontendInterface.class HurricaneInterface.class SortedCollectionInterface.class TestHurri
caneDataReader.class
Frontend.class: Frontend.java
        javac Frontend.java
FrontEndDeveloperTests.class: FrontEndDeveloperTests.java
        javac -cp .:junit5.jar FrontEndDeveloperTests.java
Backend.class: Backend.java
        javac Backend.java
BackEndDeveloperTests.class: BackEndDeveloperTests.java
        javac -cp .:junit5.jar BackEndDeveloperTests.java
Hurricane.class:Hurricane.java
        javac Hurricane.java
HurricaneDataReader.class: HurricaneDataReader.java
        javac HurricaneDataReader.java
RedBlackTree.class: RedBlackTree.java
        javac RedBlackTree.java
FrontendInterface.class: FrontendInterface.java
        javac FrontendInterface.java
HurricaneInterface.class: HurricaneInterface.java
        javac HurricaneInterface.java
SortedCollectionInterface.class: SortedCollectionInterface.java
        javac SortedCollectionInterface.java
TestHurricaneDataReader.class: TestHurricaneDataReader.java
        javac -cp .:junit5.jar TestHurricaneDataReader.java
test: testData testBackend testFrontend
testFrontend: Frontend.class FrontEndDeveloperTests.class FrontendInterface.class
        java -jar junit5.jar -cp . --scan-classpath -n FrontEndDeveloperTests
testBackend: Backend.class BackEndDeveloperTests.class
        java -jar junit5.jar -cp . --scan-classpath -n BackEndDeveloperTests
testData: Hurricane.class HurricaneDataReader.class HurricaneInterface.class TestHurricaneDataReader.class
        java -jar junit5.jar -cp . --scan-classpath -n TestHurricaneDataReader
clean:
        $(RM) *.class