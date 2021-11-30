package com.example.form;

public class TestForm {
private String testId;

public String getTestId() {
	return testId;
}

public void setTestId(String testId) {
	this.testId = testId;
}

@Override
public String toString() {
	return "TestForm [testId=" + testId + "]";
}

}
