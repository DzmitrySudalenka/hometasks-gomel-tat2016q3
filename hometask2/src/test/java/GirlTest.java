import com.epam.gomel.homework.Boy;
import com.epam.gomel.homework.Girl;
import com.epam.gomel.homework.Month;
import com.epam.gomel.homework.Mood;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.*;

/**
 * Created by Admin on 9/23/2016.
 */
@Listeners({TestListener.class})
public class GirlTest {

    @DataProvider(name = "Data for get mood test")
    public Object[][] dataForGetMoodTest() {
        return new Object[][]{
                {true, true, 1000000, Mood.EXCELLENT},
                {true, true, 999999.9, Mood.GOOD},
                {false, true, 999999.9, Mood.NEUTRAL},
                {false, false, 999999.9, Mood.I_HATE_THEM_ALL},
        };
    }

    @Test(description = "Girl has an expected mood", dataProvider = "Data for get mood test")
    public void GetMoodTest(boolean isPretty, boolean isSlimFriendGotAFewKilos, double boyWealth,
            Mood girlExpectedMood) {
        Boy boy = new Boy(Month.JANUARY, boyWealth);
        Girl girl = new Girl(isPretty, isSlimFriendGotAFewKilos, boy);
        Mood girlActualMood = girl.getMood();
        assertThat("Invalid mood of girl", girlActualMood, equalTo(girlExpectedMood));
    }

    @Test(description = "After girl spending one hundred thousand out of million money of her boyfriend, " +
            "wealth of the boyfriend must be nine hundred thousand", groups = "boyfriend")
    public void spendBoyFriendMoneyTest() {
        Boy boy = new Boy(Month.JANUARY, 1000000);
        Girl girl = new Girl(true, true, boy);
        girl.spendBoyFriendMoney(100000);
        double boyfriendActualWealth = boy.getWealth();
        Assert.assertEquals(boyfriendActualWealth, 900000D, "Invalid result of spend boyfriend money");
    }

    @Test(description = "If boyfriend has one million of money, then he is rich", groups = "boyfriend")
    public void isBoyfriendRichTest() {
        Boy boy = new Boy(Month.JANUARY, 1000000);
        Girl girl = new Girl(true, true, boy);
        boolean isBoyfriendRich = girl.isBoyfriendRich();
        assertThat("Boyfriend is not rich", isBoyfriendRich, is(true));
    }

    @Test(description = "If boyfriend has one million of money and girl is pretty, then the boyfriend will buy " +
            "new shoes", groups = "boyfriend")
    public void isBoyFriendWillBuyNewShoesTest() {
        Boy boy = new Boy(Month.JANUARY, 1000000);
        Girl girl = new Girl(true, true, boy);
        boolean isBoyFriendWillBuyNewShoes = girl.isBoyFriendWillBuyNewShoes();
        Assert.assertTrue(isBoyFriendWillBuyNewShoes, "Boyfriend won't buy new shoes");
    }

    @Test(description = "If girl is not pretty and her slim friend got a few kilos, then the slim friend became fat")
    public void isSlimFriendBecameFatTest() {
        Girl girl = new Girl(false, true);
        boolean isSlimFriendBecameFat = girl.isSlimFriendBecameFat();
        Assert.assertTrue(isSlimFriendBecameFat, "Slim friend not became fat");
    }

}