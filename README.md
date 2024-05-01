The Cucumber-Selenium project has a variety of attributes in terms of software testing automation development.
As a testing library, Selenium 4 was used in this project.
As a testing framework, Cucumber was used in this project.
TestNG framework provides more and more testing behavior compared to Junit. Therefore testing run framework is TestNG.
A test URL is a fake website used for test automation projects.
On the configuration.properties file, you can change "browser", "test URL" and "local-Grid server" by changing the property value. In that respect, this is a useful structure, especially for major test projects.
When you want to run the Grid server firstly you need to launch your Grid on your machine. Or you will get the "SessionNotCreatedException" result in your console.
If you want to expand this project you can easily do that because of used Page Object Model design pattern. 
Each page has its class and method. 
Locators are defined constants to manage all of the selectors. Selector managing is a big part of the testing framework if you have collectors and several pages to test. 
