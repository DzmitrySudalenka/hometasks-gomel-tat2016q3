import com.epam.gomel.homework.Boy;
import com.epam.gomel.homework.Girl;
import com.epam.gomel.homework.Month;
import com.epam.gomel.homework.Mood;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

/**
 * Created by Dzmitry Sudalenka on 9/23/2016.
 */
@Listeners({TestListener.class})
public class BoyTest {

    @DataProvider(name = "Data for get mood test")
    public Object[][] dataForGetMoodTest() {
        return new Object[][]{
                {Month.JUNE, 1000000, true, Mood.EXCELLENT},
                {Month.JANUARY, 1000000, true, Mood.GOOD},
                {Month.JULY, 1000000, false, Mood.NEUTRAL},
                {Month.AUGUST, 999999.9, false, Mood.BAD},
                {Month.FEBRUARY, -1, false, Mood.HORRIBLE}
        };
    }

    @Test(description = "Boy has an expected mood", dataProvider = "Data for get mood test")
    public void getMoodTest(Month boyBirthdayMonth, double boyWealth, boolean isGirlPretty, Mood boyExpectedMood) {
        Girl girl = new Girl(isGirlPretty);
        Boy boy = new Boy(boyBirthdayMonth, boyWealth, girl);
        Mood boyActualMood = boy.getMood();
        Assert.assertEquals(boyActualMood, boyExpectedMood, "Invalid mood of boy");
    }

    @Test(description = "After boy spending one out of two money, his wealth must be one", groups = "spendSomeMoney",
            dependsOnMethods = "getMoodTest")
    public void SpendSomeMoneyTest() {
        Boy boy = new Boy(Month.JANUARY, 2);
        boy.spendSomeMoney(1);
        double boyActualWealth = boy.getWealth();
        Assert.assertEquals(boyActualWealth, 1D, "Invalid result of spend some money");
    }

    @Test(description = "Spend some money test should throw RuntimeException when amount for spending more " +
            "then wealth of boy", expectedExceptions = RuntimeException.class, groups = "spendSomeMoney",
            dependsOnMethods = "getMoodTest")
    public void SpendSomeMoneyExceptionTest() {
        Boy boy = new Boy(Month.JANUARY, 1000000);
        boy.spendSomeMoney(1000000.1);
    }

    @Test(description = "If birthday month of boy is June, then he is summer month", dependsOnGroups = "spendSomeMoney")
    public void isSummerMonthTest() {
        Boy boy = new Boy(Month.JUNE);
        boolean isBoySummerMonth = boy.isSummerMonth();
        Assert.assertTrue(isBoySummerMonth, "Boy is not summer month");
    }

    @Test(description = "If wealth of boy is equal or more then one million, then he is rich", priority = 0)
    public void isRichTest() {
        Boy boy = new Boy(Month.JANUARY, 1000000);
        boolean isBoyRich = boy.isRich();
        Assert.assertTrue(isBoyRich, "Boy is not rich");
    }

    @Test(description = "If girlfriend of boy is pretty, then he has pretty girlfriend", priority = 1)
    public void isPrettyGirlFriendTest() {
        Girl girl = new Girl(true);
        Boy boy = new Boy(Month.JANUARY, 1, girl);
        boolean isPrettyGirlFriend = boy.isPrettyGirlFriend();
        Assert.assertTrue(isPrettyGirlFriend, "Girlfriend is not pretty");
    }

}