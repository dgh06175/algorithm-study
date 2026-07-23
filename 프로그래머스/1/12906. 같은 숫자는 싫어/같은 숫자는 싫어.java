import java.util.*;

public class Solution {
    public int[] solution(int []arr) {
        int [] answer = {};
        int before = arr[0];
        ArrayList<Integer> a = new ArrayList<>();
        
        for(int i=0; i<arr.length; i++){
            a.add(arr[i]);
        }
        
        for(int i=1; i<a.size(); i++){
            if(before == a.get(i)) {
                a.remove(i);
            }
            before = a.get(i).intValue();
        }
            System.out.println(a);
        
        
        return answer;
    }
}