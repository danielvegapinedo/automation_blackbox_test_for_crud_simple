package ca.danielvega.learning.seleniumforcrudsimple.utilities;

import ca.danielvega.learning.seleniumforcrudsimple.extendReports.annotations.Recorder;
import ca.danielvega.learning.seleniumforcrudsimple.extendReports.annotations.Explanation;

import ca.danielvega.learning.seleniumforcrudsimple.recorder.RecorderScreen;
import ca.danielvega.learning.seleniumforcrudsimple.selenium.Driver;
import static ca.danielvega.learning.seleniumforcrudsimple.utilities.CrudSimpleTest.extent;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Rule;
import org.junit.experimental.categories.Category;
import org.junit.rules.TestRule;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;

public class BaseTest {

    protected ExtentTest report;
    protected RecorderScreen recorderVideo;
    @Rule
    public TestRule testWatcher = new TestWatcher() {

        @Override
        public void starting(Description desc) {
            System.out.println("TestWatcher.starting...");
            initExtentReport(desc);
        }

        @Override
        public void finished(Description desc) {
            System.out.println("TestWatcher.finished ...");
            String screenshotPath = null;

            if (report.getTest().status == LogStatus.UNKNOWN) {
                report.log(LogStatus.PASS, "Forcing UNKNOWN to PASS", "The junit test did not use explicit reporting log.");
            }
           

            Recorder recorder = desc.getAnnotation(Recorder.class);
            if (recorder != null) {
                try {
                    screenshotPath = recorderVideo.stopRecording().getAbsolutePath();
                    report.log(LogStatus.INFO, report.addScreencast(screenshotPath));
                    //BasePage.attachVideo();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            
             extent.endTest(report);

        }

        @Override
        public void failed(Throwable e, Description desc) {
            System.out.print("TestWatcher.failed");
            String screenshotPath;
            if (Driver.isAlertPresent()) {
                screenshotPath = Driver.takesAlertScreenshot().getAbsolutePath();
            } else {
                screenshotPath = Driver.takesScreenshot().getAbsolutePath();
            }
            report.log(LogStatus.FAIL, e.getLocalizedMessage() + report.addScreenCapture(screenshotPath));

            Recorder recorder = desc.getAnnotation(Recorder.class);


            if (recorder != null) {
                try {
                    screenshotPath = recorderVideo.stopRecording().getAbsolutePath();
                    report.log(LogStatus.INFO, report.addScreencast(screenshotPath));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }

        private void initExtentReport(Description desc) {
            extent.addSystemInfo("Selenium Version", "2.46.0");
            extent.addSystemInfo("Environment", "Dev");
            extent.config()
                    .documentTitle("Daniel's Report")
                    .reportName("ExtentReports 001")
                    .reportHeadline("It is a Headline.")
                    .insertCustomStyles(".test { border:2px solid #444; }");

            String title = desc.getMethodName(), steps = "";
            StringBuilder sb = new StringBuilder();

            //sb.append("Class name:  ").append(desc.getClassName()).append("<br/>");
            // sb.append("Method name:  ").append(desc.getMethodName()).append("<br/>");
            Explanation explanation = desc.getAnnotation(Explanation.class);
            if (explanation != null) {
                sb.append(getItem(null, explanation.description(), "<br/>"));
                title = explanation.title();
                steps = createSteps(explanation.steps());
            }

            Category categories = desc.getAnnotation(Category.class);
            if (categories != null) {
                sb.append(getItem("Categories", "", ""));
                boolean pass = false;
                for (Class<?> cat : categories.value()) {
                    if (pass) {
                        sb.append(", ");
                    }
                    sb.append(cat.getSimpleName());
                    pass = true;
                }
            }

            sb.append("<br/>");
            sb.append(getItem("Method", desc.getDisplayName(), "<br/>"));
            if (!"".equals(steps)) {
                sb.append(getItem("Steps", steps, "<br/>"));
            } else {
                sb.append(getItem("Steps", "Steps of this test are missing.", "<br/>"));
            }

            sb.append(getItem("Is Empty", desc.isEmpty(), ", "));
            sb.append(getItem("Is Suite", desc.isSuite(), ", "));
            sb.append(getItem("is Test", desc.isTest(), ", "));
            sb.append(getItem("Test count", desc.testCount(), "<br/>"));

            sb.append(getItem("Annotations size", desc.getAnnotations().size(), "<br/>"));
            sb.append(getItem("Test", desc.getAnnotation(org.junit.Test.class).toString(), "<br/>"));

            report = extent.startTest(getTitle(title), getPointTitle(sb.toString()));

            Recorder recorder = desc.getAnnotation(Recorder.class);
            if (recorder != null) {
                recorderVideo = new RecorderScreen();
                try {
                    recorderVideo.startRecording();
                } catch (Exception ex) {
                    Logger.getLogger(BaseTest.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }

        private String getItem(String title, String desc, String tag) {
            return (title == null ? "" : (title + ": ")) + (desc == null ? "" : desc) + tag;
        }

        private String getItem(String title, Boolean desc, String tag) {
            return getItem(title, desc.toString(), tag);
        }

        private String getItem(String title, Integer desc, String tag) {
            return getItem(title, desc.toString(), tag);
        }

        private String getPointTitle(String text) {
            return "" + text + "";
        }

        private String getTitle(String text) {
            return text;
        }

        private String createSteps(String[] stepArray) {
            if (stepArray.length == 0 || (stepArray.length == 1 && "".equals(stepArray[0]))) {
                return "";
            }
            StringBuilder sb = new StringBuilder();
            sb.append("<ol>");
            for (String step : stepArray) {
                sb.append("<li>").append(step).append("</li>");
            }
            sb.append("</ol>");

            return sb.toString();
        }
    };
}
