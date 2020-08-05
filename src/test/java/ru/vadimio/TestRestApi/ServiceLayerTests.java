package ru.vadimio.TestRestApi;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.vadimio.TestRestApi.errors.Error;
import ru.vadimio.TestRestApi.service.MainService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceLayerTests {

    private MainService mainService;

    @Autowired
    public void setMainService(MainService mainService){
        this.mainService = mainService;
    }

    @Test
    public void updateStringNotIntegerTest() {
        Assert.assertEquals(mainService.updateString("asdsadads"), Error.NOT_INTEGER_VALUE.getErr());
    }

    @Test
    public void updateStringBigIntegerTest() {
        mainService.setN(1);
        Assert.assertEquals(mainService.updateString("2147483647"), Error.BIG_NUMBER.getErr());
    }

    @Test
    public void updateStringSmallIntegerTest() {
        mainService.setN(-1);
        Assert.assertEquals(mainService.updateString(" -2147483648"), Error.SMALL_NUMBER.getErr());
    }

    @Test
    public void updateStringTest(){
        mainService.setN(25);
        Assert.assertEquals(mainService.updateString("1, -4, 18,-199"), "26,21,43,-174");
    }

    @Test
    public void updateNBigIntegerTest() {
        mainService.setN(1);
        Assert.assertFalse(mainService.updateN(2147483647));
    }

    @Test
    public void updateNSmallIntegerTest() {
        mainService.setN(-1);
        Assert.assertFalse(mainService.updateN( -2147483648));
    }

    @Test
    public void updateNTest(){
        mainService.setN(25);
        Assert.assertTrue(mainService.updateN(899));
    }
}
