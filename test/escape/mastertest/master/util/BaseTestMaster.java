/*******************************************************************************
 * This files was developed for CS4233: Object-Oriented Analysis & Design.
 * The course was taken at Worcester Polytechnic Institute.
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Copyright ©2016-2023 Gary F. Pollice
 *******************************************************************************/
package escape.mastertest.master.util;

import org.junit.jupiter.api.*;

public abstract class BaseTestMaster {
    protected static MasterTestReporter testReporter;
    protected boolean currentTestResult;
    protected int currentTestPoints;
    protected String currentTestName;

    protected int extraCreditPoints;

    public BaseTestMaster() {
        testReporter = MasterTestReporter.getInstance();
    }

    protected void startTest(String testName, int points) {
        currentTestResult = false;
        currentTestName = testName;
        currentTestPoints = points;
    }

    protected void markTestPassed() {
        currentTestResult = true;
        currentTestPoints = 0;
    }

    protected void startExtraCreditTest(String testName, int points) {
        currentTestResult = false;
        currentTestName = testName;
        currentTestPoints = 0;
        extraCreditPoints = points;
    }

    protected void markExtraCreditTestPassed() {
        currentTestResult = true;
        currentTestPoints = extraCreditPoints;
    }

    @AfterEach
    protected void reportResults() {
        testReporter.reportTestResults(currentTestName, currentTestPoints, currentTestResult);
    }

    @AfterAll
    protected  static void printResults() {
        System.out.println(testReporter.endTestGroup().toString());
    }
}
