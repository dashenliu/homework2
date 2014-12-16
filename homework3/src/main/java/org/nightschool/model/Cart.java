package org.nightschool.model;

import com.sun.corba.se.impl.orb.ParserTable;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Cart {

    private final ArrayList<Disk> disks = new ArrayList<>();
    private final int[] productnum = new int[10];
    public List<Disk> getDisks() {
        return disks;
    }

    public void addDisk(Disk disk) {
        int n = disks.size();
        for(int i=0;i<n;i++) {
            if(disks.get(i) == disk) {
                productnum[i]++;
                return;
            }
        }
        disks.add(disk);
        productnum[n]++;
    }

    public int countKinds() {
        HashSet<Disk> diskSet = new HashSet<>();

        for (Disk disk : disks) {
            diskSet.add(disk);
        }

        return diskSet.size();
    }

    public void removeDisk(Disk disk) {
//        int count = 0;
//        for (Disk d : disks) {
//            if (d.equals(disk)) {
//                count++;
//            }
//        }
//
//        int i=0;
//        while (i<count){
//            disks.remove(disk);
//            i++;
//        }
        //删除对象后 要将存储商品个数的数组里的元素位置进行调整
        int index=0;
        for(Disk d : disks){
            if(d.equals(disk)){
                index = disks.indexOf(d);
            }
        }
        disks.remove(disk);
        for(int i=index;i<9;i++){
            productnum[i] = productnum[i+1];
        }
    }

    public int[] getproductnum() {
        return productnum;
    }

    public double totalprice_promote() {
        double totalprice = 0;
        int index = 0;
        for(Disk d : disks){
            if(d.getName().equals("小清新光盘")){
                index = disks.indexOf(d);
                totalprice += d.getprice()*productnum[index];
                productnum[index] += productnum[index]/2;
            }
            if (d.getName().equals("婚庆光盘")){
                index = disks.indexOf(d);
                double price = 0;
                price = d.getprice()*productnum[index];
                price -= price/100*10;
                totalprice +=price;
            }
            if (d.getName().equals("1TB光盘")){
                index = disks.indexOf(d);
                totalprice += d.getprice()*productnum[index]*0.95;
            }

        }

        return totalprice;
    }
}

