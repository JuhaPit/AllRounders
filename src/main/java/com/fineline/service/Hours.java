package com.fineline.service;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;


public class Hours {
	
	public double calculateWorkHours(String startTime, String endTime){
		
		String[] starthourMin = startTime.split(":");
		int hour = Integer.parseInt(starthourMin[0]);
		int mins = Integer.parseInt(starthourMin[1]);
		int startHoursInMins = hour * 60;
		int startTimeInMins = startHoursInMins + mins;
		double startTimeOutput = (double)startTimeInMins/60;
		
		
		String[] endhourMin = endTime.split(":");
		int endhour = Integer.parseInt(endhourMin[0]);
		int endmins = Integer.parseInt(endhourMin[1]);
		int endHoursInMins = endhour * 60;
		int endTimeInMins = endHoursInMins + endmins;
		double endTimeOutput = (double)endTimeInMins/60;
		
		double roundedOutput = (double)Math.round((endTimeOutput - startTimeOutput) * 100) / 100;
		
		if(roundedOutput < 0){
			
			return roundedOutput + 24;
		}
		
		else{
			
			return roundedOutput;
		}
		
	}
	
	public float CheckIfEveningWork(String begin, String end) {
		
		DateTimeFormatter formatter = DateTimeFormat.forPattern("HH:mm:ss");
		DateTime beginTime = formatter.parseDateTime(begin);
		DateTime endTime = formatter.parseDateTime(end);
	    float eveningWorkDuration = 0;
	    float beginHour = beginTime.getHourOfDay();
	    float endHour = endTime.getHourOfDay();

	    if (beginHour < endHour) {
	        for (float a=beginHour; a<= endHour; a++) {
	            if ((a>=18)||(a<=22)) {
	                eveningWorkDuration++;
	            }
	        }
	    } else {
	        endHour = endHour + 24;
	        for (float a=beginHour; a<= endHour; a++) {
	            if ((a>=18)||(a<=22)) {
	                eveningWorkDuration++;
	            }
	        }
	    }
	    
	    System.out.println(eveningWorkDuration);
	    return eveningWorkDuration;
	}

}
