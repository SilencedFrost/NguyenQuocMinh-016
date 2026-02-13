package PageObjects.AutomationExercise;

import Common.Constant.Constant;

public class HomePage extends GeneralPage {

    // Methods
    public HomePage open() {
        Constant.WEBDRIVER.navigate().to(Constant.AUTOMATION_EXERCISE_URL);
        return this;
    }
}
