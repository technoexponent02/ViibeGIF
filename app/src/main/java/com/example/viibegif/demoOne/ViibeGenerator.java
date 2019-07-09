package com.example.viibegif.demoOne;


import java.util.ArrayList;
import java.util.List;

public class ViibeGenerator {

    public static List<ViibeBO> viibeBOList(){
        List<ViibeBO> viibeBOList = new ArrayList<ViibeBO>();

        for(int i = 0;i<10;i++){
            ViibeBO viibeBO = new ViibeBO();
            viibeBO.setId(i+"");
            viibeBO.setMessage("Lorem Ipsum.");
            viibeBO.setSubmessage("Lorem Ipsum is simply dummy text.");
            viibeBOList.add(viibeBO);

        }
        return viibeBOList;
    }
}
