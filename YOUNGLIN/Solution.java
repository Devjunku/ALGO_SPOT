import java.util.*;

class Solution {
    int n, root, cnt;
    int[] l = new int[10005];
    int[] r = new int[10005];
    int[] x = new int[10005];
    int[] p = new int[10005];
    public int solution(int k, int[] num, int[][] links) {
        n = num.length;
        for (int i = 0; i < n; i++) p[i] = -1;
        for (int i = 0; i < n; i++) {
            l[i] = links[i][0];
            r[i] = links[i][1];
            x[i] = num[i];
            if (l[i] != -1) p[l[i]] = i;
            if (r[i] != -1) p[r[i]] = i;
        }
        for (int i = 0; i < n; i++) {
            if (p[i] == -1) {
                root = i;
                break;
            }
        }
        int st = x[0];
        for (int i = 0; i < n; i++) st = Math.max(st, x[i]);
        int en = (int)1e8;
        while (st < en) {
            int mid = (st+en)/2;
            if (solve(mid) <= k) en = mid;
            else st = mid + 1;
        }
        return st;
    }
    
    private int solve(int lim) {
        cnt = 0;
        dfs(root, lim);
        cnt++;
        return cnt;
    }
    
    private int dfs(int cur, int lim) {
        int lv = 0;
        if (l[cur] != -1) lv = dfs(l[cur], lim);
        int rv = 0;
        if (r[cur] != -1) rv = dfs(r[cur], lim);
        
        //Case 1
        if (x[cur] + lv + rv <= lim) return x[cur] + lv + rv;
        //Case 2
        if (x[cur] + Math.min(lv, rv) <= lim) {
            cnt++;
            return x[cur] + Math.min(lv, rv);
        }
        //Case 3
        cnt += 2;
        return x[cur];
    }
}