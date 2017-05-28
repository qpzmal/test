package cn.advu.workflow.web.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {

    public String[] getDateStr(String sdate, String edate){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        int days=this.getDates(sdate, edate);
        String[] zrdate=null;
        if(days<=0){
            zrdate=new String[1];
            zrdate[0]=edate;
            return zrdate;
        }else{
            zrdate=new String[days];
        }
        Date date=null;
        try {
            date=sdf.parse(edate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int num=0;
        for (int i = 0; i < zrdate.length; i++) {
            if (i > 0) {
                num = 1;
            }
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(date);
            calendar.add(calendar.DATE, -(num));
            date = calendar.getTime();
            zrdate[i] = sdf.format(date);
            System.out.println(zrdate[i]);
        }
        return zrdate;
    }



    public int getDates(String sdate, String edate) {
        int days=0;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Calendar cal1= Calendar.getInstance();
            cal1.setTime(sdf.parse(sdate));
            Calendar cal2= Calendar.getInstance();
            cal2.setTime(sdf.parse(edate));
            cal2.add(cal2.DATE,+1);
            long l=cal2.getTimeInMillis()-cal1.getTimeInMillis();
            days=new Long(l/(1000*60*60*24)).intValue();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return days;
    }

    public String[] getMonthNum(String sdate, String edate){
        String[] zrdate=null;
        if(sdate.equals(edate)){
            zrdate=new String[1];
            zrdate[0]=sdate;
            System.out.println(zrdate[0]);
            return zrdate;
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        SimpleDateFormat sdf2 = new SimpleDateFormat("MM");
        Date date=null;
        try {
            date=sdf.parse(sdate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int num=0;
        int mm=30;
        for (int i = 0; i <24; i++) {
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(date);
            if(sdf2.format(date).equals("01")){
                mm=28;
            }else{
                mm=30;
            }
            calendar.add(calendar.DATE, +(mm));
            date = calendar.getTime();
            num++;
            if(sdf.format(date).equals(edate)){
                break;
            }
        }
        try {
            date=sdf.parse(sdate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        zrdate=new String[num];
        int ms=0;
        for (int i = 0; i <num; i++) {
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(date);
            if(sdf2.format(date).equals("01")){
                ms=28;
            }else{
                ms=30;
            }
            calendar.add(calendar.DATE, +(ms));
            date = calendar.getTime();
            if(i==0){
                zrdate[i]=sdate;
            }else{
                zrdate[i]=sdf.format(date);
            }
            System.out.println(zrdate[i]);
        }
        return zrdate;
    }


    public static void main(String[] args) {
        DateUtil  du=new DateUtil();
        //du.getMonthNum("2014-01", "2014-05");
      //  System.out.println(du.getDates("2014-01-20", "2014-01-23"));
       // du.getDateStr("2017-02-01", "2017-02-31");
        String mothone="01,03,05,07,08,10,12";
        String mothtwo="04,06,09,11";
        String months[] =new String[1];
        months[0]="2017-12";
        if(mothone.contains(months[0].substring(5,7))) {
           String[] b= du.getDateStr(months[0] + "-01", months[0] + "-31");
            System.out.println(b.clone());
        }else if(mothtwo.contains(months[0].substring(5,7))){
            String[] b=du.getDateStr(months[0] + "-01", months[0] + "-30");
            System.out.println(b.clone());
        }else{
            String[] b=du.getDateStr(months[0] + "-01", months[0] + "-28");
            System.out.println(b.clone());
        }
       /* String aa="2017-02";
        System.out.println(aa.substring(5,7));*/
    }

}
