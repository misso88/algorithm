import java.util.*;
import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        sb.append("a = ").append(Integer.parseInt(st.nextToken())).append("\n");
        sb.append("b = ").append(Integer.parseInt(st.nextToken()));
        System.out.println(sb);
    }
}