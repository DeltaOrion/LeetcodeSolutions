package me.jacob.assign.lintcode;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;

/**
 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei),
 * determine if a person could attend all meetings.
 */
public class Problem920 {

    public static void main(String[] args) {
        List<Interval> meetings = new ArrayList<>();
        meetings.add(new Interval(0,30));
        meetings.add(new Interval(0,5));
        meetings.add(new Interval(5,10));
        meetings.add(new Interval(7,38));
        meetings.add(new Interval(6,12));
        System.out.println(mergeIntervals(meetings));
    }

    public static List<Interval> mergeIntervals(List<Interval> intervals) {
        if(intervals.size()==0)
            return new ArrayList<>();

        List<Interval> result = new ArrayList<>();
        intervals.sort(Comparator.comparingInt(o -> o.start));
        Interval merge = new Interval(intervals.get(0).start,intervals.get(0).end);
        result.add(merge);
        for(int i = 1;i<intervals.size();i++) {
            Interval curr = intervals.get(i);
            if(curr.start > merge.end) {
                merge = new Interval(curr.start,curr.end);
                result.add(merge);
            } else {
                merge.end = Math.max(curr.end,merge.end);
            }
        }

        return result;
    }

    public static int minMeetingRooms2(List<Interval> intervals) {
        List<Integer> starts = new ArrayList<>();
        List<Integer> finish = new ArrayList<>();

        for(Interval interval : intervals) {
            starts.add(interval.start);
            finish.add(interval.end);
        }

        starts.sort(Integer::compare);
        finish.sort(Integer::compare);

        int i = 0;
        int j = 0;

        int count = 0;
        int maxCount = 0;

        while(i < starts.size()) {
            if(starts.get(i) < finish.get(i)) {
                count++;
                i++;
            } else if(starts.get(i) >= finish.get(i)) {
                count--;
                j++;
            }

            if(count > maxCount) {
                maxCount = count;
            }
        }

        return maxCount;
    }

    public static int minMeetingRooms(List<Interval> intervals) {
        // Write your code here
        if(intervals.size()==0)
            return 0;
        intervals.sort((o1, o2) -> {
            int compare = Integer.compare(o1.start, o2.start);
            if (compare == 0) {
                compare = Integer.compare(o2.end, o1.end);
            }
            return compare;
        });

        int maxOverlapping = 0;
        List<Interval> collision = new ArrayList<>();
        for (Interval interval : intervals) {
            for (int j = collision.size() - 1; j >= 0; j--) {
                if (interval.start >= collision.get(j).end) {
                    collision.remove(j);
                }
            }

            collision.add(interval);

            //check if this is the biggest overlap
            if (collision.size() > maxOverlapping) {
                maxOverlapping = collision.size();
            }
        }

        return maxOverlapping;
    }

    public static class Interval {
        int start, end;

        Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public String toString() {
            return "(" + start + "," + end + ")";
        }
    }
}
