import java.util.*;
import java.io.*;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        long[] ary = new long[n + 1];
        long[] tree = new long[n * 4];
        for(int i = 1; i <= n; i++) {
        	ary[i] = Long.parseLong(br.readLine());
        }
        
        initTree(ary, tree, 1, 1, n);
        
        for(int i = 0; i < m + k; i++) {
        	st = new StringTokenizer(br.readLine());
        	long a = Long.parseLong(st.nextToken());
        	int b = Integer.parseInt(st.nextToken());
        	long c = Long.parseLong(st.nextToken());
        	
            
            if (a == 1) {
            	update(ary, tree, 1, 1, n, b, c - ary[b]);
            	ary[b] = c;
            } else if (a == 2) {
            	System.out.println(query(ary, tree, 1, 1, n, b, (int)c));
            }
            
        }
    }
    
    private static long initTree(long[] ary, long[] tree, int node, int start, int end) {
    	if (start == end) {
    		return tree[node] = ary[start];
    	}
    	int mid = (start + end) / 2;
    	long left = initTree(ary, tree, node * 2, start, mid);
    	long right = initTree(ary, tree, node * 2 + 1, mid + 1, end);
    	return tree[node] = left + right;
    }
    
    private static void update(long[] ary, long[] tree, int node, int start, int end, int index, long diff) {
    	if (index < start || index > end) return;
    	tree[node] += diff;
    	
    	if (start == end) return;
    	int mid = (start + end) / 2;
    	update(ary, tree, node * 2, start, mid, index, diff);
    	update(ary, tree, node * 2 + 1, mid + 1, end, index, diff);
    }
    
    private static long query(long[] ary, long[] tree, int node, int start, int end, int qs, int qe) {
    	if (qe < start || qs > end) return 0L;
    	if (qs <= start && end <= qe) return tree[node];
    	
    	int mid = (start + end) / 2;
    	long left = query(ary, tree, node * 2, start, mid, qs, qe);
    	long right = query(ary, tree, node * 2 + 1, mid + 1, end, qs, qe);
    	
    	return left + right;
    }
}