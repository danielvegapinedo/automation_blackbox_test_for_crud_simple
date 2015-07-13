package ca.danielvega.learning.seleniumforcrudsimple.extendReports;

import com.relevantcodes.extentreports.DisplayOrder;
import com.relevantcodes.extentreports.ExtentReports;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Report extends ExtentReports {

    private static ExtentReports extent;

    public static ExtentReports getInstance() {
        if (extent != null) {
            return extent;
        }
        throw new IllegalStateException("Please use the method initialize(...)");
    }

    public static ExtentReports getInstance(String filePath, boolean replaceExisting, boolean newestFirst) {
        if (extent != null) {
            return extent;
        }
        File rfile = new File(filePath);
        if(!rfile.exists()){
            try {
                rfile.createNewFile();
            } catch (IOException ex) {
                Logger.getLogger(Report.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return extent = new Report(filePath, replaceExisting, newestFirst ? DisplayOrder.NEWEST_FIRST : DisplayOrder.OLDEST_FIRST);
    }

    public static void initilize(String filePath, boolean replaceExisting, boolean newestFirst) {
        getInstance(filePath, replaceExisting, newestFirst);
    }

    public static void cleanup() {
        extent.flush();
    }

    private Report(String filePath, Boolean replaceExisting, DisplayOrder displayOrder) {
        super(filePath, replaceExisting, displayOrder);
    }
}
