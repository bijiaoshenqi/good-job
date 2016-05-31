# Good Job

[![](https://jitpack.io/v/bijiaoshenqi/good-job.svg)](https://jitpack.io/#bijiaoshenqi/good-job)

## Effect

![effect](doc/example.gif)

## How to use
To get a Git project into your build:

### gradle
Step 1. Add the JitPack repository to your build file
Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url "https://jitpack.io" }
		}
	}
	
Step 2. Add the dependency

	dependencies {
	        compile 'com.github.bijiaoshenqi:good-job:1.0'
	}
	
### maven
Step 1. Add the JitPack repository to your build file

	<repositories>
		<repository>
		    <id>jitpack.io</id>
		    <url>https://jitpack.io</url>
		</repository>
	</repositories>
	
Step 2. Add the dependency

	<dependency>
	    <groupId>com.github.bijiaoshenqi</groupId>
	    <artifactId>good-job</artifactId>
	    <version>1.0</version>
	</dependency>
	
### others
1. This project is added to jitpack,so you can find the rest way to import good-job.    
Address: [https://jitpack.io/#bijiaoshenqi/good-job](https://jitpack.io/#bijiaoshenqi/good-job)    

2. Copy the code below goodjob/src/main/java   
This project has few dependences except appcompat-v7