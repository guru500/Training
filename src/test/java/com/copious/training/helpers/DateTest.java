package com.copious.training.helpers;

import com.copious.training.util.DateUtility;
import org.joda.time.LocalDate;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static com.copious.training.util.DateUtility.setStartDateToSaturday;
import static org.junit.Assert.fail;
import static org.powermock.api.mockito.PowerMockito.spy;

@RunWith(PowerMockRunner.class)
@PrepareForTest(DateUtility.class)
public class DateTest {

    @Test(expected = NullPointerException.class)
    public void setStartDateToSaturday_NullCheck() {
        spy(DateUtility.class);
        fail(setStartDateToSaturday(null).toString());
    }

    @Test
    public void setStartDateToSaturday_DateLessThanSaturday() {
        spy(DateUtility.class);
        LocalDate mockDate = new LocalDate(2020,12,22);
        LocalDate expectedDate = new LocalDate(2020,12,19);
        Assert.assertEquals(expectedDate,setStartDateToSaturday(mockDate));
    }

    @Test
    public void setStartDateToSaturday_DateEqualSaturday() {
        spy(DateUtility.class);
        LocalDate mockDate = new LocalDate(2020,12,19);
        LocalDate expectedDate = new LocalDate(2020,12,19);
        Assert.assertEquals(expectedDate,setStartDateToSaturday(mockDate));
    }

    @Test
    public void setStartDateToSaturday_DateGreaterThanSaturday() {
        spy(DateUtility.class);
        LocalDate mockDate = new LocalDate(2020,12,20);
        LocalDate expectedDate = new LocalDate(2020,12,19);
        Assert.assertEquals(expectedDate,setStartDateToSaturday(mockDate));
    }

}
