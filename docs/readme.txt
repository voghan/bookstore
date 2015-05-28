INTRO

This project is an AEM 6.0 project utilizing the CityTech open source libraries.  This project is based on the cq-cookbook
project from my git repository.


DIFFERENCES
The variations from the cq-cookbook project are renamed modules, components using CityTech cq-component-annotations library
and using SQL2 instead of QueryBuilder.  I have removed the project name from all of the maven modules to follow the
Adobe module naming patterns.

Currently the project is not using the Spring Framework but I am planning on utilizing Spring when needed.

SAMPLE CONTENT
I have build sample content for the site and it will be included in the docs folder as an AEM package.

BUILDING
All the necessary maven commands are found in a separate document (maven commands.txt).  You will need to deploy the
dependencies project before core or ui.  If you deploy to a publish instance you will need to replicate dependencies,
config and ui packages.

CONFIG
The config module contains configuration properties that are deployed to AEM.  The module is setup to utilize AEM run modes
for environment specific configuration.  The config module is auto deployed when you use the autoInstallPackage profile
for maven builds.  It not included in the UI project because config changes should be separate from code changes.

CORE
The core module contains Java server side code that is deployed to AEM.

DEPENDENCIES
The dependencies module contains logic to package your maven dependencies for deployment to AEM.

DISPATCHER
The dispatcher module contains configuration files for setting up your dispatcher.

UI
The ui module contains JSPs, CSS, JSS and other web artifacts that are packaged for deployment to AEM.

WEBDEV
The webdev module is solely used in case you have a web developer that will be building their code outside of AEM.  This
is just a convenience so that all code for the project can be placed in one project repository.




