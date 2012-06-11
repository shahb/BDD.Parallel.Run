package com.softcrylic.testautomation;

import cucumber.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class) 
@Cucumber.Options(features = "src/test/resources/com/softcrylic/testautomation/feature/newyork.feature",format = {"pretty", "html:target/cucumber","json:target/newyork.json" })
public class RunNewYorkTests  {
}
