class Solution {
    public String solution(String[] survey, int[] choices) {
        StringBuilder sb = new StringBuilder();
        int rt = 0, cf = 0, jm = 0, an = 0;
        
        for(int i = 0, size = survey.length; i < size; i++) {
            switch(survey[i]) {
                case "RT":
                    rt += (choices[i] - 4);
                    break;
                case "TR":
                    rt -= (choices[i] - 4);
                    break;
                case "CF":
                    cf += (choices[i] - 4);
                    break;
                case "FC":
                    cf -= (choices[i] - 4);
                    break;
                case "JM":
                    jm += (choices[i] - 4);
                    break;
                case "MJ":
                    jm -= (choices[i] - 4);
                    break;
                case "AN":
                    an += (choices[i] - 4);
                    break;
                case "NA":
                    an -= (choices[i] - 4);
                    break;
            }
        }
        
        sb.append(rt <= 0 ? "R" : "T");
        sb.append(cf <= 0 ? "C" : "F");
        sb.append(jm <= 0 ? "J" : "M");
        sb.append(an <= 0 ? "A" : "N");
        
        return sb.toString();
    }
}