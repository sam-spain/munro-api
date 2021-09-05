[![Java CI with Gradle](https://github.com/sam-spain/munro-api/actions/workflows/gradle.yml/badge.svg)](https://github.com/sam-spain/munro-api/actions/workflows/gradle.yml)

How to run:
Run jar file with java -jar [application.jar file]

Sorting seems to be ignored in service layer despite test to show it working. That will likely never be fixed.

Example http query: localhost:8080?maxResults=1000

Queries:\
maxResults: int\
minHeight: double\
maxheight: double\
sort: array of strings for each category and then ascending or descending ('name' 'desc')\
categoryFilter: string
