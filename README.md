# Java_Selenium_Framework
Java selenium framework template with JUnit and Page Object Model (POM) using Selenium Page Factory

## Dependencies
* Java
* Maven

## Libraries used
* jUnit 4.13.2
* selenium-java 4.0.0-beta-4
* JUnitParams 1.1.1
* slf4j-api 1.8.0-beta2

## Steps to execute tests
```sh
1. git clone https://github.com/Valter-Silva/Java_Selenium_Framework.git
2. Build Maven dependencies
3. Configure in src/test/java/settings/ the config.properties (set the browser profile path to disable the Cookie banner)
4. Run Test from src/test/java/testScripts/ValidateModelsPrices.java file
```

## Project Structure
![image](https://user-images.githubusercontent.com/11885127/130375344-358c98b1-952d-429c-ac72-efe60d5fdc30.png)
* drivers > contains both selenium drivers .exe for google and firefox
* pictures > stores the image from the end of the Test script flow
* src/test/java/pageModels > [PageObjectModel (POM)](https://www.browserstack.com/guide/page-object-model-in-selenium) structure
* src/test/java/settings > contains the configuration properties and the class to Load/Store its values
* src/test/java/testScripts > JUnit script with test example using junit Assertions
* src/test/java/utils > Driver creator class and Save test values to .txt file class

## To Implement (future iterations)
1. Complete the implementation of first part of the Test flow example (Homepage model filter and click model configurator)
1. Implement add Cookies in Driver setup to disable automatically the Cookie banner (instead of using browser profile settings)
1. Decouple some small logics from PageObjects models and TestScript assertions
1. Add extra assertions validations to test script example
1. Implement setting to run browser headless tests  
