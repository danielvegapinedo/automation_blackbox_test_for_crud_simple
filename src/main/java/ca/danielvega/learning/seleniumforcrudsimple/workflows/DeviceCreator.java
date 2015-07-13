package ca.danielvega.learning.seleniumforcrudsimple.workflows;

import ca.danielvega.learning.seleniumforcrudsimple.pages.CreateDevicePage;
import ca.danielvega.learning.seleniumforcrudsimple.pages.RemoveDevicePage;
import java.util.Random;

public class DeviceCreator {

    private static String previousName;
    private static String previousMode;
    private static String previousStatus;
    private static String previousTariff;
    private static String previousType;
    private static String previousCustomer;
    private static final int MIN_LENGTH = 2;
    private static final int MAX_LENGTH = 20;

    public static void createDevice() {
        cleanup();
        if (!CreateDevicePage.isAt()) {
            CreateDevicePage.goTo();
        }

        previousName = createName();
        previousMode = createMode();
        previousStatus = createStatus();
        previousTariff = createTariff();
        previousType = createType();
        previousCustomer = createCustomer();

        CreateDevicePage.createDevice()
                .withName(previousName)
                .withMode(previousMode)
                .withStatus(previousStatus)
                .withTariff(previousTariff)
                .withType(previousType)
                .withCustomer(previousCustomer)
                .create();
    }

    private static String createName() {
        return "Name_" + createRandomString(10);
    }

    private static String createStatus() {
        return "Status_" + createRandomString(10);
    }

    private static String createMode() {
        return "Mode_" + createRandomString(10);
    }

    private static String createTariff() {
        return "Tariff_" + createRandomString(10);
    }

    private static String createType() {
        return "Type_" + createRandomString(10);
    }

    private static String createCustomer() {
        return "Customer_" + createRandomString(10);
    }

    private static String createRandomString(int length) {
        final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        Random rnd = new Random();

        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append(AB.charAt(rnd.nextInt(AB.length())));
        }
        return sb.toString();

    }

    public static String getPreviousName() {
        return previousName;
    }

    public static String getPreviousMode() {
        return previousMode;
    }

    public static String getPreviousStatus() {
        return previousStatus;
    }

    public static String getPreviousTariff() {
        return previousTariff;
    }

    public static String getPreviousType() {
        return previousType;
    }

    public static String getPreviousCustomer() {
        return previousCustomer;
    }

    public static void initilize() {
        previousName = null;
        previousMode = null;
        previousStatus = null;
        previousTariff = null;
        previousType = null;
        previousCustomer = null;
    }

    public static void cleanup() {
        if (isCreateADevice()) {
            removeDevice();
        }
    }

    private static boolean isCreateADevice() {
        return previousName != null;
    }

    private static void removeDevice() {
        RemoveDevicePage.remove(previousName);
        initilize();
    }

}
