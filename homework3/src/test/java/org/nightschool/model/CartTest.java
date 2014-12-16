package org.nightschool.model;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CartTest {

    private Cart cart;

    @Before
    public void setUp() throws Exception {
        cart = new Cart();
    }

    @Test
    public void should_be_empty_when_no_disk_added() throws Exception {

        List<Disk> disks = cart.getDisks();

        assertThat(disks.size(), is(0));
    }

    @Test
    public void should_able_to_add_disk_to_cart() throws Exception {
        Disk     disk =  new Disk();

        cart.addDisk(disk);
        assertThat(cart.getDisks().size(), is(1));
        assertThat(cart.getproductnum()[0],is(1));
    }

    @Test
    public void should_be_able_to_get_disk_properties() throws Exception {
        Disk disk = new Disk("小清新光盘", "../images/disk/fancy-disk.jpg", "小清新、小文艺 35元/10张",3.5);

        cart.addDisk(disk);
        Disk disk1 = cart.getDisks().get(0);

        assertThat(disk1.getName(), is("小清新光盘"));
        assertThat(disk1.getImgUrl(), is("../images/disk/fancy-disk.jpg"));
        assertThat(disk1.getDesc(), is("小清新、小文艺 35元/10张"));
        assertThat(disk1.getprice(),is(3.5));
    }

    @Test
    public void should_be_able_to_count_kinks_of_disks() throws Exception {
        Disk fancyDisk = new Disk("小清新光盘", "../images/disk/fancy-disk.jpg", "小清新、小文艺 35元/10张",3.5);
        Disk marriageDisk = new Disk("婚庆光盘", "../images/disk/marriage-disk.jpg", "记录你的美好瞬间 50元/10张",5);

        cart.addDisk(fancyDisk);
        cart.addDisk(fancyDisk);
        cart.addDisk(marriageDisk);
        cart.addDisk(marriageDisk);

        assertThat(cart.getDisks().size(), is(2));
        assertThat(cart.getproductnum()[0],is(2));
      assertThat(cart.countKinds(), is(2));
    }



    @Test
    public void should_be_able_to_remove_disk_from_cart() throws Exception {
        Disk fancyDisk = new Disk("小清新光盘", "../images/disk/fancy-disk.jpg", "小清新、小文艺 35元/10张",3.5);
        Disk marriageDisk = new Disk("婚庆光盘", "../images/disk/marriage-disk.jpg", "记录你的美好瞬间 50元/10张",5);

        cart.addDisk(fancyDisk);
        cart.addDisk(fancyDisk);
        cart.addDisk(marriageDisk);
        cart.removeDisk(fancyDisk);
        cart.addDisk(marriageDisk);

        assertThat(cart.getDisks().size(), is(1));
        assertThat(cart.getproductnum()[0], is(2));
    }

    // 在cart类中添加了一个productnum的数组用来存储和disks对应商品的数量，已将添加删除商品的方法进行修改；商品的属性加了price
    @Test
    public void should_be_able_to_compute_promotion_price() throws Exception {
        Disk fancyDisk = new Disk("小清新光盘", "../images/disk/fancy-disk.jpg", "小清新、小文艺 35元/10张",3.5);
        Disk marriageDisk = new Disk("婚庆光盘", "../images/disk/marriage-disk.jpg", "记录你的美好瞬间 50元/10张",5);
        Disk TBDisk = new Disk("1TB光盘", "../images/disk/1TB-disk.jpg", "解放你的硬盘 100元/10张",10);

        cart.addDisk(fancyDisk);
        cart.addDisk(fancyDisk);
        cart.addDisk(fancyDisk);
        for(int i=0;i<20;i++) {
            cart.addDisk(marriageDisk);
        }
        cart.addDisk(TBDisk);

        assertThat(cart.totalprice_promote(),is(110.0));//3个小清新10.5 20张婚庆90 1张TB9.5总共110元
        assertThat(cart.getproductnum()[0],is(4));//3张小清新送一张 数目变为4张
        assertThat(cart.getproductnum()[1],is(20));
        assertThat(cart.getproductnum()[2],is(1));
    }
}
