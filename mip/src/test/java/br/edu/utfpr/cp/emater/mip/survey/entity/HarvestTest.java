package br.edu.utfpr.cp.emater.mip.survey.entity;

import java.util.Date;
import org.junit.Assert;
import org.junit.Test;

public class HarvestTest {

    Long id = new Long(1L);
    String name = "2017/2018";
    Date begin = new Date();
    Date end = new Date();

    @Test
    public void test() {
        Harvest h = new Harvest();

        h.setId(id);
        h.setName(name);
        h.setBegin(begin);
        h.setEnd(end);

        Assert.assertEquals(h.getId(), id);
        Assert.assertEquals(h.getName(), name);
        Assert.assertEquals(h.getBegin(), begin);
        Assert.assertEquals(h.getEnd(), end);
    }

    @Test
    public void testConstructor() {

        Harvest h = new Harvest(id, name, begin, end);

        Assert.assertEquals(h.getId(), id);
        Assert.assertEquals(h.getName(), name);
        Assert.assertEquals(h.getBegin(), begin);
        Assert.assertEquals(h.getEnd(), end);
    }

}
